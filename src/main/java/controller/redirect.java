package controller;

import entities.Post;
import exceptions.NotFindException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
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

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by linhtran on 06/05/2017.
 */
@Controller
public class redirect {
    @Autowired
    private    UserService userService;

    @Autowired
    private    PostService postService;

    @Autowired
    private    ConfigurationService configurationService;

    @RequestMapping(value = "/write")
    public  String viewWriter(HttpServletRequest request) {

        request.setAttribute("active","write");
        return "write";
    }

    @RequestMapping(value={"/","/home"})
    public String homePage(HttpServletRequest request, ModelMap modelMap,
                           @RequestParam(value = "page",
                           required = false)String pageRequest) {
        String error = (String) request.getSession().getAttribute(RequestService.MESSAGE);
        if( error !=null) {
            modelMap.addAttribute(RequestService.MESSAGE,error);
            request.getSession().removeAttribute(RequestService.MESSAGE);
        }

        int  page = NumberUtils.toInt(pageRequest,1);

        int limit = this.configurationService.getAllConfiguration().get(0).getNumberViewPost();
        modelMap.addAttribute("userDAO",this.userService);
        List<Post> postList = this.postService.getPublic((page-1)*limit,limit);
        RequestService.setResponse(modelMap,limit,postList,this.postService.getCountPublic());
        return "home";
    }


    @RequestMapping(value = "/post")
    public  String viewPost(ModelMap modelMap,@RequestParam(value = "id",required = false) String id) throws NotFindException {



        List<Post> postSlideBar = this.postService.getPublic(0, this.configurationService.getAllConfiguration().get(0).getNumberViewPost());
        modelMap.addAttribute("postSlideBar",postSlideBar);

        if(!StringUtils.isNumeric(id) || this.postService.find(Integer.valueOf(id)) == null) {
            throw new NotFindException(NotFindException.POST_NOT_FOUND);
        }

        Post post = this.postService.find(Integer.valueOf(id));
        post.setNumberView(post.getNumberView()+1);
        this.postService.save(post);
        modelMap.addAttribute("post",post);
        return "post";
    }

    @RequestMapping(value = "/error",method = RequestMethod.GET)
    public  String pageProcessException()
    {
        return "ProcessExceptions";
    }

    @RequestMapping(value = "/404")
    public String page404(){
        return "404";
    }

    @RequestMapping(value = "/test")
    public void test() {

    }

}
