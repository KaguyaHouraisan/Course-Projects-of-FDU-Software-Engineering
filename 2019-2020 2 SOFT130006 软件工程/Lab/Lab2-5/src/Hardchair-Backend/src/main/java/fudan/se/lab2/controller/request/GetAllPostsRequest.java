package fudan.se.lab2.controller.request;

/**
 * @author LBW
 */
public class GetAllPostsRequest {
    private Long paperId;

    public void setPaperId(Long paperId) {
        this.paperId = paperId;
    }

    public Long getPaperId() {
        return paperId;
    }
}
