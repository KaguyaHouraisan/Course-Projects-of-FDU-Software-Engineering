package fudan.se.lab2.service;

import fudan.se.lab2.controller.request.*;
import fudan.se.lab2.domain.*;
import fudan.se.lab2.repository.*;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthServiceTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthorityRepository authorityRepository;
    @Autowired
    private ConferenceRepository conferenceRepository;
    @Autowired
    private InviteRepository inviteRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private PaperRepository paperRepository;
    private Set <String> authorities = new HashSet<>();
    private List<String> normalTopics = new ArrayList<>();
    private final RegisterRequest request1 = new RegisterRequest("abc1","abc11","abc1@fudan.edu.cn","1abc","1cba", authorities);
    private final RegisterRequest request2 = new RegisterRequest("abc2","abc22","abc2@fudan.edu.cn","2abc","2cba", authorities);

    @Test
    void register() {
        AuthService service = new AuthService(userRepository, authorityRepository, conferenceRepository, inviteRepository, authorRepository, paperRepository);
        BCryptPasswordEncoder encoder = service.getEncoder();
        //测试register函数返回的user对象是否与注册时的一致
        User user1 = service.register(request1);
        assertEquals(request1.getUsername(), user1.getUsername());
        assertTrue(encoder.matches(request1.getPassword(), user1.getPassword()));
        assertEquals(request1.getEmail(), user1.getEmail());
        assertEquals(request1.getRegion(), user1.getRegion());
        assertEquals(request1.getRealname(), user1.getRealname());
        //测试存放在数据库中的user对象是否与注册时的一致
        User user2 = this.userRepository.findByUsername("abc1");
        assertEquals(request1.getUsername(), user2.getUsername());
        assertTrue(encoder.matches(request1.getPassword(), user2.getPassword()));
        assertEquals(request1.getEmail(), user2.getEmail());
        assertEquals(request1.getRegion(), user2.getRegion());
        assertEquals(request1.getRealname(), user2.getRealname());
    }

    @Test
    void login() {
        AuthService service = new AuthService(userRepository, authorityRepository, conferenceRepository, inviteRepository, authorRepository, paperRepository);
        BCryptPasswordEncoder encoder = service.getEncoder();
        service.register(request2);
        String username = request2.getUsername();
        String password = request2.getPassword();
        //测试使用错误密码登录时是否会返回false
        String result1 = service.login(username, "wrongPassword");
        assertEquals("false", result1);
        //测试使用正确密码登录时是否会返回token
        User user1 = this.userRepository.findByUsername(username);
        String result2 = "";
        if (encoder.matches(password, user1.getPassword())) {
            result2 = service.login(username, password);
        }
        assertEquals(184, result2.length());
    }

    @Test
    void getEncoder() {
        AuthService service = new AuthService(userRepository, authorityRepository, conferenceRepository, inviteRepository, authorRepository, paperRepository);
        Object object = service.getEncoder();
        String typeName = object.getClass().getName();
        int length = typeName.lastIndexOf(".");
        String type = typeName.substring(length + 1);
        assertEquals("BCryptPasswordEncoder", type);
    }

    @Test
    void prePDF() {
        AuthService service = new AuthService(userRepository, authorityRepository, conferenceRepository, inviteRepository, authorRepository, paperRepository);
        File testFile = new File("./test.pdf");
        boolean result = true;
        try {
            if (!testFile.exists()) {
                if (testFile.createNewFile()) {
                    HttpServletResponse response = new MockHttpServletResponse();
                    assertEquals("success", service.prePDF("./test.pdf", response));
                    if (!testFile.delete()) {
                        result = false;
                    }
                } else {
                    result = false;
                }
            } else {
                result = false;
            }
        } catch (Exception e) {
            Logger logger = LoggerFactory.getLogger(AuthServiceTest.class);
            logger.error(e.toString(),e);
        }
        assertTrue(result);
    }
    @Test
    void registerAndloginModule() {
        AuthService service = new AuthService(userRepository, authorityRepository, conferenceRepository, inviteRepository, authorRepository, paperRepository);
        BCryptPasswordEncoder encoder = service.getEncoder();
        //测试register函数返回的user对象是否与注册时的一致
        User user1 = service.register(request2);
        assertEquals(request2.getUsername(), user1.getUsername());
        assertTrue(encoder.matches(request2.getPassword(), user1.getPassword()));
        String username = request2.getUsername();
        String password = request2.getPassword();
        //测试使用错误密码登录时是否会返回false
        String result1 = service.login(username, "wrongPassword");
        assertEquals("false", result1);
        //测试使用正确密码登录时是否会返回token
        String result2 = "";
        if (encoder.matches(password, user1.getPassword())) {
            result2 = service.login(username, password);
        }
        assertEquals(184, result2.length());

    }
}