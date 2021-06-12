package SQAT.service;

import SQAT.constant.Constant;
import SQAT.controller.request.*;
import SQAT.domain.*;
import SQAT.repository.*;
import SQAT.response.CheckIdResponse;
import SQAT.util.BackendHttpRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LoanRepaymentService {
    private AccountRepository accountRepository;
    private LoanAccountRepository loanAccountRepository;
    private LoanPlanRepository loanPlanRepository;
    private ProductRepository productRepository;
    private SpecificPurchaseRepository specificPurchaseRepository;

    @Autowired
    public LoanRepaymentService(AccountRepository accountRepository, LoanAccountRepository loanAccountRepository, LoanPlanRepository loanPlanRepository, ProductRepository productRepository, SpecificPurchaseRepository specificPurchaseRepository) {
        this.accountRepository = accountRepository;
        this.loanAccountRepository = loanAccountRepository;
        this.loanPlanRepository = loanPlanRepository;
        this.productRepository = productRepository;
        this.specificPurchaseRepository = specificPurchaseRepository;
    }

    public boolean checkId(CheckIdRequest request) {
        if (request.getToken().equals(CheckService.getToken())) {
            //Get方式请求
            String url = Constant.URL + "/account/check?" + "IDNumber=" + request.getIDNumber() + "&accountNum=" + request.getAccountNum();

            //设置请求参数，获取返回的Json字符串
            HttpHeaders headers = new HttpHeaders();
            headers.add("login-token", request.getToken());
            String result = BackendHttpRequest.backendGet(url, headers);
            System.out.println(result);

            //解析Json字符串
            CheckIdResponse checkIdResponse = null;
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                checkIdResponse = objectMapper.readValue(result, CheckIdResponse.class);
            } catch (Exception e) {
                System.out.println(e.toString());
            }

            if (checkIdResponse != null) {
                System.out.println(checkIdResponse.toString());
                return checkIdResponse.isFlag();
            }
            return false;
        }
        return false;
    }

    public List<LoanAccount> getLoanAccountList(GetLoanAccountListRequest request) {
        if (request.getToken().equals(CheckService.getToken())) {
            List<LoanAccount> loanAccounts = new ArrayList<>();
            for (LoanAccount temp : loanAccountRepository.findAll()) {
                loanAccounts.add(temp);
            }
            return loanAccounts;
        }
        return null;
    }

    public LoanAccount getLoanDetail(GetLoanDetailRequest request) {
        if (request.getToken().equals(CheckService.getToken())) {
            return loanAccountRepository.findByIouNum(request.getIouNum());
        }
        return null;
    }

    public List<LoanPlan> loanPlan(LoanPlanRequest request) {
        if (request.getToken().equals(CheckService.getToken())) {
            return new ArrayList<>(loanPlanRepository.findByIouNumAndRepaymentStatus(request.getIouNum(), Integer.parseInt(request.getRepaymentStatus())));
        }
        return null;
    }

    public LoanAccount payLoan(PayLoanRequest request) {
        if (request.getToken().equals(CheckService.getToken())) {
            LoanAccount loanAccount = loanAccountRepository.findByIouNum(request.getIouNum());
            List<LoanPlan> loanPlans = new ArrayList<>(loanPlanRepository.findByIouNumAndRepaymentStatus(request.getIouNum(), 1));
            if (loanAccount == null || loanPlans.size() == 0) {
                return null;
            }
            LoanPlan loanPlan = getMinIdPlan(loanPlans);

            loanPlan.setUpdater(loanPlan.getCreator());
            loanPlan.setUpdateTime(request.getUpdateTime());
            loanAccount.setUpdater(loanPlan.getUpdater());
            loanAccount.setUpdateTime(request.getUpdateTime());

            System.out.println(loanAccount.getBalance() + " " + loanAccount.getRepayInterest());
            System.out.println(loanPlan.getRemainAmount() + " " + loanPlan.getRemainPrincipal() + " " + loanPlan.getRemainInterest());

            if (loanPlan.getPenaltyInterest() == 0) {
                System.out.println(1);

                loanAccount.setBalance(loanAccount.getBalance() - loanPlan.getRemainPrincipal());
                loanAccount.setRepayInterest(loanAccount.getRepayInterest() + loanPlan.getRemainInterest());

                loanPlan.setRemainAmount(0);
                loanPlan.setRemainPrincipal(0);
                loanPlan.setRemainInterest(0);
                loanPlan.setRepaymentStatus(2);

                if (loanPlans.size() == 1) {
                    loanAccount.setLoanSettleStatus(2);
                    loanAccount.setLoanSettleDate(request.getUpdateTime());
                }
            } else {
                System.out.println(2);

                if (loanPlan.getRemainAmount() > loanPlan.getPenaltyInterest()) {
                    double realAmount = loanPlan.getRemainAmount() - loanPlan.getPenaltyInterest();
                    loanPlan.setRemainAmount(loanPlan.getPenaltyInterest());
                    if (loanPlan.getRemainInterest() <= realAmount) {
                        loanAccount.setRepayInterest(loanAccount.getRepayInterest() + loanPlan.getRemainInterest());
                        loanAccount.setBalance(loanAccount.getBalance() - (realAmount - loanPlan.getRemainInterest()));
                        loanPlan.setRemainPrincipal(loanPlan.getRemainPrincipal() - (realAmount - loanPlan.getRemainInterest()));
                        loanPlan.setRemainInterest(0);
                    } else {
                        loanPlan.setRemainInterest(loanPlan.getRemainInterest() - realAmount);
                        loanAccount.setRepayInterest(loanAccount.getRepayInterest() + realAmount);
                    }
                    loanPlan.setPenaltyInterest(0);
                } else {
                    loanPlan.setPenaltyInterest(loanPlan.getPenaltyInterest() - loanPlan.getRemainAmount());
                }
            }

            loanPlanRepository.save(loanPlan);
            System.out.println(loanPlan.getPlanNum() + " " + loanPlan.getPlanAmount() + " " + loanPlan.getRemainAmount());
            loanAccountRepository.save(loanAccount);
            return loanAccountRepository.findByIouNum(request.getIouNum());
        }
        return null;
    }

    public LoanAccount partPayLoan(PartPayLoanRequest partPayLoanRequest) {
        if (partPayLoanRequest.getToken().equals(CheckService.getToken())) {
            LoanAccount loanAccount = loanAccountRepository.findByIouNum(partPayLoanRequest.getIouNum());
            List<LoanPlan> loanPlans = new ArrayList<>(loanPlanRepository.findByIouNumAndRepaymentStatus(partPayLoanRequest.getIouNum(), 1));
            LoanPlan loanPlan = getMinIdPlan(loanPlans);

            if (loanAccount == null || loanPlans.size() == 0 || partPayLoanRequest.getPartialRepayment() >= loanPlan.getRemainPrincipal()) {
                return null;
            }

            loanPlan.setUpdater(loanPlan.getCreator());
            loanPlan.setUpdateTime(partPayLoanRequest.getUpdateTime());
            loanAccount.setUpdater(loanPlan.getUpdater());
            loanAccount.setUpdateTime(partPayLoanRequest.getUpdateTime());
            //罚金为0的情况
            if (loanPlan.getPenaltyInterest() == 0) {
                //默认先还利息，然后再多余的再还本金
                //还款金额大于剩余的利息的情况
                if (partPayLoanRequest.getPartialRepayment() >= loanPlan.getRemainInterest()) {
                    //在贷款账户中将剩余要归还的本金减掉本次归还的本金
                    loanAccount.setBalance(loanAccount.getBalance() - partPayLoanRequest.getPartialRepayment() + loanPlan.getRemainInterest());
                    //将本次总共要归还的利息加到总共归还的利息上
                    loanAccount.setRepayInterest(loanAccount.getRepayInterest() + loanPlan.getRemainInterest());

                    loanPlan.setRemainPrincipal(loanPlan.getRemainPrincipal() - partPayLoanRequest.getPartialRepayment() + loanPlan.getRemainInterest());
                    loanPlan.setRemainAmount(loanPlan.getPlanAmount() - partPayLoanRequest.getPartialRepayment());
                    loanPlan.setRemainInterest(0);
                } else {
                    //还款金额小于剩余利息的情况
                    //将本次归还的部分利息加到总共归还的利息上
                    loanAccount.setRepayInterest(loanAccount.getRepayInterest() + partPayLoanRequest.getPartialRepayment());
                    //在本月的还款计划的剩余利息中减掉本次归还的利息
                    loanPlan.setRemainInterest(loanPlan.getRemainInterest() - partPayLoanRequest.getPartialRepayment());
                    //在本月的还款计划的剩余还款数中减掉本次还款金额
                    loanPlan.setRemainAmount(loanPlan.getRemainAmount() - partPayLoanRequest.getPartialRepayment());

                }
            } else {//罚金不为0且还款金额大于罚金的情况
                if (partPayLoanRequest.getPartialRepayment() >= loanPlan.getPenaltyInterest()) {
                    double remainPay = partPayLoanRequest.getPartialRepayment() - loanPlan.getPenaltyInterest();
                    loanPlan.setPenaltyInterest(0);
                    //交罚金之后剩余金额大于利息的
                    if (remainPay >= loanPlan.getRemainInterest()) {
                        loanAccount.setBalance(loanAccount.getBalance() - remainPay + loanPlan.getRemainInterest());
                        loanAccount.setRepayInterest(loanAccount.getRepayInterest() + loanPlan.getRemainInterest());

                        loanPlan.setRemainAmount(loanPlan.getRemainAmount() - remainPay);
                        loanPlan.setRemainPrincipal(loanPlan.getRemainPrincipal() - remainPay + loanPlan.getRemainInterest());
                        loanPlan.setRemainInterest(0);

                    } else {
                        //本金一点也没有还，只归还了一些利息
                        loanPlan.setRemainInterest(loanPlan.getRemainInterest() - remainPay);
                        loanAccount.setRepayInterest(loanAccount.getRepayInterest() + remainPay);
                        loanPlan.setRemainAmount(loanPlan.getRemainAmount() - remainPay);

                    }

                } else {
                    //只交了部分罚金
                    loanPlan.setPenaltyInterest(loanPlan.getPenaltyInterest() - partPayLoanRequest.getPartialRepayment());
                }
            }
            loanPlanRepository.save(loanPlan);
            loanAccountRepository.save(loanAccount);
            return loanAccountRepository.findByIouNum(partPayLoanRequest.getIouNum());
        }
        return null;
    }

    public List<LoanPlan> autoPayLoan(AutoPayLoanRequest autoPayLoanRequest) {
        if (autoPayLoanRequest.getToken().equals(CheckService.getToken())) {
            //获取当日到期的贷款计划集合
            Set<LoanPlan> loanPlans = loanPlanRepository.findByPlanDate(autoPayLoanRequest.getCurrentDate());
            List<LoanPlan> returnLoanPlan = new ArrayList<>();
            //对每一个到期还款计划
            for (LoanPlan loanPlan : loanPlans) {
                //贷款账户号（借据号）
                String iouNum = loanPlan.getIouNum();
                //贷款账户
                LoanAccount loanAccount = loanAccountRepository.findByIouNum(iouNum);
                //存款账户
                Account account = accountRepository.findByAccountNum(loanAccount.getAccountNum());
                //还款流程
                //有罚金的情况下
                if (loanPlan.getPenaltyInterest() > 0) {
                    //账户余额大于罚金与剩余要还的欠款之和
                    //将欠款和罚金归还
                    //不需要计算罚金
                    if (account.getCash() >= loanPlan.getPenaltyInterest() + loanPlan.getRemainAmount()) {
                        //从账户余额减掉归还的钱
                        account.setCash(account.getCash() - (loanPlan.getPenaltyInterest() + loanPlan.getRemainAmount()));
                        //对贷款账户做操作
                        loanAccount.setRepayInterest(loanAccount.getRepayInterest() + loanPlan.getRemainInterest());
                        loanAccount.setBalance(loanAccount.getBalance() - loanPlan.getRemainPrincipal());
                        //对当月还款计划操作
                        loanPlan.setPenaltyInterest(0);
                        loanPlan.setRemainPrincipal(0);
                        loanPlan.setRemainInterest(0);
                        loanPlan.setRemainAmount(0);
                        loanPlan.setRepaymentStatus(2);

                        loanPlanRepository.save(loanPlan);
                        List<LoanPlan> remainLoanPlans = new ArrayList<>(loanPlanRepository.findByIouNumAndRepaymentStatus(iouNum, 1));
                        //这个月是最后一个月，将贷款账户状态设为已还完
                        if (loanAccount == null || loanPlans.size() == 0) {
                            loanAccount.setAccountStatus(2);
                        }
                    }
                    //需要计算罚金的情况
                    else {
                        //余额大于罚金
                        if (account.getCash() >= loanPlan.getPenaltyInterest()) {
                            //归还罚金
                            account.setCash(account.getCash() - loanPlan.getPenaltyInterest());
                            loanPlan.setPenaltyInterest(0);
                            //部分还款
                            //余额大于利息
                            if (account.getCash() >= loanPlan.getRemainInterest()) {
                                //归还全部利息
                                loanAccount.setRepayInterest(loanAccount.getRepayInterest() + loanPlan.getRemainInterest());
                                account.setCash(account.getCash() - loanPlan.getRemainInterest());
                                loanPlan.setRemainAmount(loanPlan.getRemainAmount() - loanPlan.getRemainInterest());
                                loanPlan.setRemainInterest(0);
                                //归还部分本金
                                loanAccount.setBalance(loanAccount.getBalance() - account.getCash());
                                loanPlan.setRemainPrincipal(loanPlan.getRemainPrincipal() - account.getCash());
                                loanPlan.setRemainAmount(loanPlan.getRemainAmount() - account.getCash());
                                account.setCash(0);
                            }
                            //归还部分利息
                            else {
                                loanAccount.setRepayInterest(loanAccount.getRepayInterest() + account.getCash());
                                loanPlan.setRemainInterest(loanPlan.getRemainInterest() - account.getCash());
                                account.setCash(0);
                            }
                        }
                        //余额小于罚金
                        else {
                            loanPlan.setPenaltyInterest(loanPlan.getPenaltyInterest() - account.getCash());
                            account.setCash(0);

                        }
                        //计算罚金，并将剩下的钱加到下个月
                        //获取下个月的还款计划
                        loanPlan.setRepaymentStatus(3);
                        loanPlanRepository.save(loanPlan);
                        List<LoanPlan> remainLoanPlans = new ArrayList<>(loanPlanRepository.findByIouNumAndRepaymentStatus(iouNum, 1));
                        //这个月是最后一个月，将罚金加到这个月上,其余不变
                        if (loanAccount == null || loanPlans.size() == 0) {
                            loanPlan.setPenaltyInterest(1);
                            loanPlan.setPenaltyInterest(loanPlan.getPlanAmount() * 0.05);
                        }
                        //下个月
                        LoanPlan nextLoanPlan = getMinIdPlan(remainLoanPlans);
                        nextLoanPlan.setPenaltyInterest(loanPlan.getPlanAmount() * 0.05 + loanPlan.getPenaltyInterest());
                        nextLoanPlan.setRemainInterest(nextLoanPlan.getRemainInterest() + loanPlan.getRemainInterest());
                        nextLoanPlan.setRemainPrincipal(nextLoanPlan.getRemainPrincipal() + loanPlan.getRemainPrincipal());
                        nextLoanPlan.setRemainAmount(loanPlan.getPlanAmount() + loanPlan.getRemainAmount());


                    }
                }
                //没有罚金的情况
                else {
                    //余额大于本期所有欠款
                    if (account.getCash() >= loanPlan.getRemainAmount()) {
                        account.setCash(account.getCash() - loanPlan.getRemainAmount());

                        loanAccount.setRepayInterest(loanAccount.getRepayInterest() + loanPlan.getRemainInterest());
                        loanAccount.setBalance(loanAccount.getBalance() - loanPlan.getRemainPrincipal());

                        loanPlan.setRemainAmount(0);
                        loanPlan.setRemainPrincipal(0);
                        loanPlan.setRemainInterest(0);
                        loanPlan.setRepaymentStatus(2);

                        loanPlanRepository.save(loanPlan);
                        List<LoanPlan> remainLoanPlans = new ArrayList<>(loanPlanRepository.findByIouNumAndRepaymentStatus(iouNum, 1));
                        //这个月是最后一个月，将贷款账户状态设为已还完
                        if (loanAccount == null || loanPlans.size() == 0) {
                            loanAccount.setAccountStatus(2);
                        }
                    }
                    else {
                        //余额大于利息
                        if (account.getCash() > loanPlan.getRemainInterest()) {
                            account.setCash(account.getCash() - loanPlan.getRemainInterest());

                            loanAccount.setRepayInterest(loanAccount.getRepayInterest() + loanPlan.getRemainInterest());
                            loanAccount.setBalance(loanAccount.getBalance() - account.getCash());

                            loanPlan.setRemainAmount(loanPlan.getRemainAmount() - loanPlan.getRemainInterest() - account.getCash());
                            loanPlan.setRemainInterest(0);
                            loanPlan.setRemainPrincipal(loanPlan.getRemainPrincipal() - account.getCash());
                            account.setCash(0);

                        } else {
                            loanAccount.setRepayInterest(loanAccount.getRepayInterest() + account.getCash());

                            loanPlan.setRemainAmount(loanPlan.getRemainAmount() - account.getCash());
                            loanPlan.setRemainInterest(loanPlan.getRemainInterest() - account.getCash());

                            account.setCash(0);

                        }
                        //计算罚金，并将剩下的钱加到下个月
                        //获取下个月的还款计划
                        loanPlan.setRepaymentStatus(3);
                        loanPlanRepository.save(loanPlan);
                        List<LoanPlan> remainLoanPlans = new ArrayList<>(loanPlanRepository.findByIouNumAndRepaymentStatus(iouNum, 1));
                        //这个月是最后一个月，将罚金加到这个月上,其余不变
                        if (loanAccount == null || loanPlans.size() == 0) {
                            loanPlan.setPenaltyInterest(1);
                            loanPlan.setPenaltyInterest(loanPlan.getPlanAmount() * 0.05);
                        }
                        //下个月
                        LoanPlan nextLoanPlan = getMinIdPlan(remainLoanPlans);
                        nextLoanPlan.setPenaltyInterest(loanPlan.getPlanAmount() * 0.05);
                        nextLoanPlan.setRemainInterest(nextLoanPlan.getRemainInterest() + loanPlan.getRemainInterest());
                        nextLoanPlan.setRemainPrincipal(nextLoanPlan.getRemainPrincipal() + loanPlan.getRemainPrincipal());
                        nextLoanPlan.setRemainAmount(loanPlan.getPlanAmount() + loanPlan.getRemainAmount());
                    }

                }
                returnLoanPlan.add(loanPlan);
            }
            return returnLoanPlan;
        }
        return null;
    }

    private LoanPlan getMinIdPlan(List<LoanPlan> loanPlans) {
        LoanPlan result = loanPlans.get(0);
        for (LoanPlan temp : loanPlans) {
            if (temp.getPlanNum() < result.getPlanNum()) {
                result = temp;
            }
        }
        return result;
    }
}
