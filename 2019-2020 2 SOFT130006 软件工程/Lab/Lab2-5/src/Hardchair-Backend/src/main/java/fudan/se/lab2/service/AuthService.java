package fudan.se.lab2.service;

import fudan.se.lab2.controller.request.*;
import fudan.se.lab2.domain.*;
import fudan.se.lab2.exception.UsernameHasBeenRegisteredException;
import fudan.se.lab2.repository.*;
import fudan.se.lab2.security.jwt.JwtConfigProperties;
import fudan.se.lab2.security.jwt.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/**
 * @author LBW
 */
@Service
public class AuthService {
    private UserRepository userRepository;
    private PaperRepository paperRepository;
    private BCryptPasswordEncoder encoder =new BCryptPasswordEncoder();

    @Autowired
    public AuthService(UserRepository userRepository, AuthorityRepository authorityRepository, ConferenceRepository conferenceRepository, InviteRepository inviteRepository,AuthorRepository authorRepository, PaperRepository paperRepository) {
        this.userRepository = userRepository;
        this.paperRepository = paperRepository;
    }

    //注册
    public User register(RegisterRequest request) {
        String username = request.getUsername();
        String password = request.getPassword();
        String email = request.getEmail();
        String region = request.getRegion();
        String fullname = request.getFullname();
        String realname = request.getRealname();
        User exitUser = userRepository.findByUsername(username);
        if (exitUser != null){
            throw new UsernameHasBeenRegisteredException(username);
        }
        User user1 = new User(username, realname,encoder.encode(password), fullname, region, email,new HashSet<>());
        userRepository.save(user1);
        return user1;
    }

    //登录
    public String login(String username, String rawpassword) {
        User user1 = this.userRepository.findByUsername(username);

        if (user1 == null){
            throw new UsernameNotFoundException(username);
        }else if (!encoder.matches(rawpassword,user1.getPassword())){
            return "false";
        }else{
            JwtConfigProperties jwt = new JwtConfigProperties();
            jwt.setSecret("FudanSoft");
            JwtTokenUtil jtu = new JwtTokenUtil(jwt);
            return "{\"token\":\"" + jtu.generateToken(user1) + "\"}";
        }
    }

    //预览论文
    public String reviewFile( HttpServletResponse response,@RequestParam Long paperId) throws IOException{
        Paper reviewPaper = this.paperRepository.findByPaperId(paperId);
        response.setHeader("Content-Disposition", "attachment;filename=" +  reviewPaper.getHeadline() + ".pdf");
        // 响应类型,编码
        response.setContentType("application/pdf;charset=UTF-8");
        // 形成输出流
        File dest = new File(reviewPaper.getFilePath());
        try (OutputStream osOut = response.getOutputStream(); InputStream input = new FileInputStream(dest)) {
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buf)) > 0) {
                osOut.write(buf, 0, bytesRead);
            }
        } catch (IOException e) {
            return "获取文件失败！";
        }
        return "success";
    }

    public String prePDF(String filePath, HttpServletResponse response){
        File file = new File(filePath);
        if (file.exists()) {
            byte[] data = null;
            try (FileInputStream input = new FileInputStream(file)){
                data = new byte[input.available()];
                int a = input.read(data);
                response.getOutputStream().write(data);
            } catch (Exception e) {
                return("pdf文件处理异常...");
            }
        }
        return "success";
    }

    BCryptPasswordEncoder getEncoder() {
        return encoder;
    }
}
