package ejb3;

import javax.ejb.*;
import javax.persistence.*;
import java.util.*;
import javax.annotation.PostConstruct;

@Singleton
public class FeedUpdater {	// Same data for all users
	@PersistenceContext EntityManager em;

	List<News> list;

	@PostConstruct
	public void setup() {
		list = getNewsFeeds(); // Get the RSS feeds into memory
	}
	public List<News> getNews(String category) {
		return list;
	}

	/** Simple method to get news feeds
	 * XXX Add EJB Timer annotations here to refresh periodically
	 * XXX Change em to be an emf, createEntityManager inside this method
	 */
	List<News> getNewsFeeds() {
		return null;
	}

	private class News {
		// empty
	}
}

