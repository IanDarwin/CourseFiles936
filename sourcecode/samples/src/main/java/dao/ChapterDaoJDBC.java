import javax.sql.*;

public class ChapterDaoJDBC {   // far from complete code
	DataSource ds = null; // set somehow
	public Chapter findByTitle(String title) {
		Chapter chapter = null;
		// lots of JDBC boilerplate; see page 5 in this chapter
		// Prepare stmt: "SELECT * FROM chapter WHERE name LIKE ?";
		// stmt.setString(1, title);
		// then create a Chapter object, get its fields from the ResultSet // clean up
		return chapter;
	}

	// Then do it all again for findById, etc.
}

