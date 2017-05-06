package Controller;

import Entities.Post;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by linhtran on 06/05/2017.
 */

@Controller
public class ProcessPost {

    @RequestMapping(value = "/write-post",method = RequestMethod.GET)
    public String processWritePost( HttpServletRequest httpServletRequest, ModelMap modelMap)
    {
        System.out.println(httpServletRequest.getParameter("content"));
        modelMap.addAttribute("content",httpServletRequest.getParameter("content"));
        httpServletRequest.setAttribute("content",httpServletRequest.getParameter("content"));
        return ("view");
    }

}
