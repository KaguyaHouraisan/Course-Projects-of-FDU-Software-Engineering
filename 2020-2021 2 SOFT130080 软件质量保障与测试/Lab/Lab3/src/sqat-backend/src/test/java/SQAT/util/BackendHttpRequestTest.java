package SQAT.util;

import SQAT.constant.Constant;
import SQAT.controller.request.LoginRequest;
import SQAT.repository.*;
import SQAT.service.CheckService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BackendHttpRequestTest {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private LoanAccountRepository loanAccountRepository;
    @Autowired
    private LoanPlanRepository loanPlanRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SpecificPurchaseRepository specificPurchaseRepository;

    @Test
    void sendGet() {
        CheckService checkService = new CheckService(accountRepository, loanAccountRepository, loanPlanRepository, productRepository, specificPurchaseRepository);
        LoginRequest request = new LoginRequest();
        request.setUsername("JT2103255030");
        request.setPassword("imbus123");
        checkService.login(request);

        String url = Constant.URL + "/loan";
        String result = BackendHttpRequest.sendGet(url, "pageNum=" + "1" + "&pageSize=" + "1000"
                + "&params=" + "%7B%22orderBy%22:%22order+by+b.updateTime+desc%22%7D", null);
        System.out.println(result);
        assertNotNull(result);
    }

    @Test
    void BackendHttpRequest() {
        BackendHttpRequest backendHttpRequest = new BackendHttpRequest();
        assertNotNull(backendHttpRequest);
    }
}
