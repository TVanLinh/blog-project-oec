package controller;

import dao.ConfigurationDAO;
import dao.PostDAO;
import dao.RoleDAO;
import dao.UserDAO;
import entities.Configuration;
import entities.Post;
import entities.Role;
import entities.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import service.RoleService;
import utils.number.NumberViewSort;
import utils.page.DefaultPage;
import utils.sort.PortSort;
import utils.sort.SortType;
import utils.sort.UserSort;
import utils.string.StringSessionUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by linhtran on 11/05/2017.
 */

@Controller
public class AdminController
{

    @Autowired
    ConfigurationDAO configDAO;

    @Autowired
    DefaultPage defaultPage;

    @Autowired
    UserDAO  userDAO;

    @Autowired
    RoleService roleService;

    @Autowired
    RoleDAO roleDAO;

    @Autowired
    PostDAO postDAO;

    @Autowired
    PortSort portSort;

    @Autowired
    UserSort userSort;

    @RequestMapping(value = "/admin**", method = RequestMethod.GET)
    public ModelAndView adminPage(HttpServletRequest request) {
        this.defaultPage.setDaultPage(request);
        ModelAndView model = new ModelAndView();
        List<Post> postList;
        String page=request.getParameter("page");

        if(page == null || page.trim().equals("") || !StringUtils.isNumeric(page) || Integer.valueOf(page) == 0) {
            deletePost(request);
            aprrovePost(request);
            postList=this.postDAO.getAllPost(this.portSort.getQuerySortAllPostAprrove(request,0,true));
            setListPost(request,postList);
            request.setAttribute("page",1);
            model.setViewName("admin");
            request.setAttribute("active","admin");
            request.setAttribute("totalPost",this.postDAO.getAllPost("select * from post where approve = 0 ").size());
            return model;
        }

        deletePost(request);
        aprrovePost(request);
        postList=this.postDAO.getAllPost(this.portSort.getQuerySortAllPostAprrove(request,(Integer.valueOf(page)-1)* NumberViewSort.NUMBER_VIEW,true));
        setListPost(request,postList);
        request.setAttribute("page", Integer.valueOf(page));
        model.setViewName("admin");
        request.setAttribute("active","admin");
        request.setAttribute("totalPost",this.postDAO.getAllPost("select * from post where approve = 0 ").size());
        return model;
    }


    @RequestMapping(value = "/admin-search-post-approve",method = RequestMethod.GET)
    public  String searchTableApprovePost(HttpServletRequest request) {
        this.defaultPage.setDaultPage(request);
        String page=request.getParameter("page");
        String querySearch=request.getParameter("query_search");
        SortType sortType=this.portSort.getCurrentSortType(request, StringSessionUtil.POST_APPROVE_TYPE_SORT,StringSessionUtil.CURRENT_APPROVE_POST);
        List<Post> postList;

        if(sortType == null)
        {
            sortType = new SortType();
        }
        if(page == null || page.trim().equals("") || !StringUtils.isNumeric(page) || Integer.valueOf(page) == 0 || querySearch == null || querySearch.trim().equals("'") || querySearch.trim().equals(""))
        {

            postList = this.postDAO.getAllPost("select * from  post where approve=0 and title like '%"+querySearch+"%'  order by "+sortType.orderBy +" "+sortType.typeOrder+" limit 0,"+NumberViewSort.NUMBER_VIEW);
            setListPost(request,postList);
            setResponseAdmin( request, querySearch, "admin",1);
            return "admin";
        }
        postList = this.postDAO.getAllPost("select * from post  where approve = 0 and title like '%"+querySearch+"%'  order by "+sortType.orderBy +" "+sortType.typeOrder+" limit "+(Integer.valueOf(page)-1)*NumberViewSort.NUMBER_VIEW+","+NumberViewSort.NUMBER_VIEW);
        setListPost(request,postList);
        setResponseAdmin( request, querySearch, "admin", Integer.valueOf(page));
        request.setAttribute("page", Integer.valueOf(page));
        return "admin";
    }

    public  void setResponseAdmin(HttpServletRequest request,String querySearch,String pageActive,int page)
    {
        request.setAttribute("page",1);
        request.setAttribute("querySearch",querySearch);
        request.setAttribute("totalPost",this.postDAO.getAllPost("select * from  post where approve = 0 and title like '%"+querySearch+"%'").size());
        request.setAttribute("active",pageActive);
    }

    @RequestMapping(value = "/configuration")
    public  String configurarion(HttpServletRequest request) {
        this.defaultPage.setDaultPage(request);
        request.setAttribute("conf",this.configDAO.getAllConfiguration().get(0));
        return "configuration";
    }

