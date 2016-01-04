package ch.hevs.aipu.admin.managedbean;

import ch.hevs.aipu.admin.entity.Conference;
import ch.hevs.aipu.admin.entity.Stakeholder;
import ch.hevs.aipu.admin.service.Aipu;
import ch.hevs.aipu.admin.service.AipuBean;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ManagedBean(name = "StakeholderBean")
@SessionScoped
public class StakeholderBean implements Serializable{

    private static final long serialVersionUID = 1L;

    private String type;
    private String name;
    private String website;
    private String email;

    private List<Stakeholder> stakeholderList;
    private List<Conference> conferenceList;

    public List<Conference> getSelectedConferences() {
        return selectedConferences;
    }

    public void setSelectedConferences(List<Conference> selectedConferences) {
        this.selectedConferences = selectedConferences;
    }

    private List<Conference> selectedConferences;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void deleteStakeholder(){
        Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String action = params.get("action");
        Aipu aipu = new AipuBean();
        long key = Long.parseLong(action);
        aipu.deleteStakeholder(key);
    }

    public List<Stakeholder> getStakeholderList(){
        Aipu aipu = new AipuBean();
        stakeholderList = new ArrayList<Stakeholder>();
        List<Stakeholder> temp = aipu.getAllStakeholder();
        for(int i = 0; i < temp.size(); i++){
            stakeholderList.add(temp.get(i));

        }
        return stakeholderList;
    }

    public List<Conference> getConferenceList() {
        Aipu aipu = new AipuBean();
        conferenceList = new ArrayList<Conference>();
        List<Conference> temp = aipu.getAllConferences();
        for(int i=0; i < temp.size(); i++){
            conferenceList.add(temp.get(i));
        }
        return conferenceList;
    }

    public void setConferenceList(List<Conference> conferenceList) {
        this.conferenceList = conferenceList;
    }

    public void saveStakeholder(){
        Aipu aipu = new AipuBean();
        aipu.saveStakeholder(name, type, email, website, selectedConferences);
        this.name = "";
        this.type = "";
        this.email = "";
        this.website = "";
    }

    public void modifyStakeholder(){

    }
}