package Controller;

import DAO.UserDAO;
import Entities.User;
import Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by linhtran on 06/05/2017.
 */
@Controller
public class redirect {

    @Autowired
    UserService userService;

    @Autowired
    UserDAO userDAO;
    @RequestMapping(value = "/view",method = RequestMethod.GET)
    public  String view()
    {
        System.out.println(userService.find(1));
        System.out.println(userService.find(1).getRoleList().size());
        System.out.println(userDAO.insert(new User("htkt","1234555")));
        return "view";
    }

    @RequestMapping(value = "/post")
    public  String viewPost()
    {
        return "post";
    }
    @RequestMapping(value = "/write")
    public  String viewWriter()
    {
        return "write";
    }

    @RequestMapping("/insert")
    public String insertAuthor()
    {
        return "post";
    }

    @RequestMapping(value="/home")
    public String defaultHomePage()
    {
        return "home";
    }

}
