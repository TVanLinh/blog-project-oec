package controller;

import entities.Post;
import entities.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.ConfigurationService;
import service.PostService;
import service.UserService;
import utils.page.DefaultPage;
import utils.sort.SortType;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by linhtran on 19/05/2017.
 */

@Controller
public class SearchController {

    @Autowired
    PostService postService;

    @Autowired
    DefaultPage defaultPage;


    @Autowired
    ConfigurationService configurationService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/view-search")
    public  String  processSearchAll(HttpServletRequest request, ModelMap modelMap) {
        this.defaultPage.setDaultPage(request);
        int limit = this.configurationService.getAllConfiguration().get(0).getNumberViewPost();
        String title = request.getParameter("title");
        String page = request.getParameter("page");
        List<Post> list;
        if(page == null || page.trim().equals("") || !StringUtils.isNumeric(page)) {
            if(title == null) {
                return "redirect:/home";
            }
            request.setAttribute("title",title);
            modelMap.addAttribute("postList",this.postService.getPostPublicByTitle(new SortType(),title,0,limit));
            modelMap.addAttribute("page",1);
            modelMap.addAttribute("totalList", this.postService.getPostPublicByTitle(new SortType(),title).size());
            modelMap.addAttribute("limit",limit);
            return "view-search";
        }

        request.setAttribute("title",title);
        modelMap.addAttribute("postList",this.postService.getPostPublicByTitle(new SortType(),title,(Integer.valueOf(page)-1)*limit,limit));
        modelMap.addAttribute("totalList", this.postService.getPostPublicByTitle(new SortType(),title).size());
        modelMap.addAttribute("page",Integer.valueOf(page));
        modelMap.addAttribute("limit",limit);
        return "view-search";
    }


    @RequestMapping(value = "/list-post-by-user")
    public  String  getPostByUser(HttpServletRequest request,ModelMap modelMap, @RequestParam(value = "username") String username, @RequestParam(required = false) String page )
    {
        defaultPage.setDaultPage(request);
        List<Post> posts;
        User user;
        int limit = this.configurationService.getAllConfiguration().get(0).getNumberViewPost();
        request.setAttribute("userDAO",this.userService);
        if(page == null || page.trim().equals("")|| !StringUtils.isNumeric(page)) {
            if(username==null) {
                return "redirect:/home";
            }

            user = this.userService.getUserByName(username);
            if(user != null) {
                posts=this.postService.getByIdUserAndStatus(1,user.getId(),0,limit);
                setPostList(request,posts);
                modelMap.addAttribute("page",1);
                modelMap.addAttribute("totalList", this.postService.getCounByIdAndStatus(1,user.getId()));
                modelMap.addAttribute("limit",limit);
                modelMap.addAttribute("userName",username);
                return  "post-by-user";
            }else{
                return "redirect:/home";
            }

        }

        user = this.userService.getUserByName(username);
        if(user != null) {
            posts=this.postService.getByIdUserAndStatus(1,user.getId(),(Integer.valueOf(page)-1)*limit,limit);
            setPostList(request,posts);
            modelMap.addAttribute("page",Integer.valueOf(page));
            modelMap.addAttribute("totalList", this.postService.getCounByIdAndStatus(1,user.getId()));
            modelMap.addAttribute("limit",limit);
            modelMap.addAttribute("userName",username);
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
