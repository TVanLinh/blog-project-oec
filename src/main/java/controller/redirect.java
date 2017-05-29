package controller;

import dao.PostDAO;
import dao.RoleDAO;
import entities.Post;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.ConfigurationService;
import service.PostService;
import service.UserService;
import utils.DefaultPage;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by linhtran on 06/05/2017.
 */
@Controller
public class redirect {
    final static Logger logger = Logger.getLogger(ProcessPost.class);


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
    public  String viewWriter(HttpServletRequest request) {
        defaultPage.setDaultPage(request);
        request.setAttribute("active","write");
        return "write";
    }

    @RequestMapping(value={"/","/home"})
    public String homePage(HttpServletRequest request) {
        defaultPage.setDaultPage(request);
        String page=request.getParameter("page");
        List<Post> postList;

        int limit = configurationService.find(1).getNumberViewPost();
        request.setAttribute("userSerVice",userService);
        if(page == null|| !StringUtils.isNumeric(page) || page.trim().equals("")) {
            postList = postService.getPost(0,limit);
            request.setAttribute("page",1);
            request.setAttribute("postList",postList);
            request.setAttribute("totalList",postService.getAllPostPublic().size());
            request.setAttribute("limit",configurationService.getAllConfiguration().get(0).getNumberViewPost());
            request.setAttribute("active","home");
            return "home";
        }

        postList = postService.getPost((Integer.valueOf(page)-1)*limit,limit);
        request.setAttribute("page",Integer.valueOf(page));
        request.setAttribute("postList",postList);
        request.setAttribute("totalList",postService.getAllPostPublic().size());
        request.setAttribute("limit",configurationService.getAllConfiguration().get(0).getNumberViewPost());
        request.setAttribute("active","home");
        return "home";
    }
    @RequestMapping(value = "/post")
    public  String viewPost(HttpServletRequest request) {
        defaultPage.setDaultPage(request);

        String id = request.getParameter("id");
        List<Post> postSlideBar = postService.getAllPost("select * from post  where status=1 and approve=1 order by time_post desc limit 0,5");
        request.setAttribute("postSlideBar",postSlideBar);
        if(id != null) {
            try {
                Post post=postService.find(Integer.valueOf(id));
                if(post != null) {
                    post.setNumberView(post.getNumberView()+1);
                    postDAO.update(post);
                    request.setAttribute("post",post);
                }
                request.setAttribute("active","post");
                return "post";
            }catch (Exception e) {
                return "/home";
            }
        }
        request.setAttribute("active","home");
        return "/home";
    }


}
