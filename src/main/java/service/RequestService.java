package service;

import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import java.util.List;

/**
 * Created by linhtran on 07/06/2017.
 */

@Component
public class RequestService<E> {
    public  static final String LIST = "postList";
    public  static final String PAGE = "page";
    public static final   String PAGE_ACTIVE = "active";
    public  static  final String MESSAGE = "error";
    public  static  final String TOTAL_LIST = "totalList";
    public  static  final String QUERY_SEARCH = "querySearch";
    public static  final String  CONF = "conf";

    public void setResponse(ModelMap  modelMap, List<E> postList,int totalList,int page,String  pageActive,String error,String query)
    {
        modelMap.addAttribute(LIST,postList);
        modelMap.addAttribute(TOTAL_LIST,totalList);
        modelMap.addAttribute(PAGE,page);
        modelMap.addAttribute(PAGE_ACTIVE,pageActive);
        modelMap.addAttribute(MESSAGE,error);
        modelMap.addAttribute(QUERY_SEARCH,query);
    }

    public void setResponse(ModelMap  modelMap, List<E> postList,int totalList,int page,String  pageActive,String error)
    {
        modelMap.addAttribute(LIST,postList);
        modelMap.addAttribute(TOTAL_LIST,totalList);
        modelMap.addAttribute(PAGE,page);
        modelMap.addAttribute(PAGE_ACTIVE,pageActive);
        modelMap.addAttribute(MESSAGE,error);
    }

    public void setResponse(ModelMap  modelMap, List<E> postList,int totalList,int page)
    {
        modelMap.addAttribute(LIST,postList);
        modelMap.addAttribute(TOTAL_LIST,totalList);
        modelMap.addAttribute(PAGE,page);
    }

}
