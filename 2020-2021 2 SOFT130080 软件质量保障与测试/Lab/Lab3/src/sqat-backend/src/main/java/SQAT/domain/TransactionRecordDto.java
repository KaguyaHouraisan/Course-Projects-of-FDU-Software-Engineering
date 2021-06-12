package SQAT.domain;

public class TransactionRecordDto {
    private String account;
    private double amount;
    private double balance;
    private String branchName;
    private String branchNum;
    private int currency;
    private int depositId;
    private int id;
    private String idNumber;
    private long operator;
    private String operatorTime;
    private String password;
    private String reciprocalAccount;
    private String reciprocalName;
    private String tellerName;
    private String transactionCode;
    private String transactionNum;
    private String transactionType;
    private int verifyType;

    public TransactionRecordDto() {
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchNum() {
        return branchNum;
    }

    public void setBranchNum(String branchNum) {
        this.branchNum = branchNum;
    }

    public int getCurrency() {
        return currency;
    }

    public void setCurrency(int currency) {
        this.currency = currency;
    }

    public int getDepositId() {
        return depositId;
    }

    public void setDepositId(int depositId) {
        this.depositId = depositId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public long getOperator() {
        return operator;
    }

    public void setOperator(long operator) {
        this.operator = operator;
    }

    public String getOperatorTime() {
        return operatorTime;
    }

    public void setOperatorTime(String operatorTime) {
        this.operatorTime = operatorTime;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getReciprocalAccount() {
        return reciprocalAccount;
    }

    public void setReciprocalAccount(String reciprocalAccount) {
        this.reciprocalAccount = reciprocalAccount;
    }

    public String getReciprocalName() {
        return reciprocalName;
    }

    public void setReciprocalName(String reciprocalName) {
        this.reciprocalName = reciprocalName;
    }

    public String getTellerName() {
        return tellerName;
    }

    public void setTellerName(String tellerName) {
        this.tellerName = tellerName;
    }

    public String getTransactionCode() {
        return transactionCode;
    }

    public void setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
    }

    public String getTransactionNum() {
        return transactionNum;
    }

    public void setTransactionNum(String transactionNum) {
        this.transactionNum = transactionNum;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public int getVerifyType() {
        return verifyType;
    }

    public void setVerifyType(int verifyType) {
        this.verifyType = verifyType;
    }
}
