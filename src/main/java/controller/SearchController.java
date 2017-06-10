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
import utils.page.DefaultPages;
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
    DefaultPages defaultPage;


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
    public  String  getPostByUser(HttpServletRequest request, ModelMap modelMap, @RequestParam(value = "username") String username, @RequestParam(required = false) String page )
    {
        defaultPage.setDaultPage(request);
        List<Post> posts;
        User user;
        int limit = this.configurationService.getAllConfiguration().get(0).getNumberViewPost();
        request.setAttribute("userDAO",this.userService);

        String  userName= (String) request.getSession().getAttribute("username");
        if(page == null || page.trim().equals("")|| !StringUtils.isNumeric(page)) {
            if(username == null) {
                return "redirect:/home";
            }

            user = this.userService.getUserByName(username);
            if(user != null) {
                if(user.getUserName().equals(userName))
                {
                    posts = this.postService.getPostByIdUser(user.getId(),0,limit);
                    this.setResult(modelMap,posts,user.getUserName(),1,this.postService.getPostByIdUser(user.getId()).size(),limit);
                    return  "post-by-user";
                }

                SortType sortType=new SortType();
                sortType.orderBy="time_post";
                posts=this.postService.getPost(user.getId(),1,1,sortType,0,limit);
                this.setResult(modelMap,posts,user.getUserName(),1,this.postService.
                getCount(user.getId(),1,1),limit);
                return  "post-by-user";
            }else{
                return "redirect:/home";
            }

        }

        user = this.userService.getUserByName(username);
        if(user != null) {
            if(user.getUserName().equals(userName))
            {
                posts = this.postService.getPostByIdUser(user.getId(),(Integer.valueOf(page)-1)*limit,limit);
                this.setResult(modelMap,posts,user.getUserName(),Integer.valueOf(page),this.postService.getPostByIdUser(user.getId()).size(),limit);
                return  "post-by-user";
            }

            SortType sortType=new SortType();
            sortType.orderBy = "time_post";

            posts = this.postService.getPost(user.getId(),1,1,sortType,(Integer.valueOf(page)-1)*limit,limit);

            this.setResult(modelMap,posts,user.getUserName(),Integer.valueOf(page),this.postService.getCount(user.getId(),1,1),limit);
            return  "post-by-user";
        }

        return "redirect:/home";

    }

    private  void setResult(ModelMap modelMap,List<Post> list,String username,int page,int totalList,int limit)
    {
        if(list == null)
        {
            list = new ArrayList<Post>();
        }
        modelMap.addAttribute("postList",list);
        modelMap.addAttribute("page",page);
        modelMap.addAttribute("totalList", totalList);
        modelMap.addAttribute("limit",limit);
        modelMap.addAttribute("userName",username);
    }
}
