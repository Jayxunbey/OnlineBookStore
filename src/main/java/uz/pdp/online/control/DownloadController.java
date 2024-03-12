package uz.pdp.online.control;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uz.pdp.online.dao.BookDao;
import uz.pdp.online.dao.UserDao;
import uz.pdp.online.dao.impls.RatingDaoImpls;
import uz.pdp.online.entity.Book;
import uz.pdp.online.entity.Rating;
import uz.pdp.online.entity.User;
import uz.pdp.online.service.BookService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/books/download")
public class DownloadController {

    private final BookService bookService;
    private final BookDao bookDao;
    private final RatingDaoImpls ratingDao;
    private final UserDao userDao;




    @GetMapping("/book")
    public String  viewForDownload(Model model, @RequestParam(value = "id")  String id ){
        Book book = bookDao.getById(id);

        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        System.out.println("authentication = " + authentication);

        System.out.println("authentication.getName() = " + authentication.getName());

        User user = userDao.getByUserName(authentication.getName());

        Rating rating = ratingDao.getRatingIfAvailable(user, id);
        System.out.println("rating = " + rating);
        if (rating!=null) {
            model.addAttribute("rating",rating);
        }else
        {
            Rating customRating = new Rating();
            customRating.setRate(0);
            customRating.setAverageOfAll(ratingDao.getAverageOffAll(id));
            model.addAttribute("rating",customRating);
        }

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
