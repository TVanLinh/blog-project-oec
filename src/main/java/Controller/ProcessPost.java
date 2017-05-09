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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
//        System.out.println("------------------------------------------------------"+post.getImage().getLink());
//        System.out.println("------------------------------------------------------"+post.getImage().getAlt());


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

    @RequestMapping(name = "/like")
    @ResponseBody
    public  String  like(HttpServletRequest httpServletRequest)
    {
        String id= httpServletRequest.getParameter("id");
        System.out.println(id+"-------------------------------------------------------");
        return "ok duoc roi nhe";
    }
}
