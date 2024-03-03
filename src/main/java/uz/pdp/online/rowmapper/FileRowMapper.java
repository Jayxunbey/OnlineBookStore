package uz.pdp.online.rowmapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import uz.pdp.online.entity.FileDetailes;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class FileRowMapper implements RowMapper<FileDetailes> {
    @Override
    public FileDetailes mapRow(ResultSet rs, int rowNum) throws SQLException {
       return FileDetailes.builder()
                .id(rs.getLong("id"))
                .fileName(rs.getString("file_name"))
                .fileAbsolutePath(rs.getString("file_absolute_path"))
                .extension(rs.getString("extension"))
                .size((long) rs.getDouble("size"))
                .bookId(rs.getString("book_id"))
                .build();
    }
}
