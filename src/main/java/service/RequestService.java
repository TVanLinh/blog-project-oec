package service;

import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by linhtran on 07/06/2017.
 */

@Component
public class RequestService {
    public static final String LIST = "list";
    public  static final String PAGE = "page";

    public  static  final String MESSAGE = "error";
    public  static  final String TOTAL_LIST = "totalList";

    public  static  final  String SUCCESS = "success";
    public  static  final  String UPDATE_SUCCESS = "request.update_success";
    public  static  final  String UPDATE_NOT_SUCCESS = "update.not.success";
    public  static  final  String INSERT_SUCCESS = "request.insert.success";
    public  static  final  String DELETE_SUCCESS = "delete.success";
    public  static  final  String DELETE_NOT_SUCCESS = "delete.not.success";
    public  static  final  String INSERT_NOT_SUCCESS = "insert.not.success";
    public  static  final  String POST_APPROVE_SUCCESS = "post.approve.success";
    public  static  final  String POST_DELETE_NOT_SUCCESS= "post.approve.success";
    public  static  final  String POST_APPROVE_NOT_SUCCESS= "post.approve.not.success";
    public  static  final  String POST_APPROVED= "post.approved";
    public  static  final  String VALID_FIELD_POST_TITLE_NOT_BLANK = "validation.field.post_title_not_blank";
    public  static  final  String LIMIT = "limit";

    public static void setResponse(ModelMap modelMap, int limit, List List, BigInteger totalList)
    {
        modelMap.addAttribute(LIST,List);
        modelMap.addAttribute(TOTAL_LIST, totalList.intValue());
        modelMap.addAttribute(LIMIT,limit);
    }

    public static void setResponse(RedirectAttributes redirectAttributes,String page,String message){
        redirectAttributes.addFlashAttribute(RequestService.MESSAGE,message);
        redirectAttributes.addFlashAttribute(RequestService.PAGE,page);
    }

    public static void setResponse(RedirectAttributes redirectAttributes,String message){
        redirectAttributes.addFlashAttribute(RequestService.MESSAGE,message);
    }

}
