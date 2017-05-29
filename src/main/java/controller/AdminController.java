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
import service.ConfigurationService;
import service.PostService;
import service.RoleService;
import service.UserService;
import utils.*;

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
    PostService postService;

    @Autowired
    ConfigurationService configService;

    @Autowired
    ConfigurationDAO configDAO;

    @Autowired
    DefaultPage defaultPage;

    @Autowired
    UserService userService;

    @Autowired
    UserDAO userDAO;

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
        defaultPage.setDaultPage(request);
        ModelAndView model = new ModelAndView();
        List<Post> postList;
        String page=request.getParameter("page");

        if(page == null || page.trim().equals("") || !StringUtils.isNumeric(page) || Integer.valueOf(page) == 0) {
            deletePost(request);
            aprrovePost(request);
            postList=postService.getAllPost(portSort.getQuerySortAllPostAprrove(request,0,true));
            setListPost(request,postList);
            request.setAttribute("page",1);
            model.setViewName("admin");
            request.setAttribute("active","admin");
            request.setAttribute("totalPost",postService.getAllPost("select * from post where approve = 0 ").size());
            return model;
        }

        deletePost(request);
        aprrovePost(request);
        postList=postService.getAllPost(portSort.getQuerySortAllPostAprrove(request,(Integer.valueOf(page)-1)*NumberViewSort.NUMBER_VIEW,true));
        setListPost(request,postList);
        request.setAttribute("page", Integer.valueOf(page));
        model.setViewName("admin");
        request.setAttribute("active","admin");
        request.setAttribute("totalPost",postService.getAllPost("select * from post where approve = 0 ").size());
        return model;
    }


    @RequestMapping(value = "/admin-search-post-approve",method = RequestMethod.GET)
    public  String searchTableApprovePost(HttpServletRequest request) {
        defaultPage.setDaultPage(request);
        String page=request.getParameter("page");
        String querySearch=request.getParameter("query_search");
        SortType sortType=portSort.getCurrentSortType(request, StringSessionUtil.POST_APPROVE_TYPE_SORT,StringSessionUtil.CURRENT_APPROVE_POST);
        List<Post> postList;

        if(sortType == null)
        {
            sortType = new SortType();
        }
        if(page == null || page.trim().equals("") || !StringUtils.isNumeric(page) || Integer.valueOf(page) == 0 || querySearch == null || querySearch.trim().equals("'") || querySearch.trim().equals(""))
        {

            postList = postService.getAllPost("select * from  post where approve=0 and title like '%"+querySearch+"%'  order by "+sortType.orderBy +" "+sortType.typeOrder+" limit 0,"+NumberViewSort.NUMBER_VIEW);
            setListPost(request,postList);
            request.setAttribute("page",1);
            request.setAttribute("querySearch",querySearch);
            request.setAttribute("totalPost",postService.getAllPost("select * from  post where approve = 0 and title like '%"+querySearch+"%'").size());
            request.setAttribute("active","admin");
            return "admin";
        }
        postList = postService.getAllPost("select * from post  where approve = 0 and title like '%"+querySearch+"%'  order by "+sortType.orderBy +" "+sortType.typeOrder+" limit "+(Integer.valueOf(page)-1)*NumberViewSort.NUMBER_VIEW+","+NumberViewSort.NUMBER_VIEW);
        setListPost(request,postList);
        request.setAttribute("page", Integer.valueOf(page));
        request.setAttribute("querySearch",querySearch);
        request.setAttribute("totalPost",postService.getAllPost("select * from  post where approve = 0 and title like '%"+querySearch+"%'").size());
        request.setAttribute("active","admin");
        return "admin";
    }



    @RequestMapping(value = "/configuration")
    public  String configurarion(HttpServletRequest request) {
        defaultPage.setDaultPage(request);
        request.setAttribute("conf",configService.getAllConfiguration().get(0));
        return "configuration";
    }

    @RequestMapping("/processConfigurarion")
    public  String processConfigurarion(HttpServletRequest request) {
        defaultPage.setDaultPage(request);

        String title=request.getParameter("titleBlog");
        String formatTime =request.getParameter("formatTime");
        String numberPost =request.getParameter("numberPost");
        System.out.println(title+"  \t"+formatTime+"\t"+numberPost);

        if(title == null || formatTime == null || numberPost == null) {
            request.setAttribute("error","Not valid!");
            request.setAttribute("conf",configService.getAllConfiguration().get(0));
            return "configuration";
        }

        if(title.trim().equals("") || formatTime.trim().equals("") || numberPost.trim().equals("")) {
            request.setAttribute("error","Title ,format time not valid");
            request.setAttribute("conf",configService.getAllConfiguration().get(0));
            return "configuration";
        }

        if(Integer.valueOf(numberPost)<0) {
            request.setAttribute("error","Number Post must great than 0.!");
            request.setAttribute("conf",configService.getAllConfiguration().get(0));
            return "configuration";
        }

        Configuration configuration=configService.getAllConfiguration().get(0);

        try {
           int result=0;
           if(configuration==null)
           {
               configuration=new Configuration();
           }else {
               result=1;
           }

           configuration.setNumberViewPost(Integer.valueOf(numberPost));
           configuration.setWebTitle(title);
           configuration.setDateFormat(formatTime);

           if(result==0) {
               configDAO.insert(configuration);
           }
           if(result==1) {
               configDAO.update(configuration);
               defaultPage.setDaultPage(request);
           }

           request.setAttribute("error","Successfully!");
           request.setAttribute("conf",configService.getAllConfiguration().get(0));
       }catch (Exception e)
       {
           return "configuration";
       }
        return "configuration";
    }

    @RequestMapping("/manager-post")
    public  String managerPost(HttpServletRequest request) {
        defaultPage.setDaultPage(request);
        List<Post> postList;
        HttpSession session=request.getSession();

        String page=request.getParameter("page");

         if(page==null||page.trim()==""||!StringUtils.isNumeric(page)||Integer.valueOf(page)==0) {
                deletePost(request);
                postList=postService.getAllPost(portSort.getQuerySortAllPost(request,0));
                setListPost(request,postList);
                request.setAttribute("page",1);
                return "manager-post";
         }

        deletePost(request);
        postList=postService.getAllPost(portSort.getQuerySortAllPost(request,(Integer.valueOf(page)-1)*NumberViewSort.NUMBER_VIEW));
        setListPost(request,postList);
        request.setAttribute("page", Integer.valueOf(page));
        return "manager-post";
    }

    @RequestMapping(value = "/manager-post-search",method = RequestMethod.GET)
    public  String searchTableAllPost(HttpServletRequest request) {
        defaultPage.setDaultPage(request);
        String page=request.getParameter("page");
        String querySearch=request.getParameter("query_search");
        HttpSession session=request.getSession();
        SortType sortType=portSort.getCurrentSortType(request,StringSessionUtil.POST_ALL_TYPE_SORT,StringSessionUtil.CURRENT_ALL_POST);
        List<Post> postList;

        if(sortType==null) {
            sortType=new SortType();
        }
        if(page==null||page.trim()==""||!StringUtils.isNumeric(page)||Integer.valueOf(page)==0||querySearch==null||querySearch.trim().equals("'")||querySearch.trim().equals("")) {

            postList=postService.getAllPost("select * from  post where title like '%"+querySearch+"%'  order by "+sortType.orderBy +" "+sortType.typeOrder+" limit 0,"+NumberViewSort.NUMBER_VIEW);
            setListPost(request,postList);
            request.setAttribute("page",1);
            request.setAttribute("querySearch",querySearch);
            request.setAttribute("totalPost",postService.getAllPost("select * from  post where title like '%"+querySearch+"%'").size());
            return "manager-post";
        }

        postList=postService.getAllPost("select * from post  where title like '%"+querySearch+"%'  order by "+sortType.orderBy +" "+sortType.typeOrder+" limit "+(Integer.valueOf(page)-1)*NumberViewSort.NUMBER_VIEW+","+NumberViewSort.NUMBER_VIEW);
        setListPost(request,postList);
        request.setAttribute("page", Integer.valueOf(page));
        request.setAttribute("querySearch",querySearch);
        request.setAttribute("totalPost",postService.getAllPost("select * from  post where title like '%"+querySearch+"%'").size());
        return "manager-post";
    }

    private void setListPost(HttpServletRequest request,List<Post> postList) {
        if(postList==null) {
            postList=new ArrayList<Post>();
        }
        request.setAttribute("postList",postList);
        request.setAttribute("totalPost",postService.getAllPost().size());
    }

    private void deletePost(HttpServletRequest request) {
        String action=request.getParameter("action");
        String id  = request.getParameter("id");

        if(action!=null &&action.equals("delete")) {
            if(id != null && StringUtils.isNumeric(id)) {
                if(postService.find(Integer.valueOf(id))!=null) {
                    postDAO.delete(Integer.valueOf(id));
                }
            }
        }
    }

    public void aprrovePost(HttpServletRequest request)
    {
        String action = request.getParameter("action");
        String id = request.getParameter("id");
        if(action!=null && action.equals("approve")) {
            Date date;
            Calendar calendar=Calendar.getInstance();
            Post post;
            date = calendar.getTime();
            post = postService.find(Integer.valueOf(id));
            if(post != null) {

                post.setApprovedTime(date);
                post.setApprove(1);
                postDAO.update(post);
            }
        }
    }

    @RequestMapping("/manager-user")
    public  String managerUser(HttpServletRequest request)
    {
        defaultPage.setDaultPage(request);
        List<User> userList;
        String page=request.getParameter("page");

        if(page == null || page.trim().equals("") || !StringUtils.isNumeric(page) || Integer.valueOf(page) == 0) {
            if(userSort.getQueryUserByRole(request,0)!=null) {
                userList=userService.getAllUser(userSort.getQueryUserByRole(request,0));
                setListUser(request,userList);
                request.setAttribute("totalList",userService.getAllUser().size());
                request.setAttribute("page",1);
                return "manager-user";
            }

            deleteUser(request);
            userList = userService.getAllUser(userSort.getQuerySort(request,0));
            setListUser(request,userList);
            request.setAttribute("totalList",userService.getAllUser().size());
            request.setAttribute("page",1);
            return "manager-user";
        }

        deleteUser(request);

        if(userSort.getQueryUserByRole(request,0) != null) {
            userList = userService.getAllUser(userSort.getQueryUserByRole(request,(Integer.valueOf(page)-1)*NumberViewSort.NUMBER_VIEW));
            setListUser(request,userList);
            request.setAttribute("totalList",userService.getAllUser().size());
            request.setAttribute("page",Integer.valueOf(page));
            return "manager-user";
        }

        userList = userService.getAllUser(userSort.getQuerySort(request,(Integer.valueOf(page)-1)*NumberViewSort.NUMBER_VIEW));
        setListUser(request,userList);
        request.setAttribute("totalList",userService.getAllUser().size());
        request.setAttribute("page", Integer.valueOf(page));
        return "manager-user";
    }

    private void deleteUser(HttpServletRequest request)
    {
        String action = request.getParameter("action");
        String id=request.getParameter("id");

        if(action != null && action.equals("delete")) {

            if(id != null && StringUtils.isNumeric(id))
            {
                User user=userService.find(Integer.valueOf(id));
                if(user != null) {
                    userDAO.delete(user.getId());
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
        defaultPage.setDaultPage(request);
        return "insert-user";
    }

    @RequestMapping(value = "/action-insert-user",method = RequestMethod.GET)
    public String actionInsertUser(HttpServletRequest request)
    {
        defaultPage.setDaultPage(request);
          return "redirect:manager-user";
    }
    @RequestMapping(value = "/action-insert-user",method = RequestMethod.POST)
    public  String actionInsertUser(HttpServletRequest request,@ModelAttribute User user) {
        defaultPage.setDaultPage(request);

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
        userDAO.insert(user);
        return "redirect:manager-user";
    }

    public boolean checkUserInsert(HttpServletRequest request,User user,String[] arr)
    {
        if(user.getUserName() == null || user.getUserName().trim().equals("")) {
            request.setAttribute("error"," user name not null!");
            return false;
        }
        if(userService.getUserByName(user.getUserName().trim()) != null)
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
        defaultPage.setDaultPage(request);
        request.setAttribute("user",userService.find(id));
        request.getSession().setAttribute("idUser",id);
        return "update-user";
    }


    @RequestMapping(value = "/action-update-user",method = RequestMethod.GET)
    public String actionUpdateUser(HttpServletRequest request) {
         defaultPage.setDaultPage(request);
        return "redirect:manager-user";
    }

    @RequestMapping(value = "/action-update-user",method = RequestMethod.POST)
    public String actionUpdateUser(HttpServletRequest request,@ModelAttribute User user) {
        defaultPage.setDaultPage(request);
        request.setAttribute("roleList",userService.getAllUser());
        String []listRoles = request.getParameterValues("listRole");

       if(!checkUserUpdate(request,user,listRoles)) {
           return "update-user";
       }else {
           HttpSession session = request.getSession();
           User userUpdate = userService.find((Integer) session.getAttribute("idUser"));
           if(userUpdate.getUserName().equalsIgnoreCase(user.getUserName())) {
               User user1 = userService.getUserByName(user.getUserName());
               roleDAO.delete(user1.getUserName());
               user1.setRoleList(roleService.getListRole(listRoles));
               user1.setPassWord(user.getPassWord());
               userDAO.update(user1);
               return "redirect:manager-user";
           }else if(userService.getUserByName(user.getUserName()) !=  null) {
               request.setAttribute("error"," user name exits available !");
               return "update-user";
           }else {
               User user1 = userService.find((Integer) session.getAttribute("idUser"));
               roleDAO.delete(user1.getUserName());
               userDAO.delete(user1.getId());

               user.setRoleList(roleService.getListRole(listRoles));

               userDAO.update(user);
           }
       }
        return "redirect:manager-user";
    }

    @RequestMapping(value = "/manager-user-search",method = RequestMethod.GET)
    public  String searchUser(HttpServletRequest request) {
        defaultPage.setDaultPage(request);
        String page = request.getParameter("page");
        String querySearch = request.getParameter("query_search");
        SortType sortType = userSort.getSort().getCurrentSortType(request,StringSessionUtil.USER_TYPE_SORT,StringSessionUtil.CURRENT_USER_SORT);
        List<User> userList;

        if(sortType == null) {
            sortType=new SortType();
        }

        if(page == null || page.trim().equals("") || !StringUtils.isNumeric(page)||Integer.valueOf(page) == 0 || querySearch == null || querySearch.trim().equals("'") || querySearch.trim().equals("")) {

            userList = userService.getAllUser("select * from  user where user_name like '%"+querySearch+"%'  order by "+sortType.orderBy +" "+sortType.typeOrder+" limit 0,"+NumberViewSort.NUMBER_VIEW);
            setListUser(request,userList);
            request.setAttribute("page",1);
            request.setAttribute("querySearch",querySearch);
            request.setAttribute("totalList",userService.getAllUser("select * from  user where user_name like '%"+querySearch+"%'").size());
            return "manager-user";
        }

        userList = userService.getAllUser("select * from user  where user_name like '%"+querySearch+"%'  order by "+sortType.orderBy +" "+sortType.typeOrder+" limit "+(Integer.valueOf(page)-1)*NumberViewSort.NUMBER_VIEW+","+NumberViewSort.NUMBER_VIEW);
        setListUser(request,userList);
        request.setAttribute("page", Integer.valueOf(page));
        request.setAttribute("querySearch",querySearch);
        request.setAttribute("totalList",userService.getAllUser("select * from  user where user_name like '%"+querySearch+"%'").size());
        return "manager-user";
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
