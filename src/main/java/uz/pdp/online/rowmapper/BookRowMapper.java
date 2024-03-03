package uz.pdp.online.rowmapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import uz.pdp.online.entity.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class BookRowMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Book.builder()
                .id(rs.getString("id"))
                .name(rs.getString("name"))
                .genre(rs.getString("genre"))
                .year(rs.getString("year"))
                .build();
    }
}
