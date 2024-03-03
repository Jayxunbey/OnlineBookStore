package uz.pdp.online.control;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import uz.pdp.online.dto.BookDto;
import uz.pdp.online.service.BookService;

@Controller
@RequestMapping("/upload")
@RequiredArgsConstructor
public class UploadController {

    private final BookService bookService;

    @GetMapping
    public ModelAndView uploadBookPage(ModelAndView modelAndView){
        modelAndView.setViewName("book_upload");
        return modelAndView;
    }

    @PostMapping
    public String inputPostValues(@ModelAttribute BookDto bookDto) {

        bookService.addBookToDatabase(bookDto);

        System.out.println("Uploadning Post Metodi ishladi");
        return "redirect:/books";

    }



}
