/* package ch.hevs.aipu.admin.managedBean;

import ch.hevs.aipu.admin.controller.NewsController;
import ch.hevs.aipu.admin.model.Conference;
import ch.hevs.aipu.admin.model.News;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ManagedBean
@SessionScoped
public class ConferenceBean implements Serializable{

    private static final long serialVersionUID = 1L;

    private Long id;
    private String title;
    private String start;
    private String end;
    private String room;
    private String website;
    private List<Conference> conferenceList = new ArrayList<Conference>();
    //private transient NewsController nc;
    @PostConstruct
    public void initialize(){

        //nc = new NewsController();
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

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
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

    public List<Conference> getConferenceList(){
        NewsController nc = new NewsController();
        this.newsList = nc.getNewsList();
        return this.newsList;}
    public void setNewsList(List<News> newsList){this.newsList = newsList;}

    public void insertNews(){
        NewsController nc = new NewsController();
        nc.setNews(title, text);

        this.title = "";
        this.text = "";
    }

    public void deleteNews(){
        NewsController nc = new NewsController();
        Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String action = params.get("action");
        Long id = Long.parseLong(action);
        nc.deleteNewsById(id);
    }

    public String modifyNews(){
        NewsController nc = new NewsController();
        Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String action = params.get("action");
        Long id = Long.parseLong(action);
        News news = nc.modifyNewsById(id);
        this.id = news.getId();
        this.title = news.getTitle();
        this.text = news.getText();
        this.publishedDate = news.getPublishedDate();
        return "Succes";
    }

    public String updateNews(){
        NewsController nc = new NewsController();
        News news = new News();
        news.setId(this.id);
        news.setTitle(this.title);
        news.setText(this.text);
        nc.updateNews(news);
        this.id = null;
        this.title = "";
        this.text = "";
        this.publishedDate = "";
        return "Succes";
    }
}
*/