package SQAT.controller.request;

public class BuyProductRequest {
    private String token;
    private String accountNum;
    private String productNum;
    private double amount;
    private String purchaseTime;
    private long shareAmount;

    public BuyProductRequest() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public String getProductNum() {
        return productNum;
    }

    public void setProductNum(String productNum) {
        this.productNum = productNum;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(String purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public long getShareAmount() {
        return shareAmount;
    }

    public void setShareAmount(long shareAmount) {
        this.shareAmount = shareAmount;
    }
}
