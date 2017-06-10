package controller;

import entities.Image;
import entities.Post;
import entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import service.ConfigurationService;
import service.ImageService;
import service.PostService;
import service.UserService;
import utils.page.DefaultPages;
import utils.string.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by linhtran on 06/05/2017.
 */

@Controller
public class ProcessPost {

    @Autowired
    UserService userService;

    @Autowired
    ImageService imageService;

    @Autowired
    DefaultPages defaultPage;

    @Autowired
    PostService postService;

    @Autowired
    ConfigurationService configurationService;


    @RequestMapping(value = "/write-post", method = RequestMethod.POST)
    public ModelAndView processWritePost(@ModelAttribute(value = "post") Post post, HttpServletRequest request, Principal principal,ModelMap modelMap) {
        this.defaultPage.setDaultPage(request);
        this.setPostSliderbar(modelMap);

        if(post.getTitle().trim().equals("")|| !StringUtils.checkVid(post.getTitle()))
        {
            request.setAttribute("error","Title not valid ");
            request.getSession().setAttribute("postUpdate", post);
            return new ModelAndView("write");
        }
        HttpSession session = request.getSession();
        User user = this.userService.getUserByName(principal.getName());
        post.setUser(user);

        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();

        post.setNumberView(1);
        post.setNumberLike(0);
        post.setTimePost(date);
        post.setUserUpdated(principal.getName());
        post.setUpdateTime(date);

        String linkImage = request.getParameter("link-image");
        String altImage=request.getParameter("alt-image");
        if(linkImage != null && !linkImage.trim().equals("")) {
            Image image = new Image();
            image.setLink(linkImage);
            if(altImage != null && !altImage.trim().equals("")) {
                image.setAlt(altImage);
            }
            post.setImage(image);
        }
        this.postService.save(post);
        request.setAttribute("post", post);
        session.setAttribute("post-id", post.getId());
        return new ModelAndView("redirect:/view-post");

    }

    private  void setPostSliderbar(ModelMap modelMap)
    {
        List<Post> postSlideBar = this.postService.getPublic(0, this.configurationService.getAllConfiguration().get(0).getNumberViewPost());
        modelMap.addAttribute("postSlideBar",postSlideBar);
    }
    @RequestMapping(value = "/write-post", method = RequestMethod.GET)
    public String processWritePost(HttpServletRequest request) {
        this.defaultPage.setDaultPage(request);
        return "home";
    }

    @RequestMapping(value = "/view-post", method = RequestMethod.GET)
    public String view(HttpServletRequest request) {
        this.defaultPage.setDaultPage(request);

        HttpSession session = request.getSession();
        Integer postId = (Integer) session.getAttribute("post-id");
        try {
            System.out.println("nuber: " + postId);
            Post post = this.postService.find(postId);
            if (post != null) {
                request.setAttribute("post", post);
            }
            return "view";
        } catch (Exception e) {
            return "view";
        }
    }

    @RequestMapping(value = "/update",method = RequestMethod.GET)
    public  String updatePost(HttpServletRequest request) {
        this.defaultPage.setDaultPage(request);
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        String postId = request.getParameter("id");

        if(!org.apache.commons.lang3.StringUtils.isNumeric(postId) || !"update".equals(action)) {
            return "redirect:/home";
        }
        if(this.postService.find(Integer.valueOf(postId))==null)
        {
            return "redirect:/home";
        }
        session.setAttribute("postUpdate", this.postService.find(Integer.valueOf(postId)));
        return "update";
    }

    @RequestMapping(value = "/write-update",method = RequestMethod.POST)
    public  String viewUpdatePost(@ModelAttribute(value = "post")Post post, HttpServletRequest request,Principal principal) {
        this.defaultPage.setDaultPage(request);
        HttpSession session = request.getSession();

        Post postUpdate = (Post) session.getAttribute("postUpdate");
        if(!postUpdate.getUser().getUserName().equals(principal.getName()) || !this.userService.isRoleAdmin(this.userService.getUserByName(principal.getName())))
        {
            return "redirect:/home";
        }
        if(post.getTitle().trim().equals("")|| !StringUtils.checkVid(post.getTitle()))
        {
            request.setAttribute("error","Title not valid ");
            session.setAttribute("postUpdate", postUpdate);
            return "update";
        }
        Date date = Calendar.getInstance().getTime();

        postUpdate.setUpdateTime(date);
        postUpdate.setUserUpdated((String)session.getAttribute("username"));
        postUpdate.setTitle(post.getTitle());
        postUpdate.setContent(post.getContent());
        postUpdate.setStatus(post.getStatus());

        String linkImage = request.getParameter("link-image");
        String altImage = request.getParameter("alt-image");

        Image image = new  Image();

        if(linkImage != null && !linkImage.trim().equals("")) {
            image.setLink(linkImage);
        }
        if(altImage != null && !altImage.trim().equals("")) {
            image.setAlt(altImage);
        }

        if(image.getLink()!=null && postUpdate.getImage()!=null)
        {
            this.imageService.deleteByIdPost(postUpdate.getId());
            postUpdate.setImage(image);
        }

        if(image.getLink()!=null && postUpdate.getImage()==null)
        {
            postUpdate.setImage(image);
        }

        this.postService.save(postUpdate);
        session.setAttribute("post-id",postUpdate.getId());
        session.removeAttribute("postUpdate");
        return "redirect:/view-post";
    }

    @RequestMapping(value = "/delete-post")
    public String deletePost(@RequestParam(value = "id") int id,Principal principal) {
        Post post = this.postService.find(id);
        if( post != null) {
            if(post.getUser().getUserName().equals(principal.getName()) || this.userService.isRoleAdmin(this.userService.getUserByName(principal.getName())))
            {
                this.postService.delete(id);
            }
        }
        return "redirect:/home";
    }

}
