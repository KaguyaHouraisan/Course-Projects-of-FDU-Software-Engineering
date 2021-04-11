package DataBase1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    Logger logger = LoggerFactory.getLogger(AdminController.class);


    @GetMapping("/admin/queueingApplication")
    public ResponseEntity<?> getqueueingApplication() {
        logger.debug("Get queuing applications by admin");
        return ResponseEntity.ok("");
    }

}

