package Controller;

import DAO.PostDAO;
import DAO.UserDAOIML;
import Entities.Configuration;
import Entities.Post;
import Service.ConfigurationService;
import Service.PostService;
import Service.UserService;
import Utils.DefaultPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
    UserDAOIML authorDAOIML;

    @Autowired
    PostService postService;

    @Autowired
    ConfigurationService configurationService;

    @Autowired
    UserService userService;

    @Autowired
    private PostDAO postDAO;

    @Autowired
    DefaultPage defaultPage;
    @RequestMapping(value = "/user")
    public  String userInfor(Principal principal,HttpServletRequest request)
    {
            defaultPage.setDaultPage(request);
            String page=request.getParameter("page");
            if(request.getSession().getAttribute("username")==null)
            {
                System.out.println(principal.getName());
                System.out.println("Create Session");
                HttpSession session=request.getSession();
                request.setAttribute("postList",postService.getPostByIdUser(userService.getUserByName(principal.getName()).getId()));
                session.setAttribute("username",principal.getName());
                //----------------------------------------------
                Configuration configuration=configurationService.getAllConfiguration().get(0);
                session=request.getSession();
                if(configuration!=null)
                {
                    session.setAttribute("dateFormat",configuration.getDateFormat());
                    session.setAttribute("blogTitle",configuration.getWebTitle());
                }

                //-----------------------------
            }
            List<Post> postList;
            int limit=configurationService.find(1).getNumberViewPost();
            if(page==null)
            {
                postList=postService.getPostByIdUser(userService.getUserByName(principal.getName()).getId(),0,limit);
                request.setAttribute("page",1);
                request.setAttribute("postList",postList);
                return "author";
            }
            try
            {
                postList=postService.getPostByIdUser(userService.getUserByName(principal.getName()).getId(),(Integer.valueOf(page)-1)*limit,limit);
                request.setAttribute("page",Integer.valueOf(page));
                System.out.println(page);
                request.setAttribute("postList",postList);

            }catch (Exception e)
            {
                postList=postService.getPostByIdUser(userService.getUserByName(principal.getName()).getId(),0,limit);
                request.setAttribute("page",1);
                request.setAttribute("postList",postList);
                return "author";
            }

        return "author";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout, HttpSession session) {


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
    public String  logout(HttpServletRequest request)
    {
        request.getSession().removeAttribute("username");
        request.getSession().invalidate();
        HttpSession session=request.getSession();
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
            System.out.println(userDetail);

            model.addObject("username", userDetail.getUsername());

        }

        model.setViewName("403");
        return model;

    }

    @RequestMapping(value = "/wellcome/index")
    @ResponseBody
    public  String index()
    {
//        User u = this.userService.find(1);
//        for (int i = 101; i < 1000; i++) {
//            Post p = new Post();
//            p.setTitle("Title " + i);
//            p.setContent("Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat.");
//            p.setStatus(1);
//            p.setApprove(1);
//            p.setUser(u);
//
//            this.postDAO.insert(p);
//        }
        return "index";
    }


}
