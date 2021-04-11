package fudan.se.lab2.service;

import fudan.se.lab2.controller.request.*;
import fudan.se.lab2.domain.*;
import fudan.se.lab2.exception.MeetingnameHasBeenRegisteredException;
import fudan.se.lab2.repository.*;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author LBW
 */
@Service
public class ConferenceService {
    private UserRepository userRepository;
    private ConferenceRepository conferenceRepository;
    private AuthorRepository authorRepository;
    private PaperRepository paperRepository;
    private EvaluationRepository evaluationRepository;
    private Random r = new Random();

    @Autowired
    public ConferenceService( EvaluationRepository evaluationRepository, UserRepository userRepository, AuthorityRepository authorityRepository, ConferenceRepository conferenceRepository, InviteRepository inviteRepository, AuthorRepository authorRepository, PaperRepository paperRepository) {
        this.userRepository = userRepository;
        this.conferenceRepository = conferenceRepository;
        this.authorRepository = authorRepository;
        this.paperRepository = paperRepository;
        this.evaluationRepository = evaluationRepository;
    }

    //发起会议
    public Conference startMeeting(StartMeetingRequest request) {
        User chairman = userRepository.findByUsername(request.getChairMan());
        String briefName = request.getBriefName();
        String fullName = request.getFullName();
        String address = request.getAddress();
        String date = request.getDate();
        String paperDDL = request.getPaperDDL();
        String distributeDate = request.getDistributeDate();
        String stage = request.getStage();
        List<String> topics = request.getTopics();

        Conference exitConference = conferenceRepository.findByfullName(fullName);
        if (exitConference != null) {
            throw new MeetingnameHasBeenRegisteredException(fullName);
        }
        if (chairman == null) {
            throw new UsernameNotFoundException(fullName);
        }
        Conference conference1 = new Conference(chairman, briefName, fullName, date, address, paperDDL, distributeDate, stage, topics);
        conference1.setEmploymentStage(("unreleased"));
        conference1.setReviewStage("notStarted");
        conferenceRepository.save(conference1);
        return conference1;
    }

    //审核会议
    public Set<Conference> verifyMeetings(VerifyMeetingsRequest request){
        String meetingName = request.getMeetingName();
        String attitude = request.getAttitude();
        Conference conference = conferenceRepository.findByfullName(meetingName);
        if (attitude.equals("accept")){
            conference.setStage("accept");
            conference.getChairMan().getReviewConference().add(conference.getFullName());
            conference.getReviewerList().add(conference.getChairMan().getUsername());
            conferenceRepository.save(conference);
            return this.conferenceRepository.findByStage("pending");
        } else {
            conference.setStage("reject");
            conferenceRepository.save(conference);
            return this.conferenceRepository.findByStage("pending");
        }
    }

    //开启投稿
    public Conference openContribute(ContributeOperationRequest request) {
        String conferenceName = request.getConferenceName();
        String username = request.getUsername();
        Conference conference = conferenceRepository.findByfullName(conferenceName);
        if (conference.getChairMan().getUsername().equals(username)) {  //验证是否是该会议的chair
            conference.setContributionStage("acceptContribution");
            conference.setReviewStage("notStarted");
            conferenceRepository.save(conference);
            return conference;
        } else {
            throw new UsernameNotFoundException(username);
        }
    }

    //关闭投稿
    public Conference closeContribute(ContributeOperationRequest request) {
        String conferenceName = request.getConferenceName();
        String username = request.getUsername();
        Conference conference = conferenceRepository.findByfullName(conferenceName);
        if (conference.getChairMan().getUsername().equals(username)) {  //验证是否是该会议的chair
            conference.setContributionStage("rejectContribution");
            for (Paper item : conference.getPaperList()){
                item.setReviewStatus("pending");
            }
            conferenceRepository.save(conference);
            return conference;
        } else {
            throw new UsernameNotFoundException(username);
        }
    }

