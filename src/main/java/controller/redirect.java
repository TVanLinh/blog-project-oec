package controller;

import entities.Post;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import service.ConfigurationService;
import service.PostService;
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
    UserService userService;

    @Autowired
    PostService postService;

    @Autowired
    ConfigurationService configurationService;

    @Autowired
    DefaultPages defaultPage;

    @RequestMapping(value = "/write")
    public  String viewWriter(HttpServletRequest request) {
        this.defaultPage.setDaultPage(request);
        request.setAttribute("active","write");
        return "write";
    }

    @RequestMapping(value={"/","/home"})
    public String homePage(HttpServletRequest request, ModelMap modelMap) {
        this.defaultPage.setDaultPage(request);
        String page=request.getParameter("page");
        List<Post> postList;

        int limit = this.configurationService.getAllConfiguration().get(0).getNumberViewPost();
        request.setAttribute("userDAO",this.userService);
        if(page == null|| !StringUtils.isNumeric(page) || page.trim().equals("")) {
            postList = this.postService.getPublic(0,limit);
            setResponeHome(modelMap,postList,1);
            return "home";
        }

        postList = this.postService.getPublic((Integer.valueOf(page)-1)*limit,limit);
        setResponeHome(modelMap,postList,Integer.valueOf(page));
        request.setAttribute("page",Integer.valueOf(page));
        return "home";
    }

    private void setResponeHome(ModelMap modelMap,List<Post> postList,int page)
    {
        modelMap.addAttribute("page",page);
        modelMap.addAttribute("postList",postList);
        modelMap.addAttribute("totalList",this.postService.getCountPublic());
        modelMap.addAttribute("limit",this.configurationService.getAllConfiguration().get(0).getNumberViewPost());
        modelMap.addAttribute("active","home");
    }

    @RequestMapping(value = "/post")
    public  String viewPost(HttpServletRequest request,ModelMap modelMap) {
        this.defaultPage.setDaultPage(request);

        String id = request.getParameter("id");
        List<Post> postSlideBar = this.postService.getPublic(0, this.configurationService.getAllConfiguration().get(0).getNumberViewPost());
        modelMap.addAttribute("postSlideBar",postSlideBar);
        if(id == null ||  !StringUtils.isNumeric(id))
        {
            return "redirect:/home";
        }

        if(id != null) {
            try {
                Post post = this.postService.find(Integer.valueOf(id));
                if(post != null) {
                    post.setNumberView(post.getNumberView()+1);
                    this.postService.save(post);
                    modelMap.addAttribute("post",post);
                }
                modelMap.addAttribute("active","post");
                return "post";
            }catch (Exception e) {
                return "/home";
            }
        }
        modelMap.addAttribute("active","home");
        return "/home";
    }

//    @RequestMapping(value = "/tanso")
//    public String tanso(HttpServletRequest request)
//    {
//        this.defaultPage.setDaultPage(request);
//        System.out.println(this.postDAO.getStatisticByMonth());
//        return "tanso";
//    }

}
