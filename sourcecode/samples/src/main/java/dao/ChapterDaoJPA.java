import javax.persistence.*;

public class ChapterDaoJPA {    // Complete code
	@PersistenceContext EntityManager em;
	public Chapter findByTitle(String title) {
		return em.createQuery(
			"from Chapter c where c.title like ?1", Chapter.class).
			setParameter(1, title).
			getSingleResult();
	}
	public Chapter findById(long id) {
		return em.find(Chapter.class, id);
	}
}

