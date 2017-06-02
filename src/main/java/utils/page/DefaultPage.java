package utils.page;

import dao.ConfigurationDAO;
import entities.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by linhtran on 15/05/2017.
 */
@Component
public class DefaultPage {
    @Autowired
    ConfigurationDAO  configDAO;
    public void setDaultPage(HttpServletRequest request) {
        HttpSession session = request.getSession();

        Configuration configuration = configDAO.getAllConfiguration().get(0);

        if(configuration != null) {
            session.setAttribute("dateFormat",configuration.getDateFormat());
            session.setAttribute("blogTitle",configuration.getWebTitle());
        }
    }
}