package ch.hevs.aipu.admin.entity;

import com.google.appengine.api.datastore.Key;


import javax.annotation.Nullable;
import javax.jdo.annotations.Persistent;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
public class Stakeholder implements Comparable<Stakeholder>, Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Key id;
    private String name;
    private String type;
    private String email;
    private String website;
    @Persistent
    @Column(nullable = true)
    private List<Key> conferences;

    /*@ManyToOne(fetch = FetchType.LAZY, optional = true)
    //@Column(nullable = true)
    private Conference conference;*/

    //constructor
    public Stakeholder(){};

    public Stakeholder(String name, String type, String email, String website, List<Key> conferences) {
        this.name = name;
        this.type = type;
        this.email = email;
        this.website = website;
        this.conferences = conferences;
    }

    //getter and setter
    public Key getId() {
        return id;
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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getWebsite() {
        return website;
    }
    public void setWebsite(String website) {
        this.website = website;
    }
    public List<Key> getConferences() {
        return conferences;
    }

    public void setConferences(List<Key> conferences) {
        this.conferences = conferences;
    }
    @Override
    public int compareTo(Stakeholder o) {
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Stakeholder)) {
            return false;
        }
        Stakeholder stakeholder = (Stakeholder) obj;
        return (this.id.getId() == stakeholder.id.getId());
    }

    public void addConference(Key key){
        conferences.add(key);
    }
}

