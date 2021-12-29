package SELab.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name="User")
public class User{

    private static final long serialVersionUID = -6140085056226164016L;

    @Id
    @Column(name="Id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="Username")
    private String username;
//    private String fullname;
//    private String password;
//    private String email;
//    private String institution;
//    private String region;
//
//    public User(){}
//
//    public User(String username, String fullname, String password, String email, String institution, String region) {
//        this.username = username;
//        this.fullname = fullname;
//        this.password = password;
//        this.email = email;
//        this.institution = institution;
//        this.region = region;
//    }
//    public User(User user) {
//        this.username = user.getUsername();
//        this.fullname = user.getFullname();
//        this.password = user.getPassword();
//        this.email = user.getEmail();
//        this.institution = user.getInstitution();
//        this.region = user.getRegion();
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return null;
//    }
//
//    @Override
//    public String getPassword() {
//        return this.password;
//    }
//
//    @Override
//    public String getUsername() {
//        return this.username;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return false;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return false;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getFullname() {
//        return fullname;
//    }
//
//    public void setFullname(String fullname) {
//        this.fullname = fullname;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getInstitution() {
//        return institution;
//    }
//
//    public void setInstitution(String institution) {
//        this.institution = institution;
//    }
//
//    public String getRegion() {
//        return region;
//    }
//
//    public void setRegion(String region) {
//        this.region = region;
//    }


}