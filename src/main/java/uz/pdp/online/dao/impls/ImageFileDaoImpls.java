package uz.pdp.online.dao.impls;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import uz.pdp.online.dao.ImageFileDao;
import uz.pdp.online.entity.FileDetailes;
import uz.pdp.online.rowmapper.FileRowMapper;

@Repository
@RequiredArgsConstructor
public class ImageFileDaoImpls implements ImageFileDao {
    private final JdbcTemplate jdbcTemplate;
    private final FileRowMapper fileRowMapper;
    @Override
    public FileDetailes save(FileDetailes fileD) {
        jdbcTemplate.update("insert into image(file_name, file_absolute_path, extension, size, book_id) VALUES(?,?,?,?,?)",
                fileD.getFileName(), fileD.getFileAbsolutePath(),fileD.getExtension(),fileD.getSize(), fileD.getBookId());


        return new FileDetailes();
    }

    private FileDetailes getByFileName(String fileName) {
        return jdbcTemplate.queryForObject(
                "SELECT * from file where file_name = ?",
                fileRowMapper,
                fileName);

    }

    @Override
    public FileDetailes getById(String bookId) {
        return jdbcTemplate.queryForObject(
                "SELECT * from image where book_id = ?",
                fileRowMapper,
                bookId);
    }

    @Override
    public FileDetailes update(FileDetailes fileDetailes) {
        return null;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }
}
