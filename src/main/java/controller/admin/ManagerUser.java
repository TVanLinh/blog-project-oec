package controller.admin;

import entities.User;
import exceptions.AccessDenieException;
import exceptions.NotFindException;
import forms.UserForm;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.*;
import utils.page.DefaultPages;
import utils.session.SessionUtils;
import utils.sort.Sort;
import utils.sort.SortType;
import utils.sort.UserSort;
import utils.string.StringSessionUtil;
import vadilator.UserFormInsertUserValidator;
import vadilator.UserFormUpdateValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by linhtran on 02/06/2017.
 */

@Controller
public class ManagerUser {

    @Autowired
    private UserService userService;

    @Autowired
    private DefaultPages defaultPage;

    @Autowired
    private RoleService roleService;


    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private Sort sort;

    @Autowired
    private UserSort userSort;

    @Autowired
    private UserSortService userSortService;


    @Autowired
    private UserFormUpdateValidator updateUserValidator;

    @Autowired
    private UserFormInsertUserValidator insertUserValidator;

    @ModelAttribute
    public UserForm initUserForm() {
        return new UserForm();
    }

    @Autowired
    private PostService postService;

    @RequestMapping("/manager-user")
    public String managerUser(HttpServletRequest request,
                              ModelMap modelMap,
                              @RequestParam(value = "page", required = false) String pageRequest,
                              @RequestParam(value = "number", required = false) String numberView) {
        int page = NumberUtils.toInt(pageRequest, 1);
        int limit = this.postService.getLimit(numberView);
        SortType sortType = this.userSort.getSortType(request, StringSessionUtil.CURRENT_USER_SORT,"user_name");
        List<User> userList = this.userSortService.getUsers(sortType, (page - 1) * limit, limit);
        RequestService.setResponse(modelMap, limit, userList, this.userService.getCount());
        return "manager-user";
    }

    @RequestMapping(value = "/delete-user")
    public String deleteUser(@RequestParam(value = "id",required = false)String id,
                             @RequestParam(value = "page", required = false) String pageRequest,
                             RedirectAttributes redirectAttributes) throws AccessDenieException, NotFindException {

        try{
            this.userService.delete(Integer.valueOf(id));
            RequestService.setResponse(redirectAttributes,pageRequest,RequestService.DELETE_SUCCESS);
        }catch (Exception ex){
            RequestService.setResponse(redirectAttributes,pageRequest,RequestService.DELETE_NOT_SUCCESS);
            return "redirect:/manager-user";
        }
        return "redirect:/manager-user";
    }


    @RequestMapping(value = "/insert-user")
    public String pageInsertUser(HttpServletRequest request) {
        this.defaultPage.setDaultPage(request);
        return "insert-user";
    }

    @RequestMapping(value = "/action-insert-user", method = RequestMethod.GET)
    public String actionInsertUser(HttpServletRequest request) {
        this.defaultPage.setDaultPage(request);
        return "redirect:manager-user";
    }

    @RequestMapping(value = "/action-insert-user", method = RequestMethod.POST)
    public String actionInsertUser(HttpServletRequest request,
                                   @ModelAttribute UserForm userForm,
                                   ModelMap modelMap,
                                   BindingResult bindingResult,
                                   RedirectAttributes redirectAttributes) {
        this.defaultPage.setDaultPage(request);

        this.insertUserValidator.validate(userForm,bindingResult);

        if(bindingResult.hasErrors()) {
            modelMap.addAttribute("errors",this.insertUserValidator.getCodeErrors(bindingResult));
            return  "insert-user";
        }
        userForm.getUser().setPassWord(passwordEncoder.encode(userForm.getUser().getPassWord()));
        this.userService.save(userForm.getUser());
        RequestService.setResponse(redirectAttributes,RequestService.INSERT_SUCCESS);
        return "redirect:manager-user";
    }

    ///---------------update----------------------------------
    @RequestMapping(value = "/update-user")
    public String pageUpdateUser(HttpServletRequest request,ModelMap modelMap,RedirectAttributes redirectAttributes) {
        this.defaultPage.setDaultPage(request);
        String id = request.getParameter("id");
        User user ;
        if (!StringUtils.isNumeric(id) || (user = this.userService.find(Integer.valueOf(id)) ) == null) {
            RequestService.setResponse(redirectAttributes,"",NotFindException.USER_NOT_FOUND);
            return "redirect:/manager-user";
        }
        setDefaultUpdateUser(user,modelMap);
        return "update-user";
    }

    private   void setDefaultUpdateUser(User user,ModelMap modelMap)
    {
        modelMap.addAttribute("user", user);
        modelMap.addAttribute("userService",this.userService);
    }

    @RequestMapping(value = "/action-update-user", method = RequestMethod.GET)
    public String actionUpdateUser() {
        return "redirect:manager-user";
    }

    @RequestMapping(value = "/action-update-user", method = RequestMethod.POST)
    @Transactional
    public String actionUpdateUser(ModelMap modelMap,
                                   HttpServletRequest request,
                                   @ModelAttribute(value = "userForm") UserForm  userForm,
                                   @RequestParam(value = "page", required = false) String pageRequest,
                                   BindingResult bs,
                                   RedirectAttributes redirectAttributes) {
        updateUserValidator.validate(userForm, bs);

        User userCurrent = this.userService.find(userForm.getUser().getId());

        //th doi chinh minh

        if(bs.hasErrors()) {
            modelMap.addAttribute("errors",updateUserValidator.getCodeErrors(bs));
            return "update-user";
        }


        //th doi chinh nguoi dang dang nhap
        User user = (User) request.getSession().getAttribute(SessionUtils.USER_LOGIN);
        if (user.getUserName().equals(userCurrent.getUserName())) {
            request.getSession().setAttribute(SessionUtils.USER_LOGIN, userForm.getUser());
        }

        this.roleService.deleteByUserId(userForm.getUser().getId());

        if(!StringUtils.isBlank(userForm.getUser().getPassWord())) {
            userCurrent.setPassWord(this.passwordEncoder.encode(userForm.getRePassWord()));
        }

        userCurrent.setUserName(userForm.getUser().getUserName());
        userCurrent.setRoleList(userForm.getUser().getRoleList());
        try {
                this.userService.save(userCurrent);
                RequestService.setResponse(redirectAttributes,pageRequest,RequestService.UPDATE_SUCCESS);
        }catch (Exception ex){
            RequestService.setResponse(redirectAttributes,pageRequest,RequestService.UPDATE_NOT_SUCCESS);
        }

        return  "redirect:manager-user";
    }
    //-----------------------end update
    @RequestMapping(value = "/manager-user-search", method = RequestMethod.GET)
    public String searchUser(HttpServletRequest request, ModelMap modelMap,
                             @RequestParam(value = "page", required = false) String pageReques,
                             @RequestParam(value = "query_search", required = false) String querySearch,
                             @RequestParam(value = "number", required = false) String numberView) {
        int page = NumberUtils.toInt(pageReques, 1);
        int limit = this.postService.getLimit(numberView);
        SortType sortType = this.sort.getCurrentSortType(request, StringSessionUtil.CURRENT_USER_SORT);
        List<User> userList = this.userService.getUserBeginByUserName(querySearch, sortType, (page - 1) * limit, limit);
        RequestService.setResponse(modelMap, limit, userList, this.userService.getCountBeginUserName(querySearch));
        return "manager-user";
    }


}
