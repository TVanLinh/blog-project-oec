package controller;

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
import service.RequestService;
import service.RoleService;
import service.UserService;
import service.UserSortService;
import utils.number.NumberViewSort;
import utils.page.DefaultPages;
import utils.sort.Sort;
import utils.sort.SortType;
import utils.string.StringSessionUtil;
import vadilator.UserFormInsertUserValidator;
import vadilator.UserFormUpdateValidator;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
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
    private RequestService<User> requestService;

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

    @RequestMapping("/manager-user")
    public String managerUser(HttpServletRequest request,
                              ModelMap modelMap,
                              Principal principal,
                              @RequestParam(value = "page", required = false) String pageRequest) {
        List<User> userList;

        int page = NumberUtils.toInt(pageRequest, 1);

        if (request.getSession().getAttribute(requestService.MESSAGE) != null) {
            request.setAttribute(requestService.MESSAGE, request.getSession().getAttribute(requestService.MESSAGE));
            request.getSession().removeAttribute(requestService.MESSAGE);
        }

//        deleteUser(request, principal);

        userList = this.userSortService.getUser(request, (page - 1) * NumberViewSort.NUMBER_VIEW, NumberViewSort.getNumberView());
        this.requestService.setResponse(modelMap, userList, this.userService.findAll(User.class, "user").size(), page);
        return "manager-user";
    }

    @RequestMapping(value = "/delete-user")
    public String deleteUser(HttpServletRequest request,ModelMap modelMap,@RequestParam(value = "id",required = false)String id,
                             @RequestParam(value = "page", required = false) String pageRequest) throws AccessDenieException, NotFindException {
        String userName= (String) request.getSession().getAttribute("username");
        if(!userService.isRoleAdmin(this.userService.getUserByName(userName))){
            throw new AccessDenieException(AccessDenieException.ACESS_NOT_ROLE_PAGE);
        }
        if(!StringUtils.isNumeric(id) || this.userService.find(Integer.valueOf(id)) == null) {
           throw new NotFindException(NotFindException.USER_NOT_FOUND);
        }

        this.userService.delete(Integer.valueOf(id));

        int page = NumberUtils.toInt(pageRequest,1);
        List<User> userList = this.userSortService.getUser(request, (page - 1) * NumberViewSort.NUMBER_VIEW, NumberViewSort.getNumberView());
        this.requestService.setResponse(modelMap, userList, this.userService.findAll(User.class, "user").size(), page);

        modelMap.addAttribute(requestService.MESSAGE,"delete.success");

        return "manager-user";
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
    public String actionInsertUser(HttpServletRequest request, @ModelAttribute UserForm userForm, ModelMap modelMap, BindingResult bindingResult) {
        this.defaultPage.setDaultPage(request);

        this.insertUserValidator.validate(userForm,bindingResult);

        if(bindingResult.hasErrors()) {
            modelMap.addAttribute("errors",this.insertUserValidator.getCodeErrors(bindingResult));
            return  "insert-user";
        }

        userForm.getUser().setPassWord(passwordEncoder.encode(userForm.getUser().getPassWord()));
        this.userService.save(userForm.getUser());
        request.getSession().setAttribute(requestService.MESSAGE, requestService.INSERT_SUCCESS);
        return "redirect:manager-user";
    }

    ///---------------update----------------------------------
    @RequestMapping(value = "/update-user")
    public String pageUpdateUser(HttpServletRequest request,ModelMap modelMap) {
        this.defaultPage.setDaultPage(request);
        String id = request.getParameter("id");
        if (!StringUtils.isNumeric(id)) {
            return "redirect:/manager-user";
        }

        User user = this.userService.find(Integer.valueOf(id));
        if (user == null || user.getUserName() == null) {
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
                                   BindingResult bs,Principal principal) {
        updateUserValidator.validate(userForm, bs);

        User userCurrent = this.userService.find(userForm.getUser().getId());

        //th doi chinh minh

        if(bs.hasErrors()) {
            modelMap.addAttribute("errors",updateUserValidator.getCodeErrors(bs));
            return "update-user";
        }


        //th doi chinh nguoi dang dang nhap
        if(principal.getName().equals(userCurrent.getUserName())) {
            request.getSession().setAttribute("username",userForm.getUser().getUserName());
        }

        this.roleService.deleteByUserId(userForm.getUser().getId());

        if(!StringUtils.isBlank(userForm.getUser().getPassWord())) {
            userCurrent.setPassWord(this.passwordEncoder.encode(userForm.getRePassWord()));
        }

        userCurrent.setUserName(userForm.getUser().getUserName());
        userCurrent.setRoleList(userForm.getUser().getRoleList());

        this.userService.save(userCurrent);
        request.getSession().setAttribute(requestService.MESSAGE, "request.update_success");
        return  "redirect:manager-user";
    }


    //-----------------------end update


    @RequestMapping(value = "/manager-user-search", method = RequestMethod.GET)
    public String searchUser(HttpServletRequest request, ModelMap modelMap,
                             @RequestParam(value = "page", required = false) String pageReques,
                             @RequestParam(value = "query_search", required = false) String querySearch) {
        int page = NumberUtils.toInt(pageReques, 1);
        SortType sortType = this.sort.getCurrentSortType(request, StringSessionUtil.CURRENT_USER_SORT);

        List<User> userList;
        userList = this.userService.getUserBeginByUserName(querySearch, sortType, (page - 1) * NumberViewSort.getNumberView());

        this.requestService.setResponse(modelMap, userList, this.userService.getCountBeginUserName(querySearch), page, null, null, querySearch);
        return "manager-user";
    }


}
