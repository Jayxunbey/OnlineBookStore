package uz.pdp.online.control;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import uz.pdp.online.dao.BookDao;
import uz.pdp.online.entity.Book;
import uz.pdp.online.service.BookService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/books/download")
public class DownloadController {

    private final BookService bookService;
    private final BookDao bookDao;

    @GetMapping("/book")
    public String  viewForDownload(Model model, @RequestParam(value = "id")  String id ){
        Book book = bookDao.getById(id);

        System.out.println(".......... //books/download/book");
        System.out.println("book = " + book);

        model.addAttribute("book", bookDao.getById(id));
        return "book_download_page";
    }

    @GetMapping("/file")
    public ResponseEntity<Resource> downloadBook(Model model, @RequestParam("filename") String bookId){

//        Book book = bookService.getBookById(bookId);

        Book book = bookDao.getById(bookId);


        System.out.println("Download book ishladi");
        System.out.println("filename = " + bookId);

        if (book==null){
            return ResponseEntity.notFound().allow(HttpMethod.GET).build();
        }

        FileSystemResource fileSystemResource = new FileSystemResource(book.getFile().getFileAbsolutePath());

       return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF)
                .header("Content-disposition", "attachment;filename=%s.%s".formatted(book.getName(),book.getFile().getExtension()))
                .body(fileSystemResource);


    }

}
