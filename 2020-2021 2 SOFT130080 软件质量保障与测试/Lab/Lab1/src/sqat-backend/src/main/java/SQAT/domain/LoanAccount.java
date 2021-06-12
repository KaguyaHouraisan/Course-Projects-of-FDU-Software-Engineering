package SQAT.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class LoanAccount implements Serializable  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int accountStatus;
    private double balance;
    private String createTime;
    private String updateTime;
    private String contractNum;
    private String accountNum;
    private int payMethod;
    private int loanMethod;
    private double currency;
    private double contractAmount;
    private double loanCost;
    private double loanAmount;
    private String iouDate;
    private double yearlyRate;
    private long creator;
    private long updater;
    private String customerCode;
    private String customerName;
    private String dueDate;
    private String iouNum;
    private int loanCancelStatus;
    private String loanDate;
    private int loanSettleStatus;
    private String loanSettleDate;
    private int loanStatus;
    private double overdueBalance;
    private String productCode;
    private String productName;
    private int repaymentMethod;
    private int repaymentCount;
    private double repayInterest;
    private String loanUse;
    private double totalAmount;
    private double totalInterest;
    private String branchNum;
    private String transactionCode;

    public LoanAccount() {
    }

    public LoanAccount(int accountStatus, double balance, String createTime, long creator, String customerCode, String customerName, String dueDate, String iouNum, int loanCancelStatus, String loanDate, int loanSettleStatus, int loanStatus, double overdueBalance, String productCode, String productName, int repaymentMethod, double totalAmount, double totalInterest) {
        this.accountStatus = accountStatus;
        this.balance = balance;
        this.createTime = createTime;
        this.creator = creator;
        this.customerCode = customerCode;
        this.customerName = customerName;
        this.dueDate = dueDate;
        this.iouNum = iouNum;
        this.loanCancelStatus = loanCancelStatus;
        this.loanDate = loanDate;
        this.loanSettleStatus = loanSettleStatus;
        this.loanStatus = loanStatus;
        this.overdueBalance = overdueBalance;
        this.productCode = productCode;
        this.productName = productName;
        this.repaymentMethod = repaymentMethod;
        this.totalAmount = totalAmount;
        this.totalInterest = totalInterest;
    }

    public int getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(int accountStatus) {
        this.accountStatus = accountStatus;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public long getCreator() {
        return creator;
    }

    public void setCreator(long creator) {
        this.creator = creator;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getIouNum() {
        return iouNum;
    }

    public void setIouNum(String iouNum) {
        this.iouNum = iouNum;
    }

    public int getLoanCancelStatus() {
        return loanCancelStatus;
    }

    public void setLoanCancelStatus(int loanCancelStatus) {
        this.loanCancelStatus = loanCancelStatus;
    }

    public String getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(String loanDate) {
        this.loanDate = loanDate;
    }

    public int getLoanSettleStatus() {
        return loanSettleStatus;
    }

    public void setLoanSettleStatus(int loanSettleStatus) {
        this.loanSettleStatus = loanSettleStatus;
    }

    public int getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(int loanStatus) {
        this.loanStatus = loanStatus;
    }

    public double getOverdueBalance() {
        return overdueBalance;
    }

    public void setOverdueBalance(double overdueBalance) {
        this.overdueBalance = overdueBalance;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getRepaymentMethod() {
        return repaymentMethod;
    }

    public void setRepaymentMethod(int repaymentMethod) {
        this.repaymentMethod = repaymentMethod;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getTotalInterest() {
        return totalInterest;
    }

    public void setTotalInterest(double totalInterest) {
        this.totalInterest = totalInterest;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getContractNum() {
        return contractNum;
    }

    public void setContractNum(String contractNum) {
        this.contractNum = contractNum;
    }

    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public int getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(int payMethod) {
        this.payMethod = payMethod;
    }

    public int getLoanMethod() {
        return loanMethod;
    }

    public void setLoanMethod(int loanMethod) {
        this.loanMethod = loanMethod;
    }

    public double getCurrency() {
        return currency;
    }

    public void setCurrency(double currency) {
        this.currency = currency;
    }

    public double getContractAmount() {
        return contractAmount;
    }

    public void setContractAmount(double contractAmount) {
        this.contractAmount = contractAmount;
    }

    public double getLoanCost() {
        return loanCost;
    }

    public void setLoanCost(double loanCost) {
        this.loanCost = loanCost;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getIouDate() {
        return iouDate;
    }

    public void setIouDate(String iouDate) {
        this.iouDate = iouDate;
    }

    public double getYearlyRate() {
        return yearlyRate;
    }

    public void setYearlyRate(double yearlyRate) {
        this.yearlyRate = yearlyRate;
    }

    public String getLoanSettleDate() {
        return loanSettleDate;
    }

    public void setLoanSettleDate(String loanSettleDate) {
        this.loanSettleDate = loanSettleDate;
    }

    public int getRepaymentCount() {
        return repaymentCount;
    }

    public void setRepaymentCount(int repaymentCount) {
        this.repaymentCount = repaymentCount;
    }

    public double getRepayInterest() {
        return repayInterest;
    }

    public void setRepayInterest(double repayInterest) {
        this.repayInterest = repayInterest;
    }

    public String getLoanUse() {
        return loanUse;
    }

    public void setLoanUse(String loanUse) {
        this.loanUse = loanUse;
    }

    public String getBranchNum() {
        return branchNum;
    }

    public void setBranchNum(String branchNum) {
        this.branchNum = branchNum;
    }

    public String getTransactionCode() {
        return transactionCode;
    }

    public void setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
    }

    public long getUpdater() {
        return updater;
    }

    public void setUpdater(long updater) {
        this.updater = updater;
    }
}
