package uz.pdp.online.dao.impls;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import uz.pdp.online.dao.RatingDao;
import uz.pdp.online.entity.Rating;
import uz.pdp.online.entity.User;

@Repository
@RequiredArgsConstructor
public class RatingDaoImpls implements RatingDao {

    private final JdbcTemplate jdbcTemplate;
    @Override
    public Rating save(Rating rating) {

        int update = jdbcTemplate.update(
                "INSERT INTO rating(user_id, book_id, rate) VALUES (?,?,?)",
                rating.getUserId(), rating.getBookId(), rating.getRate());

        if (update>0) return rating;

        return null;
    }

    @Override
    public Rating getById(Integer bookId) {
        return null;
    }

    @Override
    public Rating update(Rating rating) {

        jdbcTemplate.update(
                "update rating set rate = ? where user_id = ? and book_id = ?",
                rating.getRate(), rating.getUserId(), rating.getBookId());

        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    public Rating getRatingByUserIdAndBookId(Long userId, String bookId) {
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(
                "SELECT * FROM rating where user_id = ? and book_id = ?"
                , userId,bookId);

        if (sqlRowSet.next()) {
            return new Rating(sqlRowSet.getInt("id"),
                    sqlRowSet.getInt("user_id"),
                    sqlRowSet.getString("book_id"),
                    sqlRowSet.getInt("rate"),
                    getAverageOffAll(bookId));
        }else {
            return null;
        }


    }

    public float getAverageOffAll(String bookId) {

        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(
                "SELECT avg(rate) as average FROM rating where book_id=?",bookId);

        if (sqlRowSet.next()) {
            System.out.println("sqlRowSet = " + sqlRowSet);
//            return sqlRowSet.getInt("average");
            float average = sqlRowSet.getFloat("average");
            average = (float) ((int) (average * 10));
            return average/10;
        }
        return 0;
    }

    public boolean hasRatingForBook(User user, String bookId) {

        Rating rating = getRatingByUserIdAndBookId(user.getId(),bookId);
        return rating != null;
    }

    public Rating getRatingIfAvailable(User user, String bookId) {

        return getRatingByUserIdAndBookId(user.getId(),bookId);
    }
}
