package fudan.se.lab2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author LBW
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String getHomePage() {
        return "forward:Index.html";
    }
}
