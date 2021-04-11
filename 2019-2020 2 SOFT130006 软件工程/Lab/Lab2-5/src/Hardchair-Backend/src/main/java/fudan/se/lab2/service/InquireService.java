package fudan.se.lab2.service;

import fudan.se.lab2.controller.request.*;
import fudan.se.lab2.domain.*;
import fudan.se.lab2.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.*;

/**
 * @author LBW
 */
@Service
public class InquireService {
    private UserRepository userRepository;
    private ConferenceRepository conferenceRepository;
    private InviteRepository inviteRepository;
    private PaperRepository paperRepository;
    private EvaluationRepository evaluationRepository;

    @Autowired
    public InquireService(EvaluationRepository evaluationRepository, UserRepository userRepository, AuthorityRepository authorityRepository, ConferenceRepository conferenceRepository, InviteRepository inviteRepository, AuthorRepository authorRepository, PaperRepository paperRepository) {
        this.userRepository = userRepository;
        this.conferenceRepository = conferenceRepository;
        this.inviteRepository = inviteRepository;
        this.paperRepository = paperRepository;
        this.evaluationRepository = evaluationRepository;
    }

    //根据真实姓名查找用户
    public Set<User> findUserByRealname(FindUserByRealnameRequest request) {
        String meetingName = request.getMeetingName();
        String realname = request.getRealname();
        Set<User> myuser = this.userRepository.findByRealname(realname);
        Conference conference = this.conferenceRepository.findByfullName(meetingName);
        for (User iter : myuser) {
            if (iter.getUsername().equals("admin")) {
                myuser.remove(iter);
            }
            if (conference.getReviewerList().contains(iter.getUsername())) {
                iter.setValidate(false);
            } else {
                iter.setValidate(true);
            }
        }
        return myuser;
    }

    //根据身份查找会议
    public Set<Conference> findMeetingByIdentity(FindMeetingByIdentityRequest request) {
        String username = request.getUsername();
        String identity = request.getIdentity();
        User thisUser = this.userRepository.findByUsername(username);
        if (thisUser == null) {
            throw new UsernameNotFoundException(username);
        }
        if (identity.equals("creator")) {
            return this.conferenceRepository.findByChairMan(thisUser);
        } else if (identity.equals("contributor")){
            List<String> contributeConferenceNames = thisUser.getContributeConference();
            Set<Conference> contributeConference = new HashSet<>();
            for (String iter : contributeConferenceNames){
                contributeConference.add(this.conferenceRepository.findByfullName(iter));
            }
            return contributeConference;
        } else if (identity.equals("reviewer")){
            List<String> reviewConferenceNames = thisUser.getReviewConference();
            Set<Conference> reviewConference = new HashSet<>();
            for (String iter : reviewConferenceNames){
                reviewConference.add(this.conferenceRepository.findByfullName(iter));
            }
            return reviewConference;
        } else if (identity.equals("admin")){
            return this.conferenceRepository.findByStage("pending");
        } else if (identity.equals("showAllMeetings")) {
            return this.conferenceRepository.findByStage("accept");
        }
        return null;
    }

    //根据身份查找邀请
    public Set<Invite> findInviteByIdentity(FindInviteByIdentityRequest request) {
        String username = request.getUsername();
        String identity = request.getIdentity();
        User thisUser = this.userRepository.findByUsername(username);
        if (thisUser == null) {
            throw new UsernameNotFoundException(username);
        }
        if (identity.equals("creator")) {  //邀请创建者
            return this.inviteRepository.findByChairName(username);
        } else if (identity.equals("reviewer")) {  //邀请接受者
            return this.inviteRepository.findByPcmemberName(username);
        }
        return null;
    }

    //根据会议名和会议ID返回topic集合
    public Set<String> getTopicByMeetingID(GetTopicByMeetingIDRequest request){
        String meetingName = request.getMeetingName();
        Conference currentConference = this.conferenceRepository.findByfullName(meetingName);
        if (currentConference == null){
            return null;
        }
        List<String> topicList = currentConference.getTopics();
        Set<String> topicSet = new HashSet<>();
        for (String item : topicList){
            topicSet.add(item);
        }
        return topicSet;
    }

