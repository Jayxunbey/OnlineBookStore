package uz.pdp.online.service;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import uz.pdp.online.rowmapper.UserRowMapper;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
@Component
//@Repository
@RequiredArgsConstructor
public class CustomUserDeatilsService implements UserDetailsService {

    private final JdbcTemplate jdbcTemplate;
    private final UserRowMapper userRowMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("SSS");

        uz.pdp.online.entity.User userRep = jdbcTemplate.queryForObject(
                "SELECT * from users where user_name = ?",
                userRowMapper,
                username);

        List<String> strings = jdbcTemplate.queryForList(
                "select r.name from user_role ur  inner join role r on ur.role_id = r.id where ur.user_id = ?",
                String.class, userRep.getId());

        String[] array = strings.toArray(String[]::new);

        System.out.println(getClass().getName()+" ning ichida: ");
        System.out.println("array = " + Arrays.toString(array));

        return User.builder()
                .username(userRep.getUsername())
                .password(userRep.getPassword())
                .roles(array)
                .build();
    }
}
