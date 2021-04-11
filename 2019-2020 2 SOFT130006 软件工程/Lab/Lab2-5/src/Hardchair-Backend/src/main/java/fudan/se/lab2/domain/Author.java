package fudan.se.lab2.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Author implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long antuorId;

    //作者姓名
    private String authorName;
    //作者所属机构
    private String authorMechanism;
    //作者地区
    private String authorArea;
    //作者邮箱
    private String authorEmail;
    //所属论文ID
    private long paperId;



    // empty constructor
    public Author() {
    }

    public Author(String authorName, String authorMechanism, String authorArea, String authorEmail, long paperId){
        this.authorName = authorName;
        this.authorArea = authorArea;
        this.authorMechanism = authorMechanism;
        this.authorEmail = authorEmail;
        this.paperId = paperId;
    }

    public Long getAntuorId() {
        return antuorId;
    }

    public long getPaperId() {
        return paperId;
    }

    public String getAuthorArea() {
        return authorArea;
    }

    public String getAuthorEmail() {
        return authorEmail;
    }

    public String getAuthorMechanism() {
        return authorMechanism;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorArea(String authorArea) {
        this.authorArea = authorArea;
    }

    public void setAuthorEmail(String authorEmail) {
        this.authorEmail = authorEmail;
    }

    public void setAuthorMechanism(String authorMechanism) {
        this.authorMechanism = authorMechanism;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setPaperId(long paperId) {
        this.paperId = paperId;
    }

}