    @RequestMapping("/processConfigurarion")
    public  String processConfigurarion(HttpServletRequest request) {
        this.defaultPage.setDaultPage(request);

        String title=request.getParameter("titleBlog");
        String formatTime =request.getParameter("formatTime");
        String numberPost =request.getParameter("numberPost");
        System.out.println(title+"  \t"+formatTime+"\t"+numberPost);

        if(title == null || formatTime == null || numberPost == null) {
            this.setResultConfig(request,"Not valid!");
            return "configuration";
        }

        if(title.trim().equals("") || formatTime.trim().equals("") || numberPost.trim().equals("")) {
            this.setResultConfig(request,"Title ,format time not valid!");
            return "configuration";
        }

        if(Integer.valueOf(numberPost)<0) {
            this.setResultConfig(request,"Number Post must great than 0.!");
            return "configuration";
        }

        Configuration configuration = this.configDAO.getAllConfiguration().get(0);

        try {
           int result = 0;
           if(configuration == null)
           {
               configuration = new Configuration();
           }else {
               result=1;
           }

           configuration.setNumberViewPost(Integer.valueOf(numberPost));
           configuration.setWebTitle(title);
           configuration.setDateFormat(formatTime);

           if(result == 0) {
               this.configDAO.update(configuration);
           }
           if(result == 1) {
               this.configDAO.update(configuration);
               this.defaultPage.setDaultPage(request);
           }
            this.setResultConfig(request,"Successfully !");
       }catch (Exception e)
       {
           return "configuration";
       }
        return "configuration";
    }

    private void  setResultConfig(HttpServletRequest request,String err)
    {
        request.setAttribute("error",err);
        request.setAttribute("conf",this.configDAO.getAllConfiguration().get(0));
    }

    @RequestMapping("/manager-post")
    public  String managerPost(HttpServletRequest request) {
        this.defaultPage.setDaultPage(request);
        List<Post> postList;
        HttpSession session=request.getSession();

        String page = request.getParameter("page");

         if(page == null||page.trim().equals("") || !StringUtils.isNumeric(page)||Integer.valueOf(page )== 0) {
                deletePost(request);
                postList=this.postDAO.getAllPost(this.portSort.getQuerySortAllPost(request,0));
                setListPost(request,postList);
                request.setAttribute("page",1);
                return "manager-post";
         }

        deletePost(request);
        postList = this.postDAO.getAllPost(this.portSort.getQuerySortAllPost(request,(Integer.valueOf(page)-1)*NumberViewSort.NUMBER_VIEW));
        setListPost(request,postList);
        request.setAttribute("page", Integer.valueOf(page));
        return "manager-post";
    }

    @RequestMapping(value = "/manager-post-search",method = RequestMethod.GET)
    public  String searchTableAllPost(HttpServletRequest request) {
        this.defaultPage.setDaultPage(request);
        String page=request.getParameter("page");
        String querySearch=request.getParameter("query_search");
        SortType sortType=this.portSort.getCurrentSortType(request,StringSessionUtil.POST_ALL_TYPE_SORT,StringSessionUtil.CURRENT_ALL_POST);
        List<Post> postList;

        if(sortType == null) {
            sortType=new SortType();
        }

        if(page == null||page.trim().equals("") || !StringUtils.isNumeric(page) || Integer.valueOf(page)==0 || querySearch == null || querySearch.trim().equals("'") || querySearch.trim().equals("")) {

            postList = this.postDAO.getAllPost("select * from  post where title like '%"+querySearch+"%'  order by "+sortType.orderBy +" "+sortType.typeOrder+" limit 0,"+NumberViewSort.NUMBER_VIEW);
            setListPost(request,postList);
            setResultManagerPost(request,querySearch,1);
            return "manager-post";
        }

        postList = this.postDAO.getAllPost("select * from post  where title like '%"+querySearch+"%'  order by "+sortType.orderBy +" "+sortType.typeOrder+" limit "+(Integer.valueOf(page)-1)*NumberViewSort.NUMBER_VIEW+","+NumberViewSort.NUMBER_VIEW);
        setListPost(request,postList);
        setResultManagerPost(request,querySearch,Integer.valueOf(page));
        return "manager-post";
    }

    private void setResultManagerPost(HttpServletRequest request,String querySearch,int page)
    {
        request.setAttribute("page",page);
        request.setAttribute("querySearch",querySearch);
        request.setAttribute("totalPost",this.postDAO.getAllPost("select * from  post where title like '%"+querySearch+"%'").size());
    }

