package SQAT.service;

import SQAT.controller.request.*;
import SQAT.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CheckServiceTest {
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
    void login() {
        CheckService checkService = new CheckService(accountRepository, loanAccountRepository, loanPlanRepository, productRepository, specificPurchaseRepository);
        LoginRequest request = new LoginRequest();
        request.setUsername("JT2103255030");
        request.setPassword("imbus123");
        String response = checkService.login(request);
        assertNotNull(response);
    }

    @Test
    void getLoanAccountList() {
        CheckService checkService = new CheckService(accountRepository, loanAccountRepository, loanPlanRepository, productRepository, specificPurchaseRepository);
        GetLoanAccountListRequest getLoanAccountListRequest = new GetLoanAccountListRequest();
        getLoanAccountListRequest.setToken("0000");
        assertNull(checkService.getLoanAccountList(getLoanAccountListRequest));
    }

    @Test
    void loanPlan() {
        CheckService checkService = new CheckService(accountRepository, loanAccountRepository, loanPlanRepository, productRepository, specificPurchaseRepository);
        LoanPlanRequest loanPlanRequest = new LoanPlanRequest();
        loanPlanRequest.setToken("000");
        assertNull(checkService.loanPlan(loanPlanRequest));
    }
}