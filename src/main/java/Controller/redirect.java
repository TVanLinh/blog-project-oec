package Controller;

import DAO.PostDAO;
import DAO.RoleDAO;
import DAO.UserDAO;
import Entities.Configuration;
import Entities.Post;
import Entities.Role;
import Entities.User;
import Service.ConfigurationService;
import Service.PostService;
import Service.UserService;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.management.HotspotMemoryMBean;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by linhtran on 06/05/2017.
 */
@Controller
public class redirect {
    final static Logger logger = Logger.getLogger(ProcessPost.class);

    @Autowired
    UserDAO userDAO;

    @Autowired
    UserService userService;

    @Autowired
    RoleDAO roleDAO;

    @Autowired
    PostService postService;

    @Autowired
    PostDAO postDAO;

    @Autowired
    ConfigurationService configurationService;



    @RequestMapping(value = "/write")
    public  String viewWriter()
    {
        System.out.println(postService.getPost(4,8).size());
        System.out.println(postService.getPost(4,8).size());
        System.out.println("five:"+postService.getPost(5,10).size());
        System.out.println("first:"+postService.getPost(0,4).size());
        return "write";
    }

    @RequestMapping(value={"/","/home"})
    public String homePage(HttpServletRequest request)
    {
        String page=request.getParameter("page");
        List<Post> postList;
        int limit=configurationService.find(1).getNumberViewPost();
        if(page==null)
        {
            postList=postService.getPost(0,limit);
            request.setAttribute("page",1);
            request.setAttribute("postList",postList);
            return "home";
        }
        try
        {
            postList=postService.getPost((Integer.valueOf(page)-1)*limit,limit);
            request.setAttribute("page",Integer.valueOf(page));
            System.out.println(page);
            request.setAttribute("postList",postList);

        }catch (Exception e)
        {
            postList=postService.getPost(0,limit);
            request.setAttribute("page",1);
            request.setAttribute("postList",postList);
            return "home";
        }
        return "home";
    }
    @RequestMapping(value = "/post")
    public  String viewPost(HttpServletRequest request)
    {
        String id=request.getParameter("id");
        if(id!=null)
        {
            try {
                Post post=postService.find(Integer.valueOf(id));
                if(post!=null)
                {
                    post.setNumberView(post.getNumberView()+1);
                    postDAO.update(post);
                    request.setAttribute("post",post);
                }
                return "post";
            }catch (Exception e)
            {
                return "/home";
            }
        }
        return "/home";
    }

    @RequestMapping(value = "/testjson")
    public  String testJon()
    {
        return "testjson";
    }
}
