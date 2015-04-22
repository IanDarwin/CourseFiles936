import javax.ejb.*;
import javax.persistence.*;
import java.util.*;

@Singleton
public class FeedUpdater {	// Same data for all users
@PersistenceContext EntityManager em;

List<News> list;

@PostConstruct
public void setup() {
list = // Get the RSS feeds into memory
}
public List<News> getNews(String category) {
return list;
}
}

