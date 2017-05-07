package Controller;

import DAO.PostDAO;
import DAO.UserDAOIML;
import Entities.Post;
import Entities.User;
import Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by linhtran on 06/05/2017.
 */

@Controller
public class ProcessPost {
    @Autowired
    UserService userService;

    @Autowired
    PostDAO postDAO;

//    @RequestMapping(value = "/write-post",method = RequestMethod.GET)
//    public String processWritePost( HttpServletRequest httpServletRequest, ModelMap modelMap)
//    {
//        System.out.println(httpServletRequest.getParameter("content"));
//        modelMap.addAttribute("content",httpServletRequest.getParameter("content"));
//        httpServletRequest.setAttribute("content",httpServletRequest.getParameter("content"));
//        return ("view");
//    }

    @RequestMapping(value = "/write-post",method = RequestMethod.GET)
    public ModelAndView processWritePost(@ModelAttribute(value = "post")Post post)
    {
        User user=userService.getUserByName("tvlinh");
        System.out.println(user);
        post.setUser(user);
        Calendar calendar=Calendar.getInstance();
        Date date=calendar.getTime();

        System.out.println(postDAO.insert(post));
//        System.out.println(post.getContent());
        return new ModelAndView("view");

    }
}
