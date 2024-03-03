package uz.pdp.online.control;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorContext;
import jakarta.validation.constraintvalidation.ValidationTarget;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import uz.pdp.online.dao.UserDao;
import uz.pdp.online.entity.User;
import uz.pdp.online.reqobj.ReqLognObjBody;
import uz.pdp.online.reqobj.ReqSignObjBody;

import java.util.Arrays;
import java.util.function.BiConsumer;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {



    private final UserDao userDao;



    @GetMapping("/login")
    public String login(Model model){
//        model.addAttribute("bindingResult");
        model.addAttribute("logdto", new ReqLognObjBody());
        return "login";
    }

    @GetMapping("/signup")
    public String signup( Model model){
        model.addAttribute("dto",new ReqSignObjBody());
        return "signup";
    }




    //    @ResponseBody
//    @GetMapping("")
    @GetMapping
    public String hom(ModelAndView modelAndView){

        return "redirect: /auth/login";

//        modelAndView.setViewName("signup");
//        modelAndView.addObject("isSignUp",Boolean.TRUE);
//
//        return modelAndView;
    }


    @PostMapping(value = "/signup")
    public String home(@ModelAttribute("dto") @Valid ReqSignObjBody signObj,
                       Errors result, Model model){





        if (result.hasErrors()) {
            return "signup";

        }

        if (!signObj.getNew_password().equals(signObj.getRepeat_password())){
            model.addAttribute("passwordNotEqual", "passwords not equal");
            model.addAttribute("signFormValues", signObj);
            System.out.println("Pasportlar teng emas");
            return "signup";
        }

        User savedUser = userDao.save(
                User.builder()
                        .username(signObj.getUsername())
                        .email(signObj.getEmail())
                        .password(signObj.getNew_password())
                        .build()
        );

        if (savedUser==null) {
            model.addAttribute("cannotRegister", "Cannot Register. May be already you registered");
            model.addAttribute("signFormValues", signObj);
            return "signup";
        }

        ReqLognObjBody lognObjBody = new ReqLognObjBody();
        lognObjBody.setUsername(savedUser.getUsername());
        lognObjBody.setPassword(savedUser.getPassword());

        model.addAttribute("logdto", lognObjBody);
        return "login";
    }

    @PostMapping("/login")
    public String home2(@ModelAttribute("logdto") @Valid ReqLognObjBody objBody, Errors errors, Model model){

        System.out.println("objBody.getPassword() = " + objBody.getPassword());
        System.out.println("objBody.getUsername() = " + objBody.getUsername());

        System.out.println("errors.hasErrors() = " + errors.hasErrors());

        if (errors.hasErrors()) {
            return "login";
        }


        User user;
        try {
            user = userDao.getByUserName(objBody.getUsername());
        } catch (EmptyResultDataAccessException e) {
            model.addAttribute("cannotRegister", "Connot register. May be this account not found or password change");

            return "signup";        }

        System.out.println("user.getUserName() = " + user.getUsername());
        System.out.println("user.getPassword() = " + user.getPassword());



        model.addAttribute("user", user);
        return "books_page";
    }

    @GetMapping("/kino")
    @ResponseBody
    public String home3(HttpServletRequest request){

        String url;
        System.out.println("request.getServletPath() = " + request.getServletPath());
        System.out.println("request.getQueryString() = " + request.getQueryString());
        url = request.getRequestURI();
        return url;
    }

//    @ExceptionHandler({EmptyResultDataAccessException.class})
//    public String handlerNotFound(Model model, EmptyResultDataAccessException e){
//        model.addAttribute("error", e.getMessage());
//        return "register";
//    }

}
