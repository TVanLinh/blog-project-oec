package utils.page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.ConfigurationService;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by linhtran on 15/05/2017.
 */
@Component
public class DefaultPages {

    @Autowired
    ConfigurationService configurationService;
    public void setDaultPage(HttpServletRequest request) {
//        HttpSession session = request.getSession(true);
//        Configurations configurations = configurationService.getAllConfiguration().get(0);
//        if(configurations != null) {
//            session.setAttribute("dateFormat", configurations.getDateFormat());
//            session.setAttribute("blogTitle", configurations.getWebTitle());
//        }
    }
}
