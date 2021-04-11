package DataBase1.relation;
import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

public class SingleCategoryResult implements Serializable {

    @JSONField(name = "category")
    private String category;
    @JSONField(name = "whetherDone")
    private boolean whetherDone;
    @JSONField(name = "totalNumOfInspect")
    private int totalNumOfInspect;
    @JSONField(name = "unqualifiedNumOfInspect")
    private int unqualifiedNumOfInspect;
    @JSONField(name = "date")
    private String date;


    public SingleCategoryResult(String category, boolean whetherDone, int totalNumOfInspect, int unqualifiedNumOfInspect, String date) {
        this.category = category;
        this.whetherDone = whetherDone;
        this.totalNumOfInspect = totalNumOfInspect;
        this.unqualifiedNumOfInspect = unqualifiedNumOfInspect;
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isWhetherDone() {
        return whetherDone;
    }

    public void setWhetherDone(boolean whetherDone) {
        this.whetherDone = whetherDone;
    }

    public int getTotalNumOfInspect() {
        return totalNumOfInspect;
    }

    public void setTotalNumOfInspect(int totalNumOfInspect) {
        this.totalNumOfInspect = totalNumOfInspect;
    }

    public int getUnqualifiedNumOfInspect() {
        return unqualifiedNumOfInspect;
    }

    public void setUnqualifiedNumOfInspect(int unqualifiedNumOfInspect) {
        this.unqualifiedNumOfInspect = unqualifiedNumOfInspect;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
