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
public class InquireServiceTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthorityRepository authorityRepository;
    @Autowired
    private ConferenceRepository conferenceRepository;
    @Autowired
    private InviteRepository inviteRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private EvaluationRepository evaluationRepository;
    @Autowired
    private PaperRepository paperRepository;
    private Set <String> authorities = new HashSet<>();
    private List<String> normalTopics = new ArrayList<>();
    private final RegisterRequest request1 = new RegisterRequest("abc1","abc11","abc1@fudan.edu.cn","1abc","1cba", authorities);
    private final RegisterRequest request2 = new RegisterRequest("abc2","abc22","abc2@fudan.edu.cn","2abc","2cba", authorities);
    private final RegisterRequest request3 = new RegisterRequest("abc3","abc33","abc3@fudan.edu.cn","3abc","3cba", authorities);
    private final RegisterRequest request4 = new RegisterRequest("abc4","abc44","abc4@fudan.edu.cn","4abc","3cba", authorities);//注意，这条为了测试相同realname
    private final RegisterRequest request7 = new RegisterRequest("abc7","abc77","abc7@fudan.edu.cn","7abc","7cba", authorities);
    private final RegisterRequest request8 = new RegisterRequest("abc8","abc88","abc8@fudan.edu.cn","8abc","8cba", authorities);
    private final RegisterRequest request11 = new RegisterRequest("abc11","abc1111","abc11@fudan.edu.cn","11abc","11cba", authorities);

    @Test
    void findUserByRealname() {
        AuthService service = new AuthService(userRepository, authorityRepository, conferenceRepository, inviteRepository, authorRepository, paperRepository);
        //测试两个realname相同的用户是否都可以被正确搜索到
        service.register(request3);
        service.register(request4);
        String realname = request3.getRealname();
        assertEquals(realname, request4.getRealname());
        Set<User> users = this.userRepository.findByRealname(realname);
        ArrayList<String> usernames = new ArrayList<>(2);
        for (User item : users) {
            usernames.add(item.getUsername());
        }
        assertTrue(usernames.contains(request3.getUsername()) && usernames.contains(request4.getUsername()));
    }

    @Test
    void findMeetingByIdentity(){
        AuthService authService = new AuthService(userRepository, authorityRepository, conferenceRepository, inviteRepository, authorRepository, paperRepository);
        InquireService service = new InquireService(evaluationRepository, userRepository, authorityRepository, conferenceRepository, inviteRepository, authorRepository, paperRepository);
        if (this.userRepository.findByUsername(request1.getUsername()) == null) {
            authService.register(request1);
        }
        if (this.userRepository.findByUsername(request2.getUsername()) == null) {
            authService.register(request2);
        }
        if (this.userRepository.findByUsername(request3.getUsername()) == null) {
            authService.register(request3);
        }
        if (this.userRepository.findByUsername(request4.getUsername()) == null) {
            authService.register(request4);
        }
        Conference conference1 = new Conference(this.userRepository.findByUsername(request1.getUsername()), "BN1", "FN1", "date1", "add1", "DDL1", "date11", "pending", normalTopics);
        Conference conference2 = new Conference(this.userRepository.findByUsername(request2.getUsername()), "BN2", "FN2", "date2", "add2", "DDL2", "date22", "pending", normalTopics);
        Conference conference3 = new Conference(this.userRepository.findByUsername(request3.getUsername()), "BN3", "FN3", "date3", "add3", "DDL3", "date33", "accept", normalTopics);
        Conference conference4 = new Conference(this.userRepository.findByUsername(request4.getUsername()), "BN4", "FN4", "date4", "add4", "DDL4", "date44", "accept", normalTopics);
        conferenceRepository.save(conference1);
        conferenceRepository.save(conference2);
        conferenceRepository.save(conference3);
        conferenceRepository.save(conference4);
        //测试当identity不合法时是否返回null
        FindMeetingByIdentityRequest meeting1 = new FindMeetingByIdentityRequest(request1.getUsername());
        meeting1.setIdentity("wrongIdentity");
        assertNull(service.findMeetingByIdentity(meeting1));
        //测试当identity为admin时是否返回审核状态为pending的会议
        FindMeetingByIdentityRequest meeting2 = new FindMeetingByIdentityRequest(request1.getUsername());
        meeting2.setIdentity("admin");
        ArrayList<String> meeting2names = new ArrayList<>(2);
        for (Conference item : service.findMeetingByIdentity(meeting2)) {
            meeting2names.add(item.getFullName());
        }
        assertTrue(meeting2names.contains(conference1.getFullName()) && meeting2names.contains(conference2.getFullName()));
        //测试当identity为showAllMeetings时是否返回所有通过审核的会议
        FindMeetingByIdentityRequest meeting3 = new FindMeetingByIdentityRequest(request1.getUsername());
        meeting3.setIdentity("showAllMeetings");
        ArrayList<String> meeting3names = new ArrayList<>(2);
        for (Conference item : service.findMeetingByIdentity(meeting3)) {
            meeting3names.add(item.getFullName());
        }
        assertTrue(meeting3names.contains(conference3.getFullName()) && meeting3names.contains(conference4.getFullName()));
        //测试当identity为creator时是否返回所有该用户申请的会议
        FindMeetingByIdentityRequest meeting4 = new FindMeetingByIdentityRequest(request1.getUsername());
        meeting4.setIdentity("creator");
        ArrayList<String> meeting4names = new ArrayList<>(2);
        for (Conference item : service.findMeetingByIdentity(meeting4)) {
            meeting4names.add(item.getFullName());
        }
        assertTrue(meeting4names.contains(conference1.getFullName()));
        //测试当identity为contributor时是否返回所有该用户已投稿的会议
        FindMeetingByIdentityRequest meeting5 = new FindMeetingByIdentityRequest(request2.getUsername());
        meeting5.setIdentity("contributor");
        ArrayList<String> meeting5names = new ArrayList<>(2);
        User user2 = this.userRepository.findByUsername(request2.getUsername());
        user2.getContributeConference().add(conference1.getFullName());
        this.userRepository.save(user2);
        for (Conference item : service.findMeetingByIdentity(meeting5)) {
            meeting5names.add(item.getFullName());
        }
        assertTrue(meeting5names.contains(conference1.getFullName()));
        //测试当identity为reviewer时是否返回所有该用户被邀请审稿的会议
        FindMeetingByIdentityRequest meeting6 = new FindMeetingByIdentityRequest(request2.getUsername());
        meeting6.setIdentity("reviewer");
        ArrayList<String> meeting6names = new ArrayList<>(2);
        user2.getReviewConference().add(conference3.getFullName());
        this.userRepository.save(user2);
        for (Conference item : service.findMeetingByIdentity(meeting6)) {
            meeting6names.add(item.getFullName());
        }
        assertTrue(meeting6names.contains(conference3.getFullName()));
    }

    @Test
    void findInviteByIdentity(){
        AuthService authService = new AuthService(userRepository, authorityRepository, conferenceRepository, inviteRepository, authorRepository, paperRepository);
        InquireService service = new InquireService(evaluationRepository, userRepository, authorityRepository, conferenceRepository, inviteRepository, authorRepository, paperRepository);
        if (this.userRepository.findByUsername(request7.getUsername()) == null) {
            authService.register(request7);
        }
        if (this.userRepository.findByUsername(request8.getUsername()) == null) {
            authService.register(request8);
        }
        Conference conference1 = new Conference(this.userRepository.findByUsername(request7.getUsername()), "BN7", "FN7", "date7", "add7", "DDL7", "date77", "pending", normalTopics);
        conferenceRepository.save(conference1);
        Invite invite1 = new Invite(request8.getUsername(),this.userRepository.findByUsername(request8.getUsername()), conference1, conference1.getChairMan().getUsername(),"GN7","EM7",conference1.getFullName(),"ToBeConfirmed", normalTopics);
        this.inviteRepository.save(invite1);
        //测试当identity不合法时是否返回null
        FindInviteByIdentityRequest findInviteByIdentityRequest1 = new FindInviteByIdentityRequest(request7.getUsername());
        findInviteByIdentityRequest1.setIdentity("wrongIdentity");
        assertNull(service.findInviteByIdentity(findInviteByIdentityRequest1));
        //测试当identity为creator时是否返回该用户邀请他人的信息
        FindInviteByIdentityRequest findInviteByIdentityRequest2 = new FindInviteByIdentityRequest(request7.getUsername());
        findInviteByIdentityRequest2.setIdentity("creator");
        Set<Invite> invites1 = service.findInviteByIdentity(findInviteByIdentityRequest2);
        ArrayList<String> conferenceNames1 = new ArrayList<>(2);
        for (Invite item : invites1) {
            conferenceNames1.add(item.getConference().getFullName());
        }
        assertTrue(conferenceNames1.contains(conference1.getFullName()));
        //测试当identity为reviewer时是否返回该用户被邀请信息
        FindInviteByIdentityRequest findInviteByIdentityRequest3 = new FindInviteByIdentityRequest(request8.getUsername());
        findInviteByIdentityRequest3.setIdentity("reviewer");
        Set<Invite> invites2 = service.findInviteByIdentity(findInviteByIdentityRequest3);
        ArrayList<String> conferenceNames2 = new ArrayList<>(2);
        for (Invite item : invites2) {
            conferenceNames2.add(item.getConference().getFullName());
        }
        assertTrue(conferenceNames2.contains(conference1.getFullName()));
    }

    @Test
    void getTopicByMeetingID() {
        AuthService authService = new AuthService(userRepository, authorityRepository, conferenceRepository, inviteRepository, authorRepository, paperRepository);
        InquireService service = new InquireService(evaluationRepository, userRepository, authorityRepository, conferenceRepository, inviteRepository, authorRepository, paperRepository);
        if (this.userRepository.findByUsername(request11.getUsername()) == null) {
            authService.register(request11);
        }
        User user = this.userRepository.findByUsername(request11.getUsername());
        List<String> topics = new ArrayList<>();
        topics.add("test1");
        topics.add("test2");
        Conference conference = new Conference(user, "BN7", "FN7", "date7", "add7", "DDL7", "date77", "pending", topics);
        if (this.conferenceRepository.findByfullName(conference.getFullName()) == null) {
            this.conferenceRepository.save(conference);
        }
        GetTopicByMeetingIDRequest getTopicByMeetingIDRequest = new GetTopicByMeetingIDRequest();
        getTopicByMeetingIDRequest.setMeetingID(conference.getConferenceId());
        getTopicByMeetingIDRequest.setMeetingName(conference.getFullName());
        Set<String> topicSet = service.getTopicByMeetingID(getTopicByMeetingIDRequest);
        boolean temp = true;
        for (String item : topics) {
            if (!topicSet.contains(item)) {
                temp = false;
            }
        }
        assertTrue(temp);
    }

    @Test
    void getContribute() {
        AuthService authService = new AuthService(userRepository, authorityRepository, conferenceRepository, inviteRepository, authorRepository, paperRepository);
        InquireService service = new InquireService(evaluationRepository, userRepository, authorityRepository, conferenceRepository, inviteRepository, authorRepository, paperRepository);
        if (this.userRepository.findByUsername(request11.getUsername()) == null) {
            authService.register(request11);
        }
        User user = this.userRepository.findByUsername(request11.getUsername());
        Conference conference = new Conference(user, "BN8", "FN8", "date8", "add8", "DDL8", "date88", "pending", normalTopics);
        if (this.conferenceRepository.findByfullName(conference.getFullName()) == null) {
            this.conferenceRepository.save(conference);
        }
        Paper paper = new Paper("PP7", "PP7", "PP7", "PP7", "PP8", conference.getConferenceId());
        if (this.paperRepository.findByHeadline("PP7") == null) {
            this.paperRepository.save(paper);
        }
        GetContributRequest getContributRequest = new GetContributRequest();
        getContributRequest.setMeetingName(conference.getFullName());
        getContributRequest.setContributionID(paper.getPaperId());
        Paper temp = service.getContribute(getContributRequest);
        assertEquals(paper.getHeadline(), temp.getHeadline());
    }

    @Test
    void getConferenceByMeetingID() {
        AuthService authService = new AuthService(userRepository, authorityRepository, conferenceRepository, inviteRepository, authorRepository, paperRepository);
        InquireService service = new InquireService(evaluationRepository, userRepository, authorityRepository, conferenceRepository, inviteRepository, authorRepository, paperRepository);
        if (this.userRepository.findByUsername(request11.getUsername()) == null) {
            authService.register(request11);
        }
        User user = this.userRepository.findByUsername(request11.getUsername());
        Conference conference = new Conference(user, "BN14", "FN14", "date14", "add14", "DDL14", "date1414", "pending", normalTopics);
        if (this.conferenceRepository.findByfullName(conference.getFullName()) == null) {
            this.conferenceRepository.save(conference);
        }
        GetConferenceByMeetingIDRequest getConferenceByMeetingIDRequest = new GetConferenceByMeetingIDRequest();
        getConferenceByMeetingIDRequest.setMeetingID(conference.getConferenceId());
        getConferenceByMeetingIDRequest.setMeetingName(conference.getFullName());
        Conference temp = service.getConferenceByMeetingID(getConferenceByMeetingIDRequest);
        assertEquals(conference.getFullName(), temp.getFullName());
    }

    @Test
    void getContributionState() {
        AuthService authService = new AuthService(userRepository, authorityRepository, conferenceRepository, inviteRepository, authorRepository, paperRepository);
        InquireService service = new InquireService(evaluationRepository, userRepository, authorityRepository, conferenceRepository, inviteRepository, authorRepository, paperRepository);
        if (this.userRepository.findByUsername(request11.getUsername()) == null) {
            authService.register(request11);
        }
        User user = this.userRepository.findByUsername(request11.getUsername());
        Conference conference1 = new Conference(user, "BN17", "FN17", "date17", "add17", "DDL17", "date1717", "pending", normalTopics);
        Paper paper1 = new Paper("test1", "dig1", "path1", user.getUsername(), "stat1", conference1.getConferenceId());
        Paper paper2 = new Paper("test2", "dig2", "path2", user.getUsername(), "stat2", conference1.getConferenceId());
        Paper paper3 = new Paper("test3", "dig3", "path3", "TEST", "stat3", conference1.getConferenceId());
        if (this.paperRepository.findByHeadline(paper1.getHeadline()) == null) {
            this.paperRepository.save(paper1);
        }
        if (this.paperRepository.findByHeadline(paper2.getHeadline()) == null) {
            this.paperRepository.save(paper2);
        }
        if (this.paperRepository.findByHeadline(paper3.getHeadline()) == null) {
            this.paperRepository.save(paper3);
        }
        conference1.getPaperList().add(paper1);
        conference1.getPaperList().add(paper2);
        conference1.getPaperList().add(paper3);
        if (this.conferenceRepository.findByfullName(conference1.getFullName()) == null) {
            this.conferenceRepository.save(conference1);
        }
        GetContributionStateRequest getContributionStateRequest = new GetContributionStateRequest();
        getContributionStateRequest.setMeetingName(conference1.getFullName());
        getContributionStateRequest.setUsername(request11.getUsername());
        List<Paper> results = service.getContributionState(getContributionStateRequest);
        ArrayList<String> paperNames = new ArrayList<>();
        for (Paper item : results) {
            paperNames.add(item.getHeadline());
        }
        assertTrue(paperNames.contains(paper1.getHeadline()) && paperNames.contains(paper2.getHeadline()) && (!paperNames.contains(paper3.getHeadline())));
    }

    @Test
    void getReviewList() {
        AuthService authService = new AuthService(userRepository, authorityRepository, conferenceRepository, inviteRepository, authorRepository, paperRepository);
        InquireService service = new InquireService(evaluationRepository, userRepository, authorityRepository, conferenceRepository, inviteRepository, authorRepository, paperRepository);
        if (this.userRepository.findByUsername(request11.getUsername()) == null) {
            authService.register(request11);
        }
        User user1 = this.userRepository.findByUsername(request11.getUsername());
        Conference conference = new Conference(user1, "BN19", "FN19", "date19", "add19", "DDL19", "date19", "pending", normalTopics);
        if (this.conferenceRepository.findByfullName(conference.getFullName()) == null) {
            this.conferenceRepository.save(conference);
        }
        Paper paper1 = new Paper("test11", "dig11", "path11", user1.getUsername(), "stat11", conference.getConferenceId());
        Paper paper2 = new Paper("test12", "dig12", "path12", user1.getUsername(), "stat12", conference.getConferenceId());
        Paper paper3 = new Paper("test13", "dig13", "path13", user1.getUsername(), "stat13", conference.getConferenceId());

        List<String> temp = new ArrayList<>();
        temp.add(user1.getUsername());
        temp.add("TEST1");
        temp.add("TEST2");
        paper1.setReviewerNameList(temp);
        paper2.setReviewerNameList(temp);

        List<String> temp2 = new ArrayList<>();
        temp2.add("TEST1");
        temp2.add("TEST2");
        temp2.add("TEST3");
        paper3.setReviewerNameList(temp2);

        this.paperRepository.save(paper1);
        this.paperRepository.save(paper2);
        this.paperRepository.save(paper3);
        ArrayList<Paper> papers = new ArrayList<>();
        papers.add(paper1);
        papers.add(paper2);
        papers.add(paper3);
        conference.setPaperList(papers);
        this.conferenceRepository.save(conference);
        GetReviewListRequest getReviewListRequest = new GetReviewListRequest();
        getReviewListRequest.setUsername(user1.getUsername());
        getReviewListRequest.setMeetingID(conference.getConferenceId());
        getReviewListRequest.setMeetingName(conference.getFullName());

        List<Paper> results = service.getReviewList(getReviewListRequest);
        List<String> myPapers = new ArrayList<>();
        for (Paper item : results) {
            if (item.getReviewerNameList().contains(user1.getUsername())) {
                myPapers.add(item.getHeadline());
            }
        }
        assertEquals(2, myPapers.size());
        assertTrue(myPapers.contains(paper1.getHeadline()));
        assertTrue(myPapers.contains(paper2.getHeadline()));
        assertFalse(myPapers.contains(paper3.getHeadline()));
    }

    @Test
    void getReviewResult() {
        AuthService authService = new AuthService(userRepository, authorityRepository, conferenceRepository, inviteRepository, authorRepository, paperRepository);
        InquireService service = new InquireService(evaluationRepository, userRepository, authorityRepository, conferenceRepository, inviteRepository, authorRepository, paperRepository);
        if (this.userRepository.findByUsername(request11.getUsername()) == null) {
            authService.register(request11);
        }
        User user = this.userRepository.findByUsername(request11.getUsername());
        Conference conference = new Conference(user, "BN39", "FN39", "date39", "add39", "DDL39", "date39", "pending", normalTopics);
        conference.setEmploymentStage("firstReleased");
        if (this.conferenceRepository.findByfullName(conference.getFullName()) == null) {
            this.conferenceRepository.save(conference);
        }
        conference = this.conferenceRepository.findByfullName("FN39");
        Paper paper = new Paper("test4", "dig4", "path4", user.getUsername(), "stat4", (long)4321);
        paper.setConferenceId(conference.getConferenceId());
        Set<Evaluation> evaluations = new HashSet<>();
        paper.setEvaluationList(evaluations);
        if (this.paperRepository.findByHeadline(paper.getHeadline()) == null) {
            this.paperRepository.save(paper);
        }
        GetReviewResultRequest getReviewResultRequest = new GetReviewResultRequest();
        getReviewResultRequest.setConbutrionID(paper.getPaperId());
        Set<Evaluation> results = service.getReviewResult(getReviewResultRequest);
        assertTrue(results.isEmpty());
    }

    @Test
    void firstRelease() {
        AuthService authService = new AuthService(userRepository, authorityRepository, conferenceRepository, inviteRepository, authorRepository, paperRepository);
        InquireService service = new InquireService(evaluationRepository, userRepository, authorityRepository, conferenceRepository, inviteRepository, authorRepository, paperRepository);
        if (this.userRepository.findByUsername(request11.getUsername()) == null) {
            authService.register(request11);
        }
        User user = this.userRepository.findByUsername(request11.getUsername());
        Conference conference = new Conference(user, "BN40", "FN40", "date40", "add40", "DDL40", "date40", "pending", normalTopics);
        conference.setEmploymentStage("firstReleased");
        if (this.conferenceRepository.findByfullName(conference.getFullName()) == null) {
            this.conferenceRepository.save(conference);
        }
        conference = this.conferenceRepository.findByfullName("FN40");
        Paper paper = new Paper("test14", "dig14", "path14", user.getUsername(), "stat14", (long)4321);
        paper.setConferenceId(conference.getConferenceId());
        Evaluation evaluation1 = new Evaluation("TTT001", 2, 2, "a", "aa");
        Evaluation evaluation2 = new Evaluation("TTT002", -1, -1, "b", "bb");
        Evaluation evaluation3 = new Evaluation("TTT003", 2, 2, "c", "cc");
        Evaluation evaluation4 = new Evaluation("TTT004", -1, -1, "d", "dd");
        this.evaluationRepository.save(evaluation1);
        this.evaluationRepository.save(evaluation2);
        this.evaluationRepository.save(evaluation3);
        this.evaluationRepository.save(evaluation4);
        paper.getEvaluationList().add(evaluation1);
        paper.getEvaluationList().add(evaluation2);
        paper.getEvaluationBackup().add(evaluation3);
        paper.getEvaluationBackup().add(evaluation4);
        if (this.paperRepository.findByHeadline(paper.getHeadline()) == null) {
            this.paperRepository.save(paper);
        }
        conference.getPaperList().add(paper);
        this.conferenceRepository.save(conference);
        conference = this.conferenceRepository.findByfullName("FN40");
        ReleaseRequest releaseRequest = new ReleaseRequest();
        releaseRequest.setConferenceId(conference.getConferenceId());
        Set<Paper> papers = service.firstRelease(releaseRequest);
        conference = this.conferenceRepository.findByfullName("FN40");

        boolean test = true;
        for (Paper item1 : papers) {
            for (Evaluation item2 : item1.getEvaluationBackup()) {
                if (item2.getScore() == -1 || item2.getScore() == -2) {
                    test = false;
                    break;
                }
            }
        }
        assertTrue(test);

        for (Paper item1 : conference.getPaperList()) {
            for (Evaluation item2 : item1.getEvaluationList()) {
                if (item2.getScore() != -1 && item2.getScore() != -2) {
                    assertEquals("finalModified", item2.getModifyStage());
                }
            }
        }
    }

    @Test
    void finalRelease() {
        AuthService authService = new AuthService(userRepository, authorityRepository, conferenceRepository, inviteRepository, authorRepository, paperRepository);
        InquireService service = new InquireService(evaluationRepository, userRepository, authorityRepository, conferenceRepository, inviteRepository, authorRepository, paperRepository);
        if (this.userRepository.findByUsername(request11.getUsername()) == null) {
            authService.register(request11);
        }
        User user = this.userRepository.findByUsername(request11.getUsername());
        Conference conference = new Conference(user, "BN42", "FN42", "date42", "add42", "DDL42", "date42", "pending", normalTopics);
        conference.setEmploymentStage("firstReleased");
        if (this.conferenceRepository.findByfullName(conference.getFullName()) == null) {
            this.conferenceRepository.save(conference);
        }
        conference = this.conferenceRepository.findByfullName("FN42");
        Paper paper = new Paper("test17", "dig17", "path17", user.getUsername(), "stat17", (long)432105);
        paper.setConferenceId(conference.getConferenceId());
        Evaluation evaluation1 = new Evaluation("TTT005", 2, 2, "a", "aa");
        Evaluation evaluation2 = new Evaluation("TTT006", -1, -1, "b", "bb");
        Evaluation evaluation3 = new Evaluation("TTT007", 2, 2, "c", "cc");
        Evaluation evaluation4 = new Evaluation("TTT008", -1, -1, "d", "dd");
        this.evaluationRepository.save(evaluation1);
        this.evaluationRepository.save(evaluation2);
        this.evaluationRepository.save(evaluation3);
        this.evaluationRepository.save(evaluation4);
        paper.getEvaluationList().add(evaluation1);
        paper.getEvaluationList().add(evaluation2);
        paper.getEvaluationBackup().add(evaluation3);
        paper.getEvaluationBackup().add(evaluation4);
        if (this.paperRepository.findByHeadline(paper.getHeadline()) == null) {
            this.paperRepository.save(paper);
        }
        conference.getPaperList().add(paper);
        this.conferenceRepository.save(conference);
        conference = this.conferenceRepository.findByfullName("FN42");
        ReleaseRequest releaseRequest = new ReleaseRequest();
        releaseRequest.setConferenceId(conference.getConferenceId());
        Set<Paper> papers = service.finalRelease(releaseRequest);

        boolean test = false;
        for (Paper item1 : papers) {
            for (Evaluation item2 : item1.getEvaluationBackup()) {
                if (item2.getScore() != -1 && item2.getScore() != -2) {
                    test = true;
                    break;
                }
            }
        }
        assertTrue(test);
    }

    @Test
    void getOldResult() {
        InquireService service = new InquireService(evaluationRepository, userRepository, authorityRepository, conferenceRepository, inviteRepository, authorRepository, paperRepository);
        Paper paper1 = new Paper("Test0005", "t1", "t2", "t3", "t4", (long)100868);
        Evaluation evaluation1 = new Evaluation("high", 2, 2, "a", "aa");
        Evaluation evaluation2 = new Evaluation("low", -1, -1, "b", "bb");
        evaluation1.setPcmemberName("test1");
        evaluation2.setPcmemberName("test2");
        this.evaluationRepository.save(evaluation1);
        this.evaluationRepository.save(evaluation2);
        paper1.getEvaluationList().add(evaluation1);
        paper1.getEvaluationList().add(evaluation2);
        this.paperRepository.save(paper1);
        paper1 = this.paperRepository.findByHeadline("Test0005");
        GetOldResultRequest getOldResultRequest = new GetOldResultRequest();
        getOldResultRequest.setPaperId(paper1.getPaperId());
        getOldResultRequest.setPcMemberName("test1");
        Evaluation res = service.getOldResult(getOldResultRequest);
        assertEquals("high", res.getReviews());
        assertEquals(2, res.getScore());
        assertEquals(2, res.getConfidence());
        assertEquals("a", res.getTitle());
        assertEquals("aa", res.getDigest());
    }
}
