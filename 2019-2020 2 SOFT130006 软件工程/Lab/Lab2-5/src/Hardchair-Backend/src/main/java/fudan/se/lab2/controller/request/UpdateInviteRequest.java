package fudan.se.lab2.controller.request;

import fudan.se.lab2.config.ListToStringConverter;

import javax.persistence.Convert;
import java.util.ArrayList;
import java.util.List;

public class UpdateInviteRequest {
    private String username;
    private String choice;
    private String conferenceName;
    @Convert(converter = ListToStringConverter.class)
    private List<String> topic=new ArrayList<>();

    public UpdateInviteRequest() {

    }

    public void setTopic(List<String> topic) {
        this.topic = topic;
    }

    public List<String> getTopic() {
        return topic;
    }

    public UpdateInviteRequest(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public String getConferenceName() {
        return conferenceName;
    }

    public void setConferenceName(String conferenceName) {
        this.conferenceName = conferenceName;
    }
}
