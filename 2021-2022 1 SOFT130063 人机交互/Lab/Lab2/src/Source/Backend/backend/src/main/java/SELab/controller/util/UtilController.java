package SELab.controller.util;

import SELab.request.PostCurrPosRequest;
import SELab.request.PostInitRequest;
import SELab.service.Service;
import javafx.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
public class UtilController {

    Logger logger = LoggerFactory.getLogger(UtilController.class);
    private Service service;

    @Autowired
    public UtilController(Service service) { this.service = service;}

    @GetMapping("/welcome")
    public ResponseEntity<?> welcome() {
        Map<String, String> response = new HashMap<>();
        String message = "Welcome to 2020 Software Engineering Lab2";
        response.put("message", message);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/postInit")
    public ResponseEntity<?> postInit(@RequestBody PostInitRequest request) {
        return ResponseEntity.ok(service.postInit(request));
    }

    @PostMapping("/upload")
    public @ResponseBody boolean upload(
            @RequestParam MultipartFile file,
            @RequestParam String fileName
    ) {
        if (file == null || file.isEmpty() || fileName == null || fileName.isEmpty()){
            return false;
        }
        try (InputStream inputStream = file.getInputStream()){
            File savefile = new File("testGround_pic.jpg");
            copyInputStreamToFile(inputStream, savefile);
        }
        catch (IOException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }


    @PostMapping("/postCurrPos")
    public ResponseEntity<?> postCurrPos(@RequestBody PostCurrPosRequest request) {
        return ResponseEntity.ok(service.postCurrPos(request));
    }

    @GetMapping("/getCurrPos")
    public ResponseEntity<?> getCurrPos() {
        return ResponseEntity.ok(service.getCurrPos());
    }

    @PostMapping("/download")
    public ResponseEntity<FileSystemResource> download() {
        File file = new File("testGround_pic.jpg");
        if (file.exists()){
            logger.debug("-----file exists-----");
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(new FileSystemResource(file));
        }
        return null;
    }

    private void copyInputStreamToFile(InputStream inputStream, File file)
            throws IOException {

        try (FileOutputStream outputStream = new FileOutputStream(file)) {

            int read;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        }

    }



}
