package utils.string;

import org.springframework.web.servlet.ModelAndView;

/**
 * Created by linhtran on 03/06/2017.
 */
public class StringUtils {

    public static  boolean checkVid(String str)
    {
        if(str.contains("/")||str.contains("%")||str.contains("^")||str.contains(";")||str.contains("'")||str.contains("\\")||str.contains("#")||str.contains("$")||str.contains(";")||str.contains("|")||str.contains("?")||str.contains("&"))
        {
            str.replaceAll("//;%\\\\,#&&@$|:?^","");
            return false;
        }
        return true;
    }

    public  static boolean  isEquals(String str1,String str2)
    {
        if(str1.equals(str2))
        {
            return true;
        }
        return false;
    }

    public  static  boolean checkVidPassWord(ModelAndView modelAndView,String pass1,String pass2)
    {
        if(pass1==null||pass2==null)
        {
            return false;
        }
        if("".equals(pass1)|| !org.apache.commons.lang3.StringUtils.isNotBlank(pass1)||"".equals(pass2)||!org.apache.commons.lang3.StringUtils.isNotBlank(pass2)){
            modelAndView.addObject("error","pass word not null!");
            return false;
        }
        if(!pass1.equals(pass2))
        {
            modelAndView.addObject("error","Password not overlap!");
            return false;
        }
        return true;
    }
}
