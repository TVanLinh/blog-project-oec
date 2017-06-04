package controller;

import dao.PostDAO;
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

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by linhtran on 19/05/2017.
 */

@Controller
public class SearchController {

    @Autowired
    PostDAO  postDAO;

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

        if(title == null) {
            return "redirect:/home";
        }
       if( defaultSearch(modelMap,page,title)) {
           return "view-search";
       }
        setPostListClient(modelMap,title,(Integer.valueOf(page)-1)*limit);
        modelMap.addAttribute("page",Integer.valueOf(page));
        modelMap.addAttribute("limit",limit);
        return "view-search";
    }

    public void setPostListClient(ModelMap modelMap,String searchBy,int offset) {
        List<Post> list;
        int limit = this.configurationService.getAllConfiguration().get(0).getNumberViewPost();
        list = this.postDAO.getAllPost("select * from post  where status=1 and  approve=1 and UPPER(title) like '%"+searchBy.toUpperCase()+"%' order by time_post desc limit "+offset+","+limit);
        if(list == null) {
            list = new ArrayList<Post>();
        }
        modelMap.addAttribute("postList",list);
        modelMap.addAttribute("title",searchBy);
        modelMap.addAttribute("totalList", this.postDAO.getAllPost("select * from post  where status=1 and  approve=1 and UPPER(title) like '%"+searchBy.toUpperCase()+"%' ").size());
    }

    public  boolean defaultSearch(ModelMap modelMap,String page,String searchBy) {
        modelMap.addAttribute("userDAO",this.userService);
        int limit = this.configurationService.getAllConfiguration().get(0).getNumberViewPost();

        if(searchBy == null) {
            return false;
        }
        if(page == null || page.trim().equals("") || !StringUtils.isNumeric(page)) {
            setPostListClient(modelMap,searchBy,0);
            modelMap.addAttribute("page",1);
            modelMap.addAttribute("limit",limit);
            return true;
        }
        return false;
    }

    @RequestMapping(value = "/user-search")
    public  String  processUserSearch(HttpServletRequest request,ModelMap modelMap) {
        this.defaultPage.setDaultPage(request);
        int limit = this.configurationService.getAllConfiguration().get(0).getNumberViewPost();
        String title = request.getParameter("title");
        String page = request.getParameter("page");

        if(title == null) {
            return "redirect:/home";
        }
        if( defaultSearch(modelMap,page,title)) {
            return "view-search";
        }
        setPostListUser(request,title,(Integer.valueOf(page)-1)*limit);
        request.setAttribute("page",Integer.valueOf(page));
        request.setAttribute("limit",limit);
        return "view-search";

    }
    public void setPostListUser(HttpServletRequest request,String searchBy,int offset) {
        List<Post> list;
        int limit = this.configurationService.getAllConfiguration().get(0).getNumberViewPost();
        String name = (String) request.getSession().getAttribute("username");
        User user =  this.userService.getUserByName(name);
        list = this.postDAO.getAllPost("select * from post where (status = 1 or id_user =  "+user.getId()+") and UPPER(title) like '%"+searchBy.toUpperCase()+"%' limit "+offset+","+limit);
        if(list == null) {
            list = new ArrayList<Post>();
        }
        request.setAttribute("postList",list);
        request.setAttribute("title",searchBy);
        request.setAttribute("totalList", this.postDAO.getAllPost("select * from post  where (status = 1 or id_user =  "+user.getId()+") and UPPER(title) like'%"+searchBy.toUpperCase()+"%' ").size());
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