    //根据会议名和论文ID返回整个论文实体
    public Paper getContribute(GetContributRequest request){
        Long contributionId = request.getContributionID();
        Paper paper = this.paperRepository.findByPaperId(contributionId);
        if (paper == null){
            return null;
        }
        return paper;

    }

    //根据会议名返回会议实体
    public Conference getConferenceByMeetingID(GetConferenceByMeetingIDRequest request){
        Long meetingID = request.getMeetingID();
        Conference currentConference = this.conferenceRepository.findByConferenceId(meetingID);
        if (currentConference == null){
            return null;
        }
        return currentConference;
    }

    //根据用户名和会议名获取投稿状态
    public List<Paper> getContributionState(GetContributionStateRequest request){
        String meetingName = request.getMeetingName();
        String userName = request.getUsername();
        Conference conference = this.conferenceRepository.findByfullName(meetingName);
        List<Paper> userPaperList = new ArrayList<>();
        List<Paper> conferencePaperList = conference.getPaperList();
        for (Paper item : conferencePaperList){
            if (userName.equals(item.getContributor())){
                userPaperList.add(item);
            }
        }
        return userPaperList;
    }

   //获取当前用户在当前会议下要审的稿子
    public List<Paper> getReviewList(GetReviewListRequest request){
        String username = request.getUsername();
        Long meetingId = request.getMeetingID();
        List<Paper> allPaper = this.conferenceRepository.findByConferenceId(meetingId).getPaperList();
        List<Paper> reviewPaper = new ArrayList<>();
        for (Paper item : allPaper){
            if (item.getReviewerNameList().contains(username)){
                reviewPaper.add(item);
            }
        }
        return reviewPaper;
    }

    //返回审核结果
    public Set<Evaluation> getReviewResult(GetReviewResultRequest request){
        Long contributionID = request.getConbutrionID();
        Paper mypaper = this.paperRepository.findByPaperId(contributionID);
        Conference myConference = this.conferenceRepository.findByConferenceId(mypaper.getConferenceId());
        if(myConference.getEmploymentStage().equals("firstReleased") || myConference.getEmploymentStage().equals("canFinalRelease")){
            return mypaper.getEvaluationBackup();
        }
        return mypaper.getEvaluationList();
    }

    //查看第一次录用结果
    public Set<Paper> firstRelease(ReleaseRequest request){
        Long conferenceId = request.getConferenceId();
        Conference myConference = this.conferenceRepository.findByConferenceId(conferenceId);
        List<Paper> paperList = myConference.getPaperList();
        Set<Paper> myPaper = new HashSet<>();
        System.out.println(paperList);
        for(Paper item : paperList){
            boolean add=true;
            System.out.println(item);
            for(Evaluation item1 : item.getEvaluationBackup()){
                System.out.println(item);
                if(item1.getScore() == -1 || item1.getScore() == -2){
                    add=false;
                    break;
                }
            }
            if (add) myPaper.add(item);
            for(Evaluation item1 : item.getEvaluationList()){
                if(item1.getScore() != -1 && item1.getScore() != -2){
                    item1.setModifyStage("finalModified");
                    this.evaluationRepository.save(item1);
                }
            }
        }
        return myPaper;
    }

    //查看最终录用结果
    public Set<Paper> finalRelease(ReleaseRequest request){
        Long conferenceId = request.getConferenceId();
        Conference myConference = this.conferenceRepository.findByConferenceId(conferenceId);
        List<Paper> paperList = myConference.getPaperList();
        Set<Paper> myPaper = new HashSet<>();
        System.out.println(paperList);
        for(Paper item : paperList){
            boolean add=true;
            System.out.println(item);
            for(Evaluation item1 : item.getEvaluationList()){
                System.out.println(item);
                if(item1.getScore() == -1 || item1.getScore() == -2){
                    add=false;
                    break;
                }
            }
            if (add) myPaper.add(item);
        }
        return myPaper;
    }

    //返回旧的评审结果
    public Evaluation getOldResult(GetOldResultRequest request){
        Long paperID = request.getPaperId();
        String pcmenberName = request.getPcMemberName();
        Paper myPaper = this.paperRepository.findByPaperId(paperID);
        for(Evaluation item : myPaper.getEvaluationList()){
            if(pcmenberName.equals(item.getPcmemberName())){
                return item;
            }
        }
        return null;
    }
}
