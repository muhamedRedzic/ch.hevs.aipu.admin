package ch.hevs.aipu.admin.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
public class Conference implements Comparable<Conference>, Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Date start;
    private Date end;
    private String room;
    private String website;
    @OneToMany(mappedBy = "conference")
    private List<Stakeholder> stakeholders;

    //Constructor
    public Conference(){
        stakeholders = new ArrayList<>();
    };

    public Conference(String title, Date start, Date end, String room, String website, List<Stakeholder> stakeholders) {
        stakeholders = new ArrayList<>();
        this.title = title;
        this.start = start;
        this.end = end;
        this.room = room;
        this.website = website;
        this.stakeholders = stakeholders;
    }

    //getter and setter
    public Long getId() {return id;}
    public String getTitle() {return title;
    }
    public void setTitle(String title) {this.title = title;}
    public Date getStart() {
        return start;
    }
    public void setStart(Date start) {
        this.start = start;
    }
    public Date getEnd() {
        return end;
    }
    public void setEnd(Date end) {
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
    public List<Stakeholder> getStakeholders() {return stakeholders;}
    public void setStakeholders(List<Stakeholder> stakeholders) {this.stakeholders = stakeholders;}


    @Override
    public int compareTo(Conference o) {
        return 0;
    }
}