package controller;

import dao.ConfigurationDAO;
import dao.ImageDAO;
import dao.PostDAO;
import dao.UserDAO;
import entities.Image;
import entities.Post;
import entities.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import utils.DefaultPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by linhtran on 06/05/2017.
 */

@Controller
public class ProcessPost {

    @Autowired
    UserDAO userDAO;

    @Autowired
    PostDAO postDAO;


    @Autowired
    ImageDAO imageDAO;


    @Autowired
    DefaultPage  defaultPage;


    @Autowired
    ConfigurationDAO configDAO;

    @RequestMapping(value = "/write-post", method = RequestMethod.POST)
    public ModelAndView processWritePost(@ModelAttribute(value = "post") Post post, HttpServletRequest httpServletRequest, Principal principal) {
        defaultPage.setDaultPage(httpServletRequest);
        HttpSession session = httpServletRequest.getSession();

        User user = userDAO.getUserByName(principal.getName());
        post.setUser(user);

        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();

        post.setNumberView(1);
        post.setNumberLike(0);
        post.setTimePost(date);
        post.setUserUpdated(principal.getName());
        post.setUpdateTime(date);

        String linkImage = httpServletRequest.getParameter("link-image");
        String altImage=httpServletRequest.getParameter("alt-image");
        if(linkImage != null && !linkImage.trim().equals("")) {
            Image image = new Image();
            image.setLink(linkImage);
            if(altImage != null && !altImage.trim().equals("")) {
                image.setAlt(altImage);
            }
            post.setImage(image);
        }
        postDAO.update(post);
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
        try {
            System.out.println("nuber: " + postId);
            Post post = postDAO.find(postId);
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
        defaultPage.setDaultPage(request);
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        String postId = request.getParameter("id");

        if(action != null && action.trim().equals("update") && postId != null && !postId.trim().equals("")) {
            session.setAttribute("postUpdate", postDAO.find(Integer.valueOf(postId)));
        }
        return "update";
    }

    @RequestMapping(value = "/write-update",method = RequestMethod.POST)
    public  String viewUpdatePost(@ModelAttribute(value = "post")Post post, HttpServletRequest request) {
        defaultPage.setDaultPage(request);
        HttpSession session = request.getSession();

        Post postUpdate = (Post) session.getAttribute("postUpdate");
        Date date = Calendar.getInstance().getTime();

        Post  post1 = postDAO.find(postUpdate.getId());
        post1.setUpdateTime(date);
        post1.setUserUpdated((String)session.getAttribute("username"));
        post1.setTitle(post.getTitle());
        post1.setContent(post.getContent());
        post1.setStatus(post.getStatus());

        String linkImage = request.getParameter("link-image");
        String altImage = request.getParameter("alt-image");

        Image image = new  Image();

        if(linkImage != null && !linkImage.trim().equals("")) {
            image.setLink(linkImage);
        }
        if(altImage != null && !altImage.trim().equals("")) {
            image.setAlt(altImage);
        }

        if(post1.getImage() != null) {
            imageDAO.deleteByIdPost(post1.getId());
            post1.setImage(image);
        }else {
            post1.setImage(image);
        }

        postDAO.update(post1);
        session.setAttribute("post-id",post1.getId());
        session.removeAttribute("postUpdate");
        return "redirect:/view-post";
    }

    @RequestMapping(value = "/delete-post")
    public String deletePost(@RequestParam(value = "id") int id,HttpServletRequest request) {
        request.setAttribute("page",0);
        System.out.println();
        if(postDAO.find(id)!=null) {
            postDAO.delete(id);
        }
        return "redirect:/user";
    }

    @RequestMapping(value = "/list-post-by-user")
    public  String  getPostByUser(HttpServletRequest request,@RequestParam(value = "username") String username,@RequestParam(required = false) String page )
    {
        defaultPage.setDaultPage(request);

        List<Post> posts;
        User user;
        int limit = configDAO.getAllConfiguration().get(0).getNumberViewPost();
        if(page == null || page.trim().equals("")|| !StringUtils.isNumeric(page)) {
            if(username==null) {
                return "redirect:/home";
            }

            user = userDAO.getUserByName(username);
            if(user!=null) {
                posts=postDAO.getAllPost("select * from post where status = 1 and id_user = "+user.getId()+" order by time_post desc limit 0,"+limit);
                setPostList(request,posts);
                request.setAttribute("page",1);
                request.setAttribute("totalList", postDAO.getAllPost("select * from post where status = 1 and id_user = "+user.getId()).size());
                return  "post-by-user";
            }else{
                return "redirect:/home";
            }

        }

        user = userDAO.getUserByName(username);
        if(user != null) {
            posts = postDAO.getAllPost("select * from post where status = 1 and id_user = "+user.getId()+" order by time_post desc limit "+(Integer.valueOf(page)-1)*limit +"," +limit);
            setPostList(request,posts);
            request.setAttribute("page",Integer.valueOf(page));
            request.setAttribute("totalList",postDAO.getAllPost("select * from post where status = 1 and id_user = "+user.getId()).size());
            return  "post-by-user";
        }

        return "redirect:/home";

    }

    private  void setPostList(HttpServletRequest request,List<Post> list)
    {
        if(list == null)
        {
            request.setAttribute("postList", new ArrayList<Post>());
        }else {
            request.setAttribute("postList",list);
        }
    }
}
