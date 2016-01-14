package ch.hevs.aipu.admin.service;

import ch.hevs.aipu.admin.entity.Conference;
import ch.hevs.aipu.admin.entity.News;
import ch.hevs.aipu.admin.entity.Stakeholder;
import com.google.appengine.api.datastore.Key;

import java.util.Date;
import java.util.List;

/**
 * Created by Muhamed on 03.12.2015.
 */
public interface Aipu {
    //news
    public News getNews(Long newsId);
    public List<News> getAllNews();
    public void saveNews(String title, String text);
    public void deleteNews(Long newsId);
    public void updateNews(Long id, String title, String text);
    //conference
    public Conference getConference(long conferenceId);
    public List<Conference> getAllConferences();
    public void saveConference(String title, Date start, Date end, String room, String website,List<Stakeholder> stakeholders);
    public void deleteConference(long conferenceId);
    //stakeholder
    public Stakeholder getStakeholder(long StakeholderId);
    public List<Stakeholder> getAllStakeholder();
    public void saveStakeholder(String name, String type, String email, String website, List<Conference> conferences);
    public void deleteStakeholder(long StakeholderId);
    public void updateStakeholder(Long id,String type ,String name, String website,String email,List<Conference> conferences);
}
