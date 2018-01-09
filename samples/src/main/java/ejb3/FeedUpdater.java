package ejb3;

import javax.ejb.*;
import javax.persistence.*;
import java.util.*;
import javax.annotation.PostConstruct;

@Singleton
public class FeedUpdater {	// Same data for all users
	// Singletons must run multi-threaded:
	@PersistenceUnit EntityManagerFactory emf;

	List<News> list;

	@PostConstruct
	public void setup() {
		list = getNewsFeeds(); // Get the RSS feeds into memory
	}
	public List<News> getNews(String category) {
		return list;
	}

	/** Simple method to get news feeds.
	 * Could add EJB Timer annotations here to refresh periodically
	 */
	List<News> getNewsFeeds() {
		EntityManager em = emf.createEntityManager();
		return em.createQuery("from News order by date desc", News.class).getResultList();
	}

	private class News {
		// empty
	}
}

