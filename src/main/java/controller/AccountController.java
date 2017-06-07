package controller;

import entities.Configuration;
import entities.Post;
import entities.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import utils.number.NumberViewSort;
import utils.page.DefaultPage;
import utils.sort.PortSort;
import utils.sort.SortType;
import utils.string.StringSessionUtil;

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

    @Autowired
    PortSort portSort;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @RequestMapping(value = "/user")
    public  String userInfor(Principal principal,HttpServletRequest request,ModelMap modelMap) {
        defaultPage.setDaultPage(request);
        setDefaultUser(principal,request);

        String page = request.getParameter("page");
        request.setAttribute("userDAO",this.userService);
        List<Post> postList;

        User user= userService.getUserByName(principal.getName());
        if(page == null||page.trim().equals("") || !StringUtils.isNumeric(page)||Integer.valueOf(page )== 0) {
            this.postService.deletePost(request);
            postList=this.postService.finAll(this.portSort.getQuerySortAllPostByUserName(request,0,user));
            this.postService.setListPost(modelMap,postList,this.postService.getPostByIdUser(user.getId()).size());
            request.setAttribute("page",1);
            return "author";
        }

        this.postService.deletePost(request);
        postList = this.postService.finAll(this.portSort.getQuerySortAllPostByUserName(request,(Integer.valueOf(page)-1)* NumberViewSort.NUMBER_VIEW,user));
        this.postService.setListPost(modelMap,postList,this.postService.getPostByIdUser(user.getId()).size());

        request.setAttribute("page", Integer.valueOf(page));
        return "author";
    }

    private  void setDefaultUser(Principal principal,HttpServletRequest request)
    {

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
    }

    @RequestMapping(value = "/user-post-search",method = RequestMethod.GET)
    public  String searchTableAllPost(HttpServletRequest request,ModelMap modelMap,Principal principal) {
        this.defaultPage.setDaultPage(request);
        String page=request.getParameter("page");
        String querySearch=request.getParameter("query_search");
        SortType sortType=this.portSort.getCurrentSortType(request, StringSessionUtil.POST_ALL_TYPE_SORT_BY_USER,StringSessionUtil.CURRENT_POST_ALL_TYPE_SORT_BY_USER);
        List<Post> postList;
        User user=this.userService.getUserByName(principal.getName());
        if(sortType == null) {
            sortType=new SortType();
        }

        if(page == null||page.trim().equals("") || !StringUtils.isNumeric(page) || Integer.valueOf(page)==0 || querySearch == null || querySearch.trim().equals("'") || querySearch.trim().equals("")) {

            postList=this.postService.getPostByIdUser(sortType,querySearch,user.getId(),0);
            this.postService.setListPost(modelMap,postList,this.postService.getCountByUserContainsTitle(querySearch,user.getId()));
            this.postService.setResultQuerySearch(modelMap,querySearch,1,this.postService.getCountByUserContainsTitle(querySearch,user.getId()));
            return "author";
        }

      //  postList=this.postService.getAllByTitle(sortType,querySearch, (Integer.valueOf(page)-1)*NumberViewSort.NUMBER_VIEW);
        postList=this.postService.getPostByIdUser(sortType,querySearch,user.getId(),(Integer.valueOf(page)-1)*NumberViewSort.NUMBER_VIEW);
        this.postService.setListPost(modelMap,postList,user.getId());
      //  setResultManagerPost(modelMap,querySearch,Integer.valueOf(page));
        this.postService.setResultQuerySearch(modelMap,querySearch,Integer.valueOf(page),this.postService.getCountByUserContainsTitle(querySearch,user.getId()));
        return "author";
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

    @RequestMapping(value = "/change-pass-word",method = RequestMethod.GET)
    public String pageChangePassWord(HttpServletRequest request)
    {
        defaultPage.setDaultPage(request);
        return "change-pass-word";
    }

    @RequestMapping(value = "/change-pass-word",method = RequestMethod.POST)
    public ModelAndView actionChangePassWord(Principal principal,HttpServletRequest request,@RequestParam(value = "passWord")String passWord,@RequestParam(value = "rePassWord")String rePassWord,@RequestParam(value = "oldPassWord")String oldPassWord)
    {
        defaultPage.setDaultPage(request);
        ModelAndView modelAndView=new ModelAndView();
        User user=userService.getUserByName(principal.getName());
        if(user!=null && !passwordEncoder.matches(oldPassWord,user.getPassWord())) {
            modelAndView.addObject("error", "pass word not right !");
            modelAndView.setViewName("change-pass-word");
            return modelAndView;
        }

        if(!utils.string.StringUtils.checkVidPassWord(modelAndView,passWord,rePassWord)) {
            modelAndView.setViewName("change-pass-word");
            return  modelAndView;
        }

        user.setPassWord(passwordEncoder.encode(passWord));
        userService.save(user);
        modelAndView.addObject("error","Change password successful .!");
        modelAndView.setViewName("change-pass-word");
        return modelAndView;
    }
}
