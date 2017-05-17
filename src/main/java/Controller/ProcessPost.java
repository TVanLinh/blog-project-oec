package Controller;

import DAO.ImageDAO;
import DAO.PostDAO;
import Entities.Image;
import Entities.Post;
import Entities.User;
import Service.ConfigurationService;
import Service.PostService;
import Service.UserService;
import Utils.DefaultPage;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

    @Autowired
    ImageDAO imageDAO;

    @Autowired
    ConfigurationService     configurationService;

    @Autowired
    DefaultPage  defaultPage;

    @RequestMapping(value = "/write-post", method = RequestMethod.POST)
    public ModelAndView processWritePost(@ModelAttribute(value = "post") Post post, HttpServletRequest httpServletRequest, Principal principal) {
        defaultPage.setDaultPage(httpServletRequest);
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
        postDAO.insert(post);
        httpServletRequest.setAttribute("post", post);
        session.setAttribute("post-id", post.getId());
        return new ModelAndView("redirect:/view-post");

    }

    @RequestMapping(value = "/write-post", method = RequestMethod.GET)
    public String processWritePost(HttpServletRequest request) {
        defaultPage.setDaultPage(request);
        return "home";
    }

    @RequestMapping(value = "/view-post", method = RequestMethod.GET)
    public String view(HttpServletRequest request) {
        defaultPage.setDaultPage(request);

        HttpSession session = request.getSession();
        Integer postId = (Integer) session.getAttribute("post-id");
        System.out.println("--------------------------------------------"+postId);
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
        defaultPage.setDaultPage(request);
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
        defaultPage.setDaultPage(request);
        HttpSession session=request.getSession();

        Post postUpdate= (Post) session.getAttribute("postUpdate");
        Date date=Calendar.getInstance().getTime();

        Post  post1=postService.find(postUpdate.getId());
        post1.setUpdateTime(date);
        post1.setUserUpdated((String)session.getAttribute("username"));
        post1.setTitle(post.getTitle());
        post1.setContent(post.getContent());
        post1.setStatus(post.getStatus());

        String linkImage= request.getParameter("link-image");
        String altImage=request.getParameter("alt-image");

        if(post.getImage()!=null)
        {
            if(linkImage!=null&&!linkImage.trim().equals(""))
            {
                post1.getImage().setLink(linkImage);
            }
            if(altImage!=null&&!altImage.trim().equals(""))
            {
                post1.getImage().setAlt(altImage);
            }
        }else
        {
            Image image=new Image();
            if(linkImage!=null&&!linkImage.trim().equals(""))
            {
                image.setLink(linkImage);
            }
            if(altImage!=null&&!altImage.trim().equals(""))
            {
                image.setAlt(altImage);
            }
            if(image.getLink()!=null)
            {
                System.out.println(image.getLink());
                image.setPost(post1);
                imageDAO.insert(image);
            }
        }
        postDAO.update(post1);
        session.setAttribute("post-id",post1.getId());
        session.removeAttribute("postUpdate");
        return "redirect:/view-post";
    }

    @RequestMapping(value = "/delete-post")
    public String deletePost(@RequestParam(value = "id") int id,HttpServletRequest request)
    {
        request.setAttribute("page",0);
        System.out.println();
        postDAO.delete(id);
        return "redirect:/user";
    }


}
