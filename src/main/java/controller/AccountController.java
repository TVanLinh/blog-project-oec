package controller;

import entities.Configuration;
import entities.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import service.ConfigurationService;
import service.PostService;
import service.UserService;
import utils.page.DefaultPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

/**
 * Created by linhtran on 06/05/2017.
 */

@Controller
public class AccountController {


    @Autowired
    ConfigurationService   configurationService;

    @Autowired
    UserService userService;

    @Autowired
    PostService postService;

    @Autowired
    DefaultPage defaultPage;

    @RequestMapping(value = "/user")

    public  String userInfor(Principal principal,HttpServletRequest request,ModelMap modelMap) {
            defaultPage.setDaultPage(request);
            String page = request.getParameter("page");
            request.setAttribute("userDAO",this.userService);
            if(request.getSession().getAttribute("username") == null) {
                System.out.println(principal.getName());
                System.out.println("Create Session");
                HttpSession session=request.getSession();
                request.setAttribute("postList",this.postService.getPostByIdUser(this.userService.getUserByName(principal.getName()).getId()));
                session.setAttribute("username",principal.getName());

                Configuration configuration = this.configurationService.getAllConfiguration().get(0);
                session = request.getSession();
                if(configuration != null) {
                    session.setAttribute("dateFormat",configuration.getDateFormat());
                    session.setAttribute("blogTitle",configuration.getWebTitle());
                }

            }

            List<Post> postList;
            int limit =   this.configurationService.find(1).getNumberViewPost();

            if(page == null) {
                postList = this.postService.getPostByIdUser(this.userService.getUserByName(principal.getName()).getId(),0,limit);
                setReponseUserInfor(modelMap,postList,1,"author");
                return "author";
            }
            try {
                postList = this.postService.getPostByIdUser(this.userService.getUserByName(principal.getName()).getId(),(Integer.valueOf(page)-1)*limit,limit);
                setReponseUserInfor(modelMap,postList,Integer.valueOf(page),"author");

            }catch (Exception e) {
                postList = this.postService.getPostByIdUser(this.userService.getUserByName(principal.getName()).getId(),0,limit);
                setReponseUserInfor(modelMap,postList,1,"author");
                return "author";
            }
        return "author";
    }

    private  void setReponseUserInfor(ModelMap modelMap,List<Post> postList,int page,String ative)
    {
        modelMap.addAttribute("page",page);
        modelMap.addAttribute("postList",postList);
        modelMap.addAttribute("active",ative);
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error, @RequestParam(value = "logout", required = false) String logout) {
        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid username or password!");
        }

        if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
        }

        model.setViewName("login");

        return model;

    }
    @RequestMapping(value = "/logout")
    public String  logout(HttpServletRequest request) {
        request.getSession().removeAttribute("username");
        request.getSession().invalidate();
        HttpSession session = request.getSession();
        session.setAttribute("begin",0);
        return "/home";
    }

    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "/home";
    }

    //for 403 access denied page
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView accesssDenied() {

        ModelAndView model = new ModelAndView();

        //check if user is login
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            model.addObject("username", userDetail.getUsername());
        }

        model.setViewName("redirect:/home");
        return model;

    }

}
