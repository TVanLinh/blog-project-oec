package filter;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import service.ConfigurationService;
import service.UserService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by linhtran on 15/05/2017.
 */

public class DefaultPage implements Filter {
    private ConfigurationService configurationService;
    private ApplicationContext context;
    private UserService userService;

    public void init(FilterConfig filterConfig) throws ServletException {
        context = WebApplicationContextUtils.getWebApplicationContext(filterConfig.getServletContext());
//        context.getAutowireCapableBeanFactory().autowireBean(this);
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        this.setDefaultPage((HttpServletRequest)servletRequest);
        filterChain.doFilter(servletRequest,servletResponse);
    }

    private void setDefaultPage(HttpServletRequest request) {
        this.configurationService = this.context.getBean("configurationService", ConfigurationService.class);
        this.userService = this.context.getBean("userService", UserService.class);
        HttpSession session = request.getSession();
        entities.Configuration configuration = configurationService.getAllConfiguration().get(0);
        if (configuration != null) {
            session.setAttribute("dateFormat", configuration.getDateFormat());
            session.setAttribute("blogTitle", configuration.getWebTitle());
        }
        if (session.getAttribute("userService") == null){
            session.setAttribute("userService",this.userService);
        }
    }

    public void destroy() {

    }
}
