package controller;

import exceptions.NotFindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by linhtran on 09/06/2017.
 */
@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(NotFindException.class)
    public ModelAndView  handleIOException(NotFindException ex) {
        System.out.println(ex.getMessage());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/home");
        return  modelAndView;
    }
}
