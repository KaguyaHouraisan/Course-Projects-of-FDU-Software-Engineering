package SQAT.service;

import SQAT.controller.request.*;
import SQAT.domain.Account;
import SQAT.domain.LoanAccount;
import SQAT.domain.LoanPlan;
import SQAT.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
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

        CheckIdRequest checkIdRequest1 = new CheckIdRequest();
        checkIdRequest1.setAccountNum("0000");
        checkIdRequest1.setIDNumber("0000");
        checkIdRequest1.setToken("0000");
        assertFalse(loanRepaymentService.checkId(checkIdRequest1));
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

        GetLoanAccountListRequest getLoanAccountListRequest1 = new GetLoanAccountListRequest();
        getLoanAccountListRequest1.setToken("0000");
        assertNull(loanRepaymentService.getLoanAccountList(getLoanAccountListRequest1));
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

        GetLoanDetailRequest getLoanDetailRequest1 = new GetLoanDetailRequest();
        getLoanDetailRequest1.setToken("0000");
        getLoanDetailRequest1.setIouNum("0000");
        assertNull(loanRepaymentService.getLoanDetail(getLoanDetailRequest1));
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

        LoanPlanRequest loanPlanRequest1 = new LoanPlanRequest();
        loanPlanRequest1.setToken("0000");
        loanPlanRequest1.setRepaymentStatus("1");
        loanPlanRequest1.setIouNum("0000");
        assertNull(loanRepaymentService.loanPlan(loanPlanRequest1));
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

        PayLoanRequest payLoanRequest1 = new PayLoanRequest();
        payLoanRequest1.setToken("0000");
        payLoanRequest1.setIouNum("0000");
        payLoanRequest1.setUpdateTime("2021-04-10 15:36:21");
        assertNull(loanRepaymentService.payLoan(payLoanRequest1));

        PayLoanRequest payLoanRequest2 = new PayLoanRequest();
        payLoanRequest2.setToken(CheckService.getToken());
        payLoanRequest2.setIouNum("0000");
        payLoanRequest2.setUpdateTime("2021-04-10 15:36:21");
        assertNull(loanRepaymentService.payLoan(payLoanRequest2));

        Account account1 = new Account();
        account1.setAccountNum("TestTemp01");
        account1.setCash(10000);
        accountRepository.save(account1);
        LoanAccount loanAccount1 = new LoanAccount();
        loanAccount1.setAccountNum(account1.getAccountNum());
        loanAccount1.setLoanSettleStatus(1);
        loanAccount1.setIouNum("Iou01");
        loanAccountRepository.save(loanAccount1);
        PayLoanRequest payLoanRequest3 = new PayLoanRequest();
        payLoanRequest3.setToken(CheckService.getToken());
        payLoanRequest3.setIouNum(loanAccount1.getIouNum());
        payLoanRequest3.setUpdateTime("2021-04-10 15:36:21");
        assertNull(loanRepaymentService.payLoan(payLoanRequest3));

        LoanPlan loanPlan1 = new LoanPlan();
        loanPlan1.setIouNum(loanAccount1.getIouNum());
        loanPlan1.setPlanNum(1);
        loanPlan1.setRemainAmount(10000);
        loanPlan1.setRemainInterest(3000);
        loanPlan1.setRemainPrincipal(7000);
        loanPlan1.setPenaltyInterest(5000);
        loanPlan1.setRepaymentStatus(1);
        loanPlanRepository.save(loanPlan1);
        PayLoanRequest payLoanRequest4 = new PayLoanRequest();
        payLoanRequest4.setToken(CheckService.getToken());
        payLoanRequest4.setIouNum(loanAccount1.getIouNum());
        payLoanRequest4.setUpdateTime("2021-04-10 15:36:21");
        assertNotNull(loanRepaymentService.payLoan(payLoanRequest4));
        loanPlan1 = loanRepaymentService.getMinIdPlan(new ArrayList<>(loanPlanRepository.findByIouNumAndRepaymentStatus(loanAccount1.getIouNum(), 1)));
        assertEquals(0, loanPlan1.getPenaltyInterest());
        assertEquals(0, loanPlan1.getRemainInterest());
        assertEquals(5000, loanPlan1.getRemainPrincipal());
        assertEquals(5000, loanPlan1.getRemainAmount());
        loanPlanRepository.delete(loanPlan1);

        LoanPlan loanPlan2 = new LoanPlan();
        loanPlan2.setIouNum(loanAccount1.getIouNum());
        loanPlan2.setPlanNum(2);
        loanPlan2.setRemainAmount(10000);
        loanPlan2.setRemainInterest(7000);
        loanPlan2.setRemainPrincipal(3000);
        loanPlan2.setPenaltyInterest(5000);
        loanPlan2.setRepaymentStatus(1);
        loanPlanRepository.save(loanPlan2);
        PayLoanRequest payLoanRequest5 = new PayLoanRequest();
        payLoanRequest5.setToken(CheckService.getToken());
        payLoanRequest5.setIouNum(loanAccount1.getIouNum());
        payLoanRequest5.setUpdateTime("2021-04-10 15:36:21");
        assertNotNull(loanRepaymentService.payLoan(payLoanRequest5));
        loanPlan2 = loanRepaymentService.getMinIdPlan(new ArrayList<>(loanPlanRepository.findByIouNumAndRepaymentStatus(loanAccount1.getIouNum(), 1)));
        assertEquals(0, loanPlan2.getPenaltyInterest());
        assertEquals(2000, loanPlan2.getRemainInterest());
        assertEquals(3000, loanPlan2.getRemainPrincipal());
        assertEquals(5000, loanPlan2.getRemainAmount());
        loanPlanRepository.delete(loanPlan2);

        LoanPlan loanPlan3 = new LoanPlan();
        loanPlan3.setIouNum(loanAccount1.getIouNum());
        loanPlan3.setPlanNum(3);
        loanPlan3.setRemainAmount(10000);
        loanPlan3.setRemainInterest(7000);
        loanPlan3.setRemainPrincipal(3000);
        loanPlan3.setPenaltyInterest(15000);
        loanPlan3.setRepaymentStatus(1);
        loanPlanRepository.save(loanPlan3);
        PayLoanRequest payLoanRequest6 = new PayLoanRequest();
        payLoanRequest6.setToken(CheckService.getToken());
        payLoanRequest6.setIouNum(loanAccount1.getIouNum());
        payLoanRequest6.setUpdateTime("2021-04-10 15:36:21");
        assertNotNull(loanRepaymentService.payLoan(payLoanRequest6));
        loanPlan3 = loanRepaymentService.getMinIdPlan(new ArrayList<>(loanPlanRepository.findByIouNumAndRepaymentStatus(loanAccount1.getIouNum(), 1)));
        assertEquals(5000, loanPlan3.getPenaltyInterest());
        assertEquals(7000, loanPlan3.getRemainInterest());
        assertEquals(3000, loanPlan3.getRemainPrincipal());
        assertEquals(10000, loanPlan3.getRemainAmount());
        loanPlanRepository.delete(loanPlan3);

        LoanPlan loanPlan4 = new LoanPlan();
        loanPlan4.setIouNum(loanAccount1.getIouNum());
        loanPlan4.setPlanNum(3);
        loanPlan4.setRemainAmount(10000);
        loanPlan4.setRemainInterest(7000);
        loanPlan4.setRemainPrincipal(3000);
        loanPlan4.setPenaltyInterest(0);
        loanPlan4.setRepaymentStatus(1);
        loanPlanRepository.save(loanPlan4);
        PayLoanRequest payLoanRequest7 = new PayLoanRequest();
        payLoanRequest7.setToken(CheckService.getToken());
        payLoanRequest7.setIouNum(loanAccount1.getIouNum());
        payLoanRequest7.setUpdateTime("2021-04-10 15:36:21");
        assertNotNull(loanRepaymentService.payLoan(payLoanRequest7));
        loanPlan4 = loanRepaymentService.getMinIdPlan(new ArrayList<>(loanPlanRepository.findByIouNumAndRepaymentStatus(loanAccount1.getIouNum(), 2)));
        assertEquals(0, loanPlan4.getPenaltyInterest());
        assertEquals(0, loanPlan4.getRemainInterest());
        assertEquals(0, loanPlan4.getRemainPrincipal());
        assertEquals(0, loanPlan4.getRemainAmount());
        assertEquals(2, loanPlan4.getRepaymentStatus());
        assertEquals(2, loanAccountRepository.findByIouNum(loanAccount1.getIouNum()).getLoanSettleStatus());
        loanPlanRepository.delete(loanPlan4);

        accountRepository.delete(account1);
        loanAccountRepository.delete(loanAccount1);
    }

    @Test
    void partPayLoan(){
        CheckService checkService = new CheckService(accountRepository, loanAccountRepository, loanPlanRepository, productRepository, specificPurchaseRepository);
        LoginRequest request = new LoginRequest();
        request.setUsername("JT2103255030");
        request.setPassword("imbus123");
        checkService.login(request);

        LoanRepaymentService loanRepaymentService = new LoanRepaymentService(accountRepository, loanAccountRepository, loanPlanRepository, productRepository, specificPurchaseRepository);
        //判断登陆无效语句是否执行的request
        PartPayLoanRequest checkLoginRequest = new PartPayLoanRequest();
        checkLoginRequest.setToken("0000");
        assertNull(loanRepaymentService.partPayLoan(checkLoginRequest));

        //预设一个贷款账户
        LoanAccount loanAccount = new LoanAccount();
        loanAccount.setLoanSettleStatus(1);
        loanAccount.setIouNum("TestIou1");
        loanAccount.setRepayInterest(0);
        loanAccount.setBalance(1000);
        loanAccountRepository.save(loanAccount);

        //生成无罚金的贷款计划
        LoanPlan loanPlan1 = new LoanPlan();
        loanPlan1.setIouNum(loanAccount.getIouNum());
        loanPlan1.setPlanNum(1);
        loanPlan1.setPenaltyInterest(0);
        loanPlan1.setRemainInterest(100);
        loanPlan1.setRemainAmount(500);
        loanPlan1.setRepaymentStatus(1);
        loanPlan1.setUpdater(1234567);
        loanPlanRepository.save(loanPlan1);
        //判断无罚金的情况的操作是否正确的request
        PartPayLoanRequest checkNotPenaltyInterestRequest = new PartPayLoanRequest();
        checkNotPenaltyInterestRequest.setToken(CheckService.getToken());
        checkNotPenaltyInterestRequest.setUpdateTime("2021-04-10 15:36:21");
        checkNotPenaltyInterestRequest.setIouNum("TestIou1");

        checkNotPenaltyInterestRequest.setPartialRepayment(10);
        assertEquals(10,loanRepaymentService.partPayLoan(checkNotPenaltyInterestRequest).getRepayInterest());

        checkNotPenaltyInterestRequest.setPartialRepayment(100);
        assertEquals(990,loanRepaymentService.partPayLoan(checkNotPenaltyInterestRequest).getBalance());

        checkNotPenaltyInterestRequest.setPartialRepayment(10000000);
        assertNull(loanRepaymentService.partPayLoan(checkNotPenaltyInterestRequest));

        loanAccount.setRepayInterest(0);
        loanAccount.setBalance(1000);
        loanAccountRepository.save(loanAccount);
        loanPlan1.setPenaltyInterest(100);
        loanPlan1.setRemainInterest(100);
        loanPlan1.setRemainAmount(500);
        loanPlanRepository.save(loanPlan1);

        //判断有罚金的情况的操作是否正确的request
        PartPayLoanRequest checkPenaltyInterestRequest = new PartPayLoanRequest();
        checkPenaltyInterestRequest.setToken(CheckService.getToken());
        checkPenaltyInterestRequest.setIouNum("TestIou1");
        checkPenaltyInterestRequest.setUpdateTime("2021-04-10 15:36:21");

        checkPenaltyInterestRequest.setPartialRepayment(10);
        loanRepaymentService.partPayLoan(checkPenaltyInterestRequest);
        //assertEquals(90,loanPlan1.getPenaltyInterest());

        checkPenaltyInterestRequest.setPartialRepayment(100);
        assertEquals(10,loanRepaymentService.partPayLoan(checkPenaltyInterestRequest).getRepayInterest());

        loanPlan1.setPenaltyInterest(100);
        loanPlanRepository.save(loanPlan1);
        checkPenaltyInterestRequest.setPartialRepayment(210);
        assertEquals(990,loanRepaymentService.partPayLoan(checkPenaltyInterestRequest).getBalance());

        //无贷款计划的request

        PartPayLoanRequest notLoanPlanRequest = new PartPayLoanRequest();
        notLoanPlanRequest.setToken(CheckService.getToken());
        notLoanPlanRequest.setIouNum("TestIou");
        assertNull(loanRepaymentService.partPayLoan(notLoanPlanRequest));

        loanPlan1.setIouNum("TestIou");
        loanPlanRepository.save(loanPlan1);
        notLoanPlanRequest.setIouNum("TestIou1");
        assertNull(loanRepaymentService.partPayLoan(notLoanPlanRequest));

        loanAccountRepository.delete(loanAccount);
        loanPlanRepository.delete(loanPlan1);
    }

    @Test
    void autoPayLoan(){
        CheckService checkService = new CheckService(accountRepository, loanAccountRepository, loanPlanRepository, productRepository, specificPurchaseRepository);
        LoginRequest request = new LoginRequest();
        request.setUsername("JT2103255030");
        request.setPassword("imbus123");
        checkService.login(request);

        LoanRepaymentService loanRepaymentService = new LoanRepaymentService(accountRepository, loanAccountRepository, loanPlanRepository, productRepository, specificPurchaseRepository);
        //判断登陆无效语句是否执行的request
        AutoPayLoanRequest autoPayLoanRequest1 = new AutoPayLoanRequest();
        autoPayLoanRequest1.setToken("0000");
        assertNull(loanRepaymentService.autoPayLoan(autoPayLoanRequest1));

        //生成执行操作的request
        AutoPayLoanRequest autoPayLoanRequest2 = new AutoPayLoanRequest();
        autoPayLoanRequest2.setCurrentDate("2021-04-24");
        autoPayLoanRequest2.setToken(CheckService.getToken());
        //生成还款账号
        Account account1 = new Account();
        account1.setAccountNum("testAcount1");
        accountRepository.save(account1);
        //生成贷款账户
        LoanAccount loanAccount1 = new LoanAccount();
        loanAccount1.setIouNum("testIou1");
        loanAccount1.setAccountNum("testAcount1");
        loanAccountRepository.save(loanAccount1);

        //生成一个还款计划，根据需要改变这两个计划的值以完成测试
        LoanPlan loanPlan1 = new LoanPlan();
        loanPlan1.setPlanNum(1);
        loanPlan1.setIouNum("testIou1");
        loanPlan1.setPlanDate("2021-04-24");
        loanPlan1.setRepaymentStatus(1);
        loanPlan1.setPenaltyInterest(0);
        loanPlanRepository.save(loanPlan1);

        //1.测试无罚金，最后一个还款计划情况下余额大于欠款的情况
        account1.setCash(1000);
        accountRepository.save(account1);
        loanAccount1.setBalance(800);
        loanAccount1.setRepayInterest(0);
        loanAccountRepository.save(loanAccount1);
        loanPlan1.setPlanAmount(400);
        loanPlan1.setRemainAmount(500);
        loanPlan1.setRemainPrincipal(400);
        loanPlan1.setRemainInterest(100);
        loanPlanRepository.save(loanPlan1);
        List<LoanPlan> retuenLoanPlan1 = loanRepaymentService.autoPayLoan(autoPayLoanRequest2);
        for (LoanPlan loanPlan : retuenLoanPlan1) {
            System.out.println(loanPlan.getRemainPrincipal()+" "+loanPlan.getRemainInterest()+" "+loanPlan.getRemainAmount()+" "+loanPlan.getRepaymentStatus());
//            System.out.println(account1.getCash());
            System.out.println("-----测试1------");
        }
        //2.测试无罚金，最后一个还款计划下余额不大于欠款，不小于利息的情况
        account1.setCash(200);
        accountRepository.save(account1);
        loanAccount1.setBalance(800);
        loanAccount1.setRepayInterest(0);
        loanAccountRepository.save(loanAccount1);
        loanPlan1.setPlanAmount(400);
        loanPlan1.setRemainAmount(500);
        loanPlan1.setRemainPrincipal(400);
        loanPlan1.setRemainInterest(100);
        loanPlanRepository.save(loanPlan1);
        List<LoanPlan> retuenLoanPlan2 = loanRepaymentService.autoPayLoan(autoPayLoanRequest2);
        for (LoanPlan loanPlan : retuenLoanPlan2) {
            System.out.println(loanPlan.getRemainPrincipal()+" "+loanPlan.getRemainInterest()+" "+loanPlan.getRemainAmount()+" "+loanPlan.getRepaymentStatus()+" "+loanPlan.getPenaltyInterest());
            System.out.println("-----测试2------");
        }
        //3.测试无罚金，最后一个还款计划下余额小于利息的情况
        account1.setCash(50);
        accountRepository.save(account1);
        loanAccount1.setBalance(800);
        loanAccount1.setRepayInterest(0);
        loanAccountRepository.save(loanAccount1);
        loanPlan1.setPlanAmount(400);
        loanPlan1.setRemainAmount(500);
        loanPlan1.setRemainPrincipal(400);
        loanPlan1.setRemainInterest(100);
        loanPlanRepository.save(loanPlan1);
        List<LoanPlan> retuenLoanPlan3 = loanRepaymentService.autoPayLoan(autoPayLoanRequest2);
        for (LoanPlan loanPlan : retuenLoanPlan3) {
            System.out.println(loanPlan.getRemainPrincipal()+" "+loanPlan.getRemainInterest()+" "+loanPlan.getRemainAmount()+" "+loanPlan.getRepaymentStatus()+" "+loanPlan.getPenaltyInterest());
            System.out.println("-----测试3------");
        }
        //4.测试有罚金，最后一个还款计划情况下余额大于欠款加罚金的情况
        account1.setCash(1000);
        accountRepository.save(account1);
        loanAccount1.setBalance(800);
        loanAccount1.setRepayInterest(0);
        loanAccountRepository.save(loanAccount1);
        loanPlan1.setPlanAmount(400);
        loanPlan1.setRemainAmount(500);
        loanPlan1.setRemainPrincipal(400);
        loanPlan1.setRemainInterest(100);
        loanPlan1.setPenaltyInterest(100);
        loanPlanRepository.save(loanPlan1);
        List<LoanPlan> retuenLoanPlan4 = loanRepaymentService.autoPayLoan(autoPayLoanRequest2);
        for (LoanPlan loanPlan : retuenLoanPlan4) {
            System.out.println(loanPlan.getRemainPrincipal()+" "+loanPlan.getRemainInterest()+" "+loanPlan.getRemainAmount()+" "+loanPlan.getRepaymentStatus()+" "+loanPlan.getPenaltyInterest());
            System.out.println("-----测试4------");
        }
        //5.测试有罚金，最后一个还款计划情况下余额大于罚金加利息的情况
        account1.setCash(300);
        accountRepository.save(account1);
        loanAccount1.setBalance(800);
        loanAccount1.setRepayInterest(0);
        loanAccountRepository.save(loanAccount1);
        loanPlan1.setPlanAmount(400);
        loanPlan1.setRemainAmount(500);
        loanPlan1.setRemainPrincipal(400);
        loanPlan1.setRemainInterest(100);
        loanPlan1.setPenaltyInterest(100);
        loanPlanRepository.save(loanPlan1);
        List<LoanPlan> retuenLoanPlan5 = loanRepaymentService.autoPayLoan(autoPayLoanRequest2);
        for (LoanPlan loanPlan : retuenLoanPlan5) {
            System.out.println(loanPlan.getRemainPrincipal()+" "+loanPlan.getRemainInterest()+" "+loanPlan.getRemainAmount()+" "+loanPlan.getRepaymentStatus()+" "+loanPlan.getPenaltyInterest());
            System.out.println("-----测试5------");
        }
        //6.测试有罚金，最后一个还款计划情况下余额只大于罚金的情况
        account1.setCash(150);
        accountRepository.save(account1);
        loanAccount1.setBalance(800);
        loanAccount1.setRepayInterest(0);
        loanAccountRepository.save(loanAccount1);
        loanPlan1.setPlanAmount(400);
        loanPlan1.setRemainAmount(500);
        loanPlan1.setRemainPrincipal(400);
        loanPlan1.setRemainInterest(100);
        loanPlan1.setPenaltyInterest(100);
        loanPlanRepository.save(loanPlan1);
        List<LoanPlan> retuenLoanPlan6 = loanRepaymentService.autoPayLoan(autoPayLoanRequest2);
        for (LoanPlan loanPlan : retuenLoanPlan6) {
            System.out.println(loanPlan.getRemainPrincipal()+" "+loanPlan.getRemainInterest()+" "+loanPlan.getRemainAmount()+" "+loanPlan.getRepaymentStatus()+" "+loanPlan.getPenaltyInterest());
            System.out.println("-----测试6------");
        }
        //7.测试有罚金，最后一个还款计划情况下余额只小于罚金的情况
        account1.setCash(50);
        accountRepository.save(account1);
        loanAccount1.setBalance(800);
        loanAccount1.setRepayInterest(0);
        loanAccountRepository.save(loanAccount1);
        loanPlan1.setPlanAmount(400);
        loanPlan1.setRemainAmount(500);
        loanPlan1.setRemainPrincipal(400);
        loanPlan1.setRemainInterest(100);
        loanPlan1.setPenaltyInterest(100);
        loanPlanRepository.save(loanPlan1);
        List<LoanPlan> retuenLoanPlan7 = loanRepaymentService.autoPayLoan(autoPayLoanRequest2);
        for (LoanPlan loanPlan : retuenLoanPlan7) {
            System.out.println(loanPlan.getRemainPrincipal()+" "+loanPlan.getRemainInterest()+" "+loanPlan.getRemainAmount()+" "+loanPlan.getRepaymentStatus()+" "+loanPlan.getPenaltyInterest());
            System.out.println("------测试7-----");
        }

        LoanPlan loanPlan2 = new LoanPlan();
        loanPlan2.setPlanNum(2);
        loanPlan2.setIouNum("testIou1");
        loanPlan2.setPlanDate("2021-05-24");
        loanPlan2.setRepaymentStatus(1);
        loanPlanRepository.save(loanPlan2);

        //8.测试无罚金，全部还款的情况
        account1.setCash(2000);
        accountRepository.save(account1);
        loanAccount1.setBalance(800);
        loanAccount1.setRepayInterest(0);
        loanAccountRepository.save(loanAccount1);
        loanPlan1.setPlanAmount(400);
        loanPlan1.setRemainAmount(500);
        loanPlan1.setRemainPrincipal(400);
        loanPlan1.setRemainInterest(100);
        loanPlan1.setPenaltyInterest(0);

        loanPlan2.setPlanAmount(400);
        loanPlan2.setRemainAmount(500);
        loanPlan2.setRemainPrincipal(400);
        loanPlan2.setRemainInterest(100);
        loanPlan2.setPenaltyInterest(0);
        loanPlanRepository.save(loanPlan1);
        loanPlanRepository.save(loanPlan2);
        List<LoanPlan> retuenLoanPlan8 = loanRepaymentService.autoPayLoan(autoPayLoanRequest2);
        for (LoanPlan loanPlan : retuenLoanPlan8) {
            System.out.println(loanPlan.getRemainPrincipal()+" "+loanPlan.getRemainInterest()+" "+loanPlan.getRemainAmount()+" "+loanPlan.getRepaymentStatus()+" "+loanPlan.getPenaltyInterest());
            System.out.println("------测试8-----");
        }
        //9.测试有罚金，全部还款的情况
        account1.setCash(2000);
        accountRepository.save(account1);
        loanAccount1.setBalance(800);
        loanAccount1.setRepayInterest(0);
        loanAccountRepository.save(loanAccount1);
        loanPlan1.setPlanAmount(400);
        loanPlan1.setRemainAmount(500);
        loanPlan1.setRemainPrincipal(400);
        loanPlan1.setRemainInterest(100);
        loanPlan1.setPenaltyInterest(100);

        loanPlan2.setPlanAmount(400);
        loanPlan2.setRemainAmount(500);
        loanPlan2.setRemainPrincipal(400);
        loanPlan2.setRemainInterest(100);
        loanPlan2.setPenaltyInterest(0);
        loanPlanRepository.save(loanPlan1);
        loanPlanRepository.save(loanPlan2);
        List<LoanPlan> retuenLoanPlan9 = loanRepaymentService.autoPayLoan(autoPayLoanRequest2);
        for (LoanPlan loanPlan : retuenLoanPlan9) {
            System.out.println(loanPlan.getRemainPrincipal()+" "+loanPlan.getRemainInterest()+" "+loanPlan.getRemainAmount()+" "+loanPlan.getRepaymentStatus()+" "+loanPlan.getPenaltyInterest());
            System.out.println("-----测试9------");
        }

        //10.测试无罚金，需要将罚金算到下个月的情况
        account1.setCash(200);
        accountRepository.save(account1);
        loanAccount1.setBalance(800);
        loanAccount1.setRepayInterest(0);
        loanAccountRepository.save(loanAccount1);
        loanPlan1.setPlanAmount(400);
        loanPlan1.setRemainAmount(500);
        loanPlan1.setRemainPrincipal(400);
        loanPlan1.setRemainInterest(100);
        loanPlan1.setPenaltyInterest(0);

        loanPlan2.setPlanAmount(400);
        loanPlan2.setRemainAmount(500);
        loanPlan2.setRemainPrincipal(400);
        loanPlan2.setRemainInterest(100);
        loanPlan2.setPenaltyInterest(0);
        loanPlanRepository.save(loanPlan1);
        loanPlanRepository.save(loanPlan2);
        List<LoanPlan> retuenLoanPlan10 = loanRepaymentService.autoPayLoan(autoPayLoanRequest2);
        for (LoanPlan loanPlan : retuenLoanPlan10) {
            System.out.println(loanPlan.getRemainPrincipal()+" "+loanPlan.getRemainInterest()+" "+loanPlan.getRemainAmount()+" "+loanPlan.getRepaymentStatus()+" "+loanPlan.getPenaltyInterest());
            System.out.println("------测试8-----");
        }
        //11.测试有罚金，需要将罚金算到下个月的情况
        account1.setCash(50);
        accountRepository.save(account1);
        loanAccount1.setBalance(800);
        loanAccount1.setRepayInterest(0);
        loanAccountRepository.save(loanAccount1);
        loanPlan1.setPlanAmount(400);
        loanPlan1.setRemainAmount(500);
        loanPlan1.setRemainPrincipal(400);
        loanPlan1.setRemainInterest(100);
        loanPlan1.setPenaltyInterest(100);

        loanPlan2.setPlanAmount(400);
        loanPlan2.setRemainAmount(500);
        loanPlan2.setRemainPrincipal(400);
        loanPlan2.setRemainInterest(100);
        loanPlan2.setPenaltyInterest(0);
        loanPlanRepository.save(loanPlan1);
        loanPlanRepository.save(loanPlan2);
        List<LoanPlan> retuenLoanPlan11 = loanRepaymentService.autoPayLoan(autoPayLoanRequest2);
        for (LoanPlan loanPlan : retuenLoanPlan11) {
            System.out.println(loanPlan.getRemainPrincipal()+" "+loanPlan.getRemainInterest()+" "+loanPlan.getRemainAmount()+" "+loanPlan.getRepaymentStatus()+" "+loanPlan.getPenaltyInterest());
            System.out.println("-----测试9------");
        }
        accountRepository.delete(account1);
        loanAccountRepository.delete(loanAccount1);
        loanPlanRepository.delete(loanPlan1);
        loanPlanRepository.delete(loanPlan2);
    }

    @Test
    void getMinIdPlan() {
        LoanRepaymentService loanRepaymentService = new LoanRepaymentService(accountRepository, loanAccountRepository, loanPlanRepository, productRepository, specificPurchaseRepository);
        List<LoanPlan> loanPlanList = new ArrayList<>();
        LoanPlan loanPlan1 = new LoanPlan();
        loanPlan1.setPlanNum(3);
        loanPlanList.add(loanPlan1);
        LoanPlan loanPlan2 = new LoanPlan();
        loanPlan2.setPlanNum(1);
        loanPlanList.add(loanPlan2);
        LoanPlan loanPlan3 = new LoanPlan();
        loanPlan3.setPlanNum(4);
        loanPlanList.add(loanPlan3);
        LoanPlan loanPlan4 = new LoanPlan();
        loanPlan4.setPlanNum(2);
        loanPlanList.add(loanPlan4);
        assertEquals(1, loanRepaymentService.getMinIdPlan(loanPlanList).getPlanNum());
    }
}
