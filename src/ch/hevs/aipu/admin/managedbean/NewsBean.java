package ch.hevs.aipu.admin.managedbean;

import ch.hevs.aipu.admin.entity.News;
import ch.hevs.aipu.admin.service.Aipu;
import ch.hevs.aipu.admin.service.AipuBean;
import com.google.appengine.api.datastore.Key;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.security.KeyFactory;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@ManagedBean(name = "NewsBean")
@SessionScoped
public class NewsBean implements Serializable{

    private static final long serialVersionUID = 1L;

    private Long id;
    private String title;
    private String text;
    private Date published;
    private List<News> newsList;

    @PostConstruct
    public void initialize(){
        Aipu aipu = new AipuBean();
    }

    //getter and setter
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public Date getPublished() {return published;}
    public void setPublished(Date published) {this.published = published;}

    public String getTitle(){
        return this.title;
    }
    public void setTitle(String title){
        this.title = title;
    }

    public String getText(){return this.text;}
    public void setText(String text){
        this.text = text;
    }

    public List<News> getNewsList(){
        Aipu aipu = new AipuBean();
        newsList = new ArrayList<News>();
        List<News> temp = aipu.getAllNews();

        for(int i = 0; i < temp.size(); i++){
            newsList.add(temp.get(i));
        }
        return newsList;
    }
    public void setNewsList(List<News> newsList){this.newsList = newsList;}

    //methods
    public void saveNews(){
        Aipu aipu = new AipuBean();
        aipu.saveNews(title, text);
        this.title = "";
        this.text = "";
    }

    public String modifyNews() {
        Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String action = params.get("action");
        Long id = Long.parseLong(action);
        Aipu aipu = new AipuBean();
        News news = aipu.getNews(id);
        this.id = news.getId();
        this.title = news.getTitle();
        this.text = news.getText();
        return "newsModify";
    }

    public String updateNews(){
        Aipu aipu = new AipuBean();
        aipu.updateNews(id, title, text);
        id = null;
        this.title = "";
        this.text = "";
        return "news";
    }

    public void deleteNews() {
        Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String action = params.get("action");
        Long id = Long.parseLong(action);
        Aipu aipu = new AipuBean();
        aipu.deleteNews(id);
    }
}
