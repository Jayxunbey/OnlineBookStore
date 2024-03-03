package uz.pdp.online.exception_handler;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({EmptyResultDataAccessException.class})

    public String handlerNotFound(Model model,EmptyResultDataAccessException e){

        model.addAttribute("error", e.getMessage());
        return "register";

    }

}
