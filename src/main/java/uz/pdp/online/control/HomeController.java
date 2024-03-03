package uz.pdp.online.control;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import uz.pdp.online.dao.BookDao;
import uz.pdp.online.dao.impls.BookDaoImpls;
import uz.pdp.online.entity.Book;
import uz.pdp.online.repo.BooksDatabase;
import uz.pdp.online.service.BookService;

import java.util.List;

@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class HomeController {

    private final BooksDatabase booksDatabase;
    private final BookDaoImpls bookDaoImpls;

    @GetMapping
    public String bookPage(Model model) {
        System.out.println("Books Get isshladi");
        List<Book> bookLists = bookDaoImpls.getBookLists();
        model.addAttribute("bookList" , bookLists);
        model.addAttribute("userName", "Jayxunbey");

        System.out.println("............................. At returning Books");
        for (Book book : bookLists) {
            System.out.println("book = " + book);
        }
        return "books_page";
    }

}
