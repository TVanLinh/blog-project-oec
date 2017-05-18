package Controller;

import DAO.ConfigurationDAO;
import DAO.RoleDAO;
import DAO.UserDAO;
import Entities.Configuration;
import Entities.Post;
import Entities.Role;
import Entities.User;
import Service.ConfigurationService;
import Service.PostService;
import Service.RoleService;
import Service.UserService;
import Utils.DefaultPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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


    @RequestMapping(value = "/admin**", method = RequestMethod.GET)
    public ModelAndView adminPage(HttpServletRequest request) {
        defaultPage.setDaultPage(request);
        ModelAndView model = new ModelAndView();
        model.setViewName("admin");
        List<Post> postList= postService.getAllPost("select * from post where approve=0  limit 0,10");
        System.out.println(postList.size());
        if(postList==null)
        {
            postList=new ArrayList<Post>();
        }
        request.setAttribute("postList",postList);
        request.setAttribute("totalPost",postService.getAllPost("select * from post where approve = 0 ").size());
        return model;
    }

    @RequestMapping(value = "/configuration")
    public  String configurarion(HttpServletRequest request)
    {
        defaultPage.setDaultPage(request);
        return "configuration";
    }

    @RequestMapping("/processConfigurarion")
    public  String processConfigurarion(HttpServletRequest request)
    {
        String title=request.getParameter("titleBlog");
        String formatTime =request.getParameter("formatTime");
        String numberPost =request.getParameter("numberPost");
        System.out.println(title+"  \t"+formatTime+"\t"+numberPost);

        if(title==null||formatTime==null||numberPost==null)
        {
            request.setAttribute("error","Not valid!");
            return "configuration";
        }

        if(title.trim().equals("")||formatTime.trim().equals("")||numberPost.trim().equals(""))
        {
            request.setAttribute("error","Title ,format time not valid");
            return "configuration";
        }
        if(Integer.valueOf(numberPost)<0)
        {
            request.setAttribute("error","Number Post must great than 0.!");
            return "configuration";
        }
        Configuration configuration=configService.getAllConfiguration().get(0);
       try
       {
           int result=0;
           if(configuration==null)
           {
               configuration=new Configuration();
           }else
           {
               result=1;
           }

           configuration.setNumberViewPost(Integer.valueOf(numberPost));
           configuration.setWebTitle(title);
           configuration.setDateFormat(formatTime);
           if(result==0)
           {
               configDAO.insert(configuration);
           }
           if(result==1)
           {
               configDAO.update(configuration);
               defaultPage.setDaultPage(request);
           }
           request.setAttribute("error","Successfully!");
       }catch (Exception e)
       {
           return "configuration";
       }
        return "configuration";
    }

    @RequestMapping("/manager-post")
    public  String managerPost(HttpServletRequest request)
    {
        defaultPage.setDaultPage(request);
        List<Post> postList=postService.getAllPost("select * from post  order by time_post limit 0,10");
        if(postList==null)
        {
            postList=new ArrayList<Post>();
        }
        request.setAttribute("postList",postList);
        request.setAttribute("totalPost",postService.getAllPost().size());
        return "manager-post";
    }

    @RequestMapping("/manager-user")
    public  String managerUser(HttpServletRequest request)
    {
        defaultPage.setDaultPage(request);
        String action=request.getParameter("action");
        String id=request.getParameter("id");
        if(action!=null&&id!=null)
        {
            if(action.trim().equals("delete"))
            {
                userDAO.delete(Integer.valueOf(id));
            }
            setListUser(request);
            return "manager-user";
        }
        else
        {
            setListUser(request);
        }
        return "manager-user";
    }

    public void setListUser(HttpServletRequest request)
    {
        List<User> users=userService.getAllUser();
        if(users==null)
        {
            users=new ArrayList<User>();
        }
        request.setAttribute("userList",users);
    }

    @RequestMapping(value = "/insert-user")
    public  String pageInsertUser(HttpServletRequest request)
    {
        defaultPage.setDaultPage(request);
        return "insert-user";
    }

    @RequestMapping(value = "/action-insert-user",method = RequestMethod.GET)
    public String actionInsertUser(HttpServletRequest request)
    {
        setListUser(request);
        return "manager-user";
    }
    @RequestMapping(value = "/action-insert-user",method = RequestMethod.POST)
    public  String actionInsertUser(HttpServletRequest request,@ModelAttribute User user)
    {
        System.out.println(user);
        String[] arr=request.getParameterValues("listRole");
        if(!checkUserInsert(request,user,arr))
        {
            return "insert-user";
        }
        System.out.println(arr.length);
        List<Role> roles=new ArrayList<Role>();
        System.out.println(arr.length+"---------------------");
        for(int i=0;i<arr.length;i++)
        {
            roles.add(new Role(arr[i],user));
        }
        System.out.println("roles ---------------"+roles.size());
        user.setRoleList(roles);
        userDAO.insert(user);
        setListUser(request);
        return "manager-user";
    }

    public boolean checkUserInsert(HttpServletRequest request,User user,String[] arr)
    {
        if(user.getUserName()==null||user.getUserName().trim().equals(""))
        {
            request.setAttribute("error"," user name not null!");
            return false;
        }
        if(userService.getUserByName(user.getUserName().trim())!=null)
        {
            request.setAttribute("error"," user name exits available !");
            return false;
        }
        if(user.getPassWord()==null||user.getPassWord().trim().equals(""))
        {
            request.setAttribute("error"," pass word not null!");
            return false;
        }

        if(arr==null)
        {
            request.setAttribute("error","Not choice role!");
            return false;
        }

        return true;
    }

    @RequestMapping(value = "/update-user")
    public  String pageUpdateUser(HttpServletRequest request, @RequestParam(value = "id",required = true) int id)
    {
        defaultPage.setDaultPage(request);
        request.setAttribute("user",userService.find(id));
        return "update-user";
    }

    @RequestMapping(value = "/action-update-user",method = RequestMethod.GET)
    public String actionUpdateUser(HttpServletRequest request)
    {
        setListUser(request);
        return "manager-user";
    }
    @RequestMapping(value = "/action-update-user",method = RequestMethod.POST)
    public String actionUpdateUser(HttpServletRequest request,@ModelAttribute User user)
    {
        defaultPage.setDaultPage(request);
        request.setAttribute("roleList",userService.getAllUser());
        String []listRoles=request.getParameterValues("listRole");
       if(!checkUserUpdate(request,user,listRoles))
       {
           return "update-user";
       }else
       {
           User user1=userService.getUserByName(user.getUserName());
           roleDAO.delete(user1.getUserName());
           user1.setRoleList(roleService.getListRole(listRoles));
           userDAO.update(user1);
       }
        setListUser(request);
        return "manager-user";
    }


    public boolean checkUserUpdate(HttpServletRequest request,User user,String[] arr)
    {
        if(user.getUserName()==null||user.getUserName().trim().equals(""))
        {
            request.setAttribute("error"," user name not null!");
            return false;
        }
        if(user.getPassWord()==null||user.getPassWord().trim().equals(""))
        {
            request.setAttribute("error"," pass word not null!");
            return false;
        }
        if(arr==null)
        {
            request.setAttribute("error","Not choice role!");
            return false;
        }

        return true;
    }


}
