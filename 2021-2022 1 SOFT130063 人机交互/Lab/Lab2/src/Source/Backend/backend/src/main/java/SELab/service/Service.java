package SELab.service;

import SELab.domain.BackGround;
import SELab.repository.*;
import SELab.request.PostCurrPosRequest;
import SELab.request.PostInitRequest;
import SELab.utility.response.ImageString;
import SELab.utility.response.ResponseGenerator;
import SELab.utility.response.ResponseWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;
import java.util.HashMap;

@org.springframework.stereotype.Service
@RestController
public class Service {

    Logger logger = LoggerFactory.getLogger(Service.class);

    private UserRepository userRepository;
    private BackGroundRepository backGroundRepository;

    @Autowired
    public Service(
            UserRepository userRepository,
            BackGroundRepository backGroundRepository
    ) {
        this.userRepository = userRepository;
        this.backGroundRepository = backGroundRepository;
    }

    public Service(){}


    public ResponseWrapper<?> getCurrPos() {
        BackGround backGround = backGroundRepository.findAll().iterator().next();
        HashMap<String, Object> body = new HashMap<>();
        body.put("height", backGround.getGroundHeight());
        body.put("width", backGround.getGroundWidth());
        body.put("x",backGround.getCurrX());
        body.put("y",backGround.getCurrY());
        return new ResponseWrapper<>(200, ResponseGenerator.success, body);
    }

    public ResponseWrapper<?> postCurrPos(PostCurrPosRequest request) {
        BackGround backGround = backGroundRepository.findAll().iterator().next();
        backGround.setCurrX(request.getCurrX());
        backGround.setCurrY(request.getCurrY());
        backGroundRepository.save(backGround);
        HashMap<String, Object> body = new HashMap<>();
        body.put("update", "success");
        return new ResponseWrapper<>(200, ResponseGenerator.success, body);
    }

    public ResponseWrapper<?> postInit(PostInitRequest request) {

        BackGround backGround = new BackGround(
                "testGround",
                request.getGroundWidth(),
                request.getGroundHeight(),
                request.getCurrX(),
                request.getCurrY()
        );
        HashMap<String, Object> body = new HashMap<>();
        if (backGroundRepository.findByGroundName("testGround").isEmpty()) {
            backGroundRepository.save(backGround);
            body.put("info", "init");
        }
        body.put("info", "ground exist");
        return new ResponseWrapper<>(200, ResponseGenerator.success, body);

    }
}
