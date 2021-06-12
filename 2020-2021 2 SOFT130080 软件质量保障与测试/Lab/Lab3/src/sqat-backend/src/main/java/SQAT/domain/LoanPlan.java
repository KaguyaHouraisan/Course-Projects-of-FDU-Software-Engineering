package SQAT.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class LoanPlan implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String createTime;
    private String updateTime;
    private long creator;
    private long updater;
    private String iouNum;
    private int planNum;
    private String planDate;
    private double planAmount;
    private double planPrincipal;
    private double planInterest;
    private int payMethod;
    private double remainAmount;
    private double remainPrincipal;
    private double remainInterest;
    private int repaymentStatus;
    private double penaltyInterest;
    private double compoundInterest;

    public LoanPlan() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public long getCreator() {
        return creator;
    }

    public void setCreator(long creator) {
        this.creator = creator;
    }

    public long getUpdater() {
        return updater;
    }

    public void setUpdater(long updater) {
        this.updater = updater;
    }

    public String getIouNum() {
        return iouNum;
    }

    public void setIouNum(String iouNum) {
        this.iouNum = iouNum;
    }

    public int getPlanNum() {
        return planNum;
    }

    public void setPlanNum(int planNum) {
        this.planNum = planNum;
    }

    public String getPlanDate() {
        return planDate;
    }

    public void setPlanDate(String planDate) {
        this.planDate = planDate;
    }

    public double getPlanAmount() {
        return planAmount;
    }

    public void setPlanAmount(double planAmount) {
        this.planAmount = planAmount;
    }

    public double getPlanPrincipal() {
        return planPrincipal;
    }

    public void setPlanPrincipal(double planPrincipal) {
        this.planPrincipal = planPrincipal;
    }

    public double getPlanInterest() {
        return planInterest;
    }

    public void setPlanInterest(double planInterest) {
        this.planInterest = planInterest;
    }

    public int getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(int payMethod) {
        this.payMethod = payMethod;
    }

    public double getRemainAmount() {
        return remainAmount;
    }

    public void setRemainAmount(double remainAmount) {
        this.remainAmount = remainAmount;
    }

    public double getRemainPrincipal() {
        return remainPrincipal;
    }

    public void setRemainPrincipal(double remainPrincipal) {
        this.remainPrincipal = remainPrincipal;
    }

    public double getRemainInterest() {
        return remainInterest;
    }

    public void setRemainInterest(double remainInterest) {
        this.remainInterest = remainInterest;
    }

    public int getRepaymentStatus() {
        return repaymentStatus;
    }

    public void setRepaymentStatus(int repaymentStatus) {
        this.repaymentStatus = repaymentStatus;
    }

    public double getPenaltyInterest() {
        return penaltyInterest;
    }

    public void setPenaltyInterest(double penaltyInterest) {
        this.penaltyInterest = penaltyInterest;
    }

    public double getCompoundInterest() {
        return compoundInterest;
    }

    public void setCompoundInterest(double compoundInterest) {
        this.compoundInterest = compoundInterest;
    }
}
