package controller.exception;

import exceptions.AccessDenieException;
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
    public ModelAndView handlerNotFindException(NotFindException ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ProcessExceptions");
        modelAndView.addObject("error", ex.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(AccessDenieException.class)
    public ModelAndView handlerAccessDeniesException(AccessDenieException ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("error", ex.getMessage());
        modelAndView.setViewName("ProcessExceptions");
        return modelAndView;
    }

}