    //上传会议文件
    public String uploadFile(@RequestParam String meetingName, @RequestParam MultipartFile uploadfile, @RequestParam String  title, @RequestParam String digest, @RequestParam String userName,@RequestParam Set<String> topics,@RequestParam List<String[]> authors, Logger logger,Long contributionID) {
        if (uploadfile == null || uploadfile.isEmpty()) {
            return "false";
        }

        if (contributionID != -1) {
            Conference modifyConference = conferenceRepository.findByfullName(meetingName);
            modifyPaperFile(uploadfile, contributionID, title, digest, topics, authors, logger, modifyConference);
        }
        try {
            String filePath = "src/contributeFile/";
            String fileName = uploadfile.getOriginalFilename();
            File dest = new File(new File(filePath).getAbsolutePath() + File.separator + fileName);
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            if (!dest.exists()) {
                if (!dest.createNewFile()) return "false";
            }
            uploadfile.transferTo(dest);

            Conference myconference = conferenceRepository.findByfullName(meetingName);
            List<Paper> paper1 = myconference.getPaperList();
            Paper mypaper = new Paper();
            List<String> userConference = this.userRepository.findByUsername(userName).getContributeConference();

            myconference.getAuthorList().add(userName);
            userConference.add(myconference.getFullName());
            userRepository.save(this.userRepository.findByUsername(userName));
            mypaper.setDigest(digest);
            mypaper.setHeadline(title);
            mypaper.setConferenceId(myconference.getConferenceId());
            mypaper.setFilePath(new File(filePath).getAbsolutePath() + File.separator + fileName);
            mypaper.setContributor(userName);
            for (String item : topics) {
                mypaper.getTopicList().add(item);
            }
            mypaper.setReviewStatus("open");
            //将对应的pcmemberName加到论文的pcmemberList中
            for (Topicrelated item : myconference.getTopicrelatedList()) {
                addpcmemberList(item, topics, mypaper);
            }
            mypaper.getAllPcmemberNameList().add(myconference.getChairMan().getUsername());
            this.paperRepository.save(mypaper);
            paper1.add(mypaper);
            this.conferenceRepository.save(myconference);
            //将传入的作者信息存起来
            for (int i = 0; i < authors.size() / 4; i++) {
                Author author = new Author();
                author.setPaperId(mypaper.getPaperId());
                author.setAuthorName(authors.get(i * 4)[0]);
                author.setAuthorMechanism(authors.get(i * 4 + 1)[0]);
                author.setAuthorArea(authors.get(i * 4 + 2)[0]);
                author.setAuthorEmail(authors.get(i * 4 + 3)[0]);
                this.authorRepository.save(author);
                mypaper.getAuthorList().add(author);
                this.paperRepository.save(mypaper);
            }
            for (Topicrelated item : myconference.getTopicrelatedList()) {
                addpcmemberList(item, topics, mypaper);
            }
            return "上传成功";
        } catch (IOException e) {
            logger.debug(e.getMessage());
            return "上传失败！";
        }

    }

    public String modifyPaperFile(@RequestParam MultipartFile uploadfile, Long contributionID, String  title, String digest, Set<String> topics, List<String[]> authors, Logger logger, Conference conference) {
        try {
            Paper modifyPaper = this.paperRepository.findByPaperId(contributionID);
            boolean b = FileSystemUtils.deleteRecursively(new File(modifyPaper.getFilePath()));
            if (!b) {
                return "文件删除失败";
            }
            String filePath = "src/contributeFile/";
            String fileName = uploadfile.getOriginalFilename();
            File dest = new File(new File(filePath).getAbsolutePath() + File.separator + fileName);
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            if (!dest.exists()) {
                if (!dest.createNewFile()) return "false";
            }
            uploadfile.transferTo(dest);
            modifyPaper.setFilePath(new File(filePath).getAbsolutePath() + File.separator + fileName);
            return modifyPaperCore(modifyPaper,title,digest,topics,authors,conference);
        } catch (IOException e) {
            logger.debug(e.getMessage());
            return "修改失败！";
        }
    }

