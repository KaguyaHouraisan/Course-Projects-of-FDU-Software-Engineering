package fudan.se.lab2.controller.request;

/**
 * @author LBW
 */
public class GetAllRepliesRequest {
    private Long postId;
    private Long paperId;

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getPostId() {
        return postId;
    }

    public Long getPaperId() {
        return paperId;
    }

    public void setPaperId(Long paperId) {
        this.paperId = paperId;
    }
}
