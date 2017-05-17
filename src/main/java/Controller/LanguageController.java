package Controller;

/**
 * Created by linhtran on 17/05/2017.
 */

import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;

@Controller
public class LanguageController {

    @RequestMapping("/language")
    public String changeLanguage(HttpServletRequest request, @RequestParam String language)
    {
        HttpSession session=request.getSession();
        if(language==null)
        {
            setResource(request,"blog_vn_VN",new Locale("vn","VN"));
        }else if(language.trim().equalsIgnoreCase("en"))
        {
            setResource(request,"blog_en_US",new Locale("en","US"));
        }else {
            setResource(request,"blog_vn_VN",new Locale("vn","VN"));
        }
        return "redirect:/";
    }

    public  void setResource(HttpServletRequest request,String source ,Locale locale)
    {
        HttpSession session=request.getSession();
        ResourceBundleMessageSource messageSource= new ResourceBundleMessageSource();
        messageSource.setBasename(source);
        session.setAttribute("locale",locale);
        session.setAttribute("messageSource",messageSource);
    }
}