    public String modifyPaperCore(Paper modifyPaper, String  title, String digest, Set<String> topics, List<String[]> authors, Conference conference) {
            modifyPaper.setHeadline(title);
            modifyPaper.setDigest(digest);
            modifyPaper.getAuthorList().clear();
            for (int i = 0; i < authors.size() / 4; i++) {
                Author author = new Author();
                author.setPaperId(modifyPaper.getPaperId());
                author.setAuthorName(authors.get(i * 4)[0]);
                author.setAuthorMechanism(authors.get(i * 4 + 1)[0]);
                author.setAuthorArea(authors.get(i * 4 + 2)[0]);
                author.setAuthorEmail(authors.get(i * 4 + 3)[0]);
                this.authorRepository.save(author);
                modifyPaper.getAuthorList().add(author);
            }
            this.paperRepository.save(modifyPaper);
            //修改成新的topic
            modifyPaper.getTopicList().clear();
            for (String item : topics) {
                modifyPaper.getTopicList().add(item);
            }
            modifyPaper.getAllPcmemberNameList().clear();
            for (Topicrelated item : conference.getTopicrelatedList()){
                addpcmemberList(item, topics,modifyPaper);
            }
            return "修改成功！";
    }

    public String modifyPaper(Long contributionID, String  title, String digest, Set<String> topics, List<String[]> authors,String meetingName) {
        Conference conference = conferenceRepository.findByfullName(meetingName);
        Paper modifyPaper = this.paperRepository.findByPaperId(contributionID);
        return modifyPaperCore(modifyPaper,title,digest,topics,authors,conference);
    }

    public boolean addpcmemberList(Topicrelated topicrelated, Set<String> paperTopicList,Paper moPaper){
        boolean j = false;
        User user = this.userRepository.findByUsername(topicrelated.getPcmemberName());
        //如果pcmenber是在author中，那么久不会被加入到审核候选人集合中
        for(Author author : moPaper.getAuthorList()){
            if(author.getAuthorName().equals(user.getRealname())&&author.getAuthorEmail().equals(user.getEmail())){
                return false;
            }
        }
        for (String item1 : topicrelated.getTopicList()){
            for (String item2 : paperTopicList){
                if (item2.equals(item1)){
                    moPaper.getAllPcmemberNameList().add(topicrelated.getPcmemberName());
                    j = true;
                }
            }
            if (j){
                break;
            }
        }
        return true;
    }

    //开启审核
    public String reviewMeetings(ReviewMeetingsRequest request){
        String meetingName = request.getMeetingName();
        String reviewModl = request.getReviewModl();
        Conference conference = this.conferenceRepository.findByfullName(meetingName);
        if (conference == null){
            return null;
        }
        if (conference.getContributionStage().equals("acceptContribution")){
            conference.setContributionStage("rejectContribution");
        }
        conference.setReviewStage("pending");
        //将稿件分配给PCmember
        if (reviewModl.equals("relativity")){
            for (Paper item : conference.getPaperList()){
                if (item.getAllPcmemberNameList().size()==3){
                    pattern1_case1(item);
                }else if (item.getAllPcmemberNameList().size()>3){
                    pattern1_case2(item);
                }else{
                    pattern1_case3(item, conference);
                }
            }
            this.conferenceRepository.save(conference);
        }else if (reviewModl.equals("average")){
            pattern2(allotOfAverage(conference.getPaperList(),conference.getReviewerList()));
            this.conferenceRepository.save(conference);
        }
        return "true";
    }

