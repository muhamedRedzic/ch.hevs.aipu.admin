package ch.hevs.aipu.admin.managedbean;

import ch.hevs.aipu.admin.entity.Conference;
import ch.hevs.aipu.admin.entity.Stakeholder;
import ch.hevs.aipu.admin.service.Aipu;
import ch.hevs.aipu.admin.service.AipuBean;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
    private List<String> stakeholdersKey;

    private List<Conference> conferenceList;


    //private transient NewsController nc;
    @PostConstruct
    public void initialize(){
        Aipu aipu = new AipuBean();
        conferenceList = new ArrayList<Conference>();
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

    public List<String> getStakeholdersKey() {
        return stakeholdersKey;
    }

    public void setStakeholdersKey(List<String> stakeholdersKey) {
        this.stakeholdersKey = stakeholdersKey;
    }

    public List<Conference> getConferenceList(){
        Aipu aipu = new AipuBean();
        conferenceList = new ArrayList<Conference>();
        List<Conference> temp = aipu.getAllConferences();
        for(int i = 0; i < temp.size(); i++){
            conferenceList.add(temp.get(i));
        }

        //TODO remove this
        //conferenceList.get(0).addStakeholder(new Stakeholder("jean","Orator","ypo","www"));


        return conferenceList;
    }

    public void save(){
        Aipu aipu = new AipuBean();

        //get all related Stakeholder
        List<Stakeholder> stakeholders = new ArrayList<>();
        for(String keyString : stakeholdersKey){
            Key k = KeyFactory.createKey("Stakeholder",Long.parseLong(keyString));
            stakeholders.add(aipu.getStakeholder(k));
        }

        aipu.saveConference(title,startDate,endDate,room,website,stakeholders);
        this.title = "";
        /*this.startDate = "";
        this.endDate = "";*/
        this.room = "";
        this.website = "";
    }

    public void deleteConference() {
        Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String action = params.get("action");
        Long id = Long.parseLong(action);
        Aipu aipu = new AipuBean();
        aipu.deleteConference(id);
    }
}