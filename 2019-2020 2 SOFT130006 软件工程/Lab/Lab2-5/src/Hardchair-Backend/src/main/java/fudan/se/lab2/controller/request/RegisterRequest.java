package fudan.se.lab2.controller.request;

import java.util.Set;

/**
 * @author LBW
 */
public class RegisterRequest {
    private String username;
    private String realname;
    private String password;
    private String fullname;
    private String email;
    private String region;
    private Set<String> authorities;

    public RegisterRequest() {}

    public RegisterRequest(String username, String password, String email, String region, String realname, Set<String> authorities) {
        this.username = username;
        this.realname = realname;
        this.password = password;
        this.region = region;
        this.email = email;
        this.authorities = authorities;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Set<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<String> authorities) {
        this.authorities = authorities;
    }
}

