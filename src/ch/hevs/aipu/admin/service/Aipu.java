package ch.hevs.aipu.admin.service;

import ch.hevs.aipu.admin.entity.Conference;
import ch.hevs.aipu.admin.entity.News;
import ch.hevs.aipu.admin.entity.Stakeholder;

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
    //conference
    public Conference getConference(Long conferenceId);
    public List<Conference> getAllConferences();
    public void saveConference(String title, Date start, Date end, String room, String website, List<Stakeholder> stakeholders);
    public void deleteConference(Long conferenceId);
    //stakeholder
    public News getStakeholder(Long StakeholderId);
    public List<Stakeholder> getAllStakeholder();
    public void saveStakeholder(String name, String type, String email, String website);
    public void deleteStakeholder(Long StakeholderId);
}
