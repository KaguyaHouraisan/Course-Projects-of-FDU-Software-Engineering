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
public class InviteServiceTest {
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
    private final RegisterRequest request9 = new RegisterRequest("abc9","abc99","abc9@fudan.edu.cn","9abc","9cba", authorities);
    private final RegisterRequest request10 = new RegisterRequest("abc10","abc1010","abc10@fudan.edu.cn","10abc","10cba", authorities);
    private final RegisterRequest request11 = new RegisterRequest("abc11","abc1111","abc11@fudan.edu.cn","11abc","11cba", authorities);

    @Test
    void updateInvite(){
        AuthService authService = new AuthService(userRepository, authorityRepository, conferenceRepository, inviteRepository, authorRepository, paperRepository);
        InviteService service = new InviteService(userRepository, authorityRepository, conferenceRepository, inviteRepository, authorRepository, paperRepository);
        if (this.userRepository.findByUsername(request9.getUsername()) == null) {
            authService.register(request9);
        }
        if (this.userRepository.findByUsername(request10.getUsername()) == null) {
            authService.register(request10);
        }
        if (this.userRepository.findByUsername(request11.getUsername()) == null) {
            authService.register(request11);
        }
        User user1 = this.userRepository.findByUsername(request9.getUsername());
        User user2 = this.userRepository.findByUsername(request10.getUsername());
        User user3 = this.userRepository.findByUsername(request11.getUsername());
        Conference conference1 = new Conference(user1, "BN8", "FN8", "date8", "add8", "DDL8", "date88", "accept", normalTopics);
        Conference conference2 = new Conference(user1, "BN9", "FN9", "date9", "add9", "DDL9", "date99", "accept", normalTopics);
        this.conferenceRepository.save(conference1);
        this.conferenceRepository.save(conference2);
        Invite invite1 = new Invite(user2.getUsername(), user2, conference1, conference1.getChairMan().getUsername(), "GN8", "EM8", conference1.getFullName(), "ToBeConfirmed", normalTopics);
        Invite invite2 = new Invite(user3.getUsername(), user3, conference2, conference2.getChairMan().getUsername(), "GN9", "EM9", conference2.getFullName(), "ToBeConfirmed", normalTopics);
        this.inviteRepository.save(invite1);
        this.inviteRepository.save(invite2);
        //测试当用户拒绝邀请时是否正确更新
        UpdateInviteRequest updateInviteRequest1 = new UpdateInviteRequest(user2.getUsername());
        updateInviteRequest1.setChoice("rejected");
        updateInviteRequest1.setConferenceName(conference1.getFullName());
        service.updateInvite(updateInviteRequest1);
        Invite invite3 = new Invite();
        Set<Invite> tempInvites1 = this.inviteRepository.findByConferenceName(conference1.getFullName());
        for (Invite item : tempInvites1) {
            if (item.getPCmemberName().equals(user2.getUsername())) {
                invite3 = item;
            }
        }
        assertNotNull(invite3);
        assertEquals("rejected", invite3.getStage());
    }

    @Test
    void contribute() {
        AuthService authService = new AuthService(userRepository, authorityRepository, conferenceRepository, inviteRepository, authorRepository, paperRepository);
        ConferenceService conferenceService = new ConferenceService(evaluationRepository,userRepository, authorityRepository, conferenceRepository, inviteRepository, authorRepository, paperRepository);
        InviteService service = new InviteService(userRepository, authorityRepository, conferenceRepository, inviteRepository, authorRepository, paperRepository);
        if (this.userRepository.findByUsername(request11.getUsername()) == null) {
            authService.register(request11);
        }
        User user1 = this.userRepository.findByUsername(request11.getUsername());
        StartMeetingRequest startMeetingRequest = new StartMeetingRequest(user1.getUsername(), "BN11", "FN11", "add11", "2020-04-11", "2020-04-12", "2020-04-13", "pending", normalTopics);
        if (this.conferenceRepository.findByfullName(startMeetingRequest.getFullName()) == null) {
            conferenceService.startMeeting(startMeetingRequest);
        }
        ContributeRequest contributeRequest = new ContributeRequest(request11.getUsername(), startMeetingRequest.getChairMan(), "RN11", "EM11", startMeetingRequest.getFullName());
        service.contribute(contributeRequest);
        Conference conference = this.conferenceRepository.findByfullName(startMeetingRequest.getFullName());
        Invite invite = null;
        Set<Invite> tempInvites = this.inviteRepository.findByConferenceName(conference.getFullName());
        for (Invite item : tempInvites) {
            if (item.getPCmemberName().equals(user1.getUsername())) {
                invite = item;
            }
        }
        assertNotNull(invite);
    }
    @Test
    void inviteOperationMudle(){
        AuthService authService = new AuthService(userRepository, authorityRepository, conferenceRepository, inviteRepository, authorRepository, paperRepository);
        ConferenceService conferenceService = new ConferenceService(evaluationRepository,userRepository, authorityRepository, conferenceRepository, inviteRepository, authorRepository, paperRepository);
        InviteService service = new InviteService(userRepository, authorityRepository, conferenceRepository, inviteRepository, authorRepository, paperRepository);
        if (this.userRepository.findByUsername(request11.getUsername()) == null) {
            authService.register(request11);
        }
        if (this.userRepository.findByUsername(request10.getUsername()) == null) {
            authService.register(request10);
        }
        User user1 = this.userRepository.findByUsername(request11.getUsername());
        User user2 = this.userRepository.findByUsername(request10.getUsername());
        Conference conference = new Conference(user1, "BN8", "FN8", "date8", "add8", "DDL8", "date88", "accept", normalTopics);
        this.conferenceRepository.save(conference);
        ContributeRequest contributeRequest = new ContributeRequest(request10.getUsername(), conference.getChairMan().getUsername(), "RN11", "EM11", conference.getFullName());
        service.contribute(contributeRequest);
        UpdateInviteRequest updateInviteRequest1 = new UpdateInviteRequest(user2.getUsername());
        updateInviteRequest1.setChoice("rejected");
        updateInviteRequest1.setConferenceName(conference.getFullName());
        service.updateInvite(updateInviteRequest1);
        this.conferenceRepository.save(conference);
        Invite invite = null;
        Set<Invite> tempInvites = this.inviteRepository.findByConferenceName(conference.getFullName());
        for (Invite item : tempInvites) {
            if (item.getPCmemberName().equals(user2.getUsername())) {
                invite = item;
            }
        }
        assertNotNull(invite);
        assertEquals("rejected", invite.getStage());
    }
}
