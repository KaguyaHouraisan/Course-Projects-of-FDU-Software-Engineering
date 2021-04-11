package fudan.se.lab2.service;

import fudan.se.lab2.controller.request.ContributeRequest;
import fudan.se.lab2.controller.request.UpdateInviteRequest;
import fudan.se.lab2.domain.*;
import fudan.se.lab2.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * @author LBW
 */
@Service
public class InviteService {
    private UserRepository userRepository;
    private ConferenceRepository conferenceRepository;
    private InviteRepository inviteRepository;

    @Autowired
    public InviteService(UserRepository userRepository, AuthorityRepository authorityRepository, ConferenceRepository conferenceRepository, InviteRepository inviteRepository, AuthorRepository authorRepository, PaperRepository paperRepository) {
        this.userRepository = userRepository;
        this.conferenceRepository = conferenceRepository;
        this.inviteRepository = inviteRepository;
    }

    //更新邀请状态（拒绝或接受）
    public Invite updateInvite(UpdateInviteRequest request) {
        String username = request.getUsername();
        String choice = request.getChoice();
        String conferenceName = request.getConferenceName();
        List<String> jointopic = request.getTopic();
        User thisUser = this.userRepository.findByUsername(username);
        Conference exitconference = this.conferenceRepository.findByfullName(conferenceName);

        if (thisUser == null) {
            throw new UsernameNotFoundException(username);
        }
        Invite invite1 = null;
        Set<Invite> tempInvites = this.inviteRepository.findByConferenceName(conferenceName);
        for (Invite item : tempInvites) {
            if (item.getPCmemberName().equals(username)) {
                invite1 = item;
            }
        }
        if (invite1 == null) {
            return null;
        }
        if (choice.equals("rejected")) {  //用户拒绝接受邀请
            invite1.setStage("rejected");
            inviteRepository.save(invite1);
            return invite1;
        } else if (choice.equals("approved")) {  //用户同意接受邀请
            invite1.setStage("approved");
            inviteRepository.save(invite1);
            Topicrelated mytopicrelated = new Topicrelated(thisUser.getUsername(),jointopic);
            exitconference.getTopicrelatedList().add(mytopicrelated);
            List<String> userConference = thisUser.getReviewConference();
            //用户加上当前会议
            userConference.add(this.conferenceRepository.findByfullName(conferenceName).getFullName());
            //会议加上当前用户
            exitconference.getReviewerList().add(thisUser.getUsername());
            conferenceRepository.save(exitconference);
            return invite1;
        }
        return null;
    }

    //发送邀请
    public Invite contribute(ContributeRequest request) {
        String PCmemberName = request.getpcmemberName();
        String chairname = request.getChairName();
        String region = request.getRegion();
        String email = request.getEmail();
        String conference = request.getConferenceName();
        //若邀请已存在，则返回空
        Set<Invite> tempInvites = this.inviteRepository.findByPcmemberName(PCmemberName);
        for (Invite item : tempInvites){
            if (item.getConferenceName().equals(conference)&&item.getChairName().equals(chairname)&&(item.getStage().equals("ToBeConfirmed")||item.getStage().equals("approved"))){
               return null;
            }
        }
        User PCmember=this.userRepository.findByUsername(PCmemberName);
        Conference conference1=this.conferenceRepository.findByfullName(conference);
        List<String> currentTopic = conference1.getTopics();
        Invite invite1 = new Invite(PCmemberName, PCmember,conference1, chairname, region, email, conference, "ToBeConfirmed",currentTopic);
        inviteRepository.save(invite1);
        return invite1;
    }
}
