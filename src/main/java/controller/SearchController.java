package controller;

import entities.Post;
import entities.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.ConfigurationService;
import service.PostService;
import service.UserService;
import utils.DefaultPage;
import utils.NumberViewSort;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by linhtran on 19/05/2017.
 */

@Controller
public class SearchController {
    @Autowired
    PostService postService;

    @Autowired
    DefaultPage defaultPage;

    @Autowired
    ConfigurationService configurationService;

    @Autowired
    UserService userService;

    @Autowired
    NumberViewSort numberViewSort;

    @RequestMapping(value = "/view-search")
    public  String  processSearchAll(HttpServletRequest request)
    {
        defaultPage.setDaultPage(request);
        int limit=configurationService.getAllConfiguration().get(0).getNumberViewPost();
        String title=request.getParameter("title");
        String page=request.getParameter("page");
        List<Post> list;

        if(title==null)
        {
            return "redirect:/home";
        }
       if( defaultSearch(request,page,title))
       {
           return "view-search";
       }
        setPostListClient(request,title,(Integer.valueOf(page)-1)*limit);
        request.setAttribute("page",Integer.valueOf(page));
        request.setAttribute("limit",limit);
        return "view-search";
    }

    public void setPostListClient(HttpServletRequest request,String searchBy,int offset)
    {
        List<Post> list;
        int limit=configurationService.getAllConfiguration().get(0).getNumberViewPost();
        list=postService.getAllPost("select * from post  where status=1 and  approve=1 and UPPER(title) like '%"+searchBy.toUpperCase()+"%' order by time_post desc limit "+offset+","+limit);
        if(list==null)
        {
            list=new ArrayList<Post>();
        }
        request.setAttribute("postList",list);
        request.setAttribute("title",searchBy);
        request.setAttribute("totalList",postService.getAllPost("select * from post  where status=1 and  approve=1 and UPPER(title) like '%"+searchBy.toUpperCase()+"%' ").size());
    }

    public  boolean defaultSearch(HttpServletRequest request,String page,String searchBy)
    {
        request.setAttribute("userSerVice",userService);
        int limit=configurationService.getAllConfiguration().get(0).getNumberViewPost();

        if(searchBy==null)
        {
            return false;
        }
        if(page==null||page.trim().equals("")|| !StringUtils.isNumeric(page))
        {
            setPostListClient(request,searchBy,0);
            request.setAttribute("page",1);
            request.setAttribute("limit",limit);
            return true;
        }
        return false;
    }

    @RequestMapping(value = "/user-search")
    public  String  processUserSearch(HttpServletRequest request)
    {


        defaultPage.setDaultPage(request);
        int limit=configurationService.getAllConfiguration().get(0).getNumberViewPost();
        String title=request.getParameter("title");
        String page=request.getParameter("page");
        List<Post> list;

        if(title==null)
        {
            return "redirect:/home";
        }
        if( defaultSearch(request,page,title))
        {
            return "view-search";
        }
        setPostListUser(request,title,(Integer.valueOf(page)-1)*limit);
        request.setAttribute("page",Integer.valueOf(page));
        request.setAttribute("limit",limit);
        return "view-search";

    }
    public void setPostListUser(HttpServletRequest request,String searchBy,int offset)
    {
        List<Post> list;
        int limit=configurationService.getAllConfiguration().get(0).getNumberViewPost();
        String name= (String) request.getSession().getAttribute("username");
        User user= (User) userService.getUserByName(name);
        list=postService.getAllPost("select * from post where (status = 1 or id_user =  "+user.getId()+") and UPPER(title) like '%"+searchBy.toUpperCase()+"%' limit "+offset+","+limit);
        if(list==null)
        {
            list=new ArrayList<Post>();
        }
        request.setAttribute("postList",list);
        request.setAttribute("title",searchBy);
        request.setAttribute("totalList",postService.getAllPost("select * from post  where (status = 1 or id_user =  "+user.getId()+") and UPPER(title) like'%"+searchBy.toUpperCase()+"%' ").size());
    }
}
