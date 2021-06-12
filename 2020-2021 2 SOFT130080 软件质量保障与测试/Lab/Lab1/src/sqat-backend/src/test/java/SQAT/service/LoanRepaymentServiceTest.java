package SQAT.service;

import SQAT.controller.request.*;
import SQAT.domain.LoanAccount;
import SQAT.domain.LoanPlan;
import SQAT.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class LoanRepaymentServiceTest {
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
    void checkId() {
        CheckService checkService = new CheckService(accountRepository, loanAccountRepository, loanPlanRepository, productRepository, specificPurchaseRepository);
        LoginRequest request = new LoginRequest();
        request.setUsername("JT2103255030");
        request.setPassword("imbus123");
        checkService.login(request);

        LoanRepaymentService loanRepaymentService = new LoanRepaymentService(accountRepository, loanAccountRepository, loanPlanRepository, productRepository, specificPurchaseRepository);
        CheckIdRequest checkIdRequest = new CheckIdRequest();
        checkIdRequest.setAccountNum("6161694700992917584");
        checkIdRequest.setIDNumber("331003200002500000");
        checkIdRequest.setToken(CheckService.getToken());
        boolean result = loanRepaymentService.checkId(checkIdRequest);
        assertTrue(result);
    }

    @Test
    void getLoanAccountList() {
        CheckService checkService = new CheckService(accountRepository, loanAccountRepository, loanPlanRepository, productRepository, specificPurchaseRepository);
        LoginRequest request = new LoginRequest();
        request.setUsername("JT2103255030");
        request.setPassword("imbus123");
        checkService.login(request);

        LoanRepaymentService loanRepaymentService = new LoanRepaymentService(accountRepository, loanAccountRepository, loanPlanRepository, productRepository, specificPurchaseRepository);
        GetLoanAccountListRequest getLoanAccountListRequest = new GetLoanAccountListRequest();
        getLoanAccountListRequest.setToken(CheckService.getToken());
        List<LoanAccount> loanAccounts = loanRepaymentService.getLoanAccountList(getLoanAccountListRequest);
        assertNotNull(loanAccounts);
        assertNotEquals(0, loanAccounts.size());
        for (LoanAccount temp : loanAccounts) {
            System.out.println(temp.getProductName() + " " + temp.getIouNum() + " " + temp.getTotalAmount());
        }
    }

    @Test
    void getLoanDetail() {
        CheckService checkService = new CheckService(accountRepository, loanAccountRepository, loanPlanRepository, productRepository, specificPurchaseRepository);
        LoginRequest request = new LoginRequest();
        request.setUsername("JT2103255030");
        request.setPassword("imbus123");
        checkService.login(request);

        LoanRepaymentService loanRepaymentService = new LoanRepaymentService(accountRepository, loanAccountRepository, loanPlanRepository, productRepository, specificPurchaseRepository);
        GetLoanDetailRequest getLoanDetailRequest = new GetLoanDetailRequest();
        getLoanDetailRequest.setToken(CheckService.getToken());
        getLoanDetailRequest.setIouNum("L2104032231091");
        LoanAccount temp = loanRepaymentService.getLoanDetail(getLoanDetailRequest);
        assertNotNull(temp);
        System.out.println(temp.getProductName() + " " + temp.getIouNum() + " " + temp.getTotalAmount());
    }

    @Test
    void loanPlan() {
        CheckService checkService = new CheckService(accountRepository, loanAccountRepository, loanPlanRepository, productRepository, specificPurchaseRepository);
        LoginRequest request = new LoginRequest();
        request.setUsername("JT2103255030");
        request.setPassword("imbus123");
        checkService.login(request);

        LoanRepaymentService loanRepaymentService = new LoanRepaymentService(accountRepository, loanAccountRepository, loanPlanRepository, productRepository, specificPurchaseRepository);
        LoanPlanRequest loanPlanRequest = new LoanPlanRequest();
        loanPlanRequest.setIouNum("L2104091528401");
        loanPlanRequest.setToken(CheckService.getToken());
        loanPlanRequest.setRepaymentStatus("2");
        List<LoanPlan> loanPlans = loanRepaymentService.loanPlan(loanPlanRequest);
        assertNotNull(loanPlans);
        System.out.println("Repayment Status = " + loanPlanRequest.getRepaymentStatus());
        for (LoanPlan temp : loanPlans) {
            System.out.println(temp.getId() + " " + temp.getIouNum() + " " + temp.getPlanAmount() + " " + temp.getPlanInterest());
        }
        loanPlanRequest.setRepaymentStatus("1");
        loanPlans = loanRepaymentService.loanPlan(loanPlanRequest);
        System.out.println("Repayment Status = " + loanPlanRequest.getRepaymentStatus());
        for (LoanPlan temp : loanPlans) {
            System.out.println(temp.getId() + " " + temp.getIouNum() + " " + temp.getPlanAmount() + " " + temp.getPlanInterest());
        }
        loanPlanRequest.setRepaymentStatus("3");
        loanPlans = loanRepaymentService.loanPlan(loanPlanRequest);
        System.out.println("Repayment Status = " + loanPlanRequest.getRepaymentStatus());
        for (LoanPlan temp : loanPlans) {
            System.out.println(temp.getId() + " " + temp.getIouNum() + " " + temp.getPlanAmount() + " " + temp.getPlanInterest());
        }
    }

    @Test
    void payLoan() {
        CheckService checkService = new CheckService(accountRepository, loanAccountRepository, loanPlanRepository, productRepository, specificPurchaseRepository);
        LoginRequest request = new LoginRequest();
        request.setUsername("JT2103255030");
        request.setPassword("imbus123");
        checkService.login(request);

        LoanRepaymentService loanRepaymentService = new LoanRepaymentService(accountRepository, loanAccountRepository, loanPlanRepository, productRepository, specificPurchaseRepository);
        PayLoanRequest payLoanRequest = new PayLoanRequest();
        payLoanRequest.setToken(CheckService.getToken());
        payLoanRequest.setIouNum("L2104091528401");
        payLoanRequest.setUpdateTime("2021-04-10 15:36:21");
        LoanAccount temp = loanRepaymentService.payLoan(payLoanRequest);
        System.out.println(temp.getAccountNum() + " " + temp.getIouNum() + " " + temp.getBalance() + " " +temp.getRepayInterest());
    }

    @Test
    void partPayLoan() {
        CheckService checkService = new CheckService(accountRepository, loanAccountRepository, loanPlanRepository, productRepository, specificPurchaseRepository);
        LoginRequest request = new LoginRequest();
        request.setUsername("JT2103255030");
        request.setPassword("imbus123");
        checkService.login(request);

        LoanRepaymentService loanRepaymentService = new LoanRepaymentService(accountRepository, loanAccountRepository, loanPlanRepository, productRepository, specificPurchaseRepository);
        PartPayLoanRequest partPayLoanRequest = new PartPayLoanRequest();
        partPayLoanRequest.setToken(CheckService.getToken());
        partPayLoanRequest.setIouNum("L2104091528401");
        partPayLoanRequest.setUpdateTime("2021-04-10 15:36:21");
        partPayLoanRequest.setPartialRepayment(1000);
        LoanAccount temp = loanRepaymentService.partPayLoan(partPayLoanRequest);
        System.out.println(temp.getAccountNum() + " " + temp.getIouNum() + " " + temp.getBalance() + " " +temp.getRepayInterest());

    }
}
