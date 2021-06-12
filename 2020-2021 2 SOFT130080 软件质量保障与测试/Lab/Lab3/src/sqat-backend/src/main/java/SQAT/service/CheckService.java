package SQAT.service;

import SQAT.constant.Constant;
import SQAT.controller.request.GetLoanAccountListRequest;
import SQAT.controller.request.LoanPlanRequest;
import SQAT.controller.request.LoginRequest;
import SQAT.domain.Account;
import SQAT.domain.LoanAccount;
import SQAT.domain.LoanPlan;
import SQAT.domain.Product;
import SQAT.repository.*;
import SQAT.response.GetLoanAccountListResponse;
import SQAT.response.GetLoanDetailResponse;
import SQAT.response.LoanPlanResponse;
import SQAT.response.LoginResponse;
import SQAT.util.BackendHttpRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import java.util.*;

/**
 * AuthController中除login外，均不应映射到本类；
 * 本类中其他方法均服务于login。
 * By RenHao Liu
 */
@Service
public class CheckService {
    private static String token;
    private AccountRepository accountRepository;
    private LoanAccountRepository loanAccountRepository;
    private LoanPlanRepository loanPlanRepository;
    private ProductRepository productRepository;
    private SpecificPurchaseRepository specificPurchaseRepository;

    @Autowired
    public CheckService(AccountRepository accountRepository, LoanAccountRepository loanAccountRepository, LoanPlanRepository loanPlanRepository, ProductRepository productRepository, SpecificPurchaseRepository specificPurchaseRepository) {
        this.accountRepository = accountRepository;
        this.loanAccountRepository = loanAccountRepository;
        this.loanPlanRepository = loanPlanRepository;
        this.productRepository = productRepository;
        this.specificPurchaseRepository = specificPurchaseRepository;
    }

    public String login(LoginRequest loginRequest) {
        if (checkIfLoggedIn()) {
            return null;
        }

        //POST方式请求
        String url = Constant.URL + "/sys/login/restful";
        String parameter = "username=" + loginRequest.getUsername() + "&password=" + loginRequest.getPassword();

        //设置请求参数，获取返回的Json字符串
        HttpHeaders headers = new HttpHeaders();
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("username", loginRequest.getUsername());
        requestBody.put("password", loginRequest.getPassword());
        String result = BackendHttpRequest.backendPost(url + "?" + parameter, requestBody, headers);
        System.out.println(result);

        //解析Json字符串
        LoginResponse loginResponse = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            loginResponse = objectMapper.readValue(result, LoginResponse.class);
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        System.out.println(loginResponse.toString());
        token = loginResponse.getToken();

        //从BTS后端拉取数据
        loanAccountRepository.deleteAll();
        GetLoanAccountListRequest getLoanAccountListRequest = new GetLoanAccountListRequest();
        getLoanAccountListRequest.setToken(getToken());
        List<LoanAccount> loanAccounts = getLoanAccountList(getLoanAccountListRequest);
        for (LoanAccount temp : loanAccounts) {
            loanAccountRepository.save(temp);
        }
        for (LoanAccount temp : loanAccountRepository.findAll()) {
            LoanAccount loanAccount = getLoanDetail(temp.getIouNum());
            temp.setUpdateTime(loanAccount.getUpdateTime());
            temp.setUpdater(loanAccount.getUpdater());
            temp.setCustomerCode(loanAccount.getCustomerCode());
            temp.setContractNum(loanAccount.getContractNum());
            temp.setProductCode(loanAccount.getProductCode());
            temp.setAccountNum(loanAccount.getAccountNum());
            temp.setPayMethod(loanAccount.getPayMethod());
            temp.setLoanMethod(loanAccount.getLoanMethod());
            temp.setCurrency(loanAccount.getCurrency());
            temp.setContractAmount(loanAccount.getContractAmount());
            temp.setLoanCost(loanAccount.getLoanCost());
            temp.setLoanAmount(loanAccount.getLoanAmount());
            temp.setLoanDate(loanAccount.getLoanDate());
            temp.setIouDate(loanAccount.getIouDate());
            temp.setYearlyRate(loanAccount.getYearlyRate());
            temp.setRepaymentMethod(loanAccount.getRepaymentMethod());
            temp.setRepaymentCount(loanAccount.getRepaymentCount());
            temp.setDueDate(loanAccount.getDueDate());
            temp.setTotalAmount(loanAccount.getTotalAmount());
            temp.setTotalInterest(loanAccount.getTotalInterest());
            temp.setBalance(loanAccount.getBalance());
            temp.setOverdueBalance(loanAccount.getOverdueBalance());
            temp.setRepayInterest(loanAccount.getRepayInterest());
            temp.setLoanUse(loanAccount.getLoanUse());
            temp.setLoanStatus(loanAccount.getLoanStatus());
            temp.setLoanSettleStatus(loanAccount.getLoanSettleStatus());
            temp.setLoanSettleDate(loanAccount.getLoanSettleDate());
            temp.setLoanCancelStatus(loanAccount.getLoanCancelStatus());
            temp.setBranchNum(loanAccount.getBranchNum());
            loanAccountRepository.save(temp);
        }

        loanPlanRepository.deleteAll();
        LoanPlanRequest loanPlanRequest = new LoanPlanRequest();
        loanPlanRequest.setToken(getToken());
        List<LoanPlan> loanPlans;
        for (LoanAccount temp : loanAccounts) {
            loanPlanRequest.setIouNum(temp.getIouNum());

            loanPlanRequest.setRepaymentStatus("1");
            loanPlans = loanPlan(loanPlanRequest);
            for (LoanPlan loanPlan : loanPlans) {
                loanPlanRepository.save(loanPlan);
            }

            loanPlanRequest.setRepaymentStatus("2");
            loanPlans = loanPlan(loanPlanRequest);
            for (LoanPlan loanPlan : loanPlans) {
                loanPlanRepository.save(loanPlan);
            }

            loanPlanRequest.setRepaymentStatus("3");
            loanPlans = loanPlan(loanPlanRequest);
            LoanPlan testTemp = new LoanPlan();
            testTemp.setIouNum("TestTemp");
            loanPlans.add(testTemp);
            for (LoanPlan loanPlan : loanPlans) {
                loanPlanRepository.save(loanPlan);
            }
            loanPlanRepository.deleteAll(loanPlanRepository.findByIouNum("TestTemp"));
        }

        accountRepository.deleteAll();
        Set<String> customerCodes = new HashSet<>();
        for (LoanAccount temp : loanAccounts) {
            customerCodes.add(temp.getCustomerCode());
            System.out.println(temp.getAccountNum() + " " + temp.getCustomerCode());
        }
        for (String temp : customerCodes) {
            Account account = getAccount(temp);
            Set<LoanAccount> loanAccountSet = loanAccountRepository.findByCustomerCode(temp);
            String accountNum = "";
            for (LoanAccount loanAccount : loanAccountSet) {
                accountNum = loanAccount.getAccountNum();
            }
            account.setAccountNum(accountNum);
            accountRepository.save(account);
        }
        for (Account temp : accountRepository.findAll()) {
            System.out.println(temp.getId() + " " + temp.getAccountNum() + " " + temp.getCash());
        }

        productRepository.deleteAll();
        Product product1 = new Product();
        product1.setProductNum("A0001");
        product1.setProductName("优选股票");
        product1.setProductType(Constant.STOCK);
        product1.setRate(-1);
        product1.setPrice(5.5);
        productRepository.save(product1);
        Product product2 = new Product();
        product2.setProductNum("B0001");
        product2.setProductName("优选基金");
        product2.setProductType(Constant.FUND);
        product2.setRate(4.2);
        product2.setPrice(-1);
        productRepository.save(product2);
        Product product3 = new Product();
        product3.setProductNum("C0001");
        product3.setProductName("优选理财");
        product3.setProductType(Constant.WMP);
        product3.setRate(3.5);
        product3.setPrice(-1);
        productRepository.save(product3);

        specificPurchaseRepository.deleteAll();
        return result;
    }

