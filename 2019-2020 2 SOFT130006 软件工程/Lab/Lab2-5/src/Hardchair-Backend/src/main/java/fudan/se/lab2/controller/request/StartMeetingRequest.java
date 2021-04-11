package fudan.se.lab2.controller.request;

import fudan.se.lab2.config.ListToStringConverter;

import javax.persistence.Convert;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Renhao Liu
 */
public class StartMeetingRequest {
    private String chairMan;
    private String briefName;
    private String fullName;
    private String address;
    private String paperDDL;
    private String date;
    private String distributeDate;
    private String stage;
    @Convert(converter = ListToStringConverter.class)
    private List<String> topics=new ArrayList<>();

    public StartMeetingRequest() {

    }

    public StartMeetingRequest(String chairMan, String briefName, String fullName, String address, String paperDDL, String date, String distributeDate,String stage,List<String> topics) {
        this.briefName = briefName;
        this.fullName = fullName;
        this.address = address;
        this.paperDDL = paperDDL;
        this.date = date;
        this.distributeDate = distributeDate;
        this.chairMan=chairMan;
        this.stage=stage;
        this.topics = topics;

    }

    public void setTopics(List<String> topics) {
        this.topics = topics;
    }

    public List<String> getTopics() {
        return topics;
    }

    public String getBriefName() {
        return briefName;
    }

    public void setBriefName(String briefName) {
        this.briefName = briefName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPaperDDL() {
        return paperDDL;
    }

    public void setPaperDDL(String paperDDL) {
        this.paperDDL = paperDDL;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDistributeDate() {
        return distributeDate;
    }

    public void setDistributeDate(String distributeDate) {
        this.distributeDate = distributeDate;
    }

    public String getChairMan(){
        return this.chairMan;
    }
    public void setChairMan(String chairMan) {
        this.chairMan = chairMan;
    }

    public String getStage() {
        return this.stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }
}

