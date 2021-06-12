package SQAT.service;

import SQAT.controller.request.EvaluateUserCreditRatingRequest;
import SQAT.controller.request.GetProductRequest;
import SQAT.controller.request.LoginRequest;
import SQAT.domain.Account;
import SQAT.domain.LoanPlan;
import SQAT.domain.Product;
import SQAT.domain.SpecificPurchase;
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
    }
}
