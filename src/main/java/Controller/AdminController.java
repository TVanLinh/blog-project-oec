package Controller;

import DAO.ConfigurationDAO;
import Entities.Configuration;
import Entities.Post;
import Service.ConfigurationService;
import Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(value = "/admin**", method = RequestMethod.GET)
    public ModelAndView adminPage(HttpServletRequest request) {

        ModelAndView model = new ModelAndView();
        model.setViewName("admin");
        List<Post> postList= postService.getAllPost("select * from post where approve=0  limit 1,10");
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
           }
           request.setAttribute("error","Successfully!");
       }catch (Exception e)
       {
           return "configuration";
       }
        return "configuration";
    }
}
