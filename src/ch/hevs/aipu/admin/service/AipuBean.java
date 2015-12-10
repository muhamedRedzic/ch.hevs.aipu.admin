package ch.hevs.aipu.admin.service;

import ch.hevs.aipu.admin.entity.Conference;
import ch.hevs.aipu.admin.entity.News;
import ch.hevs.aipu.admin.entity.Stakeholder;
import com.google.appengine.repackaged.org.joda.time.DateTime;
import com.google.appengine.repackaged.org.joda.time.DateTimeZone;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.*;

@Stateless
public class AipuBean implements Aipu, Serializable{
    private static final long serialVersionUID = 1L;
    EntityManager em = EMF.get().createEntityManager();

    @Override
    public News getNews(Long newsId) {
        News n = em.find(News.class, newsId);
        return n;
    }

    @Override
    public List<News> getAllNews() {
        try {
            Query query = em.createQuery("SELECT n FROM News n");
            List<News> results = (List<News>) query.getResultList();
            Collections.sort(results);
            return results;
        }finally {
            em.close();
        }
    }

    @Override
    public void saveNews(String title, String text) {
        try {
            Date date = swissTimeZone();
            News n = new News(title, text, date);
            em.persist(n);
        }finally {
            em.close();
        }
    }

    private Date swissTimeZone() {
        Date date = new Date();
        date.setTime(date.getTime() + 600000*6);
        return date;
    }

    @Override
    public void deleteNews(Long newsId) {
        try {
            News n = em.find(News.class, newsId);
            em.getTransaction().begin();
            em.remove(n);
            em.getTransaction().commit();
        }finally {
            em.close();
        }
    }

    @Override
    public void updateNews(Long id, String title, String text) {
        try {
            News n = em.find(News.class, id);
            n.setTitle(title);
            n.setText(text);
            n.setPublished(swissTimeZone());
        }finally {
            em.close();
        }
    }

    @Override
    public Conference getConference(Long conferenceId) {
        return null;
    }

    @Override
    public List<Conference> getAllConferences() {
        try {
            Query query = em.createQuery("SELECT c FROM Conference c");
            List<Conference> results = (List<Conference>) query.getResultList();
            Collections.sort(results);
            return results;
        }finally {
            em.close();
        }
    }

    @Override
    public void saveConference(String title, Date start, Date end, String room, String website, List<Stakeholder> stakeholders) {

    }

    @Override
    public void deleteConference(Long conferenceId) {

    }

    @Override
    public News getStakeholder(Long StakeholderId) {
        return null;
    }

    @Override
    public List<Stakeholder> getAllStakeholder() {
        try {
            Query query = em.createQuery("SELECT s FROM Stakeholder s");
            List<Stakeholder> results = (List<Stakeholder>) query.getResultList();
            Collections.sort(results);
            return results;
        }finally {
            em.close();
        }
    }

    @Override
    public void saveStakeholder(String name, String type, String email, String website) {
        try {
            Stakeholder s = new Stakeholder(name, type, email,website);
            em.persist(s);
        }finally {
            em.close();
        }
    }

    @Override
    public void deleteStakeholder(Long StakeholderId) {

        try {
            Stakeholder s = em.find(Stakeholder.class, StakeholderId);
            em.getTransaction().begin();
            em.remove(s);
            em.getTransaction().commit();
        }finally {
            em.close();
        }
    }
}
