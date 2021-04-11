package fudan.se.lab2.domain;

import fudan.se.lab2.config.ListToStringConverter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

/**
 * @author LBW
 */
@Entity
public class User implements UserDetails {

    private static final long serialVersionUID = -6140085056226164016L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String username;
    private String realname;
    private String password;
    private String fullname;
    private String region;
    private String email;
    private Boolean validate;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<Authority> authorities = new HashSet<>();

    @Convert(converter = ListToStringConverter.class)
    private  List<String> contributeConference = new ArrayList<>();
    @Convert(converter = ListToStringConverter.class)
    private  List<String> reviewConference = new ArrayList<>();

    public User() {}
    public User(String username, String realname, String password, String fullname, String region, String email, Set<Authority> authorities) {
        this.realname = realname;
        this.username = username;
        this.password= password;
        this.fullname = fullname;
        this.authorities = authorities;
        this.region= region;
        this.email= email;
        this.validate = true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public String getRealname() {
        return realname;
    }

    public String getRegion(){return region;}

    public String getEmail(){return email;}

    public Boolean getValidate() {
        return validate;
    }

    public void setValidate(Boolean validate) {
        this.validate = validate;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    public List<String> getContributeConference() {
        return contributeConference;
    }

    public List<String> getReviewConference() {
        return reviewConference;
    }

}
