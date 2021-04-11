package fudan.se.lab2.service;

import fudan.se.lab2.controller.request.*;
import fudan.se.lab2.domain.*;
import fudan.se.lab2.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ConferenceServiceTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthorityRepository authorityRepository;
    @Autowired
    private ConferenceRepository conferenceRepository;
    @Autowired
    private InviteRepository inviteRepository;
    @Autowired
    private EvaluationRepository evaluationRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private PaperRepository paperRepository;
    private Set <String> authorities = new HashSet<>();
    private List<String> normalTopics = new ArrayList<>();
    private final RegisterRequest request5 = new RegisterRequest("abc5","abc55","abc5@fudan.edu.cn","5abc","5cba", authorities);
    private final RegisterRequest request6 = new RegisterRequest("abc6","abc66","abc6@fudan.edu.cn","6abc","6cba", authorities);
    private final RegisterRequest request8 = new RegisterRequest("abc8","abc88","abc8@fudan.edu.cn","8abc","8cba", authorities);
    private final RegisterRequest request9 = new RegisterRequest("abc9","abc99","abc9@fudan.edu.cn","9abc","9cba", authorities);
    private final RegisterRequest request10 = new RegisterRequest("abc10","abc1010","abc10@fudan.edu.cn","10abc","10cba", authorities);
    private final RegisterRequest request11 = new RegisterRequest("abc11","abc1111","abc11@fudan.edu.cn","11abc","11cba", authorities);

    @Test
    void startMeeting() {
        AuthService authService = new AuthService(userRepository, authorityRepository, conferenceRepository, inviteRepository, authorRepository, paperRepository);
        ConferenceService service = new ConferenceService(evaluationRepository,userRepository, authorityRepository, conferenceRepository, inviteRepository, authorRepository, paperRepository);
        if (this.userRepository.findByUsername(request10.getUsername()) == null) {
            authService.register(request10);
        }
        User user1 = this.userRepository.findByUsername(request10.getUsername());
        StartMeetingRequest startMeetingRequest = new StartMeetingRequest(user1.getUsername(), "BN10", "FN10", "add10", "2020-04-11", "2020-04-12", "2020-04-13", "pending", normalTopics);
        service.startMeeting(startMeetingRequest);
        Conference conference = this.conferenceRepository.findByfullName(startMeetingRequest.getFullName());
        assertEquals(startMeetingRequest.getFullName(), conference.getFullName());
        assertEquals(startMeetingRequest.getBriefName(), conference.getBriefName());
        assertEquals(startMeetingRequest.getAddress(), conference.getAddress());
        assertEquals(startMeetingRequest.getChairMan(), conference.getChairMan().getUsername());
        assertEquals(startMeetingRequest.getStage(), conference.getStage());
    }

    @Test
    void verifyMeetings() {
        AuthService authService = new AuthService(userRepository, authorityRepository, conferenceRepository, inviteRepository, authorRepository, paperRepository);
        ConferenceService service = new ConferenceService(evaluationRepository,userRepository, authorityRepository, conferenceRepository, inviteRepository, authorRepository, paperRepository);
        if (this.userRepository.findByUsername(request11.getUsername()) == null) {
            authService.register(request11);
        }
        User user1 = this.userRepository.findByUsername(request11.getUsername());
        StartMeetingRequest startMeetingRequest1 = new StartMeetingRequest(user1.getUsername(), "BN12", "FN12", "add12", "2020-04-11", "2020-04-12", "2020-04-13", "pending", normalTopics);
        service.startMeeting(startMeetingRequest1);
        StartMeetingRequest startMeetingRequest2 = new StartMeetingRequest(user1.getUsername(), "BN13", "FN13", "add13", "2020-04-11", "2020-04-12", "2020-04-13", "pending", normalTopics);
        service.startMeeting(startMeetingRequest2);
        //测试能否正常通过会议申请
        VerifyMeetingsRequest verifyMeetingsRequest1 = new VerifyMeetingsRequest();
        verifyMeetingsRequest1.setMeetingName(startMeetingRequest1.getFullName());
        verifyMeetingsRequest1.setAttitude("accept");
        service.verifyMeetings(verifyMeetingsRequest1);
        assertEquals("accept", this.conferenceRepository.findByfullName(startMeetingRequest1.getFullName()).getStage());
        //测试能否正常拒绝会议申请
        VerifyMeetingsRequest verifyMeetingsRequest2 = new VerifyMeetingsRequest();
        verifyMeetingsRequest2.setMeetingName(startMeetingRequest2.getFullName());
        verifyMeetingsRequest2.setAttitude("reject");
        service.verifyMeetings(verifyMeetingsRequest2);
        assertEquals("reject", this.conferenceRepository.findByfullName(startMeetingRequest2.getFullName()).getStage());
    }

    @Test
    void startAndVerifyMeetingModule(){
        AuthService authService = new AuthService(userRepository, authorityRepository, conferenceRepository, inviteRepository, authorRepository, paperRepository);
        ConferenceService service = new ConferenceService(evaluationRepository,userRepository, authorityRepository, conferenceRepository, inviteRepository, authorRepository, paperRepository);
        if (this.userRepository.findByUsername(request10.getUsername()) == null) {
            authService.register(request10);
        }
        User user1 = this.userRepository.findByUsername(request10.getUsername());
        StartMeetingRequest startMeetingRequest = new StartMeetingRequest(user1.getUsername(), "BN10", "FN10", "add10", "2020-04-11", "2020-04-12", "2020-04-13", "pending", normalTopics);
        service.startMeeting(startMeetingRequest);
        Conference conference = this.conferenceRepository.findByfullName(startMeetingRequest.getFullName());
        assertEquals(startMeetingRequest.getFullName(), conference.getFullName());
        assertEquals(startMeetingRequest.getBriefName(), conference.getBriefName());
        assertEquals(startMeetingRequest.getAddress(), conference.getAddress());
        assertEquals(startMeetingRequest.getChairMan(), conference.getChairMan().getUsername());
        assertEquals(startMeetingRequest.getStage(), conference.getStage());
        VerifyMeetingsRequest verifyMeetingsRequest1 = new VerifyMeetingsRequest();
        verifyMeetingsRequest1.setMeetingName(startMeetingRequest.getFullName());
        verifyMeetingsRequest1.setAttitude("accept");
        service.verifyMeetings(verifyMeetingsRequest1);
        assertEquals("accept", this.conferenceRepository.findByfullName(startMeetingRequest.getFullName()).getStage());
        //测试能否正常拒绝会议申请
        verifyMeetingsRequest1.setAttitude("reject");
        service.verifyMeetings(verifyMeetingsRequest1);
        assertEquals("reject", this.conferenceRepository.findByfullName(startMeetingRequest.getFullName()).getStage());
    }

    @Test
    void openContribute() {
        AuthService authService = new AuthService(userRepository, authorityRepository, conferenceRepository, inviteRepository, authorRepository, paperRepository);
        ConferenceService service = new ConferenceService(evaluationRepository,userRepository, authorityRepository, conferenceRepository, inviteRepository, authorRepository, paperRepository);
        //测试是否可以正常开启投稿功能
        if (this.userRepository.findByUsername(request6.getUsername()) == null) {
            authService.register(request6);
        }
        Conference conference1 = new Conference(this.userRepository.findByUsername(request6.getUsername()), "BN6", "FN6", "date6", "add6", "DDL6", "date66", "pending", normalTopics);
        if (this.conferenceRepository.findByfullName(conference1.getFullName()) == null) {
            this.conferenceRepository.save(conference1);
        }
        ContributeOperationRequest openContributeRequest = new ContributeOperationRequest(conference1.getFullName(), request6.getUsername());
        service.openContribute(openContributeRequest);
        assertEquals("acceptContribution", this.conferenceRepository.findByfullName(conference1.getFullName()).getContributionStage());
    }

    @Test
    void closeContribute() {
        AuthService authService = new AuthService(userRepository, authorityRepository, conferenceRepository, inviteRepository, authorRepository, paperRepository);
        ConferenceService service = new ConferenceService(evaluationRepository,userRepository, authorityRepository, conferenceRepository, inviteRepository, authorRepository, paperRepository);
        //测试是否可以正常关闭投稿功能
        if (this.userRepository.findByUsername(request5.getUsername()) == null) {
            authService.register(request5);
        }
        Conference conference1 = new Conference(this.userRepository.findByUsername(request5.getUsername()), "BN5", "FN5", "date5", "add5", "DDL5", "date55", "pending", normalTopics);
        if (this.conferenceRepository.findByfullName(conference1.getFullName()) == null) {
            this.conferenceRepository.save(conference1);
        }
        ContributeOperationRequest openContributeRequest = new ContributeOperationRequest(conference1.getFullName(), request5.getUsername());
        service.openContribute(openContributeRequest);
        ContributeOperationRequest closeContributeRequest = new ContributeOperationRequest(conference1.getFullName(), request5.getUsername());
        service.closeContribute(closeContributeRequest);
        assertEquals("rejectContribution", this.conferenceRepository.findByfullName(conference1.getFullName()).getContributionStage());
    }

    @Test
    void contributeOperation(){
        AuthService authService = new AuthService(userRepository, authorityRepository, conferenceRepository, inviteRepository, authorRepository, paperRepository);
        ConferenceService service = new ConferenceService(evaluationRepository,userRepository, authorityRepository, conferenceRepository, inviteRepository, authorRepository, paperRepository);
        if (this.userRepository.findByUsername(request11.getUsername()) == null) {
            authService.register(request11);
        }
        User user = this.userRepository.findByUsername(request11.getUsername());
        Conference conference1 = new Conference(this.userRepository.findByUsername(request11.getUsername()), "BN6", "FN6", "date6", "add6", "DDL6", "date66", "pending", normalTopics);
        if (this.conferenceRepository.findByfullName(conference1.getFullName()) == null) {
            this.conferenceRepository.save(conference1);
        }
        ContributeOperationRequest openContributeRequest = new ContributeOperationRequest(conference1.getFullName(), request11.getUsername());
        service.openContribute(openContributeRequest);
        assertEquals("acceptContribution", this.conferenceRepository.findByfullName(conference1.getFullName()).getContributionStage());
        ContributeOperationRequest closeContributeRequest = new ContributeOperationRequest(conference1.getFullName(), request11.getUsername());
        service.closeContribute(closeContributeRequest);
        assertEquals("rejectContribution", this.conferenceRepository.findByfullName(conference1.getFullName()).getContributionStage());
    }

    @Test
    void addpcmemberList() {
        AuthService authService = new AuthService(userRepository, authorityRepository, conferenceRepository, inviteRepository, authorRepository, paperRepository);
        ConferenceService service = new ConferenceService(evaluationRepository,userRepository, authorityRepository, conferenceRepository, inviteRepository, authorRepository, paperRepository);
        if (this.userRepository.findByUsername(request11.getUsername()) == null) {
            authService.register(request11);
        }
        User user = this.userRepository.findByUsername(request11.getUsername());
        List<String> topics = new ArrayList<>();
        topics.add("test1");
        topics.add("test2");
        Conference conference = new Conference(user, "BN7", "FN7", "date7", "add7", "DDL7", "date77", "pending", topics);
        Topicrelated topicrelated = new Topicrelated();
        Paper paper = new Paper("PP1", "PP2", "PP3", "PP4", "PP5", conference.getConferenceId());
        Set<String> paperTopicList = new HashSet<>();
        assertTrue(service.addpcmemberList(topicrelated, paperTopicList, paper));
    }


    @Test
    void reviewMeetings() {
        AuthService authService = new AuthService(userRepository, authorityRepository, conferenceRepository, inviteRepository, authorRepository, paperRepository);
        ConferenceService service = new ConferenceService(evaluationRepository,userRepository, authorityRepository, conferenceRepository, inviteRepository, authorRepository, paperRepository);
        if (this.userRepository.findByUsername(request11.getUsername()) == null) {
            authService.register(request11);
        }
        User user1 = this.userRepository.findByUsername(request11.getUsername());
        Conference conference = new Conference(user1, "BN20", "FN20", "date20", "add20", "DDL20", "date20", "TEST", normalTopics);
        conference.setContributionStage("acceptContribution");
        if (this.conferenceRepository.findByfullName(conference.getFullName()) == null) {
            this.conferenceRepository.save(conference);
        }

        ReviewMeetingsRequest reviewMeetingsRequest1 = new ReviewMeetingsRequest();
        reviewMeetingsRequest1.setMeetingName(conference.getFullName());
        reviewMeetingsRequest1.setReviewModl("relativity");
        String result1 = service.reviewMeetings(reviewMeetingsRequest1);
        assertEquals("true", result1);
        Conference conference1 = this.conferenceRepository.findByfullName(conference.getFullName());
        assertEquals("pending", conference1.getReviewStage());
        assertEquals("rejectContribution", conference1.getContributionStage());
    }

    @Test
    void Pattern1_case1() {
        ConferenceService service = new ConferenceService(evaluationRepository,userRepository, authorityRepository, conferenceRepository, inviteRepository, authorRepository, paperRepository);
        Paper paper = new Paper("PP1", "PP2", "PP3", "PP4", "PP5", (long)1234);
        ArrayList<String> allPcmemberNameList = new ArrayList<>();
        allPcmemberNameList.add("1");
        allPcmemberNameList.add("2");
        allPcmemberNameList.add("3");
        paper.setAllPcmemberNameList(allPcmemberNameList);
        service.pattern1_case1(paper);
        ArrayList<String> results = new ArrayList<>();
        for (Evaluation item : paper.getEvaluationList()) {
            results.add(item.getPcmemberName());
        }
        assertTrue(results.contains("1") && results.contains("2") && results.contains("3"));
    }

    @Test
    void Pattern1_case2() {
        ConferenceService service = new ConferenceService(evaluationRepository,userRepository, authorityRepository, conferenceRepository, inviteRepository, authorRepository, paperRepository);
        Paper paper = new Paper("PP1", "PP2", "PP3", "PP4", "PP5", (long)1234);
        ArrayList<String> allPcmemberNameList = new ArrayList<>();
        allPcmemberNameList.add("1");
        allPcmemberNameList.add("2");
        allPcmemberNameList.add("3");
        allPcmemberNameList.add("4");
        paper.setAllPcmemberNameList(allPcmemberNameList);
        service.pattern1_case1(paper);
        ArrayList<String> results = new ArrayList<>();
        for (Evaluation item : paper.getEvaluationList()) {
            results.add(item.getPcmemberName());
        }
        boolean res = true;
        for (String item : results) {
            if (!paper.getAllPcmemberNameList().contains(item)) {
                res = false;
            }
        }
        assertTrue(res);
    }

    @Test
    void Pattern1_case3() {
        AuthService authService = new AuthService(userRepository, authorityRepository, conferenceRepository, inviteRepository, authorRepository, paperRepository);
        ConferenceService service = new ConferenceService(evaluationRepository,userRepository, authorityRepository, conferenceRepository, inviteRepository, authorRepository, paperRepository);
        if (this.userRepository.findByUsername(request11.getUsername()) == null) {
            authService.register(request11);
        }
        if (this.userRepository.findByUsername(request10.getUsername()) == null) {
            authService.register(request10);
        }
        if (this.userRepository.findByUsername(request9.getUsername()) == null) {
            authService.register(request9);
        }
        User user1 = this.userRepository.findByUsername(request11.getUsername());
        User user2 = this.userRepository.findByUsername(request10.getUsername());
        User user3 = this.userRepository.findByUsername(request9.getUsername());
        Conference conference = new Conference(user1, "BN18", "FN18", "date18", "add18", "DDL18", "date18", "pending", normalTopics);
        if (this.conferenceRepository.findByfullName(conference.getFullName()) == null) {
            this.conferenceRepository.save(conference);
        }
        Paper paper = new Paper("test10", "dig10", "path10", user1.getUsername(), "stat10", conference.getConferenceId());
        paperRepository.save(paper);
        List<String> tempPCMember = new ArrayList<>();
        tempPCMember.add(user1.getUsername());
        tempPCMember.add(user2.getUsername());
        tempPCMember.add(user3.getUsername());
        conference.setReviewerList(tempPCMember);
        conferenceRepository.save(conference);
        service.pattern1_case3(paper, conference);
        Paper result = paperRepository.findByHeadline(paper.getHeadline());
        Set<Evaluation> evaluations = result.getEvaluationList();
        for (Evaluation item : evaluations) {
            assertTrue(tempPCMember.contains(item.getPcmemberName()));
            assertEquals(paper.getDigest(), item.getDigest());
            assertEquals(paper.getHeadline(), item.getTitle());
            assertEquals("pending", item.getReviewStage());
        }
    }

    @Test
    void Pattern2() {
        ConferenceService service = new ConferenceService(evaluationRepository,userRepository, authorityRepository, conferenceRepository, inviteRepository, authorRepository, paperRepository);
        Paper paper = new Paper("test9", "dig9", "path9", "TEST9", "stat9", (long)444);
        paperRepository.save(paper);
        List<Paper> tempPaper = new ArrayList<>();
        tempPaper.add(paper);
        List<String> tempPCMember = new ArrayList<>();
        tempPCMember.add("PCMember1");
        tempPCMember.add("PCMember2");
        tempPCMember.add("PCMember3");
        Map<String, List<String>> map = service.allotOfAverage(tempPaper, tempPCMember);
        service.pattern2(map);
        Paper result = paperRepository.findByHeadline(paper.getHeadline());
        Set<Evaluation> evaluations = result.getEvaluationList();
        for (Evaluation item : evaluations) {
            assertTrue(tempPCMember.contains(item.getPcmemberName()));
            assertEquals(paper.getDigest(), item.getDigest());
            assertEquals(paper.getHeadline(), item.getTitle());
            assertEquals("pending", item.getReviewStage());
        }
    }

    @Test
    void allotOfAverage() {
        ConferenceService service = new ConferenceService(evaluationRepository,userRepository, authorityRepository, conferenceRepository, inviteRepository, authorRepository, paperRepository);
        List<Paper> paperList = new ArrayList<>();
        List<String> pcmemberList = new ArrayList<>();
        Paper paper1 = new Paper("test8", "dig8", "path8", "TEST8", "stat8", (long)111);
        Paper paper2 = new Paper("test5", "dig5", "path5", "TEST5", "stat5", (long)222);
        Paper paper3 = new Paper("test6", "dig6", "path6", "TEST6", "stat6", (long)333);
        paperRepository.save(paper1);
        paperRepository.save(paper2);
        paperRepository.save(paper3);
        paperList.add(paper1);
        paperList.add(paper2);
        paperList.add(paper3);
        pcmemberList.add("PCMember1");
        pcmemberList.add("PCMember2");
        pcmemberList.add("PCMember3");
        Map<String, List<String>> result1 = service.allotOfAverage(paperList, pcmemberList);
        List<String> temp;
        assertEquals(result1.size(), paperList.size());
        for (String item1 : result1.keySet()) {
            temp = result1.get(item1);
            assertEquals(3,temp.size());
            for (String item2 : temp) {
                assertTrue(pcmemberList.contains(item2));
            }
        }

        pcmemberList.add("PCMember4");
        result1 = service.allotOfAverage(paperList, pcmemberList);
        assertEquals(result1.size(), paperList.size());
        for (String item1 : result1.keySet()) {
            temp = result1.get(item1);
            assertEquals(3,temp.size());
            for (String item2 : temp) {
                assertTrue(pcmemberList.contains(item2));
            }
        }

        Paper paper4 = new Paper("test7", "dig7", "path7", "TEST7", "stat7", (long)444);
        paperRepository.save(paper4);
        paperList.add(paper4);
        result1 = service.allotOfAverage(paperList, pcmemberList);
        assertEquals(result1.size(), paperList.size());
        for (String item1 : result1.keySet()) {
            temp = result1.get(item1);
            assertEquals(3,temp.size());
            for (String item2 : temp) {
                assertTrue(pcmemberList.contains(item2));
            }
        }
    }

    @Test
    void getRadomNumber() {
        ConferenceService service = new ConferenceService(evaluationRepository,userRepository, authorityRepository, conferenceRepository, inviteRepository, authorRepository, paperRepository);
        int temp = 5;
        int[] result = service.getRadomNumber(temp);
        boolean res = true;
        for (int i = 0; i < 3; i++) {
            if (result[i] < 0 || result[i] > temp) {
                res = false;
            }
        }
        assertTrue(res);
    }

    @Test
    void distributeResults() {
        AuthService authService = new AuthService(userRepository, authorityRepository, conferenceRepository, inviteRepository, authorRepository, paperRepository);
        ConferenceService service = new ConferenceService(evaluationRepository,userRepository, authorityRepository, conferenceRepository, inviteRepository, authorRepository, paperRepository);
        if (this.userRepository.findByUsername(request11.getUsername()) == null) {
            authService.register(request11);
        }
        User user = this.userRepository.findByUsername(request11.getUsername());
        Conference conference1 = new Conference(user, "BN16", "FN16", "date16", "add16", "DDL16", "date1616", "pending", normalTopics);
        conference1.setReviewStage("test");
        if (this.conferenceRepository.findByfullName(conference1.getFullName()) == null) {
            this.conferenceRepository.save(conference1);
        }
        Conference conference2 = new Conference(user, "BN15", "FN15", "date15", "add15", "DDL15", "date1515", "pending", normalTopics);
        conference2.setReviewStage("full");
        if (this.conferenceRepository.findByfullName(conference2.getFullName()) == null) {
            this.conferenceRepository.save(conference2);
        }
        DistributeResultsRequest distributeResultsRequest1 = new DistributeResultsRequest();
        DistributeResultsRequest distributeResultsRequest2 = new DistributeResultsRequest();
        distributeResultsRequest1.setMeetingName(conference1.getFullName());
        distributeResultsRequest2.setMeetingName(conference2.getFullName());
        assertEquals("false", service.distributeResults(distributeResultsRequest1));
        assertEquals("true", service.distributeResults(distributeResultsRequest2));
        assertEquals("finish", this.conferenceRepository.findByfullName(conference2.getFullName()).getReviewStage());
    }

    @Test
    void submitReviewInformation() {
        AuthService authService = new AuthService(userRepository, authorityRepository, conferenceRepository, inviteRepository, authorRepository, paperRepository);
        ConferenceService service = new ConferenceService(evaluationRepository,userRepository, authorityRepository, conferenceRepository, inviteRepository, authorRepository, paperRepository);
        if (this.userRepository.findByUsername(request11.getUsername()) == null) {
            authService.register(request11);
        }
        if (this.userRepository.findByUsername(request10.getUsername()) == null) {
            authService.register(request10);
        }
        if (this.userRepository.findByUsername(request9.getUsername()) == null) {
            authService.register(request9);
        }
        if (this.userRepository.findByUsername(request8.getUsername()) == null) {
            authService.register(request8);
        }
        User user1 = this.userRepository.findByUsername(request11.getUsername());
        User user2 = this.userRepository.findByUsername(request10.getUsername());
        User user3 = this.userRepository.findByUsername(request9.getUsername());
        User user4 = this.userRepository.findByUsername(request8.getUsername());
        Conference conference1 = new Conference(user1, "BN21", "FN21", "date21", "add21", "DDL21", "date21", "pending", normalTopics);
        if (this.conferenceRepository.findByfullName(conference1.getFullName()) == null) {
            this.conferenceRepository.save(conference1);
        }
        Paper paper1 = new Paper("test14", "dig14", "path14", user1.getUsername(), "stat14", conference1.getConferenceId());
        List<String> reviewers = new ArrayList<>();
        reviewers.add(user2.getUsername());
        reviewers.add(user3.getUsername());
        reviewers.add(user4.getUsername());
        paper1.setReviewerNameList(reviewers);
        if (this.paperRepository.findByHeadline(paper1.getHeadline()) == null) {
            this.paperRepository.save(paper1);
        }
        SubmitReviewIformationRequest submitReviewIformationRequest = new SubmitReviewIformationRequest();
        submitReviewIformationRequest.setConferenceID(paper1.getPaperId());
        submitReviewIformationRequest.setUsername(user2.getUsername());
        submitReviewIformationRequest.setConfidence(2);
        submitReviewIformationRequest.setScore(1);
        assertEquals("success", service.submitReviewInformation(submitReviewIformationRequest));
    }
    @Test
    void submitAndDistributeModule(){
        AuthService authService = new AuthService(userRepository, authorityRepository, conferenceRepository, inviteRepository, authorRepository, paperRepository);
        ConferenceService service = new ConferenceService(evaluationRepository,userRepository, authorityRepository, conferenceRepository, inviteRepository, authorRepository, paperRepository);
        if (this.userRepository.findByUsername(request5.getUsername()) == null) {
            authService.register(request5);
        }
        if (this.userRepository.findByUsername(request6.getUsername()) == null) {
            authService.register(request6);
        }
        if (this.userRepository.findByUsername(request8.getUsername()) == null) {
            authService.register(request8);
        }
        if (this.userRepository.findByUsername(request9.getUsername()) == null) {
            authService.register(request9);
        }
        User user1 = this.userRepository.findByUsername(request5.getUsername());
        User user2 = this.userRepository.findByUsername(request6.getUsername());
        User user3 = this.userRepository.findByUsername(request8.getUsername());
        User user4 = this.userRepository.findByUsername(request9.getUsername());
        Conference conference1 = new Conference(user1, "BN21", "FN21", "date21", "add21", "DDL21", "date21", "pending", normalTopics);
        if (this.conferenceRepository.findByfullName(conference1.getFullName()) == null) {
            this.conferenceRepository.save(conference1);
        }
        Conference conference2 = new Conference(user1, "BN15", "FN15", "date15", "add15", "DDL15", "date1515", "pending", normalTopics);
        conference2.setReviewStage("full");
        Paper paper1 = new Paper("test14", "dig14", "path14", user1.getUsername(), "stat14", conference1.getConferenceId());
        List<String> reviewers = new ArrayList<>();
        reviewers.add(user2.getUsername());
        reviewers.add(user3.getUsername());
        reviewers.add(user4.getUsername());
        paper1.setReviewerNameList(reviewers);
        if (this.paperRepository.findByHeadline(paper1.getHeadline()) == null) {
            this.paperRepository.save(paper1);
        }
        SubmitReviewIformationRequest submitReviewIformationRequest = new SubmitReviewIformationRequest();
        submitReviewIformationRequest.setConferenceID(paper1.getPaperId());
        submitReviewIformationRequest.setUsername(user2.getUsername());
        submitReviewIformationRequest.setConfidence(2);
        submitReviewIformationRequest.setScore(1);
        assertEquals("success", service.submitReviewInformation(submitReviewIformationRequest));
    }

    @Test
    void sendPost() {
        ConferenceService service = new ConferenceService(evaluationRepository,userRepository, authorityRepository, conferenceRepository, inviteRepository, authorRepository, paperRepository);
        if (this.paperRepository.findByHeadline("Test0001") == null) {
            this.paperRepository.save(new Paper("Test0001", "t1", "t2", "t3", "t4", (long)23333));
        }
        Paper paper = this.paperRepository.findByHeadline("Test0001");
        SendPostRequest sendPostRequest = new SendPostRequest();
        sendPostRequest.setPaperId(paper.getPaperId());
        sendPostRequest.setReplyId((long)221);
        sendPostRequest.setContent("A Test");
        sendPostRequest.setUserName("user1");
        sendPostRequest.setSendTime((long)20200610);
        sendPostRequest.setTitle("Test1");
        service.sendPost(sendPostRequest);
        paper = this.paperRepository.findByHeadline("Test0001");
        List<Post> posts = paper.getPostList();
        Post temp = new Post();
        for (Post item : posts) {
            if (item.getReplyId() == (long)221) {
                temp = item;
            }
        }
        assertEquals("A Test", temp.getContent());
        assertEquals("user1", temp.getUserName());
        assertEquals("Test1", temp.getTitle());
        assertEquals((long)20200610, temp.getSendTime());
    }

    @Test
    void getAllPosts() {
        ConferenceService service = new ConferenceService(evaluationRepository,userRepository, authorityRepository, conferenceRepository, inviteRepository, authorRepository, paperRepository);
        if (this.paperRepository.findByHeadline("Test0004") == null) {
            this.paperRepository.save(new Paper("Test0004", "t1", "t2", "t3", "t4", (long)100862));
        }
        Paper paper = this.paperRepository.findByHeadline("Test0004");
        Post post1 = new Post("Test A", (long)20200610, "user1", (long)2003, "test1");
        Post post2 = new Post("Test B", (long)20200611, "user2", (long)-1, "test2");
        Post post3 = new Post("Test C", (long)20200612, "user3", (long)-1, "test3");
        paper.getPostList().add(post1);
        paper.getPostList().add(post2);
        paper.getPostList().add(post3);
        this.paperRepository.save(paper);
        paper = this.paperRepository.findByHeadline("Test0004");
        GetAllPostsRequest getAllPostsRequest = new GetAllPostsRequest();
        getAllPostsRequest.setPaperId(paper.getPaperId());
        List<Post> posts = service.getAllPosts(getAllPostsRequest);
        assertEquals(2, posts.size());
        int count = 0;
        for (Post item : posts) {
            if (item.getTitle().equals(post2.getTitle())) {
                assertEquals(post2.getContent(), item.getContent());
                assertEquals(post2.getSendTime(), item.getSendTime());
                assertEquals(post2.getUserName(), item.getUserName());
                count++;
            }
            if (item.getTitle().equals(post3.getTitle())) {
                assertEquals(post3.getContent(), item.getContent());
                assertEquals(post3.getSendTime(), item.getSendTime());
                assertEquals(post3.getUserName(), item.getUserName());
                count++;
            }
        }
        assertEquals(2, count);
    }
    @Test
    void sendAndGetPost(){
        ConferenceService service = new ConferenceService(evaluationRepository,userRepository, authorityRepository, conferenceRepository, inviteRepository, authorRepository, paperRepository);
        if (this.paperRepository.findByHeadline("Test0001") == null) {
            this.paperRepository.save(new Paper("Test0001", "t1", "t2", "t3", "t4", (long)23333));
        }
        Paper paper = this.paperRepository.findByHeadline("Test0001");
        SendPostRequest sendPostRequest1 = new SendPostRequest();
        sendPostRequest1.setPaperId(paper.getPaperId());
        sendPostRequest1.setReplyId((long)221);
        sendPostRequest1.setContent("A Test");
        sendPostRequest1.setUserName("user1");
        sendPostRequest1.setSendTime((long)20200610);
        sendPostRequest1.setTitle("Test1");
        service.sendPost(sendPostRequest1);
        SendPostRequest sendPostRequest2 = new SendPostRequest();
        sendPostRequest2.setPaperId(paper.getPaperId());
        sendPostRequest2.setReplyId((long)-1);
        sendPostRequest2.setContent("Test B");
        sendPostRequest2.setUserName("user2");
        sendPostRequest2.setSendTime((long)20200611);
        sendPostRequest2.setTitle("test2");
        service.sendPost(sendPostRequest2);
        SendPostRequest sendPostRequest3 = new SendPostRequest();
        sendPostRequest3.setPaperId(paper.getPaperId());
        sendPostRequest3.setReplyId((long)-1);
        sendPostRequest3.setContent("Test C");
        sendPostRequest3.setUserName("user3");
        sendPostRequest3.setSendTime((long)20200612);
        sendPostRequest3.setTitle("test3");
        service.sendPost(sendPostRequest3);
        this.paperRepository.save(paper);
        paper = this.paperRepository.findByHeadline("Test0001");
        List<Post> posts = paper.getPostList();
        Post temp = new Post();
        for (Post item : posts) {
            if (item.getReplyId() == (long)221) {
                temp = item;
            }
        }
        assertEquals("A Test", temp.getContent());
        assertEquals("user1", temp.getUserName());
        assertEquals("Test1", temp.getTitle());
        assertEquals((long)20200610, temp.getSendTime());
        GetAllPostsRequest getAllPostsRequest = new GetAllPostsRequest();
        getAllPostsRequest.setPaperId(paper.getPaperId());
        List<Post> posts1 = service.getAllPosts(getAllPostsRequest);
        assertEquals(2, posts1.size());
        int count = 0;
        for (Post item : posts1) {
            if (item.getTitle().equals(sendPostRequest2.getTitle())) {
                assertEquals(sendPostRequest2.getContent(), item.getContent());
                assertEquals(sendPostRequest2.getSendTime(), item.getSendTime());
                assertEquals(sendPostRequest2.getUserName(), item.getUserName());
                count++;
            }
            if (item.getTitle().equals(sendPostRequest3.getTitle())) {
                assertEquals(sendPostRequest3.getContent(), item.getContent());
                assertEquals(sendPostRequest3.getSendTime(), item.getSendTime());
                assertEquals(sendPostRequest3.getUserName(), item.getUserName());
                count++;
            }
        }
        assertEquals(2, count);
    }

    @Test
    void modifyEvaluation() {
        AuthService authService = new AuthService(userRepository, authorityRepository, conferenceRepository, inviteRepository, authorRepository, paperRepository);
        ConferenceService service = new ConferenceService(evaluationRepository,userRepository, authorityRepository, conferenceRepository, inviteRepository, authorRepository, paperRepository);
        if (this.userRepository.findByUsername(request11.getUsername()) == null) {
            authService.register(request11);
        }
        User user1 = this.userRepository.findByUsername(request11.getUsername());
        Conference conference1 = new Conference(user1, "BN33", "FN33", "date33", "add33", "DDL33", "date33", "pending", normalTopics);
        if (this.conferenceRepository.findByfullName(conference1.getFullName()) == null) {
            this.conferenceRepository.save(conference1);
        }
        conference1 = this.conferenceRepository.findByfullName("FN33");
        Evaluation evaluation1 = new Evaluation("high", 2, 2, "a", "aa");
        Evaluation evaluation2 = new Evaluation("low", -1, -1, "b", "bb");
        evaluation1.setModifyStage("unmodify");
        evaluation2.setModifyStage("canFinalModify");
        this.evaluationRepository.save(evaluation1);
        this.evaluationRepository.save(evaluation2);
        long ID1 = evaluation1.getEvaluationId();
        long ID2 = evaluation2.getEvaluationId();

        ModifyEvaluationRequest modifyEvaluationRequest1 = new ModifyEvaluationRequest();
        modifyEvaluationRequest1.setConferenceId(conference1.getConferenceId());
        modifyEvaluationRequest1.setConfidence(2);
        modifyEvaluationRequest1.setEvaluationID(ID1);
        modifyEvaluationRequest1.setModifyStage("unmodify");
        modifyEvaluationRequest1.setReviews("Change1");
        modifyEvaluationRequest1.setScore(2);
        String res1 = service.modifyEvaluation(modifyEvaluationRequest1);
        assertEquals("success", res1);
        evaluation1 = this.evaluationRepository.findByEvaluationId(ID1);
        assertEquals("firstModified", evaluation1.getModifyStage());

        ModifyEvaluationRequest modifyEvaluationRequest2 = new ModifyEvaluationRequest();
        modifyEvaluationRequest2.setConferenceId(conference1.getConferenceId());
        modifyEvaluationRequest2.setConfidence(1);
        modifyEvaluationRequest2.setEvaluationID(ID2);
        modifyEvaluationRequest2.setModifyStage("canFinalModify");
        modifyEvaluationRequest2.setReviews("Change2");
        modifyEvaluationRequest2.setScore(1);
        String res2 = service.modifyEvaluation(modifyEvaluationRequest2);
        assertEquals("success", res2);
        evaluation2 = this.evaluationRepository.findByEvaluationId(ID2);
        assertEquals("finalModified", evaluation2.getModifyStage());
    }

    @Test
    void confirm() {
        AuthService authService = new AuthService(userRepository, authorityRepository, conferenceRepository, inviteRepository, authorRepository, paperRepository);
        ConferenceService service = new ConferenceService(evaluationRepository,userRepository, authorityRepository, conferenceRepository, inviteRepository, authorRepository, paperRepository);
        if (this.userRepository.findByUsername(request11.getUsername()) == null) {
            authService.register(request11);
        }
        User user1 = this.userRepository.findByUsername(request11.getUsername());
        Conference conference1 = new Conference(user1, "BN32", "FN32", "date32", "add32", "DDL32", "date30", "pending", normalTopics);
        if (this.conferenceRepository.findByfullName(conference1.getFullName()) == null) {
            this.conferenceRepository.save(conference1);
        }
        conference1 = this.conferenceRepository.findByfullName("FN32");
        Evaluation evaluation1 = new Evaluation("high", 2, 2, "a", "aa");
        Evaluation evaluation2 = new Evaluation("low", -1, -1, "b", "bb");
        evaluation1.setModifyStage("unmodify");
        evaluation2.setModifyStage("canFinalModify");
        this.evaluationRepository.save(evaluation1);
        this.evaluationRepository.save(evaluation2);
        long ID1 = evaluation1.getEvaluationId();
        long ID2 = evaluation2.getEvaluationId();

        ConfirmRequest confirmRequest1 = new ConfirmRequest();
        confirmRequest1.setConferenceId(conference1.getConferenceId());
        confirmRequest1.setEvaluationID(evaluation1.getEvaluationId());
        confirmRequest1.setModifyStage("test");
        String res1 = service.confirm(confirmRequest1);
        assertEquals("success", res1);
        evaluation1 = this.evaluationRepository.findByEvaluationId(ID1);
        assertEquals("firstModified", evaluation1.getModifyStage());

        ConfirmRequest confirmRequest2 = new ConfirmRequest();
        confirmRequest2.setConferenceId(conference1.getConferenceId());
        confirmRequest2.setEvaluationID(evaluation2.getEvaluationId());
        confirmRequest2.setModifyStage("test");
        String res2 = service.confirm(confirmRequest2);
        assertEquals("success", res2);
        evaluation2 = this.evaluationRepository.findByEvaluationId(ID2);
        assertEquals("finalModified", evaluation2.getModifyStage());
    }

    @Test
    void modifyStageJudge1() {
        ConferenceService service = new ConferenceService(evaluationRepository,userRepository, authorityRepository, conferenceRepository, inviteRepository, authorRepository, paperRepository);
        Paper paper1 = new Paper("Test0005", "t1", "t2", "t3", "t4", (long)100868);
        Paper paper2 = new Paper("Test0006", "t1", "t2", "t3", "t4", (long)100869);
        Evaluation evaluation1 = new Evaluation("high", 2, 2, "a", "aa");
        Evaluation evaluation2 = new Evaluation("low", -1, -1, "b", "bb");
        Evaluation evaluation3 = new Evaluation("very high", 2, 1, "c", "cc");
        evaluation1.setModifyStage("test");
        evaluation2.setModifyStage("firstModified");
        evaluation3.setModifyStage("firstModified");
        paper1.getEvaluationList().add(evaluation1);
        paper1.getEvaluationList().add(evaluation2);
        paper2.getEvaluationList().add(evaluation3);
        List<Paper> papers = new ArrayList<>();
        papers.add(paper1);
        papers.add(paper2);
        int res = service.modifyStageJudge1(papers);
        assertEquals(5, res);
    }

    @Test
    void modifyStageJudge2() {
        ConferenceService service = new ConferenceService(evaluationRepository,userRepository, authorityRepository, conferenceRepository, inviteRepository, authorRepository, paperRepository);
        Paper paper1 = new Paper("Test0005", "t1", "t2", "t3", "t4", (long)100868);
        Paper paper2 = new Paper("Test0006", "t1", "t2", "t3", "t4", (long)100869);
        Evaluation evaluation1 = new Evaluation("high", 2, 2, "a", "aa");
        Evaluation evaluation2 = new Evaluation("low", -1, -1, "b", "bb");
        Evaluation evaluation3 = new Evaluation("very high", 2, 1, "c", "cc");
        evaluation1.setModifyStage("test");
        evaluation2.setModifyStage("finalModified");
        evaluation3.setModifyStage("finalModified");
        paper1.getEvaluationList().add(evaluation1);
        paper1.getEvaluationList().add(evaluation2);
        paper2.getEvaluationList().add(evaluation3);
        List<Paper> papers = new ArrayList<>();
        papers.add(paper1);
        papers.add(paper2);
        int res = service.modifyStageJudge2(papers);
        assertEquals(5, res);
    }

    @Test
    void getAllReplies() {
        ConferenceService service = new ConferenceService(evaluationRepository,userRepository, authorityRepository, conferenceRepository, inviteRepository, authorRepository, paperRepository);
        if (this.paperRepository.findByHeadline("Test0003") == null) {
            this.paperRepository.save(new Paper("Test0003", "t1", "t2", "t3", "t4", (long)100861));
        }
        Paper paper = this.paperRepository.findByHeadline("Test0003");
        Post post1 = new Post("Test A", (long)20200610, "user1", (long)2001, "test1");
        Post post2 = new Post("Test B", (long)20200611, "user2", (long)2002, "test2");
        Post post3 = new Post("Test C", (long)20200612, "user3", (long)2002, "test3");
        paper.getPostList().add(post1);
        paper.getPostList().add(post2);
        paper.getPostList().add(post3);
        this.paperRepository.save(paper);
        paper = this.paperRepository.findByHeadline("Test0003");
        GetAllRepliesRequest getAllRepliesRequest = new GetAllRepliesRequest();
        getAllRepliesRequest.setPaperId(paper.getPaperId());
        getAllRepliesRequest.setPostId(post2.getReplyId());
        List<Post> posts = service.getAllReplies(getAllRepliesRequest);
        assertEquals(2, posts.size());
        int count = 0;
        for (Post item : posts) {
            if (item.getTitle().equals(post2.getTitle())) {
                assertEquals(post2.getContent(), item.getContent());
                assertEquals(post2.getSendTime(), item.getSendTime());
                assertEquals(post2.getUserName(), item.getUserName());
                count++;
            }
            if (item.getTitle().equals(post3.getTitle())) {
                assertEquals(post3.getContent(), item.getContent());
                assertEquals(post3.getSendTime(), item.getSendTime());
                assertEquals(post3.getUserName(), item.getUserName());
                count++;
            }
        }
        assertEquals(2, count);
    }

    @Test
    void sendRebuttal() {
        ConferenceService service = new ConferenceService(evaluationRepository,userRepository, authorityRepository, conferenceRepository, inviteRepository, authorRepository, paperRepository);
        if (this.paperRepository.findByHeadline("Test0002") == null) {
            this.paperRepository.save(new Paper("Test0002", "t1", "t2", "t3", "t4", (long)10086));
        }
        Paper paper = this.paperRepository.findByHeadline("Test0002");
        SendRebuttalRequest sendRebuttalRequest = new SendRebuttalRequest();
        sendRebuttalRequest.setPaperId(paper.getPaperId());
        sendRebuttalRequest.setRebuttal("test2");
        String res = service.sendRebuttal(sendRebuttalRequest);
        paper = this.paperRepository.findByHeadline("Test0002");
        assertEquals("success", res);
        assertEquals("test2", paper.getRebuttal());
    }

    @Test
    void setFirstRelease() {
        AuthService authService = new AuthService(userRepository, authorityRepository, conferenceRepository, inviteRepository, authorRepository, paperRepository);
        ConferenceService service = new ConferenceService(evaluationRepository, userRepository, authorityRepository, conferenceRepository, inviteRepository, authorRepository, paperRepository);
        if (this.userRepository.findByUsername(request9.getUsername()) == null) {
            authService.register(request9);
        }
        User user1 = this.userRepository.findByUsername(request11.getUsername());
        Conference conference1 = new Conference(user1, "BN30", "FN30", "date30", "add30", "DDL30", "date30", "pending", normalTopics);
        if (this.conferenceRepository.findByfullName(conference1.getFullName()) == null) {
            this.conferenceRepository.save(conference1);
        }
        Evaluation evaluation1 = new Evaluation("Test_a", 2, 2, "a", "aa");
        Evaluation evaluation2 = new Evaluation("Test_b", 1, 1, "b", "bb");
        Evaluation evaluation3 = new Evaluation("Test_c", -1, -1, "c", "cc");
        this.evaluationRepository.save(evaluation1);
        this.evaluationRepository.save(evaluation2);
        this.evaluationRepository.save(evaluation3);
        Paper paper1 = new Paper("Test0007", "t7", "t7", "t7", "t7", (long)10086);
        Paper paper2 = new Paper("Test0008", "t8", "t8", "t8", "t8", (long)10086);
        paper1.getEvaluationList().add(evaluation1);
        paper1.getEvaluationList().add(evaluation2);
        paper2.getEvaluationList().add(evaluation3);
        if (this.paperRepository.findByHeadline("Test0001") == null) {
            this.paperRepository.save(paper1);
        }
        if (this.paperRepository.findByHeadline("Test0002") == null) {
            this.paperRepository.save(paper2);
        }
        conference1.getPaperList().add(paper1);
        conference1.getPaperList().add(paper2);
        this.conferenceRepository.save(conference1);
        conference1 = this.conferenceRepository.findByfullName("FN30");
        ReleaseRequest releaseRequest = new ReleaseRequest();
        releaseRequest.setConferenceId(conference1.getConferenceId());
        String res = service.setFirstRelease(releaseRequest);
        assertEquals("success", res);
        conference1 = this.conferenceRepository.findByfullName("FN30");
        assertEquals("firstReleased", conference1.getEmploymentStage());
        List<Paper> papers = conference1.getPaperList();
        for (Paper paper : papers) {
            for (Evaluation evaluation : paper.getEvaluationList()) {
                assertEquals("canFinalModify", evaluation.getModifyStage());
            }
        }
    }

    @Test
    void setFinalRelease() {
        AuthService authService = new AuthService(userRepository, authorityRepository, conferenceRepository, inviteRepository, authorRepository, paperRepository);
        ConferenceService service = new ConferenceService(evaluationRepository, userRepository, authorityRepository, conferenceRepository, inviteRepository, authorRepository, paperRepository);
        if (this.userRepository.findByUsername(request9.getUsername()) == null) {
            authService.register(request9);
        }
        User user1 = this.userRepository.findByUsername(request11.getUsername());
        Conference conference1 = new Conference(user1, "BN31", "FN31", "date31", "add31", "DDL31", "date31", "pending", normalTopics);
        if (this.conferenceRepository.findByfullName(conference1.getFullName()) == null) {
            this.conferenceRepository.save(conference1);
        }
        conference1 = this.conferenceRepository.findByfullName("FN31");
        ReleaseRequest releaseRequest = new ReleaseRequest();
        releaseRequest.setConferenceId(conference1.getConferenceId());
        String res = service.setFinalRelease(releaseRequest);
        assertEquals("success", res);
        conference1 = this.conferenceRepository.findByfullName("FN31");
        assertEquals("finalReleased", conference1.getEmploymentStage());
    }
}
