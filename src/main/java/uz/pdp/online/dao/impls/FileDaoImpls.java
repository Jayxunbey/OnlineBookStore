package uz.pdp.online.dao.impls;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import uz.pdp.online.dao.FileDao;
import uz.pdp.online.entity.FileDetailes;
import uz.pdp.online.rowmapper.FileRowMapper;

@Repository
@RequiredArgsConstructor
public class FileDaoImpls implements FileDao {
    private final JdbcTemplate jdbcTemplate;
    private final FileRowMapper fileRowMapper;
    @Override
    public FileDetailes save(FileDetailes fileD) {

        jdbcTemplate.update("insert into file(file_name, file_absolute_path, extension, size, book_id) VALUES(?,?,?,?,?)",
                fileD.getFileName(), fileD.getFileAbsolutePath(),fileD.getExtension(),fileD.getSize(), fileD.getBookId());

        return new FileDetailes();

    }

    @Override
    public FileDetailes getById(String bookId) {
        return jdbcTemplate.queryForObject(
                "SELECT * from file where book_id = ?",
                fileRowMapper,
                bookId);
    }

    @Override
    public FileDetailes update(FileDetailes fileDetailes) {
        return null;
    }

    @Override
    public boolean delete(String bookId) {
        return false;
    }

    public FileDetailes getByFileName(String fileName){
        return jdbcTemplate.queryForObject(
                "SELECT * from file where file_name = ?",
                fileRowMapper,
                fileName);
    }
}
