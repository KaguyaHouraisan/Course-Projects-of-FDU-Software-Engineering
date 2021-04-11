package fudan.se.lab1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author LBW
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String getHomePage() {
        return "forward:index.html";
    }
}
