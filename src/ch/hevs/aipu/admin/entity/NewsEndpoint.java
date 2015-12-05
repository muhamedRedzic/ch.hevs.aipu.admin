package ch.hevs.aipu.admin.entity;

import ch.hevs.aipu.admin.service.EMF;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.Cursor;
import org.datanucleus.store.appengine.query.JPACursorHelper;

import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Named;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityManager;
import javax.persistence.Query;

@Api(name = "newsendpoint", namespace = @ApiNamespace(ownerDomain = "hevs.ch", ownerName = "hevs.ch", packagePath = "aipu.admin.entity"))
public class NewsEndpoint {

	/**
	 * This method lists all the entities inserted in datastore.
	 * It uses HTTP GET method and paging support.
	 *
	 * @return A CollectionResponse class containing the list of all entities
	 * persisted and a cursor to the next page.
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	@ApiMethod(name = "listNews")
	public CollectionResponse<News> listNews(
			@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("limit") Integer limit) {

		EntityManager mgr = null;
		Cursor cursor = null;
		List<News> execute = null;

		try {
			mgr = getEntityManager();
			Query query = mgr.createQuery("select News from News as News");
			if (cursorString != null && cursorString != "") {
				cursor = Cursor.fromWebSafeString(cursorString);
				query.setHint(JPACursorHelper.CURSOR_HINT, cursor);
			}

			if (limit != null) {
				query.setFirstResult(0);
				query.setMaxResults(limit);
			}

			execute = (List<News>) query.getResultList();
			cursor = JPACursorHelper.getCursor(execute);
			if (cursor != null)
				cursorString = cursor.toWebSafeString();

			// Tight loop for fetching all entities from datastore and accomodate
			// for lazy fetch.
			for (News obj : execute)
				;
		} finally {
			mgr.close();
		}

		return CollectionResponse.<News> builder().setItems(execute)
				.setNextPageToken(cursorString).build();
	}

	/**
	 * This method gets the entity having primary key id. It uses HTTP GET method.
	 *
	 * @param id the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
	@ApiMethod(name = "getNews")
	public News getNews(@Named("id") Long id) {
		EntityManager mgr = getEntityManager();
		News news = null;
		try {
			news = mgr.find(News.class, id);
		} finally {
			mgr.close();
		}
		return news;
	}

	/**
	 * This inserts a new entity into App Engine datastore. If the entity already
	 * exists in the datastore, an exception is thrown.
	 * It uses HTTP POST method.
	 *
	 * @param news the entity to be inserted.
	 * @return The inserted entity.
	 */
	@ApiMethod(name = "insertNews")
	public News insertNews(News news) {
		EntityManager mgr = getEntityManager();
		try {
			if (containsNews(news)) {
				throw new EntityExistsException("Object already exists");
			}
			mgr.persist(news);
		} finally {
			mgr.close();
		}
		return news;
	}

	/**
	 * This method is used for updating an existing entity. If the entity does not
	 * exist in the datastore, an exception is thrown.
	 * It uses HTTP PUT method.
	 *
	 * @param news the entity to be updated.
	 * @return The updated entity.
	 */
	@ApiMethod(name = "updateNews")
	public News updateNews(News news) {
		EntityManager mgr = getEntityManager();
		try {
			if (!containsNews(news)) {
				throw new EntityNotFoundException("Object does not exist");
			}
			mgr.persist(news);
		} finally {
			mgr.close();
		}
		return news;
	}

	/**
	 * This method removes the entity with primary key id.
	 * It uses HTTP DELETE method.
	 *
	 * @param id the primary key of the entity to be deleted.
	 */
	@ApiMethod(name = "removeNews")
	public void removeNews(@Named("id") Long id) {
		EntityManager mgr = getEntityManager();
		try {
			News news = mgr.find(News.class, id);
			mgr.remove(news);
		} finally {
			mgr.close();
		}
	}

	private boolean containsNews(News news) {
		EntityManager mgr = getEntityManager();
		boolean contains = true;
		try {
			News item = mgr.find(News.class, news.getId());
			if (item == null) {
				contains = false;
			}
		} finally {
			mgr.close();
		}
		return contains;
	}

	private static EntityManager getEntityManager() {
		return EMF.get().createEntityManager();
	}

}
