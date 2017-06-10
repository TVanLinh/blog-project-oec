package controller;

import entities.Post;
import exceptions.NotFindException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import service.ConfigurationService;
import service.PostService;
import service.RequestService;
import service.UserService;
import utils.page.DefaultPages;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by linhtran on 06/05/2017.
 */
@Controller
public class redirect {
    final static Logger logger = Logger.getLogger(ProcessPost.class);


    @Autowired
    private    UserService userService;

    @Autowired
    private    PostService postService;

    @Autowired
    private    ConfigurationService configurationService;

    @Autowired
    private    DefaultPages defaultPage;

    @Autowired
    private RequestService<Post> requestService;

    @RequestMapping(value = "/write")
    public  String viewWriter(HttpServletRequest request) {
        this.defaultPage.setDaultPage(request);
        request.setAttribute("active","write");
        return "write";
    }

    @RequestMapping(value={"/","/home"})
    public String homePage(HttpServletRequest request, ModelMap modelMap,
                           @RequestParam(value = "page",
                           required = false)String pageRequest) {
        String error = (String) request.getSession().getAttribute("error");
        if( error !=null)
        {
            modelMap.addAttribute("error",error);
            request.getSession().removeAttribute("error");
        }

        this.defaultPage.setDaultPage(request);
        int  page = NumberUtils.toInt(pageRequest,1);
        List<Post> postList;

        int limit = this.configurationService.getAllConfiguration().get(0).getNumberViewPost();
        modelMap.addAttribute("userDAO",this.userService);

        postList = this.postService.getPublic((page-1)*limit,limit);
        this.requestService.setResponse(modelMap,postList,
                                        this.postService.getCountPublic(),
                                        page,"home",null);
        modelMap.addAttribute("limit",limit);
        return "home";
    }


    @RequestMapping(value = "/post")
    public  String viewPost(HttpServletRequest request,ModelMap modelMap,@RequestParam(value = "id",required = false) String id) throws NotFindException {
        this.defaultPage.setDaultPage(request);


        List<Post> postSlideBar = this.postService.getPublic(0, this.configurationService.getAllConfiguration().get(0).getNumberViewPost());
        modelMap.addAttribute("postSlideBar",postSlideBar);

        if(!StringUtils.isNumeric(id) || this.postService.find(Integer.valueOf(id)) == null)
        {
            throw new NotFindException("Not find post " +id);
        }

        Post post = this.postService.find(Integer.valueOf(id));
        post.setNumberView(post.getNumberView()+1);
        this.postService.save(post);
        modelMap.addAttribute("post",post);
        modelMap.addAttribute("active","post");
        return "post";
    }

    @RequestMapping(value = "/error",method = RequestMethod.GET)
    public  String pageProcessException()
    {
        return "ProcessExceptions";
    }


}
