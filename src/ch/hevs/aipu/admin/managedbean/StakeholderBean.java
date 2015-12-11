package ch.hevs.aipu.admin.managedbean;

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
import java.util.List;
import java.util.Map;

@ManagedBean(name = "StakeholderBean")
@SessionScoped
public class StakeholderBean implements Serializable{

    private static final long serialVersionUID = 1L;

    private Key id;
    private String type;
    private String name;
    private String website;
    private String email;

    private List<Stakeholder> stakeholderList;

    @PostConstruct
    public void initialize(){
        Aipu aipu = new AipuBean();
        stakeholderList = new ArrayList<Stakeholder>();
    }

    public Key getId() {
        return id;
    }

    public void setId(Key id) {
        this.id = id;
    }

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
        System.out.print(action);
        Key id = KeyFactory.createKey("Stakeholder",Long.parseLong(action));
        Aipu aipu = new AipuBean();
        aipu.deleteStakeholder(id);
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

    public void saveStakeholder(){
        Aipu aipu = new AipuBean();
        aipu.saveStakeholder(name, type, email, website);
        this.name = "";
        this.type = "";
        this.email = "";
        this.website = "";
    }

    public void modifyStakeholder(){

    }
}