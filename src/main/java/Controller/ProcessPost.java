package Controller;

import DAO.PostDAO;
import DAO.UserDAOIML;
import Entities.Image;
import Entities.Post;
import Entities.User;
import Service.PostService;
import Service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.PostUpdate;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by linhtran on 06/05/2017.
 */

@Controller
public class ProcessPost {

    final static Logger logger = Logger.getLogger(ProcessPost.class);

    @Autowired
    UserService userService;

    @Autowired
    PostDAO postDAO;

    @Autowired
    PostService postService;

    @RequestMapping(value = "/write-post", method = RequestMethod.POST)
    public ModelAndView processWritePost(@ModelAttribute(value = "post") Post post, HttpServletRequest httpServletRequest, Principal principal) {
        HttpSession session = httpServletRequest.getSession();
        User user = userService.getUserByName(principal.getName());
        post.setUser(user);

        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();

        post.setNumberView(1);
        post.setNumberLike(0);
        post.setTimePost(date);
        post.setUserUpdated(principal.getName());
        post.setUpdateTime(date);

        String linkImage= httpServletRequest.getParameter("link-image");
        String altImage=httpServletRequest.getParameter("alt-image");
        if(linkImage!=null && linkImage.trim()!="")
        {
            Image image=new Image();
            image.setLink(linkImage);
            if(altImage!=null&&altImage.trim()!="")
            {
                image.setAlt(altImage);
                System.out.println(image+"------------------------------------------------------");
            }
            post.setImage(image);
        }

        System.out.println(postDAO.insert(post));
        httpServletRequest.setAttribute("post", post);
        session.setAttribute("post-id", post.getId());
        return new ModelAndView("redirect:/view-post");

    }

    @RequestMapping(value = "/write-post", method = RequestMethod.GET)
    public String processWritePost() {
        return "home";
    }

    @RequestMapping(value = "/view-post", method = RequestMethod.GET)
    public String view(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer postId = (Integer) session.getAttribute("post-id");
        try {
            System.out.println("nuber: " + postId);
            Post post = postService.find(postId);
            if (post != null) {
                request.setAttribute("post", post);
            }
            return "view";
        } catch (Exception e) {
            return "view";
        }
    }

    @RequestMapping(value = "/update",method = RequestMethod.GET)
    public  String updatePost(HttpServletRequest request)
    {
        HttpSession session=request.getSession();
        String action =request.getParameter("action");
        String postId=request.getParameter("id");
        if(action!=null && action.trim().equals("update")&&postId!=null&&postId.trim()!="")
        {
            session.setAttribute("postUpdate", postService.find(Integer.valueOf(postId)));
        }
        return "update";
    }

    @RequestMapping(value = "/write-update",method = RequestMethod.POST)
    public  String viewUpdatePost(@ModelAttribute(value = "post")Post post, HttpServletRequest request)
    {
        HttpSession session=request.getSession();
        Post postUpdate= (Post) session.getAttribute("postUpdate");
        Date date=Calendar.getInstance().getTime();

        Post  post1=postService.find(postUpdate.getId());
        post1.setUpdateTime(date);
        post1.setUserUpdated((String)session.getAttribute("username"));
        post1.setTitle(post.getTitle());
        post1.setContent(post.getContent());

        System.out.println(post.getTitle()+"   ");

        postDAO.update(post1);
        System.out.println("okkkkkkkk");
        String linkImage= request.getParameter("link-image");
        String altImage=request.getParameter("alt-image");

        session.setAttribute("post-id",post1.getId());
        session.removeAttribute("postUpdate");
        return "redirect:/view-post";
    }

    @RequestMapping(value = "/delete-post")
    public String deletePost(@RequestParam(value = "id") int id,HttpServletRequest request)
    {
        System.out.println("id-------------------"+id);
        request.setAttribute("page",0);
        postDAO.delete(id);
        return "redirect:/user";
    }
}
