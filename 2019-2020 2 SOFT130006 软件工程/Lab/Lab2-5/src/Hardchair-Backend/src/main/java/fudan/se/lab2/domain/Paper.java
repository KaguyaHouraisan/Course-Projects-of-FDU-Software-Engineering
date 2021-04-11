
package fudan.se.lab2.domain;

import fudan.se.lab2.config.ListToStringConverter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;


@Entity(name = "Paper")
public class Paper implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long paperId;
    //lab4新增字段,审核状态（reviewStatus），分别为pending（未审稿）和success（已审稿），以及open（允许修改）
    private String reviewStatus;

    private  String headline;
    private  String digest;
    private  String filePath;
    private  String contributor;
    private  Long conferenceId;
    private  String rebuttal;

    @Convert(converter = ListToStringConverter.class)
    private  List<String> topicList = new ArrayList<>();

    //lab4新增字段，评价集合，作者集合
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<Evaluation> evaluationList=new HashSet<>();
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Evaluation> evaluationBackup=new HashSet<>();
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<Author> authorList=new HashSet<>();
    //lab5新增字段，存放帖子实体
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private  List<Post> postList = new ArrayList<>();

    //用户id映射到evaluation
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    Map<String, Evaluation> mapA=new HashMap<>();


    //存放所有的审稿人ID
    @Convert(converter = ListToStringConverter.class)
    private  List<String> allPcmemberNameList = new ArrayList<>();
    //存放三位审稿人的用户名
    @Convert(converter = ListToStringConverter.class)
    private  List<String> reviewerNameList = new ArrayList<>();

    public  Paper(){}

    public Paper(String headline, String digest, String filePath, String contributor, String reviewStatus, Long conferenceId){
        this.headline = headline;
        this.digest = digest;
        this.contributor = contributor;
        this.filePath = filePath;
        this.conferenceId = conferenceId;
        this.reviewStatus = reviewStatus;
        this.rebuttal = null;
    }

    public void setTopicList(List<String> topicList) {
        this.topicList = topicList;
    }

    public void setEvaluationBackup(Set<Evaluation> evaluationBackup) {
        this.evaluationBackup = evaluationBackup;
    }

    public Set<Evaluation> getEvaluationBackup() {
        return evaluationBackup;
    }

    public List<String> getTopicList() {
        return topicList;
    }

    public List<String> getAllPcmemberNameList() {
        return allPcmemberNameList;
    }

    public List<String> getReviewerNameList() {
        return reviewerNameList;
    }

    public void setAllPcmemberNameList(List<String> allPcmemberNameList) {
        this.allPcmemberNameList = allPcmemberNameList;
    }

    public void setReviewerNameList(List<String> reviewerNameList) {
        this.reviewerNameList = reviewerNameList;
    }

    public void setEvaluationList(Set<Evaluation> evaluationList) {
        this.evaluationList = evaluationList;
    }

    public Set<Evaluation> getEvaluationList() {
        return evaluationList;
    }

    public Set<Author> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(Set<Author> authorList) {
        this.authorList = authorList;
    }

    public Long getPaperId() {
        return paperId;
    }

    public void setConferenceId(Long conferenceId) {
        this.conferenceId = conferenceId;
    }

    public Long getConferenceId() {
        return conferenceId;
    }

    public String getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(String reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    public void setContributor(String contributor) {
        this.contributor = contributor;
    }

    public String getContributor() {
        return contributor;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getDigest() {
        return digest;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getHeadline() {
        return headline;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getRebuttal() {
        return rebuttal;
    }

    public void setRebuttal(String rebuttal) {
        this.rebuttal = rebuttal;
    }

    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }

    public Map<String, Evaluation> getMapA() {
        return mapA;
    }

    public void setMapA(Map<String, Evaluation> mapA) {
        this.mapA = mapA;
    }
}
