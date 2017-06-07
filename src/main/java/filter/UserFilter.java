package filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import service.ConfigurationService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by linhtran on 07/05/2017.
 */

@Configuration
public class UserFilter implements Filter {
    @Autowired
    ConfigurationService configurationService;
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpSession session=request.getSession();
        filterChain.doFilter(request,servletResponse);
    }

    public void destroy() {

    }
}
