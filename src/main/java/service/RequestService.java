package service;

import entities.Post;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import java.util.List;

/**
 * Created by linhtran on 07/06/2017.
 */

@Component
public class RequestService {
    public  static final String POST_LIST = "postList";
    public  static final String PAGE = "page";
    public static final   String PAGE_ACTIVE = "active";
    public  static  final String MESSAGE = "error";
    public  static  final String TOTAL_LIST = "totalList";
    public  static  final String QUERY_SEARCH = "querySearch";

    public void setPostList(ModelMap  modelMap, List<Post> postList,int totalList,int page,String  pageActive,String error,String query)
    {
        modelMap.addAttribute(POST_LIST,postList);
        modelMap.addAttribute(TOTAL_LIST,totalList);
        modelMap.addAttribute(PAGE,page);
        modelMap.addAttribute(PAGE_ACTIVE,pageActive);
        modelMap.addAttribute(MESSAGE,error);
        modelMap.addAttribute(QUERY_SEARCH,query);
    }

    public void setPostList(ModelMap  modelMap, List<Post> postList,int totalList,int page,String  pageActive,String error)
    {
        modelMap.addAttribute(POST_LIST,postList);
        modelMap.addAttribute(TOTAL_LIST,totalList);
        modelMap.addAttribute(PAGE,page);
        modelMap.addAttribute(PAGE_ACTIVE,pageActive);
        modelMap.addAttribute(MESSAGE,error);
    }

    public void setPostList(ModelMap  modelMap, List<Post> postList,int totalList,int page)
    {
        modelMap.addAttribute(POST_LIST,postList);
        modelMap.addAttribute(TOTAL_LIST,totalList);
        modelMap.addAttribute(PAGE,page);
    }

    public void setPostList(ModelMap  modelMap, List<Post> postList,int totalList,int page,String error)
    {
        modelMap.addAttribute(POST_LIST,postList);
        modelMap.addAttribute(TOTAL_LIST,totalList);
        modelMap.addAttribute(PAGE,page);
        modelMap.addAttribute(MESSAGE,error);
    }

}
