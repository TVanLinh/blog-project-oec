package controller.user;

import entities.Image;
import entities.Post;
import entities.User;
import exceptions.AccessDenieException;
import exceptions.NotFindException;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.*;
import utils.session.SessionUtils;
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
    private PostService postService;

    @Autowired
    private ConfigurationService configurationService;


    @RequestMapping(value = "/write")
    public String viewWriter(HttpServletRequest request) {
        if (request.getAttribute("error") != null) {
            request.setAttribute("error", request.getAttribute("error"));
        }
        return "write";
    }


    @RequestMapping(value = "/write-post", method = RequestMethod.POST)
    public ModelAndView processWritePost(@ModelAttribute(value = "post") Post post,
                                         HttpServletRequest request,
                                         Principal principal, ModelMap modelMap,
                                         RedirectAttributes redirectAttributes,
                                         @RequestParam(value = "link-image", required = false) String linkImage,
                                         @RequestParam(value = "alt-image", required = false) String altImage,
                                         @RequestParam(value = "status", required = false) String status) {
        this.setPostSliderbar(modelMap);

        if (org.apache.commons.lang3.StringUtils.isBlank(post.getTitle()) || !StringUtils.checkVid(post.getTitle())) {
            redirectAttributes.addFlashAttribute("error", "validation.field.post_title_not_blank");
            request.getSession().setAttribute("postUpdate", post);
            return new ModelAndView("redirect:/write");
        }

        HttpSession session = request.getSession();
        User user = SessionUtils.getCurrentUser();
        post.setUser(user);
        int stt = NumberUtils.toInt(status, 1);
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();

        post.setNumberView(1);
        post.setNumberLike(0);
        post.setTimePost(date);
        post.setUserUpdated(principal.getName());
        post.setStatus(stt);

        if (org.apache.commons.lang3.StringUtils.isNotBlank(linkImage)) {
            Image image = new Image();
            image.setLink(linkImage);
            if (org.apache.commons.lang3.StringUtils.isNotBlank(altImage)) {
                image.setAlt(altImage);
            }
            post.setImage(image);
        }
        this.postService.save(post);
        modelMap.addAttribute("post", post);
        session.setAttribute("post-id", post.getId());
        return new ModelAndView("redirect:/view-post");

    }

    private void setPostSliderbar(ModelMap modelMap) {
        int limit = NumberUtils.toInt(this.configurationService.findByName(ConfigurationService.NUMBER_POST_VIEW).getValue(), 4);

        List<Post> postSlideBar = this.postService.getPublic(0, limit);
        modelMap.addAttribute("postSlideBar", postSlideBar);
    }

    @RequestMapping(value = "/write-post", method = RequestMethod.GET)
    public String processWritePost(HttpServletRequest request) {
        return "home";
    }

    @RequestMapping(value = "/view-post", method = RequestMethod.GET)
    public String view(HttpServletRequest request, ModelMap modelMap) throws NotFindException {

        HttpSession session = request.getSession();
        Integer postId = (Integer) session.getAttribute("post-id");
        Post post = this.postService.find(postId);
        modelMap.addAttribute("post", post);
        return "view";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String updatePost(HttpServletRequest request,
                             @RequestParam(value = "id", required = false) String postId) throws NotFindException, AccessDenieException {
        HttpSession session = request.getSession();
        Post post;
        if (request.getAttribute(RequestService.MESSAGE) != null) {
            request.setAttribute("error", request.getAttribute(RequestService.MESSAGE));
        }
        if (!org.apache.commons.lang3.StringUtils.isNumeric(postId) || (post = this.postService.find(Integer.valueOf(postId))) == null) {
            throw new NotFindException(NotFindException.POST_NOT_FOUND);
        }
        User user = SessionUtils.getCurrentUser();
        if (!this.userService.isEditPost(user, post)) {
            throw new AccessDenieException(AccessDenieException.ACCESS_NOT_ROLE_POST);
        }

        session.setAttribute("postUpdate", this.postService.find(Integer.valueOf(postId)));
        return "update";
    }


    @RequestMapping(value = "/write-update", method = RequestMethod.POST)
    public String viewUpdatePost(@ModelAttribute(value = "post") Post post,
                                 HttpServletRequest request,
                                 @RequestParam(value = "link-image", required = false) String linkImage,
                                 @RequestParam(value = "alt-image", required = false) String altImage,
                                 @RequestParam(value = "status", required = false) String status,
                                 RedirectAttributes redirectAttributes) {
        HttpSession session = request.getSession();
        Post postUpdate = (Post) session.getAttribute("postUpdate");

        if (org.apache.commons.lang3.StringUtils.isBlank(post.getTitle()) || !StringUtils.checkVid(post.getTitle())) {
            redirectAttributes.addFlashAttribute(RequestService.MESSAGE, RequestService.VALID_FIELD_POST_TITLE_NOT_BLANK);
            request.getSession().setAttribute("postUpdate", post);
            redirectAttributes.addAttribute("id", post.getId());
            return "redirect:/update";
        }

        User user = SessionUtils.getCurrentUser();
        postUpdate.setUserUpdated(user.getUserName());
        postUpdate.setTitle(post.getTitle());
        postUpdate.setContent(post.getContent());
        postUpdate.setStatus(post.getStatus());
        post.setStatus(NumberUtils.toInt(status, 1));

        Image image = new Image();

        if (org.apache.commons.lang3.StringUtils.isNotBlank(linkImage)) {
            image.setLink(linkImage);
        }

        if (org.apache.commons.lang3.StringUtils.isNotBlank(altImage)) {
            image.setAlt(altImage);
        }

        if (image.getLink() != null && postUpdate.getImage() != null) {
            this.imageService.deleteByIdPost(postUpdate.getId());
            postUpdate.setImage(image);
        }

        if (image.getLink() != null && postUpdate.getImage() == null) {
            postUpdate.setImage(image);
        }

        this.postService.save(postUpdate);
        session.setAttribute("post-id", postUpdate.getId());
        session.removeAttribute("postUpdate");
        return "redirect:/view-post";
    }

    @RequestMapping(value = "/delete-post")
    public String deletePost(@RequestParam(value = "id", required = false) String id,
                             HttpServletRequest request, RedirectAttributes redirectAttributes) {
        User user = SessionUtils.getCurrentUser();
        try {
            this.postService.delete(id, user.getUserName());
            RequestService.setResponse(redirectAttributes, "1", RequestService.DELETE_SUCCESS);
            return "redirect:/home";
        } catch (Exception ex) {
            RequestService.setResponse(redirectAttributes, "1", RequestService.POST_DELETE_NOT_SUCCESS);
        }
        return "redirect:/home";
    }

}
