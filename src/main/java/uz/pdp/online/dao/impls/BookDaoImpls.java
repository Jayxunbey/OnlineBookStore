package uz.pdp.online.dao.impls;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import uz.pdp.online.dao.BookDao;
import uz.pdp.online.dao.FileDao;
import uz.pdp.online.dao.ImageFileDao;
import uz.pdp.online.entity.Book;
import uz.pdp.online.entity.FileDetailes;
import uz.pdp.online.rowmapper.BookRowMapper;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookDaoImpls implements BookDao {

    private final JdbcTemplate jdbcTemplate;
    private final FileDao fileDao;
    private final ImageFileDao imageFileDao;
    private final BookRowMapper bookRowMapper;
    @Override
    public Book save(Book book) {
        jdbcTemplate.update("insert into book(id, name, genre, year) VALUES(?,?,?,?)",
                book.getId(), book.getName(), book.getGenre(), book.getYear());

        return book;
    }

    @Override
    public Book getById(String bookId) {

        Book book = jdbcTemplate.queryForObject(
                "SELECT * from book where id = ?",
                bookRowMapper,
                bookId);


        FileDetailes file = fileDao.getById(bookId);
        FileDetailes image = imageFileDao.getById(bookId);

        System.out.println(getClass().getName()+": "+file);
        System.out.println(getClass().getName()+": "+image);

        book.setFile(file);
        book.setPhoto(image);

        return book;


    }

    @Override
    public Book update(Book book) {
        return null;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    public List<Book> getBookLists() {

        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet("SELECT * From book");

        System.out.println("sqlRowSet.isFirst() = " + sqlRowSet.isFirst());

        List<Book> books = new ArrayList<>();

        while (sqlRowSet.next()) {


            System.out.println("...................................");
            System.out.println("sqlRowSet.getString(\"name\") = " + sqlRowSet.getString("name"));
            System.out.println("sqlRowSet.getString(\"genre\") = " + sqlRowSet.getString("genre"));

            FileDetailes file = fileDao.getById(sqlRowSet.getString("id"));
            FileDetailes image = imageFileDao.getById(sqlRowSet.getString("id"));

            System.out.println("file.getBookId() = " + file.getBookId());
            System.out.println("file.getFileAbsolutePath() = " + file.getFileAbsolutePath());
            System.out.println("file.getSize() = " + file.getSize());
            System.out.println("file.getFileName() = " + file.getFileName());


            Book book = new Book(sqlRowSet.getString("id"),
                    sqlRowSet.getString("name"),
                    sqlRowSet.getString("genre"),
                    sqlRowSet.getString("year"),
                    file, image, file.getSize());

            books.add(book);
        }


        return books;

    }
}
