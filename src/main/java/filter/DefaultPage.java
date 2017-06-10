package filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import service.ConfigurationService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by linhtran on 15/05/2017.
 */

@Configuration
public class DefaultPage implements Filter {
    @Autowired
    private ConfigurationService configurationService;

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        this.setDefaultPage((HttpServletRequest)servletRequest);
        filterChain.doFilter(servletRequest,servletResponse);
    }

    private void setDefaultPage(HttpServletRequest request) {
        HttpSession session = request.getSession(true);

        entities.Configuration configuration = configurationService.getAllConfiguration().get(0);

        if(configuration != null) {
            session.setAttribute("dateFormat",configuration.getDateFormat());
            session.setAttribute("blogTitle",configuration.getWebTitle());
        }
    }

    public void destroy() {

    }
}
