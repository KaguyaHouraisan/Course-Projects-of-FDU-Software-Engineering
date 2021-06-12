package SQAT;

import SQAT.util.BackendHttpRequest;
import org.springframework.http.HttpHeaders;
import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        String url = "http://localhost:8081/login";

        HttpHeaders headers = new HttpHeaders();
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("username", "JT2103255030");
        requestBody.put("password", "imbus123");
        String result = BackendHttpRequest.backendPost(url, requestBody, headers);
        System.out.println(result);
    }
}
