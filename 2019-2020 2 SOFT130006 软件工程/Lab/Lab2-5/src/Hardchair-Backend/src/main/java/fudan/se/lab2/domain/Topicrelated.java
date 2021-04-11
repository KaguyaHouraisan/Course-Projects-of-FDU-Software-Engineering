package fudan.se.lab2.domain;


import fudan.se.lab2.config.ListToStringConverter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Topicrelated")
public class Topicrelated{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long topicrelatedId;

    private String pcmemberName;

    @Convert(converter = ListToStringConverter.class)
    private List<String> topicList = new ArrayList<>();
    public Topicrelated(){

    }
    public Topicrelated(String pcmemberName, List<String> topicList){
        this.pcmemberName = pcmemberName;
        this.topicList = topicList;
    }

    public String getPcmemberName() {
        return pcmemberName;
    }

    public void setPcmemberName(String pcmemberName) {
        this.pcmemberName = pcmemberName;
    }

    public List<String> getTopicList() {
        return topicList;
    }

    public Long getTopicrelatedId() {
        return topicrelatedId;
    }

    public void setTopicList(List<String> topicList) {
        this.topicList = topicList;
    }
}