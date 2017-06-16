package filter;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by linhtran on 17/05/2017.
 */
public class FilterLanguage implements Filter {

    private ApplicationContext context;

    public void init(FilterConfig filterConfig) throws ServletException {
        this.context = WebApplicationContextUtils.getWebApplicationContext(filterConfig.getServletContext());
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if (request.getSession().getAttribute("locale") == null) {
            request.getSession().setAttribute("locale", "vn");
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {

    }
}