    //基于相关度分配下的审稿人数为3的情况，输入topic相关对象，审稿人名称集合，稿件名称集合，会议对象
    public void pattern1_case1(Paper paper){
        paperRepository.save(paper);
        for (int i = 0; i < 3 ; i ++){
            Evaluation evaluation = new Evaluation();
            evaluation.setPcmemberName(paper.getAllPcmemberNameList().get(i));
            evaluation.setDigest(paper.getDigest());
            evaluation.setTitle(paper.getHeadline());
            evaluation.setReviewStage("pending");
            evaluation.setModifyStage("unmodify");
            paper.getEvaluationList().add(evaluation);
            paper.getMapA().put(paper.getAllPcmemberNameList().get(i),evaluation);
            paper.getReviewerNameList().add(paper.getAllPcmemberNameList().get(i));
        }
        paperRepository.save(paper);
    }

    public void pattern1_case2(Paper paper){
        paperRepository.save(paper);
        int[] number = getRadomNumber(paper.getAllPcmemberNameList().size());
        for (int i = 0; i < 3; i++){
            Evaluation evaluation = new Evaluation();
            evaluation.setPcmemberName(paper.getAllPcmemberNameList().get(number[i]));
            evaluation.setDigest(paper.getDigest());
            evaluation.setTitle(paper.getHeadline());
            evaluation.setReviewStage("pending");
            evaluation.setModifyStage("unmodify");
            paper.getEvaluationList().add(evaluation);
            paper.getMapA().put(paper.getAllPcmemberNameList().get(i),evaluation);
            paper.getReviewerNameList().add(paper.getAllPcmemberNameList().get(number[i]));
        }
        paperRepository.save(paper);
    }

    public void pattern1_case3(Paper paper, Conference conference){
        int[] number = getRadomNumber(conference.getReviewerList().size());
        for (int i = 0; i < 3; i++){
            Evaluation evaluation = new Evaluation();
            for(Author author : paper.getAuthorList()){
                User user = this.userRepository.findByUsername(conference.getReviewerList().get(number[i]));
                if(author.getAuthorName().equals(user.getRealname())&&author.getAuthorEmail().equals(user.getEmail())){
                    number[i] = number[i] + 1;
                }
            }
            evaluation.setPcmemberName(this.userRepository.findByUsername(conference.getReviewerList().get(number[i])).getUsername());
            evaluation.setDigest(paper.getDigest());
            evaluation.setTitle(paper.getHeadline());
            evaluation.setReviewStage("pending");
            evaluation.setModifyStage("unmodify");
            paper.getEvaluationList().add(evaluation);
            paper.getMapA().put(conference.getReviewerList().get(number[i]),evaluation);
            paper.getReviewerNameList().add(this.userRepository.findByUsername(conference.getReviewerList().get(number[i])).getUsername());
        }
        paperRepository.save(paper);
        conferenceRepository.save(conference);
    }

    //会议下的所有论文和审稿人随机分配
    public void pattern2(Map<String,List<String>> relatedMap){
        Paper tempPaper;
        Set<String> myPaperID = relatedMap.keySet();
        for (String paperID : myPaperID) {
            tempPaper = this.paperRepository.findByPaperId(Long.valueOf(paperID));
            for (int i = 0; i < 3; i++){
                Evaluation evaluation = new Evaluation();
                evaluation.setPcmemberName(relatedMap.get(paperID).get(i));
                evaluation.setDigest(tempPaper.getDigest());
                evaluation.setTitle(tempPaper.getHeadline());
                evaluation.setReviewStage("pending");
                evaluation.setModifyStage("unmodify");
                tempPaper.getEvaluationList().add(evaluation);
                tempPaper.getMapA().put(relatedMap.get(paperID).get(i),evaluation);
                tempPaper.getReviewerNameList().add(relatedMap.get(paperID).get(i));
            }
            paperRepository.save(tempPaper);
        }
    }

