package controller;

import entities.Configuration;
import entities.Post;
import entities.User;
import exceptions.AccessDenieException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import service.*;
import utils.number.NumberViewSort;
import utils.page.DefaultPages;
import utils.sort.PortSort;
import utils.sort.SortType;
import utils.string.StringSessionUtil;
import vadilator.UserFormValidator;

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
    private     ConfigurationService   configurationService;

    @Autowired
    private     UserService userService;

    @Autowired
    private     PostService postService;


    @Autowired
    private     DefaultPages defaultPage;

    @Autowired
    private     PortSort portSort;

    @Autowired
    private     BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private     RequestService requestService;

    @Autowired
    private     PostSortService postSortSerVice;

    @Autowired
    private     UserFormValidator userFormValidator;

    @RequestMapping(value = "/user")
    public  String userInfor(Principal principal,HttpServletRequest request,
                             ModelMap modelMap,
                             @RequestParam(value = "page",required = false) String pageRequest,
                             @RequestParam(value = "action",required = false)String action,
                             @RequestParam(value = "id",required = false) String id) throws AccessDenieException {
        setDefaultUser(principal,request,modelMap);
        modelMap.addAttribute("userDAO",this.userService);

        List<Post> postList;
        User user = userService.getUserByName(principal.getName());

        if(StringUtils.isNumeric(id) && !this.userService.isEditPost(user,this.postService.find(Integer.valueOf(id))))
        {
            throw new AccessDenieException(AccessDenieException.ACCESS_NOT_ROLE_POST);
        }

        this.postService.deletePost(action,id);
        int page = NumberUtils.toInt(pageRequest,1);

        postList = postSortSerVice.getAllPostByUser(request,user,(page-1)* NumberViewSort.getNumberView(),NumberViewSort.getNumberView());
        this.requestService.setResponse(modelMap,postList,this.postService.getPostByIdUser(user.getId()).size(),page);
        return "author";
    }


    private  void setDefaultUser(Principal principal,HttpServletRequest request,ModelMap modelMap)
    {

        if(request.getSession().getAttribute("username") == null) {
            HttpSession session=request.getSession();
            modelMap.addAttribute("postList",this.postService.getPostByIdUser(this.userService.getUserByName(principal.getName()).getId()));
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
    public  String searchTableAllPost(HttpServletRequest request,ModelMap modelMap,@RequestParam(value = "page",required = false)String pageRequest,@RequestParam(value = "query_search",required = false)String querySearch, Principal principal) {
        SortType sortType=this.portSort.getCurrentSortType(request,StringSessionUtil.CURRENT_POST_ALL_TYPE_SORT_BY_USER);
        User user=this.userService.getUserByName(principal.getName());
        int page = NumberUtils.toInt(pageRequest,1);
        this.requestService.setResponse(modelMap,this.postService.getPostByIdUser(sortType,querySearch,user.getId(),(page-1)*NumberViewSort.getNumberView()),this.postService.getCountByUserContainsTitle(querySearch,user.getId()),page,null,null,querySearch);

        return "author";
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error, @RequestParam(value = "logout", required = false) String logout) {
        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "validation.field.not.right.pass.user");
        }
        if (logout != null) {
            model.addObject("msg", "logout.success");
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
    public String  accessDenied() throws AccessDenieException {

//        ModelAndView model = new ModelAndView();

//        //check if user is login
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (!(auth instanceof AnonymousAuthenticationToken)) {
//            UserDetails userDetail = (UserDetails) auth.getPrincipal();
//            model.addObject("username", userDetail.getUsername());
//        }
       throw new AccessDenieException(AccessDenieException.ACESS_NOT_ROLE_PAGE);
    }

    @RequestMapping(value = "/change-pass-word",method = RequestMethod.GET)
    public String pageChangePassWord(HttpServletRequest request)
    {
        return "change-pass-word";
    }

    @RequestMapping(value = "/change-pass-word",method = RequestMethod.POST)
    public ModelAndView actionChangePassWord(Principal principal,HttpServletRequest request,
                                             @RequestParam(value = "passWord")String passWord,
                                             @RequestParam(value = "rePassWord")String rePassWord,
                                             @RequestParam(value = "oldPassWord")String oldPassWord) {
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.getUserByName(principal.getName());
        modelAndView.setViewName("change-pass-word");

        if(user!=null && !passwordEncoder.matches(oldPassWord,user.getPassWord())) {
            modelAndView.addObject(requestService.MESSAGE, UserFormValidator.VALIDATION_FIELD_PASS_NOT_RIGHT);
        }else if(!this.userFormValidator.checkPassWordBlank(passWord,rePassWord))
        {
            modelAndView.addObject(requestService.MESSAGE, UserFormValidator.VALIDATION_PASS_BLANK);
        }else if(!this.userFormValidator.checkOverlapPassWord(passWord,rePassWord)) {
            modelAndView.addObject(requestService.MESSAGE, UserFormValidator.VALIDATION_PASS_NOT_OVERLAP);
        }else
        {
            user.setPassWord(passwordEncoder.encode(passWord));
            userService.save(user);
            modelAndView.addObject(requestService.MESSAGE, "request.update_success");
        }
        return modelAndView;
    }
}
