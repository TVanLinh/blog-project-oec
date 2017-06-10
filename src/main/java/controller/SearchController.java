package controller;

import entities.Post;
import entities.User;
import exceptions.NotFindException;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.ConfigurationService;
import service.PostService;
import service.RequestService;
import service.UserService;
import utils.page.DefaultPages;
import utils.sort.SortType;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by linhtran on 19/05/2017.
 */

@Controller
public class SearchController {

    @Autowired
    private     PostService postService;

    @Autowired
    private     DefaultPages defaultPage;


    @Autowired
    private     ConfigurationService configurationService;

    @Autowired
    private     UserService userService;

    @Autowired
    private     RequestService<Post> requestService;

    @RequestMapping(value = "/view-search")
    public  String  processSearchAll(HttpServletRequest request, ModelMap modelMap,
                                     @RequestParam(value = "page",required = false) String pageRequest,
                                     @RequestParam(value = "title",required = false) String title){
        this.defaultPage.setDaultPage(request);
        int limit = this.configurationService.getAllConfiguration().get(0).getNumberViewPost();
        int  page = NumberUtils.toInt(pageRequest,1);

        modelMap.addAttribute("title",title);
        requestService.setResponse(modelMap,
                                    this.postService.getPostPublicByTitle(new SortType(),title,(page-1)*limit,limit),
                                    this.postService.getPostPublicByTitle(new SortType(),title).size(),page,null,null);
        modelMap.addAttribute("limit",limit);
        return "view-search";
    }


    @RequestMapping(value = "/list-post-by-user")
    public  String  getPostByUser(HttpServletRequest request, ModelMap modelMap,
                                  @RequestParam(value = "username",required = false) String username,
                                  @RequestParam(value = "page",required = false) String pageRequest ) throws NotFindException {
        defaultPage.setDaultPage(request);
        List<Post> posts;
        User user;
        int limit = this.configurationService.getAllConfiguration().get(0).getNumberViewPost();

        modelMap.addAttribute("userDAO",this.userService);

        int  page = NumberUtils.toInt(pageRequest,1);

        user = this.userService.getUserByName(username);
        if(user == null)
        {
            throw new NotFindException("Not find user name "+username);
        }

        SortType sortType=new SortType();
        sortType.orderBy="time_post";
        posts=this.postService.getPost(user.getId(),1,1,sortType,(page-1)*limit,limit);
        this.requestService.setResponse(modelMap,posts,
                                        this.postService.getPostByIdUser(user.getId()).size(),page,
                                        null,null);
        modelMap.addAttribute("limit",limit);
        modelMap.addAttribute("userName",username);
        return  "post-by-user";
    }
}