    public Map<String,List<String>> allotOfAverage(List<Paper> paperList,List<String> pcmemberList){
        Map<String,List<String>> allot = new ConcurrentHashMap<>(); //保存分配的信息
        if (paperList.size()==0) return null;
        int num = r.nextInt(paperList.size());
        for (int i = num; i < paperList.size() * 3 + num; i = i + 3){
            List<String> list = new ArrayList<>();
            pcmemberList = move(i, pcmemberList, paperList.get((i / 3) % paperList.size()).getPaperId());
            list.add(pcmemberList.get(i % pcmemberList.size()));
            list.add(pcmemberList.get((i + 1) % pcmemberList.size()));
            list.add(pcmemberList.get((i + 2) % pcmemberList.size()));
            allot.put(paperList.get((i / 3) % paperList.size()).getPaperId().toString(), list);
            if (allot.size() == paperList.size()){
                break;
            }
        }
        return allot;
    }

    //pc的username是 list.get(j%list.size())
    //paper的id是list.get((j / 3) % paperList.size()).getPaperId()
    public List<String> move(int j, List<String> modifyList, Long paperId) {
        User user = this.userRepository.findByUsername(modifyList.get(j % modifyList.size()));
        Paper paper = this.paperRepository.findByPaperId(paperId);
        int k = j;
        while (k < j + 3) {
            String temp = null;
            boolean flag = false;
            for (Author item : paper.getAuthorList()) {
                if (user.getRealname().equals(item.getAuthorName()) && user.getEmail().equals(item.getAuthorEmail())) {
                    temp = user.getUsername();
                    flag = true;
                    modifyList.remove(j % modifyList.size());
                    k--;
                    break;
                }
            }
            if (flag) {
                modifyList.add(temp);
            }
            k++;
        }
        return modifyList;
    }

    //输入pcmember数量，返回包含三个随机数的数组
    public int[] getRadomNumber(int pcmemberNumber){
        int[] result = new int[3];
        for(int i = 0; i < 3; i++){
            result[i] = -1;
        }
        int count = 0;
        while (count < 3) {
            int num = r.nextInt(pcmemberNumber);
            boolean flag = true;
            for (int j = 0; j < 3; j++) {
                if (num == result[j]){
                    flag = false;
                    break;
                }
            }
            if (flag){
                result[count] = num;
                count++;
            }
        }
        return result;
    }

    //公布评审结果
    public String distributeResults(DistributeResultsRequest request){
        String meetingName = request.getMeetingName();
        Conference conference = this.conferenceRepository.findByfullName(meetingName);
        if (conference == null){
            return null;
        }
        if (!conference.getReviewStage().equals("full")){
            return "false";
        }
        conference.setReviewStage("finish");
        conference.setEmploymentStage("unreleased");
        this.conferenceRepository.save(conference);
        return "true";
    }

    //提交审稿信息
    public String submitReviewInformation(SubmitReviewIformationRequest request){
        int score = request.getScore();
        int confidence = request.getConfidence();
        Long conferenceId = request.getConferenceId();
        String username = request.getUsername();
        String reviews=request.getReviews();
        User reviewer = this.userRepository.findByUsername(username);
        Paper reviewPaper = this.paperRepository.findByPaperId(conferenceId);

        for (Evaluation item1 : reviewPaper.getEvaluationList()){
            if (item1.getPcmemberName().equals(reviewer.getUsername())){
                item1.setTitle(reviewPaper.getHeadline());
                item1.setDigest(reviewPaper.getDigest());
                item1.setConfidence(confidence);
                item1.setScore(score);
                item1.setReviews(reviews);
                item1.setReviewStage("finish");
            }
        }
        //在三个人都上传审核信息后把论文状态改为success
        int judge1 = 0;
        for (Evaluation item_r : reviewPaper.getEvaluationList()){
            if (item_r.getReviewStage().equals("finish")){
                judge1++;
            }
            if (judge1 == reviewPaper.getEvaluationList().size()){
                reviewPaper.setReviewStatus("success");
            }
        }
        //在所有稿件都被审核后把会议状态改为full
        int judge2 = 0;
        for (Paper item_p : this.conferenceRepository.findByConferenceId(reviewPaper.getConferenceId()).getPaperList()){
            if (item_p.getReviewStatus().equals("success")){
                judge2++;
            }
            if (judge2 == this.conferenceRepository.findByConferenceId(reviewPaper.getConferenceId()).getPaperList().size()){
                this.conferenceRepository.findByConferenceId(reviewPaper.getConferenceId()).setReviewStage("full");
            }
        }
        this.paperRepository.save(reviewPaper);
        this.conferenceRepository.save(this.conferenceRepository.findByConferenceId(reviewPaper.getConferenceId()));
        return "success";
    }

