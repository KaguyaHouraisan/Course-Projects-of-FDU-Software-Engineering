package SQAT.service;

import SQAT.controller.request.GetFlowRequest;
import SQAT.controller.request.LoginRequest;
import SQAT.domain.TransactionRecordDto;
import SQAT.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

@SpringBootTest
public class GetFlowInformationServiceTest {
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
    void getFlow() {
        CheckService checkService = new CheckService(accountRepository, loanAccountRepository, loanPlanRepository, productRepository, specificPurchaseRepository);
        LoginRequest request = new LoginRequest();
        request.setUsername("JT2103255030");
        request.setPassword("imbus123");
        checkService.login(request);

        GetFlowInformationService getFlowInformationService = new GetFlowInformationService();
        GetFlowRequest getFlowRequest = new GetFlowRequest();
        getFlowRequest.setToken(CheckService.getToken());
        List<TransactionRecordDto> transactionRecordDtos = getFlowInformationService.getFlow(getFlowRequest);
        assertNotNull(transactionRecordDtos);
        for (TransactionRecordDto temp : transactionRecordDtos) {
            System.out.println(temp.getAccount() + " " + temp.getCurrency() + " " + temp.getTransactionType());
        }

        GetFlowRequest getFlowRequest2 = new GetFlowRequest();
        getFlowRequest2.setToken(CheckService.getToken() + "test");
        assertNull(getFlowInformationService.getFlow(getFlowRequest2));
    }
}
