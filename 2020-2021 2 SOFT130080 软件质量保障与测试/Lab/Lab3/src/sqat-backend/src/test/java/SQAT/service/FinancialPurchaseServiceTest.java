package SQAT.service;

import SQAT.constant.Constant;
import SQAT.controller.request.EvaluateUserCreditRatingRequest;
import SQAT.controller.request.GetProductRequest;
import SQAT.controller.request.LoginRequest;
import SQAT.domain.*;
import SQAT.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class FinancialPurchaseServiceTest {
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
    void evaluateUserCreditRating() {
        CheckService checkService = new CheckService(accountRepository, loanAccountRepository, loanPlanRepository, productRepository, specificPurchaseRepository);
        LoginRequest request = new LoginRequest();
        request.setUsername("JT2103255030");
        request.setPassword("imbus123");
        checkService.login(request);

        FinancialPurchaseService financialPurchaseService = new FinancialPurchaseService(accountRepository, loanAccountRepository, loanPlanRepository, productRepository, specificPurchaseRepository);
        EvaluateUserCreditRatingRequest evaluateUserCreditRatingRequest = new EvaluateUserCreditRatingRequest();
        evaluateUserCreditRatingRequest.setToken(CheckService.getToken());
        evaluateUserCreditRatingRequest.setAccountNum("6161694700992917584");
        int authority = financialPurchaseService.evaluateUserCreditRating(evaluateUserCreditRatingRequest);
        System.out.println(authority);

        EvaluateUserCreditRatingRequest evaluateUserCreditRatingRequest1 = new EvaluateUserCreditRatingRequest();
        evaluateUserCreditRatingRequest1.setToken("0000");
        evaluateUserCreditRatingRequest1.setAccountNum("0000");
        assertNull(financialPurchaseService.evaluateUserCreditRating(evaluateUserCreditRatingRequest1));

        EvaluateUserCreditRatingRequest evaluateUserCreditRatingRequest2 = new EvaluateUserCreditRatingRequest();
        evaluateUserCreditRatingRequest2.setToken(CheckService.getToken());
        evaluateUserCreditRatingRequest2.setAccountNum("0000");
        assertNull(financialPurchaseService.evaluateUserCreditRating(evaluateUserCreditRatingRequest2));

        Account account1 = new Account();
        account1.setAccountNum("TestTemp01");
        account1.setCash(700000);
        accountRepository.save(account1);
        EvaluateUserCreditRatingRequest evaluateUserCreditRatingRequest3 = new EvaluateUserCreditRatingRequest();
        evaluateUserCreditRatingRequest3.setToken(CheckService.getToken());
        evaluateUserCreditRatingRequest3.setAccountNum(account1.getAccountNum());
        assertEquals(Constant.FIRST_ACCOUNT, financialPurchaseService.evaluateUserCreditRating(evaluateUserCreditRatingRequest3));
        accountRepository.delete(account1);

        Account account2 = new Account();
        account2.setAccountNum("TestTemp02");
        account2.setCash(300000);
        accountRepository.save(account2);
        EvaluateUserCreditRatingRequest evaluateUserCreditRatingRequest4 = new EvaluateUserCreditRatingRequest();
        evaluateUserCreditRatingRequest4.setToken(CheckService.getToken());
        evaluateUserCreditRatingRequest4.setAccountNum(account2.getAccountNum());
        assertEquals(Constant.SECONDARY_ACCOUNT, financialPurchaseService.evaluateUserCreditRating(evaluateUserCreditRatingRequest4));
        accountRepository.delete(account2);

        Account account3 = new Account();
        account3.setAccountNum("TestTemp03");
        account3.setCash(-1);
        accountRepository.save(account3);
        EvaluateUserCreditRatingRequest evaluateUserCreditRatingRequest5 = new EvaluateUserCreditRatingRequest();
        evaluateUserCreditRatingRequest5.setToken(CheckService.getToken());
        evaluateUserCreditRatingRequest5.setAccountNum(account3.getAccountNum());
        assertEquals(Constant.THIRD_ACCOUNT, financialPurchaseService.evaluateUserCreditRating(evaluateUserCreditRatingRequest5));
        accountRepository.delete(account3);
    }

    @Test
    void getProduct() {
        CheckService checkService = new CheckService(accountRepository, loanAccountRepository, loanPlanRepository, productRepository, specificPurchaseRepository);
        LoginRequest request = new LoginRequest();
        request.setUsername("JT2103255030");
        request.setPassword("imbus123");
        checkService.login(request);

        FinancialPurchaseService financialPurchaseService = new FinancialPurchaseService(accountRepository, loanAccountRepository, loanPlanRepository, productRepository, specificPurchaseRepository);
        GetProductRequest getProductRequest = new GetProductRequest();
        getProductRequest.setToken(CheckService.getToken());
        getProductRequest.setAccountNum("6161694700992913709");
        List<Product> products = financialPurchaseService.getProduct(getProductRequest);
        assertNotNull(products);
        for (Product temp : products) {
            System.out.println(temp.getProductNum() + " " + temp.getProductName() + " " + temp.getProductType() + " " + temp.getRate() + " " + temp.getPrice());
        }

        GetProductRequest getProductRequest1 = new GetProductRequest();
        getProductRequest1.setToken("0000");
        getProductRequest1.setAccountNum("0000");
        assertNull(financialPurchaseService.getProduct(getProductRequest1));

        GetProductRequest getProductRequest2 = new GetProductRequest();
        getProductRequest2.setToken(CheckService.getToken());
        getProductRequest2.setAccountNum("0000");
        assertNull(financialPurchaseService.getProduct(getProductRequest2));

        Account account1 = new Account();
        account1.setAccountNum("TestTemp01");
        account1.setCash(700000);
        accountRepository.save(account1);
        GetProductRequest getProductRequest3 = new GetProductRequest();
        getProductRequest3.setToken(CheckService.getToken());
        getProductRequest3.setAccountNum(account1.getAccountNum());
        assertEquals(3, financialPurchaseService.getProduct(getProductRequest3).size());
        accountRepository.delete(account1);

        Account account2 = new Account();
        account2.setAccountNum("TestTemp02");
        account2.setCash(300000);
        accountRepository.save(account2);
        GetProductRequest getProductRequest4 = new GetProductRequest();
        getProductRequest4.setToken(CheckService.getToken());
        getProductRequest4.setAccountNum(account2.getAccountNum());
        assertEquals(2, financialPurchaseService.getProduct(getProductRequest4).size());
        accountRepository.delete(account2);

        Account account3 = new Account();
        account3.setAccountNum("TestTemp03");
        account3.setCash(-1);
        accountRepository.save(account3);
        GetProductRequest getProductRequest5 = new GetProductRequest();
        getProductRequest5.setToken(CheckService.getToken());
        getProductRequest5.setAccountNum(account3.getAccountNum());
        assertEquals(1, financialPurchaseService.getProduct(getProductRequest5).size());
        accountRepository.delete(account3);
    }

    @Test
    void getPenalty() {
        CheckService checkService = new CheckService(accountRepository, loanAccountRepository, loanPlanRepository, productRepository, specificPurchaseRepository);
        LoginRequest request = new LoginRequest();
        request.setUsername("JT2103255030");
        request.setPassword("imbus123");
        checkService.login(request);

        FinancialPurchaseService financialPurchaseService = new FinancialPurchaseService(accountRepository, loanAccountRepository, loanPlanRepository, productRepository, specificPurchaseRepository);
        List<LoanPlan> loanPlans = financialPurchaseService.getPenalty(CheckService.getToken(), "6161694700992917584");
        assertEquals(0, loanPlans.size());

        assertNull(financialPurchaseService.getPenalty("0000", "0000"));
        assertNull(financialPurchaseService.getPenalty(CheckService.getToken(), "0000"));

        Account account1 = new Account();
        account1.setAccountNum("TestTemp01");
        account1.setCash(10000);
        accountRepository.save(account1);
        LoanAccount loanAccount1 = new LoanAccount();
        loanAccount1.setAccountNum(account1.getAccountNum());
        loanAccount1.setLoanSettleStatus(1);
        loanAccount1.setIouNum("Iou01");
        loanAccountRepository.save(loanAccount1);
        LoanPlan loanPlan1 = new LoanPlan();
        loanPlan1.setIouNum(loanAccount1.getIouNum());
        loanPlan1.setPlanNum(1);
        loanPlan1.setPenaltyInterest(5000);
        loanPlan1.setRepaymentStatus(1);
        LoanPlan loanPlan2 = new LoanPlan();
        loanPlan2.setIouNum(loanAccount1.getIouNum());
        loanPlan2.setPlanNum(2);
        loanPlan2.setPenaltyInterest(5000);
        loanPlan2.setRepaymentStatus(2);
        loanPlanRepository.save(loanPlan1);
        loanPlanRepository.save(loanPlan2);
        assertEquals(1, financialPurchaseService.getPenalty(CheckService.getToken(), account1.getAccountNum()).size());

        accountRepository.delete(account1);
        loanAccountRepository.delete(loanAccount1);
        loanPlanRepository.delete(loanPlan1);
        loanPlanRepository.delete(loanPlan2);
    }

    @Test
    void payPenalty() {
        CheckService checkService = new CheckService(accountRepository, loanAccountRepository, loanPlanRepository, productRepository, specificPurchaseRepository);
        LoginRequest request = new LoginRequest();
        request.setUsername("JT2103255030");
        request.setPassword("imbus123");
        checkService.login(request);

        FinancialPurchaseService financialPurchaseService = new FinancialPurchaseService(accountRepository, loanAccountRepository, loanPlanRepository, productRepository, specificPurchaseRepository);
        assertNull(financialPurchaseService.payPenalty(CheckService.getToken(), "6161694700992917584"));

        assertNull(financialPurchaseService.payPenalty("0000", "0000"));
        assertNull(financialPurchaseService.payPenalty(CheckService.getToken(), "0000"));

        Account account1 = new Account();
        account1.setAccountNum("TestTemp01");
        account1.setCash(10000);
        accountRepository.save(account1);
        LoanAccount loanAccount1 = new LoanAccount();
        loanAccount1.setAccountNum(account1.getAccountNum());
        loanAccount1.setLoanSettleStatus(1);
        loanAccount1.setIouNum("Iou01");
        loanAccountRepository.save(loanAccount1);
        LoanPlan loanPlan1 = new LoanPlan();
        loanPlan1.setIouNum(loanAccount1.getIouNum());
        loanPlan1.setPlanNum(1);
        loanPlan1.setPenaltyInterest(5000);
        loanPlan1.setRepaymentStatus(1);
        LoanPlan loanPlan2 = new LoanPlan();
        loanPlan2.setIouNum(loanAccount1.getIouNum());
        loanPlan2.setPlanNum(2);
        loanPlan2.setPenaltyInterest(5000);
        loanPlan2.setRepaymentStatus(2);
        loanPlanRepository.save(loanPlan1);
        loanPlanRepository.save(loanPlan2);
        assertEquals(account1.getCash() - loanPlan1.getPenaltyInterest(), financialPurchaseService.payPenalty(CheckService.getToken(), account1.getAccountNum()).getCash());

        LoanPlan loanPlan3 = new LoanPlan();
        loanPlan3.setIouNum(loanAccount1.getIouNum());
        loanPlan3.setPlanNum(3);
        loanPlan3.setPenaltyInterest(account1.getCash() + 1000);
        loanPlan3.setRepaymentStatus(1);
        loanPlanRepository.save(loanPlan3);
        assertNull(financialPurchaseService.payPenalty(CheckService.getToken(), account1.getAccountNum()));

        accountRepository.delete(account1);
        loanAccountRepository.delete(loanAccount1);
        loanPlanRepository.delete(loanPlan1);
        loanPlanRepository.delete(loanPlan2);
        loanPlanRepository.delete(loanPlan3);
    }

    @Test
    void buyProduct() {
        CheckService checkService = new CheckService(accountRepository, loanAccountRepository, loanPlanRepository, productRepository, specificPurchaseRepository);
        LoginRequest request = new LoginRequest();
        request.setUsername("JT2103255030");
        request.setPassword("imbus123");
        checkService.login(request);

        FinancialPurchaseService financialPurchaseService = new FinancialPurchaseService(accountRepository, loanAccountRepository, loanPlanRepository, productRepository, specificPurchaseRepository);
        SpecificPurchase specificPurchase = financialPurchaseService.buyProduct(CheckService.getToken(), "6161694700992917584", "B0001", 10000, "2021-4-11", -1);
        System.out.println(specificPurchase.getAccountNum() + " " + specificPurchase.getProductName() + " " +
                specificPurchase.getProductNum() + " " + specificPurchase.getPurchaseNum() + " " +
                specificPurchase.getCreateTime() + " " + specificPurchase.getPrincipal() + " " +
                specificPurchase.getShareAmount() + " " + specificPurchase.getRate());

        assertNull(financialPurchaseService.buyProduct("0000", "0000", "0000", 0, "0000", 0));
        assertNull(financialPurchaseService.buyProduct(CheckService.getToken(), "0000", "0000", 0, "0000", 0));
        assertNull(financialPurchaseService.buyProduct(CheckService.getToken(), "6161694700992917584", "0000", 0, "0000", 0));

        Account account = accountRepository.findByAccountNum("6161694700992917584");
        assertNull(financialPurchaseService.buyProduct(CheckService.getToken(), "6161694700992917584", "A0001", 0, "0000", (int)account.getCash()));
    }

    @Test
    void getPurchaseRecord() {
        CheckService checkService = new CheckService(accountRepository, loanAccountRepository, loanPlanRepository, productRepository, specificPurchaseRepository);
        LoginRequest request = new LoginRequest();
        request.setUsername("JT2103255030");
        request.setPassword("imbus123");
        checkService.login(request);

        Account account = accountRepository.findByAccountNum("6161694700992917584");
        assertNotNull(account);
        System.out.println(account.getCash());
        FinancialPurchaseService financialPurchaseService = new FinancialPurchaseService(accountRepository, loanAccountRepository, loanPlanRepository, productRepository, specificPurchaseRepository);
        financialPurchaseService.buyProduct(CheckService.getToken(), "6161694700992917584", "B0001", 10000, "2021-4-11", -1);
        account = accountRepository.findByAccountNum("6161694700992917584");
        assertNotNull(account);
        System.out.println(account.getCash());
        financialPurchaseService.buyProduct(CheckService.getToken(), "6161694700992917584", "C0001", 200000, "2021-4-11", -1);
        account = accountRepository.findByAccountNum("6161694700992917584");
        assertNotNull(account);
        System.out.println(account.getCash());
        financialPurchaseService.buyProduct(CheckService.getToken(), "6161694700992917584", "B0001", 230000, "2021-4-11", -1);
        account = accountRepository.findByAccountNum("6161694700992917584");
        assertNotNull(account);
        System.out.println(account.getCash());

        List<SpecificPurchase> specificPurchases = financialPurchaseService.getPurchaseRecord(CheckService.getToken(), "6161694700992917584");
        for (SpecificPurchase specificPurchase : specificPurchases) {
            System.out.println(specificPurchase.getAccountNum() + " " + specificPurchase.getProductName() + " " +
                    specificPurchase.getProductNum() + " " + specificPurchase.getPurchaseNum() + " " +
                    specificPurchase.getCreateTime() + " " + specificPurchase.getPrincipal() + " " +
                    specificPurchase.getShareAmount() + " " + specificPurchase.getRate());
        }

        assertNull(financialPurchaseService.getPurchaseRecord("0000", "0000"));
        assertNull(financialPurchaseService.getPurchaseRecord(CheckService.getToken(), "0000"));
    }
}
