package fudan.se.lab2.controller.request;

/**
 * @author LBW
 */
public class SendPostRequest {
    private Long paperId;
    private Long replyId;
    private String content;
    private String userName;
    private Long sendTime;
    private String title;

    public Long getSendTime() {
        return sendTime;
    }

    public void setReplyId(Long replyId) {
        this.replyId = replyId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setSendTime(Long sendTime) {
        this.sendTime = sendTime;
    }

    public String getContent() {
        return content;
    }

    public Long getPaperId() {
        return paperId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getReplyId() {
        return replyId;
    }

    public void setPaperId(Long paperId) {
        this.paperId = paperId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
