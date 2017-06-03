package controller;

import dao.RoleDAO;
import dao.UserDAO;
import entities.Role;
import entities.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import service.RoleService;
import service.UserService;
import utils.number.NumberViewSort;
import utils.page.DefaultPage;
import utils.sort.SortType;
import utils.sort.UserSort;
import utils.string.StringSessionUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by linhtran on 02/06/2017.
 */

@Controller
public class ManagerUser {

    @Autowired
    UserDAO userDAO;

    @Autowired
    UserService userService;

    @Autowired
    DefaultPage defaultPage;

    @Autowired
    RoleDAO roleDAO;

    @Autowired
    RoleService roleService;

    @Autowired
    UserSort userSort;

    @RequestMapping(value = "/update-user")
    public  String pageUpdateUser( HttpServletRequest request, @RequestParam(value = "id") int id) {
        this.defaultPage.setDaultPage(request);
        request.setAttribute("user",this.userService.find(id));
        request.getSession().setAttribute("idUser",id);
        return "update-user";
    }


    @RequestMapping(value = "/action-update-user",method = RequestMethod.GET)
    public String actionUpdateUser(HttpServletRequest request) {
        this.defaultPage.setDaultPage(request);
        return "redirect:manager-user";
    }

    @RequestMapping(value = "/action-update-user",method = RequestMethod.POST)
    public String actionUpdateUser(ModelMap modelMap,HttpServletRequest request,@ModelAttribute User user) {
        this.defaultPage.setDaultPage(request);
        modelMap.addAttribute("roleList",this.userService.findAll(User.class,"user"));
        String []listRoles = request.getParameterValues("listRole");

        if(!userService.checkUserValidUpdate(modelMap,user)||listRoles==null) {
            request.setAttribute("error","Not valid!");
            return "update-user";
        }else {
            HttpSession session = request.getSession();
            User userUpdate = this.userService.find((Integer) session.getAttribute("idUser"));
            if(userUpdate.getUserName().equalsIgnoreCase(user.getUserName())) {
                User user1 = this.userService.getUserByName(user.getUserName());
                if(user != null)
                {
                    this.roleDAO.delete(user1.getUserName());
                }
                user1.setRoleList(this.roleService.getListRole(listRoles));
                user1.setPassWord(user.getPassWord());
                this.userService.save(user1);
                return "redirect:manager-user";
            }else if(this.userService.getUserByName(user.getUserName()) !=  null) {
                modelMap.addAttribute("error"," user name exits available !");
                return "update-user";
            }else {
                User user1 = this.userService.find((Integer) session.getAttribute("idUser"));
                if(user1!=null)
                {
                    this.userService.delete(user1.getId());
                }
                user.setRoleList(this.roleService.getListRole(listRoles));

                this.userService.save(user);
            }
        }
        return "redirect:manager-user";
    }


    @RequestMapping("/manager-user")
    public  String managerUser(HttpServletRequest request,ModelMap modelMap)
    {
        this.defaultPage.setDaultPage(request);
        List<User> userList;
        String page=request.getParameter("page");

        if(page == null || page.trim().equals("") || !StringUtils.isNumeric(page) || Integer.valueOf(page) == 0) {
            if(this.userSort.getQueryUserByRole(request,0)!=null) {
                userList=this.userService.findAll(this.userSort.getQueryUserByRole(request,0));
                setListUser(modelMap,userList);
                setResultManagerUser(modelMap,1);
                return "manager-user";
            }

            deleteUser(request);

            userList = this.userService.findAll(this.userSort.getQuerySort(request,0));
            setListUser(modelMap,userList);
            setResultManagerUser(modelMap,1);
            return "manager-user";
        }

        deleteUser(request);

        if(this.userSort.getQueryUserByRole(request,0) != null) {
            userList = this.userService.findAll(this.userSort.getQueryUserByRole(request,(Integer.valueOf(page)-1)* NumberViewSort.NUMBER_VIEW));
            setListUser(modelMap,userList);
            setResultManagerUser(modelMap,Integer.valueOf(page));
            return "manager-user";
        }

        userList = this.userService.findAll(this.userSort.getQuerySort(request,(Integer.valueOf(page)-1)*NumberViewSort.NUMBER_VIEW));
        setListUser(modelMap,userList);
        setResultManagerUser(modelMap,Integer.valueOf(page));
        return "manager-user";
    }

