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
   private UserService userService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private  DefaultPages defaultPage;

    @Autowired
    private PostService postService;

    @Autowired
    private ConfigurationService configurationService;


    @RequestMapping(value = "/write-post", method = RequestMethod.POST)
    public ModelAndView processWritePost(@ModelAttribute(value = "post") Post post,
                                         HttpServletRequest request,
                                         Principal principal, ModelMap modelMap,
                                         @RequestParam(value = "link-image",required = false)String linkImage,
                                         @RequestParam(value = "alt-image",required = false)String altImage ) {
        this.defaultPage.setDaultPage(request);
        this.setPostSliderbar(modelMap);

        if(org.apache.commons.lang3.StringUtils.isBlank(post.getTitle())|| !StringUtils.checkVid(post.getTitle()))
        {
            request.setAttribute("error","validation.field.post_title_not_blank");
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

        if(org.apache.commons.lang3.StringUtils.isNotBlank(linkImage)) {
            Image image = new Image();
            image.setLink(linkImage);
            if(org.apache.commons.lang3.StringUtils.isNotBlank(altImage)) {
                image.setAlt(altImage);
            }
            post.setImage(image);
        }
        this.postService.save(post);
        modelMap.addAttribute("post", post);
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
        Post post = this.postService.find(postId);
        if (post != null) {
            request.setAttribute("post", post);
        }
        return "view";
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
    public  String viewUpdatePost(@ModelAttribute(value = "post")Post post,
                                  HttpServletRequest request,Principal principal,
                                  @RequestParam(value = "link-image",required = false)String linkImage,
                                  @RequestParam(value = "alt-image",required = false)String altImage ) {
        this.defaultPage.setDaultPage(request);
        HttpSession session = request.getSession();

        Post postUpdate = (Post) session.getAttribute("postUpdate");
        if(!postUpdate.getUser().getUserName().equals(principal.getName()) || !this.userService.isRoleAdmin(this.userService.getUserByName(principal.getName())))
        {
            return "redirect:/home";
        }

        if(org.apache.commons.lang3.StringUtils.isBlank(post.getTitle())|| !StringUtils.checkVid(post.getTitle()))
        {
            request.setAttribute("error","validation.field.post_title_not_blank");
            request.getSession().setAttribute("postUpdate", post);
            return "update";
        }

        Date date = Calendar.getInstance().getTime();

        postUpdate.setUpdateTime(date);
        postUpdate.setUserUpdated((String)session.getAttribute("username"));
        postUpdate.setTitle(post.getTitle());
        postUpdate.setContent(post.getContent());
        postUpdate.setStatus(post.getStatus());

        Image image = new  Image();

        if(org.apache.commons.lang3.StringUtils.isNotBlank(linkImage)) {
            image.setLink(linkImage);
        }

        if(org.apache.commons.lang3.StringUtils.isNotBlank(altImage)) {
            image.setLink(altImage);
        }

        if(image.getLink()!= null && postUpdate.getImage() != null)
        {
            this.imageService.deleteByIdPost(postUpdate.getId());
            postUpdate.setImage(image);
        }

        if(image.getLink() != null && postUpdate.getImage() == null)
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
            if(post.getUser().getUserName().equals(principal.getName()) || this.userService.isRoleAdmin(this.userService.getUserByName(principal.getName()))) {
                this.postService.delete(id);
            }
        }
        return "redirect:/home";
    }

}
