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
    public String defaultHomePage(HttpServletRequest request, HttpServletResponse response)
    {
         Configuration configuration=configurationService.find(1);
         String str=request.getParameter("action");
         List<Post> postList;
         HttpSession session  =request.getSession();

        if(str!=null)
        {
            Integer begin= (Integer) session.getAttribute("begin");
            try
            {
                if (str.equals("old_post"))
                {
                    if(begin<=0)
                    {
                        postList=postService.getPost(0,configuration.getNumberViewPost());
                        request.setAttribute("postList",postList);
                        logger.info("select with in begin<=0"+begin);
                        session.setAttribute("begin",configuration.getNumberViewPost());
                        return "home";
                    }else
                    {
                        logger.info("select with in old post "+begin +" number ");
                        postList=postService.getPost(begin-configuration.getNumberViewPost(),configuration.getNumberViewPost());
                        request.setAttribute("postList",postList);
                        session.setAttribute("begin",begin-configuration.getNumberViewPost());
                        return "home";
                    }
                }
                if (str.equals("new_post"))
                {
                    if(begin>=postService.getAllPost().size())
                    {
                        logger.info("select with newpost >=size()");
                        postList=postService.getPost(0,configuration.getNumberViewPost());
                        request.setAttribute("postList",postList);
                        session.setAttribute("begin",configuration.getNumberViewPost());
                        return "home";
                    }
                    int numerRecord=begin+configuration.getNumberViewPost();
                    postList=postService.getPost(begin,configuration.getNumberViewPost());
                    logger.info((begin+" numerRecord " +numerRecord));
                    request.setAttribute("postList",postList);
                    session.setAttribute("begin",numerRecord);
                    System.out.println(postList.size());
                    return "home";
                }
            }catch (Exception ex)
            {
                return "home";
            }
        }else {

            postList=postService.getPost(0,configuration.getNumberViewPost());
            request.setAttribute("postList",postList);
            System.out.println("size:"+postList.size()+" begin");
            System.out.println("size:"+postList.size() +" default  ");
            session.setAttribute("begin",configuration.getNumberViewPost());
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

}
