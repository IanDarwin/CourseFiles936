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

	/* stub method to get news feeds */
	List<News> getNewsFeeds() {
		return null;
	}

	private class News {
		// empty
	}
}

