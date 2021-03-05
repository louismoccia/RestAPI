package restapi.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import restapi.models.Dvd;

import java.sql.*;
import java.util.List;

@Repository
@Profile("database")
public class DvdDatabaseDao implements DvdDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DvdDatabaseDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Dvd add(Dvd dvd) {
        final String sql = "INSERT INTO dvd(title, releaseYear, director, rating, notes) " +
                "VALUES(?,?,?,?,?)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) ->{

            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, dvd.getTitle());
            stmt.setInt(2, dvd.getReleaseYear());
            stmt.setString(3, dvd.getDirector());
            stmt.setString(4, dvd.getRating());
            stmt.setString(5, dvd.getNotes());

            return stmt;
        }, keyHolder);

        dvd.setDvdId(keyHolder.getKey().intValue());

        return dvd;
    }

    @Override
    public List<Dvd> getAll() {
        final String sql = "SELECT * FROM dvd";

        return jdbcTemplate.query(sql, new DvdMapper());
    }

    @Override
    public Dvd findDvdById(int id) {
        final String sql = "SELECT * FROM dvd WHERE dvdId = ?";

        return jdbcTemplate.queryForObject(sql, new DvdMapper(), id);
    }

    @Override
    public List<Dvd> findDvdByTitle(String title) {
        final String sql = "SELECT * FROM dvd WHERE title = ?";

        return jdbcTemplate.query(sql, new DvdMapper(), title);
    }

    @Override
    public List<Dvd> findDvdByReleaseYear(int year) {
        final String sql = "SELECT * FROM dvd WHERE releaseYear = ?";

        return jdbcTemplate.query(sql, new DvdMapper(), year);
    }

    @Override
    public List<Dvd> findDvdByDirector(String name) {
        final String sql = "SELECT * FROM dvd WHERE director LIKE ?";

        return jdbcTemplate.query(sql, new DvdMapper(), "%" + name + "%");
    }

    @Override
    public List<Dvd> findDvdByRating(String rating) {
        final String sql = "SELECT * FROM dvd WHERE rating = ?";

        return jdbcTemplate.query(sql, new DvdMapper(), rating);
    }

    @Override
    public boolean update(Dvd dvd) {
        final String sql = "UPDATE dvd SET title = ?, releaseYear = ?, director = ?, " +
                "rating = ?, notes = ? WHERE dvdId = ?";

        return jdbcTemplate.update(sql, dvd.getTitle(), dvd.getReleaseYear(), dvd.getDirector(),
                dvd.getRating(), dvd.getNotes(), dvd.getDvdId()) > 0;
    }

    @Override
    public boolean delete(int id) {
        final String sql = "DELETE FROM dvd WHERE dvdId = ?";

        return jdbcTemplate.update(sql, id) > 0;
    }

    private static final class DvdMapper implements RowMapper<Dvd>{

        @Override
        public Dvd mapRow(ResultSet rs, int i) throws SQLException {
            Dvd dvd = new Dvd();
            dvd.setDvdId(rs.getInt("dvdId"));
            dvd.setTitle(rs.getString("title"));
            dvd.setReleaseYear(rs.getInt("releaseYear"));
            dvd.setDirector(rs.getString("director"));
            dvd.setRating(rs.getString("rating"));
            dvd.setNotes(rs.getString("notes"));

            return dvd;
        }
    }
}
