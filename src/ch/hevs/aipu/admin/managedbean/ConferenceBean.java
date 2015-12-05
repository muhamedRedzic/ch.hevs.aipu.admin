package ch.hevs.aipu.admin.managedbean;

import ch.hevs.aipu.admin.entity.Conference;
import ch.hevs.aipu.admin.service.Aipu;
import ch.hevs.aipu.admin.service.AipuBean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ManagedBean(name = "ConferenceBean")
@SessionScoped
public class ConferenceBean implements Serializable{

    private static final long serialVersionUID = 1L;

    private Long id;
    private String title;
    private Date startDate;
    private Date endDate;
    private String room;
    private String website;
    private List<Conference> conferenceList;
    //private transient NewsController nc;
    @PostConstruct
    public void initialize(){
    }

    //getter and setter
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setConferenceList(List<Conference> conferenceList) {
        this.conferenceList = conferenceList;
    }

    public List<Conference> getConferenceList(){
        Aipu aipu = new AipuBean();
        conferenceList = new ArrayList<Conference>();
        List<Conference> temp = aipu.getAllConferences();

        for(int i = 0; i < temp.size(); i++){
            conferenceList.add(temp.get(i));
        }
        return conferenceList;
    }

    public void save(){

    }
}