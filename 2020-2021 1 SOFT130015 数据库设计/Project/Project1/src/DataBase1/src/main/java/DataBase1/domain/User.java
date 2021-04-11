package DataBase1.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long workId;
    private String userName;
    private String password;
    private Long roleId;//constant 规定
    private String workingTreatRegion;//枚举类

    public User(){}

    public User(Long workId, String userName, String password, Long roleId, String workingTreatRegion) {
        this.workId = workId;
        this.userName = userName;
        this.password = password;
        this.roleId = roleId;
        this.workingTreatRegion = workingTreatRegion;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getWorkingTreatRegion() {
        return workingTreatRegion;
    }

    public void setWorkingTreatRegion(String workingTreatRegion) {
        this.workingTreatRegion = workingTreatRegion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWorkId() {
        return workId;
    }

    public void setWorkId(Long workId) {
        this.workId = workId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}