    private void setResultManagerUser(ModelMap modelMap,int page)
    {
        modelMap.addAttribute("page",page);
        modelMap.addAttribute("totalList",this.userService.findAll(User.class,"user").size());
    }


    private void deleteUser(HttpServletRequest request)
    {
        String action = request.getParameter("action");
        String id=request.getParameter("id");

        if(action != null && action.equals("delete")) {
            if(id != null && StringUtils.isNumeric(id)) {
                User user = this.userService.find(Integer.valueOf(id));
                if(user != null) {
                    this.userService.delete(user.getId());
                }

            }
        }
    }
    private void setListUser(ModelMap modelMap,  List<User> users)
    {
        if(users == null) {
            users = new ArrayList<User>();
        }
        modelMap.addAttribute("userList",users);
    }

    @RequestMapping(value = "/insert-user")
    public  String pageInsertUser(HttpServletRequest request) {
        this.defaultPage.setDaultPage(request);
        return "insert-user";
    }

    @RequestMapping(value = "/action-insert-user",method = RequestMethod.GET)
    public String actionInsertUser(HttpServletRequest request)
    {
        this.defaultPage.setDaultPage(request);
        return "redirect:manager-user";
    }
    @RequestMapping(value = "/action-insert-user",method = RequestMethod.POST)
    public  String actionInsertUser(HttpServletRequest request,@ModelAttribute User user,ModelMap modelMap) {
        this.defaultPage.setDaultPage(request);

        String[] arr = request.getParameterValues("listRole");
        if(!this.userService.checkUserInsert(modelMap,user,arr))
        {
            return "insert-user";
        }

        List<Role> roles = new ArrayList<Role>();
        for(int i = 0 ; i < arr.length ; i++)
        {
            roles.add(new Role(arr[i],user));
        }
        user.setRoleList(roles);
        this.userService.save(user);
        return "redirect:manager-user";
    }

    @RequestMapping(value = "/manager-user-search",method = RequestMethod.GET)
    public  String searchUser(HttpServletRequest request,ModelMap modelMap) {
        this.defaultPage.setDaultPage(request);
        String page = request.getParameter("page");
        String querySearch = request.getParameter("query_search");
        SortType sortType = this.userSort.getSort().getCurrentSortType(request, StringSessionUtil.USER_TYPE_SORT,StringSessionUtil.CURRENT_USER_SORT);
        List<User> userList;

        if(sortType == null) {
            sortType=new SortType();
        }

        if(page == null || page.trim().equals("") || !StringUtils.isNumeric(page)||Integer.valueOf(page) == 0 || querySearch == null || querySearch.trim().equals("'") || querySearch.trim().equals("")) {

            userList = this.userService.getUserBeginByUserName(querySearch,sortType,0);
            setListUser(modelMap,userList);
            setResponeManagerUser(modelMap,querySearch,1);
            return "manager-user";
        }

        userList = this.userService.getUserBeginByUserName(querySearch,sortType,(Integer.valueOf(page)-1)*NumberViewSort.NUMBER_VIEW);
        setListUser(modelMap,userList);
        setResponeManagerUser(modelMap,querySearch,Integer.valueOf(page));
        return "manager-user";
    }

    private void  setResponeManagerUser(ModelMap modelMap,String querySearch,int page)
    {
        modelMap.addAttribute("page",page);
        modelMap.addAttribute("querySearch",querySearch);
        modelMap.addAttribute("totalList",this.userService.getCountBeginUserName(querySearch));
    }
}
