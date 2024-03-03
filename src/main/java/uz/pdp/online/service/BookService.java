package uz.pdp.online.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import uz.pdp.online.dao.BookDao;
import uz.pdp.online.dao.FileDao;
import uz.pdp.online.dao.ImageFileDao;
import uz.pdp.online.dao.impls.BookDaoImpls;
import uz.pdp.online.dao.impls.FileDaoImpls;
import uz.pdp.online.dao.impls.ImageFileDaoImpls;
import uz.pdp.online.dto.BookDto;
import uz.pdp.online.entity.Book;
import uz.pdp.online.entity.FileDetailes;
import uz.pdp.online.repo.BooksDatabase;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@PropertySource("classpath:/property/application.properties")
public class BookService {

    @Value("${file.saved.location.document}")
    private String pathFile;
    @Value("${file.saved.location.image}")
    private String pathImage;

    private final FileDao fileDao;
    private final FileDaoImpls fileDaoImpls;
    private final ImageFileDao imageFileDao;
    private final BookDao bookDao;
    private final BooksDatabase booksDatabase;
    private final FileService fileService;

    public void addBookToDatabase(BookDto bookDto) {



        Book book = new Book(bookDto.getName(), bookDto.getGenre(), bookDto.getYear());
        String bookId = book.getId();



        try {
            var documentFileDetails = fileService.saveFile(bookId, bookDto.getFile(), pathFile);
            var imageFileDetails = fileService.saveFile(bookId, bookDto.getImage(), pathImage);

            book.setSize(documentFileDetails.getSize());
            bookDao.save(book);

             fileDaoImpls.save(documentFileDetails);
             imageFileDao.save(imageFileDetails);

//            book.setFile(documentFileDetails);
//            book.setPhoto(imageFileDetails);
//
            System.out.println("Book service ishladi");

        } catch (IOException e) {
             throw new RuntimeException(e);
        }


    }

    public Book getBookById(String id){
        Optional<Book> first = booksDatabase.getBooks().stream()
                .filter(book -> book.getId().equals(id))
                .findFirst();
        return first.orElse(null);
    }

}
