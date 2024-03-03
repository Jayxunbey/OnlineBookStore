package uz.pdp.online.dao.impls;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import uz.pdp.online.rowmapper.UserRowMapper;
import uz.pdp.online.dao.UserDao;
import uz.pdp.online.entity.User;

@Repository
@RequiredArgsConstructor
public class UserDaoImpls implements UserDao {

    private final JdbcTemplate jdbcTemplate;
    private final UserRowMapper userRowMapper;
    @Override
    public User save(User user) {

        jdbcTemplate.update("insert into users(user_name, email, password) VALUES(?,?,?)",
                            user.getUsername(), user.getEmail(), user.getPassword());
        return user;
    }

    @Override
    public User getById(Long id) {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public User getByUserName(String userName) {

        return jdbcTemplate.queryForObject(
                "SELECT * from users u where user_name = ?",
                userRowMapper,
                userName);
    }
}
