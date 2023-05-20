package tec.bd.movies.repository;

public class Queries {

    /**
     * Queries for Movies
     */
    public static final String MOVIES_FIND_ALL_QUERY  = "select m.id, m.title, m.release_date, m.category_id, c.category_name " +
            "from movie as m join category as c on m.category_id = c.id order by m.id asc";
    public static final String MOVIES_FIND_BY_ID_QUERY = "select m.id, m.title, m.release_date, m.category_id, c.category_name " +
            "from movie as m join category as c on m.category_id = c.id where m.id = ?";
    public static final String MOVIES_INSERT_QUERY = "insert into movie(title, release_date, category_id) values (?, ?, ?)";

    public static final String MOVIES_CREATE_MOVIE_PROC_CALL = "call create_movie(?, ?, ?, ?)";

    public static final String MOVIES_DELETE_MOVIE_ID = "delete from movie where id = ?";

    public static final String MOVIES_UPDATE_MOVIE = "update movie set title = ?, release_date = ?, category_id = ? where id = ?";

    public static final String CATEGORY_FIND_BY_ID_QUERY = "select id, category_name from category where id = ?";
}
