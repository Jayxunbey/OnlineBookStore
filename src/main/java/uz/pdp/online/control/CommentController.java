package uz.pdp.online.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/comment")
public class CommentController {


    @PostMapping("/add")
    private String addComment(){



        return "";
    }

    @PostMapping("/delete")
    private String deleteComment(){

        return "";
    }


}
