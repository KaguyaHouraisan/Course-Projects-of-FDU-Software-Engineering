package fudan.se.lab2.domain;

import fudan.se.lab2.config.ListToStringConverter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Invite implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;

    //被邀请人姓名
    private String pcmemberName;
    //被邀请人
    @ManyToOne
    private User pcmember;
    //邀请加入的会议
    @ManyToOne
    private Conference conference;
    //邀请人姓名
    private String chairName;
    //被邀请人区域
    private String region;
    //被邀请人邮箱
    private String email;
    //会议名称
    private String conferenceName;
    //邀请状态，有三种，分别为ToBeConfirmed, approved, rejected
    private  String stage;

    @Convert(converter = ListToStringConverter.class)
    private List<String> topic=new ArrayList<>();

    // empty constructor
    public Invite() {
    }

    public Invite(String PCmemberName,User PCmember, Conference conference, String chairName, String region, String email, String conferenceName, String stage,List<String> topic){
        this.pcmemberName = PCmemberName;
        this.chairName = chairName;
        this.conferenceName = conferenceName;
        this.email = email;
        this.region = region;
        this.stage = stage;
        this.pcmember=PCmember;
        this.conference=conference;
        this.topic = topic;
    }

    public List<String> getTopic() {
        return topic;
    }

    public void setTopic(List<String> topic) {
        this.topic = topic;
    }

    public Conference getConference() {
        return conference;
    }

    public User getPCmember() {
        return pcmember;
    }

    public String getPCmemberName() {
        return pcmemberName;
    }

    public String getConferenceName() {
        return conferenceName;
    }

    public String getChairName() {
        return chairName;
    }

    public String getRegion() {
        return region;
    }

    public String getEmail() {
        return email;
    }

    public String getStage() {
        return stage;
    }

    public void setPCmemberName(String pcmemberName) {
        this.pcmemberName = pcmemberName;
    }

    public void setChairName(String chairName) {
        this.chairName = chairName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public void setConferenceName(String conferenceName) {
        this.conferenceName = conferenceName;
    }

    public void setConference(Conference conference) {
        this.conference = conference;
    }

    public void setPCmember(User PCmember) {
        this.pcmember = PCmember;
    }
}
