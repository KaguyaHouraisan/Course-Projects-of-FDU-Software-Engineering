package fudan.se.lab2.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long postId;

    //帖子内容
    private String content;
    //发帖时间
    private Long sendTime;
    //发帖人
    private String userName;
    //回复的帖子ID
    private Long replyId;
    //帖子的主题
    private String title;



    // empty constructor
    public Post() {
    }
    public Post(String content, Long sendTime, String userName, Long replyId, String title){
        this.content = content;
        this.replyId = replyId;
        this.sendTime = sendTime;
        this.userName = userName;
        this.title = title;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public Long getPostId() {
        return postId;
    }

    public long getReplyId() {
        return replyId;
    }

    public String getContent() {
        return content;
    }

    public Long getSendTime() {
        return sendTime;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setReplyId(Long replyId) {
        this.replyId = replyId;
    }

    public void setSendTime(Long sendTime) {
        this.sendTime = sendTime;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
