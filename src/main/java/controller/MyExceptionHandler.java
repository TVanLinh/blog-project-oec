package controller;

import org.apache.commons.lang3.exception.ExceptionUtils;

/**
 * Created by linhtran on 09/06/2017.
 */
//@ControllerAdvice
public class MyExceptionHandler {
//    @ExceptionHandler(RuntimeException.class)
//    @ResponseBody
    public String handleIOException(RuntimeException ex) {
        return "Error: " + ExceptionUtils.getStackTrace(ex);
    }
}
