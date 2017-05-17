package Controller;

import DAO.ImageDAO;
import DAO.PostDAO;
import DAO.RoleDAO;
import DAO.UserDAO;
import Entities.Image;
import Entities.Post;
import Service.ConfigurationService;
import Service.PostService;
import Service.UserService;
import Utils.DefaultPage;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    DefaultPage defaultPage;

    @RequestMapping(value = "/write")
    public  String viewWriter(HttpServletRequest request)
    {
        defaultPage.setDaultPage(request);
        return "write";
    }

    @RequestMapping(value={"/","/home"})
    public String homePage(HttpServletRequest request)
    {
        defaultPage.setDaultPage(request);
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
        defaultPage.setDaultPage(request);
        //-----------------------------
        String id=request.getParameter("id");
        List<Post> postSlideBar=postService.getPost(0,configurationService.find(1).getNumberViewPost());
        request.setAttribute("postSlideBar",postSlideBar);
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

    @Autowired
    ImageDAO imageDAO;
    @RequestMapping(value = "/image")
    public String insertImage()
    {
        Post post = postService.find(47);
        System.out.println(post);
        Image image= new Image();
        image.setPost(post);
        image.setLink("Ok");
        image.setAlt("ok");
        imageDAO.insert(image);
        return "test";
    }

}