    public List<LoanAccount> getLoanAccountList(GetLoanAccountListRequest request) {
        if (request.getToken().equals(CheckService.getToken())) {
            //Get方式请求
            String url = Constant.URL + "/loan?" + "pageNum=" + "1" + "&pageSize=" + "1000"
                    + "&params=" + "%7B%22orderBy%22:%22order+by+b.updateTime+desc%22%7D";

            //设置请求参数，获取返回的Json字符串
            String result = BackendHttpRequest.sendGet(url, "", request.getToken());
            System.out.println(result);

            //解析Json字符串
            GetLoanAccountListResponse getLoanAccountListResponse = null;
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                getLoanAccountListResponse = objectMapper.readValue(result, GetLoanAccountListResponse.class);
            } catch (Exception e) {
                System.out.println(e.toString());
            }

            System.out.println(getLoanAccountListResponse.toString());
            return new ArrayList<>(Arrays.asList(getLoanAccountListResponse.getList()));
        }
        return null;
    }

    public List<LoanPlan> loanPlan(LoanPlanRequest request) {
        if (request.getToken().equals(CheckService.getToken())) {
            //Get方式请求
            String url = Constant.URL + "/loan/plan?" + "iouNum=" + request.getIouNum() + "&repaymentStatus="
                    + request.getRepaymentStatus();

            //设置请求参数，获取返回的Json字符串
            String result = BackendHttpRequest.sendGet(url, "", request.getToken());
            System.out.println(result);

            //解析Json字符串
            LoanPlanResponse loanPlanResponse = null;
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                loanPlanResponse = objectMapper.readValue(result, LoanPlanResponse.class);
            } catch (Exception e) {
                System.out.println(e.toString());
            }

            System.out.println(loanPlanResponse.toString());
            return new ArrayList<>(Arrays.asList(loanPlanResponse.getData()));
        }
        return null;
    }

    private Account getAccount(String customerCode) {
        //Get方式请求
        String url = Constant.URL + "/account?" + "customerCode=" + customerCode;

        //设置请求参数，获取返回的Json字符串
        String result = BackendHttpRequest.sendGet(url, "", getToken());
        System.out.println(result);
        int temp = result.indexOf("balance\":");
        result = result.substring(temp + 9);
        int temp2 = result.indexOf(",\"accountType");
        result = result.substring(0, temp2);
        System.out.println(result);
        double cash = Double.parseDouble(result);

        Account account = new Account();
        account.setCash(cash);
        return account;
    }

    private LoanAccount getLoanDetail(String iouNum) {
        //Get方式请求
        String url = Constant.URL + "/loan/" + iouNum;

        //设置请求参数，获取返回的Json字符串
        String result = BackendHttpRequest.sendGet(url, "", getToken());
        System.out.println(result);

        //解析Json字符串
        GetLoanDetailResponse getLoanDetailResponse = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            getLoanDetailResponse = objectMapper.readValue(result, GetLoanDetailResponse.class);
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        System.out.println(getLoanDetailResponse.toString());
        return getLoanDetailResponse.getData();
    }

    private boolean checkIfLoggedIn() {
        return token != null;
    }

    public static String getToken() {
        return token;
    }
}
