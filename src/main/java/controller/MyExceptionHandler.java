package controller;

import exceptions.AccessDenieException;
import exceptions.NotFindException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by linhtran on 09/06/2017.
 */
@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(NotFindException.class)
    public ModelAndView handlerNotFindException(NotFindException ex, Model model) {
        System.out.println(ex.getMessage());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ProcessExceptions");
        modelAndView.addObject("error", ex.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(AccessDenieException.class)
    public ModelAndView handlerAccessDeniesException(AccessDenieException ex) {
        System.out.println(ex.getMessage());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("error", ex.getMessage());
        modelAndView.setViewName("ProcessExceptions");
        return modelAndView;
    }

}
