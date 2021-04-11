package fudan.se.lab2.domain;
import fudan.se.lab2.config.ListToStringConverter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity(name = "Conference")
public class Conference implements Serializable {

    // empty constructor
    public Conference() {
    }
    public Conference(User chairMan,String briefName,String fullName,String date,
                      String address,String paperDDL,String distributeDate,String stage, List<String> topics){
        this.chairMan = chairMan;
        this.briefName = briefName;
        this.fullName = fullName;
        this.date = date;
        this.address = address;
        this.paperDDL = paperDDL;
        this.distributeDate = distributeDate;
        this.stage=stage;
        this.contributionStage = "rejectContribution";
        this.topics = topics;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long conferenceId;

    public Long getConferenceId() {
        return conferenceId;
    }

//   public void setConferenceId(Long conferenceId) {
//        this.conferenceId = conferenceId;
//    }

    // 会议简称
    private String briefName;
    // 会议全称
    private String fullName;
    // 举办时间
    private String date;
    // 举办地点
    private String address;


    // each conference has three stages including underreview,accept,reject
    private String stage;
    // 投稿开始日期
    private Date contributeStartTime;
    // 投稿截止日期
    private String paperDDL;
    // 评审结果发布日期
    private String distributeDate;
    // users in the conference

    //lab4新增字段审核模式,分别为相关度分配（relativity），平均分配（average）
    private  String reviewModl;

    // 接受投稿中（acceptContribution），不接受投稿（rejectContribution）
    private String contributionStage;
    //lab4新增字段评审状态reviewStage，未开始评审为notStarted，到了论文投稿截止时间或者开启审稿后变成pending
    //全部论文都审核完成后变为full，发布评审后变为finished
    private String reviewStage;
    //论文录用发布情况,可以第一次发布canFirstRelease
    //第一次发布且还不能最终发布firstReleased
    //第一次发布且可以最终发布canFinalRelease
    //最终发布 finalReleased
    private String employmentStage;

    @ManyToOne
    private User chairMan;

    @Convert(converter = ListToStringConverter.class)
    private List<String> authorList=new ArrayList<>();
    @Convert(converter = ListToStringConverter.class)
    private List<String> reviewerList=new ArrayList<>();

    //lab4新增字段topic,用于存储topic集合
    @Convert(converter = ListToStringConverter.class)
    private List<String> topics=new ArrayList<>();

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private List<Paper> paperList=new ArrayList<>();


    //lab4新增字段，topic相关内容
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<Topicrelated> topicrelatedList=new HashSet<>();


    public String getReviewStage() {
        return reviewStage;
    }

    public void setReviewStage(String reviewStage) {
        this.reviewStage = reviewStage;
    }


    public String getReviewModl() {
        return reviewModl;
    }

    public void setReviewModl(String reviewModl) {
        this.reviewModl = reviewModl;
    }

    public List<String> getTopics() {
        return topics;
    }

    public void setTopics(List<String> topic) {
        this.topics = topic;
    }

    public String getBriefName() {
        return briefName;
    }

    public void setBriefName(String briefName) {
        this.briefName = briefName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) { this.stage = stage; }

    public Date getContributeStartTime() {
        return contributeStartTime;
    }

    public void setContributeStartTime(Date contributeStartTime) {
        this.contributeStartTime = contributeStartTime;
    }

    public String getPaperDDL() {
        return paperDDL;
    }

    public void setPaperDDL(String paperDDL) {
        this.paperDDL = paperDDL;
    }

    public User getChairMan() {
        return chairMan;
    }

    public void setChairMan(User chairMan) {
        this.chairMan = chairMan;
    }

    public List<String> getAuthorList() {
        return authorList;
    }

    public List<String> getReviewerList() {
        return reviewerList;
    }

    public void setAuthorList(List<String> authorList) {
        this.authorList = authorList;
    }

    public void setReviewerList(List<String> reviewerList) {
        this.reviewerList = reviewerList;
    }

    public List<Paper> getPaperList() {
        return paperList;
    }

    public Set<Topicrelated> getTopicrelatedList() {
        return topicrelatedList;
    }

    public void setPaperList(List<Paper> paperList) {
        this.paperList = paperList;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistributeDate() {
        return distributeDate;
    }

    public void setDistributeDate(String distributeDate) {
        this.distributeDate = distributeDate;
    }

    public String getContributionStage() {
        return contributionStage;
    }

    public void setContributionStage(String contributionStage) {
        this.contributionStage = contributionStage;
    }

    public String getEmploymentStage() {
        return employmentStage;
    }

    public void setEmploymentStage(String employmentStage) {
        this.employmentStage = employmentStage;
    }

    @Override
    public String toString() {
        return "Conference{" +
                "conferenceId=" + conferenceId +
                ", briefName='" + briefName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", date=" + date +
                ", address='" + address + '\'' +
                ", stage='" + stage + '\'' +
                ", contributeStartTime=" + contributeStartTime +
                ", paperDDL=" + paperDDL +
                ", distributeDate=" + distributeDate +
                ", chairMan=" + chairMan +
                ", authorSet=" + authorList +
                ", reviewerSet=" + reviewerList +
                ", paperList=" + paperList +
                '}';
    }


    }


