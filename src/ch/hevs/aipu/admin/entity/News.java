package ch.hevs.aipu.admin.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class News implements Comparable<News>, Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String text;
    private Date published;

    //constructor
    public News(){};
    public News(String title, String text, Date published){
        this.title = title;
        this.text = text;
        this.published = published;
    }

    //getter and setter
    public Long getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public Date getPublished() {return published;}
    public void setPublished(Date published) {this.published = published;}

    @Override
    public int compareTo(News n) {
        if (this.getPublished().before(n.getPublished())){
            return 1;
        } else if (this.getPublished().after(n.getPublished())) {
            return -1;
        } else {
            return 0;
        }
    }
}