    //回复或者新建帖子
    public Long sendPost(SendPostRequest request){
        String content = request.getContent();
        Long sendTime = request.getSendTime();
        Long replyId = request.getReplyId();
        String userName = request.getUserName();
        Long paperId = request.getPaperId();
        String title = request.getTitle();
        Post myPost = new Post(content,sendTime,userName,replyId,title);
        Paper myPaper = this.paperRepository.findByPaperId(paperId);
        myPaper.getPostList().add(myPost);
        this.paperRepository.save(myPaper);
        return myPost.getPostId();
    }

    //修改评审结果
    public String modifyEvaluation(ModifyEvaluationRequest request){
        int score = request.getScore();
        int confidence = request.getConfidence();
        String reviews = request.getReviews();
        Long evaluationId = request.getEvaluationID();
        Long conferenceId = request.getConferenceId();
        Conference myConference = this.conferenceRepository.findByConferenceId(conferenceId);
        Evaluation myEvaluation = this.evaluationRepository.findByEvaluationId(evaluationId);
        if(myEvaluation.getModifyStage().equals("unmodify")){
            myEvaluation.setModifyStage("firstModified");
        }else if(myEvaluation.getModifyStage().equals("canFinalModify")){
            myEvaluation.setModifyStage("finalModified");
        }
        myEvaluation.setConfidence(confidence);
        myEvaluation.setReviews(reviews);
        myEvaluation.setScore(score);
        this.evaluationRepository.save(myEvaluation);
        //若所有Evaluation的modifyStage都已经改成firstModified，则将EmploymentStage设为canFirstRelease
        int judge2 = 0;
        judge2 = modifyStageJudge1(myConference.getPaperList());
        if (judge2 == myConference.getPaperList().size()*3) {
            myConference.setEmploymentStage("canFirstRelease");
        }
        //若所有提交了rebuttal的论文的Evaluation的modifyStage都已经改成finalModified，则将EmploymentStage设为canFinalRelease
        List<Paper> rebuttalPaperList = new ArrayList<>();
        for(Paper item : myConference.getPaperList()){
            if(item.getRebuttal()!=null){
                rebuttalPaperList.add(item);
            }
        }
        int judge3 = 0;
        judge3 = modifyStageJudge2(rebuttalPaperList);
        if (judge3 == rebuttalPaperList.size()*3) {
            myConference.setEmploymentStage("canFinalRelease");
        }
        this.conferenceRepository.save(myConference);
        return "success";
    }

    //确认评审结果
    public String confirm(ConfirmRequest request){
        Long evaluationId = request.getEvaluationID();
        Long conferenceId = request.getConferenceId();
        Conference myConference = this.conferenceRepository.findByConferenceId(conferenceId);
        Evaluation myEvaluation = this.evaluationRepository.findByEvaluationId(evaluationId);
        if(myEvaluation.getModifyStage().equals("unmodify")){
            myEvaluation.setModifyStage("firstModified");
        }else if(myEvaluation.getModifyStage().equals("canFinalModify")){
            myEvaluation.setModifyStage("finalModified");
        }
        this.evaluationRepository.save(myEvaluation);
        //若所有Evaluation的modifyStage都已经改成firstModified，则将EmploymentStage设为canFirstRelease
        int judge2 = 0;
        judge2 = modifyStageJudge1(myConference.getPaperList());
        if (judge2 == myConference.getPaperList().size()*3) {
            myConference.setEmploymentStage("canFirstRelease");
        }
        //若所有提交了rebuttal的论文的Evaluation的modifyStage都已经改成finalModified，则将EmploymentStage设为canFinalRelease
        List<Paper> rebuttalPaperList = new ArrayList<>();
        for(Paper item : myConference.getPaperList()){
            if(item.getRebuttal()!=null){
                rebuttalPaperList.add(item);
            }
        }
        int judge3 = 0;
        judge3 = modifyStageJudge2(rebuttalPaperList);
        if (judge3 == rebuttalPaperList.size()*3) {
            myConference.setEmploymentStage("canFinalRelease");
        }
        this.conferenceRepository.save(myConference);
        return "success";
    }

