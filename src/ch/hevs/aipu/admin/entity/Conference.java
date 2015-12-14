package ch.hevs.aipu.admin.entity;

import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.annotations.Persistent;
import javax.persistence.*;

@Entity
public class Conference implements Comparable<Conference>, Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Key id;
    private String title;
    private Date start;
    private Date end;
    private String room;
    private String website;
    @Persistent
    @Column(nullable = true)
    private List<Key> stakeholders = new ArrayList<>();

    //Constructor
    public Conference(){
        this.stakeholders = new ArrayList<>();
    };

    public Conference(String title, Date start, Date end, String room, String website, List<Key> stakeholders) {
        this.stakeholders = new ArrayList<>();
        this.title = title;
        this.start = start;
        this.end = end;
        this.room = room;
        this.website = website;
        this.stakeholders = stakeholders;
    }

    //getter and setter
    public Key getId() {return id;}
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

    public List<Key> getStakeholders() {
        return stakeholders;
    }

    public void setStakeholders(List<Key> stakeholders) {
        this.stakeholders = stakeholders;
    }

    public void addStakeholder(Key key){
        System.out.println(key);
        stakeholders.add(key);

    }

    @Override
    public int compareTo(Conference o) {
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Conference)) {
            return false;
        }
        Conference conference = (Conference) obj;
        return (this.id.getId() == conference.id.getId());
    }
}