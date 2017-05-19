package Controller;

import Entities.Post;
import Entities.User;
import Service.ConfigurationService;
import Service.PostService;
import Service.UserService;
import Utils.DefaultPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
    @RequestMapping(value = "/view-search")
    public  String  processSearchAll(HttpServletRequest request)
    {
        defaultPage.setDaultPage(request);
        String title=request.getParameter("title");
        if(title==null)
        {
            request.setAttribute("postList",new ArrayList<Post>());
            return "view-search";
        }
        List<Post> list;
        list=postService.getAllPost("select * from post  where status=1 and  approve=1 and UPPER(title) like '%"+title.toUpperCase()+"%'");
        if(list==null)
        {
            list=new ArrayList<Post>();
        }
        request.setAttribute("postList",list);
        return "view-search";
    }

    @RequestMapping(value = "/user-search")
    public  String  processUserSearch(HttpServletRequest request)
    {
        defaultPage.setDaultPage(request);
        String title=request.getParameter("title");
        if(title==null)
        {
            request.setAttribute("postList",new ArrayList<Post>());
            return "view-search";
        }
        List<Post> list;
        String name= (String) request.getSession().getAttribute("username");
        User user= (User) userService.getUserByName(name);
        list=postService.getAllPost("select * from post where id_user =  "+user.getId()+" and UPPER(title) like '%"+title.toUpperCase()+"%'");
        if(list==null)
        {
            list=new ArrayList<Post>();
        }
        request.setAttribute("postList",list);
        return "view-search";
    }

}