    private void setListPost(HttpServletRequest request,List<Post> postList) {
        if(postList == null) {
            postList = new ArrayList<Post>();
        }
        request.setAttribute("postList",postList);
        request.setAttribute("totalPost",this.postDAO.getAllPost().size());
    }

    private void deletePost(HttpServletRequest request) {
        String action = request.getParameter("action");
        String id  = request.getParameter("id");

        if(action != null &&action.equals("delete")) {
            if(id != null && StringUtils.isNumeric(id)) {
                if(this.postDAO.find(Integer.valueOf(id)) != null) {
                    this.postDAO.delete(Integer.valueOf(id));
                }
            }
        }
    }

    public void aprrovePost(HttpServletRequest request)
    {
        String action = request.getParameter("action");
        String id = request.getParameter("id");
        if(action != null && action.equals("approve")) {
            Date date;
            Calendar calendar=Calendar.getInstance();
            Post post;
            date = calendar.getTime();
            post = this.postDAO.find(Integer.valueOf(id));
            if(post != null) {

                post.setApprovedTime(date);
                post.setApprove(1);
                this.postDAO.update(post);
            }
        }
    }

    @RequestMapping("/manager-user")
    public  String managerUser(HttpServletRequest request)
    {
        this.defaultPage.setDaultPage(request);
        List<User> userList;
        String page=request.getParameter("page");

        if(page == null || page.trim().equals("") || !StringUtils.isNumeric(page) || Integer.valueOf(page) == 0) {
            if(this.userSort.getQueryUserByRole(request,0)!=null) {
                userList=this.userDAO.getAllUser(this.userSort.getQueryUserByRole(request,0));
                setListUser(request,userList);
                setResultManagerUser(request,1);
                return "manager-user";
            }

            deleteUser(request);

            userList = this.userDAO.getAllUser(this.userSort.getQuerySort(request,0));
            setListUser(request,userList);
            setResultManagerUser(request,1);
            return "manager-user";
        }

        deleteUser(request);

        if(this.userSort.getQueryUserByRole(request,0) != null) {
            userList = this.userDAO.getAllUser(this.userSort.getQueryUserByRole(request,(Integer.valueOf(page)-1)*NumberViewSort.NUMBER_VIEW));
            setListUser(request,userList);
            setResultManagerUser(request,Integer.valueOf(page));
            return "manager-user";
        }

        userList = this.userDAO.getAllUser(this.userSort.getQuerySort(request,(Integer.valueOf(page)-1)*NumberViewSort.NUMBER_VIEW));
        setListUser(request,userList);
        setResultManagerUser(request,Integer.valueOf(page));
        return "manager-user";
    }

    private void setResultManagerUser(HttpServletRequest request,int page)
    {
        request.setAttribute("page",page);
        request.setAttribute("totalList",this.userDAO.getAllUser().size());
    }


