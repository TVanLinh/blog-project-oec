package utils.page;

import entities.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.ConfigurationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by linhtran on 15/05/2017.
 */
@Component
public class DefaultPages {
    @Autowired
    ConfigurationService configurationService;
    public void setDaultPage(HttpServletRequest request) {
        HttpSession session = request.getSession();

        Configuration configuration = configurationService.getAllConfiguration().get(0);

        if(configuration != null) {
            session.setAttribute("dateFormat",configuration.getDateFormat());
            session.setAttribute("blogTitle",configuration.getWebTitle());
        }
    }
}
