package ch.hevs.aipu.admin.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Stakeholder implements Comparable<Stakeholder>, Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private String name;
    private String type;
    private String email;
    private String website;

    //constructor
    public Stakeholder(){};

    public Stakeholder(String name, String type, String email, String website) {
        this.name = name;
        this.type = type;
        this.email = email;
        this.website = website;
    }

    //getter and setter
    public Long getId() {
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

    @Override
    public int compareTo(Stakeholder o) {
        return 0;
    }
}

