package SQAT.service;

import SQAT.constant.Constant;
import SQAT.controller.request.EvaluateUserCreditRatingRequest;
import SQAT.controller.request.GetProductRequest;
import SQAT.domain.*;
import SQAT.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class FinancialPurchaseService {
    private AccountRepository accountRepository;
    private LoanAccountRepository loanAccountRepository;
    private LoanPlanRepository loanPlanRepository;
    private ProductRepository productRepository;
    private SpecificPurchaseRepository specificPurchaseRepository;

    @Autowired
    public FinancialPurchaseService(AccountRepository accountRepository, LoanAccountRepository loanAccountRepository, LoanPlanRepository loanPlanRepository, ProductRepository productRepository, SpecificPurchaseRepository specificPurchaseRepository) {
        this.accountRepository = accountRepository;
        this.loanAccountRepository = loanAccountRepository;
        this.loanPlanRepository = loanPlanRepository;
        this.productRepository = productRepository;
        this.specificPurchaseRepository = specificPurchaseRepository;
    }

    public Integer evaluateUserCreditRating(EvaluateUserCreditRatingRequest request) {
        if (request.getToken().equals(CheckService.getToken())) {
            Account account = accountRepository.findByAccountNum(request.getAccountNum());
            if (account == null) {
                return null;
            }

            double loanAmount = 0;
            double creditAmount = account.getCash();
            System.out.println(creditAmount);
            Set<LoanAccount> loanAccountSet = loanAccountRepository.findByAccountNum(account.getAccountNum());
            for (LoanAccount temp : loanAccountSet) {
                if (temp.getLoanSettleStatus() != 2) {
                    loanAmount = loanAmount + temp.getBalance();
                    System.out.println(temp.getIouNum() + " " + temp.getBalance());
                }
            }

            double result = creditAmount - loanAmount;
            System.out.println(result);
            if (result > 500000) {
                account.setAuthority(Constant.FIRST_ACCOUNT);
            } else if (result <= 500000 && result >= 0) {
                account.setAuthority(Constant.SECONDARY_ACCOUNT);
            } else {
                account.setAuthority(Constant.THIRD_ACCOUNT);
            }

            accountRepository.save(account);
            return account.getAuthority();
        }
        return null;
    }

    public List<Product> getProduct(GetProductRequest request) {
        if (request.getToken().equals(CheckService.getToken())) {
            Account account = accountRepository.findByAccountNum(request.getAccountNum());
            if (account == null) {
                return null;
            }
            EvaluateUserCreditRatingRequest evaluateUserCreditRatingRequest = new EvaluateUserCreditRatingRequest();
            evaluateUserCreditRatingRequest.setToken(request.getToken());
            evaluateUserCreditRatingRequest.setAccountNum(account.getAccountNum());
            int authority = evaluateUserCreditRating(evaluateUserCreditRatingRequest);
            System.out.println(authority);

            List<Product> products = new ArrayList<>(productRepository.findByProductType(Constant.WMP));
            if (authority == Constant.FIRST_ACCOUNT) {
                products.addAll(productRepository.findByProductType(Constant.FUND));
                products.addAll(productRepository.findByProductType(Constant.STOCK));
                return products;
            } else if (authority == Constant.SECONDARY_ACCOUNT) {
                products.addAll(productRepository.findByProductType(Constant.FUND));
                return products;
            } else if (authority == Constant.THIRD_ACCOUNT) {
                return products;
            } else {
                return null;
            }
        }
        return null;
    }

    public List<LoanPlan> getPenalty(String token, String accountNum) {
        if (token.equals(CheckService.getToken())) {
            Account account = accountRepository.findByAccountNum(accountNum);
            if (account == null) {
                return null;
            }

            List<LoanPlan> loanPlans = new ArrayList<>();
            Set<LoanAccount> loanAccountSet = loanAccountRepository.findByAccountNum(account.getAccountNum());
            for (LoanAccount loanAccount : loanAccountSet) {
                if (loanAccount.getLoanSettleStatus() == 1) {
                    Set<LoanPlan> loanPlanSet = loanPlanRepository.findByIouNumAndRepaymentStatus(loanAccount.getIouNum(), 1);
                    for (LoanPlan loanPlan : loanPlanSet) {
                        if (loanPlan.getPenaltyInterest() > 0 && loanPlan.getRepaymentStatus() != 2) {
                            loanPlans.add(loanPlan);
                        }
                    }
                }
            }
            return loanPlans;
        }
        return null;
    }

    public Account payPenalty(String token, String accountNum) {
        if (token.equals(CheckService.getToken())) {
            Account account = accountRepository.findByAccountNum(accountNum);
            if (account == null) {
                return null;
            }

            List<LoanPlan> loanPlans = getPenalty(token, accountNum);
            if (loanPlans.size() == 0) {
                return null;
            }

            double penaltyAmount = 0;
            for (LoanPlan temp : loanPlans) {
                penaltyAmount = penaltyAmount + temp.getPenaltyInterest();
            }
            if (penaltyAmount > account.getCash()) {
                return null;
            }

            for (LoanPlan temp : loanPlans) {
                temp.setPenaltyInterest(0);
                loanPlanRepository.save(temp);
            }
            account.setCash(account.getCash() - penaltyAmount);
            accountRepository.save(account);
            return accountRepository.findByAccountNum(accountNum);
        }
        return null;
    }

    public SpecificPurchase buyProduct(String token, String accountNum, String productNum, double amount, String purchaseTime, long shareAmount) {
        if (token.equals(CheckService.getToken())) {
            Account account = accountRepository.findByAccountNum(accountNum);
            if (account == null) {
                return null;
            }

            Product product = productRepository.findByProductNum(productNum);
            if (product == null) {
                return null;
            }

            if (product.getProductType() == Constant.STOCK) {
                amount = product.getPrice() * shareAmount;
            }
            if (account.getCash() < amount) {
                return null;
            }

            SpecificPurchase specificPurchase = new SpecificPurchase();
            specificPurchase.setProductNum(product.getProductNum());
            specificPurchase.setProductName(product.getProductName());
            specificPurchase.setProductType(product.getProductType());
            specificPurchase.setRate(product.getRate());
            specificPurchase.setPrice(product.getPrice());
            specificPurchase.setPrincipal(amount);
            specificPurchase.setCreateTime(purchaseTime);
            specificPurchase.setShareAmount(shareAmount);
            specificPurchase.setAccountNum(accountNum);
            specificPurchaseRepository.save(specificPurchase);
            specificPurchase.setPurchaseNum("SP" + product.getProductType() + product.getProductNum() + specificPurchase.getId());
            specificPurchaseRepository.save(specificPurchase);

            account.setCash(account.getCash() - amount);
            accountRepository.save(account);

            return specificPurchase;
        }
        return null;
    }

    public List<SpecificPurchase> getPurchaseRecord(String token, String accountNum) {
        if (token.equals(CheckService.getToken())) {
            Set<SpecificPurchase> specificPurchases = specificPurchaseRepository.findByAccountNum(accountNum);
            if (specificPurchases.size() == 0) {
                return null;
            }

            return new ArrayList<>(specificPurchases);
        }
        return null;
    }
}