    public int modifyStageJudge1(List<Paper> papers){
        int judge=0;
        for (Paper item_p : papers){
            for(Evaluation item_e : item_p.getEvaluationList()) {
                if (item_e.getModifyStage().equals("firstModified")) {
                    judge++;
                }
            }
        }
        return judge;
    }

    public int modifyStageJudge2(List<Paper> papers){
        int judge=0;
        for (Paper item_p : papers){
            for(Evaluation item_e : item_p.getEvaluationList()) {
                if (item_e.getModifyStage().equals("finalModified")) {
                    judge++;
                }
            }
        }
        return judge;
    }

    //返回所有非回复的帖子
    public List<Post> getAllPosts(GetAllPostsRequest requast){
        Long paperId = requast.getPaperId();
        List<Post> posts = this.paperRepository.findByPaperId(paperId).getPostList();
        List<Post> myPosts = new ArrayList<>();
        for(Post item : posts){
            if(item.getReplyId()==-1){
                myPosts.add(item);
            }
        }
        return myPosts;
    }

    //返回某一帖子下的所有回帖
    public List<Post> getAllReplies(GetAllRepliesRequest request){
        Long postId = request.getPostId();
        Long paperId = request.getPaperId();
        List<Post> posts = this.paperRepository.findByPaperId(paperId).getPostList();
        List<Post> myPosts = new ArrayList<>();
        for(Post item : posts){
            if(item.getReplyId()==postId || item.getPostId().equals(postId)){
                myPosts.add(item);
            }
        }
        return myPosts;
    }

    //发送rebuttal
    public String sendRebuttal(SendRebuttalRequest request){
        Long paperId = request.getPaperId();
        String rebuttal = request.getRebuttal();
        Paper myPaper = this.paperRepository.findByPaperId(paperId);
        myPaper.setRebuttal(rebuttal);
        this.paperRepository.save(myPaper);
        return "success";
    }

    //发布第一次录用结果
    public String setFirstRelease(ReleaseRequest request){
        Long conferenceId = request.getConferenceId();
        Conference myConference = this.conferenceRepository.findByConferenceId(conferenceId);
        myConference.setEmploymentStage("firstReleased");
        List<Paper> paperList = myConference.getPaperList();
        for(Paper item1 : paperList){
            for(Evaluation item2 : item1.getEvaluationList()){
                Evaluation evaluationBackup = new Evaluation(item2.getReviews(),item2.getScore(),item2.getConfidence(),item2.getTitle(),item2.getDigest());
                evaluationBackup.setPcmemberName(item2.getPcmemberName());
                item1.getEvaluationBackup().add(evaluationBackup);
                //this.paperRepository.save(item1);
                item2.setModifyStage("canFinalModify");
                this.evaluationRepository.save(item2);
            }
            this.paperRepository.save(item1);
        }
        this.conferenceRepository.save(myConference);
        return "success";
    }

    //发布最终录用结果
    public String setFinalRelease(ReleaseRequest request){
        Long conferenceId = request.getConferenceId();
        Conference myConference = this.conferenceRepository.findByConferenceId(conferenceId);
        myConference.setEmploymentStage("finalReleased");
        this.conferenceRepository.save(myConference);
        return "success";
    }
}