    private void deleteUser(HttpServletRequest request)
    {
        String action = request.getParameter("action");
        String id=request.getParameter("id");

        if(action != null && action.equals("delete")) {
            if(id != null && StringUtils.isNumeric(id)) {
                User user = this.userDAO.find(Integer.valueOf(id));
                if(user != null) {
                    this.userDAO.delete(user.getId());
                }

            }
        }
    }
    private void setListUser(HttpServletRequest request,  List<User> users)
    {
        if(users == null) {
            users = new ArrayList<User>();
        }
        request.setAttribute("userList",users);
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
    public  String actionInsertUser(HttpServletRequest request,@ModelAttribute User user) {
        this.defaultPage.setDaultPage(request);

        String[] arr = request.getParameterValues("listRole");
        if(!checkUserInsert(request,user,arr))
        {
            return "insert-user";
        }
        System.out.println(arr.length);
        List<Role> roles = new ArrayList<Role>();
        for(int i = 0 ; i < arr.length ; i++)
        {
            roles.add(new Role(arr[i],user));
        }
        user.setRoleList(roles);
        this.userDAO.update(user);
        return "redirect:manager-user";
    }

    public boolean checkUserInsert(HttpServletRequest request,User user,String[] arr)
    {
        if(user.getUserName() == null || user.getUserName().trim().equals("")) {
            request.setAttribute("error"," user name not null!");
            return false;
        }
        if(this.userDAO.getUserByName(user.getUserName().trim()) != null)
        {
            request.setAttribute("error"," user name exits available !");
            return false;
        }
        if(user.getPassWord() == null || user.getPassWord().trim().equals(""))
        {
            request.setAttribute("error"," pass word not null!");
            return false;
        }

        if(arr == null) {
            request.setAttribute("error","Not choice role!");
            return false;
        }

        return true;
    }

    @RequestMapping(value = "/update-user")
    public  String pageUpdateUser(HttpServletRequest request, @RequestParam(value = "id") int id) {
        this.defaultPage.setDaultPage(request);
        request.setAttribute("user",this.userDAO.find(id));
        request.getSession().setAttribute("idUser",id);
        return "update-user";
    }


    @RequestMapping(value = "/action-update-user",method = RequestMethod.GET)
    public String actionUpdateUser(HttpServletRequest request) {
         this.defaultPage.setDaultPage(request);
        return "redirect:manager-user";
    }

    @RequestMapping(value = "/action-update-user",method = RequestMethod.POST)
    public String actionUpdateUser(HttpServletRequest request,@ModelAttribute User user) {
        this.defaultPage.setDaultPage(request);
        request.setAttribute("roleList",this.userDAO.getAllUser());
        String []listRoles = request.getParameterValues("listRole");

       if(!checkUserUpdate(request,user,listRoles)) {
           return "update-user";
       }else {
           HttpSession session = request.getSession();
           User userUpdate = this.userDAO.find((Integer) session.getAttribute("idUser"));
           if(userUpdate.getUserName().equalsIgnoreCase(user.getUserName())) {
               User user1 = this.userDAO.getUserByName(user.getUserName());
               if(user != null)
               {
                   this.roleDAO.delete(user1.getUserName());
               }
               user1.setRoleList(this.roleService.getListRole(listRoles));
               user1.setPassWord(user.getPassWord());
               this.userDAO.update(user1);
               return "redirect:manager-user";
           }else if(this.userDAO.getUserByName(user.getUserName()) !=  null) {
               request.setAttribute("error"," user name exits available !");
               return "update-user";
           }else {
               User user1 = this.userDAO.find((Integer) session.getAttribute("idUser"));
                if(user1!=null)
                {
                    this.userDAO.delete(user1.getId());
                }
               user.setRoleList(this.roleService.getListRole(listRoles));

               this.userDAO.update(user);
           }
       }
        return "redirect:manager-user";
    }

    @RequestMapping(value = "/manager-user-search",method = RequestMethod.GET)
    public  String searchUser(HttpServletRequest request) {
        this.defaultPage.setDaultPage(request);
        String page = request.getParameter("page");
        String querySearch = request.getParameter("query_search");
        SortType sortType = this.userSort.getSort().getCurrentSortType(request,StringSessionUtil.USER_TYPE_SORT,StringSessionUtil.CURRENT_USER_SORT);
        List<User> userList;

        if(sortType == null) {
            sortType=new SortType();
        }

        if(page == null || page.trim().equals("") || !StringUtils.isNumeric(page)||Integer.valueOf(page) == 0 || querySearch == null || querySearch.trim().equals("'") || querySearch.trim().equals("")) {

            userList = this.userDAO.getAllUser("select * from  user where user_name like '%"+querySearch+"%'  order by "+sortType.orderBy +" "+sortType.typeOrder+" limit 0,"+NumberViewSort.NUMBER_VIEW);
            setListUser(request,userList);
            setResponeManagerUser(request,querySearch,1);
            return "manager-user";
        }

        userList = this.userDAO.getAllUser("select * from user  where user_name like '%"+querySearch+"%'  order by "+sortType.orderBy +" "+sortType.typeOrder+" limit "+(Integer.valueOf(page)-1)*NumberViewSort.NUMBER_VIEW+","+NumberViewSort.NUMBER_VIEW);
        setListUser(request,userList);
        setResponeManagerUser(request,querySearch,Integer.valueOf(page));
        return "manager-user";
    }

    private void  setResponeManagerUser(HttpServletRequest request,String querySearch,int page)
    {
        request.setAttribute("page",page);
        request.setAttribute("querySearch",querySearch);
        request.setAttribute("totalList",this.userDAO.getAllUser("select * from  user where user_name like '%"+querySearch+"%'").size());

    }

    private boolean checkUserUpdate(HttpServletRequest request,User user,String[] arr) {
        if(user.getUserName() == null || user.getUserName().trim().equals("")) {
            request.setAttribute("error"," user name not null!");
            return false;
        }
        if(user.getPassWord() == null || user.getPassWord().trim().equals("")) {
            request.setAttribute("error"," pass word not null!");
            return false;
        }
        if(arr == null) {
            request.setAttribute("error","Not choice role!");
            return false;
        }

        return true;
    }


}
