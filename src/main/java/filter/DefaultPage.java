package filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by linhtran on 15/05/2017.
 */

public class DefaultPage implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        String dateFormat= (String) request.getSession().getAttribute("dateFormat");
        String titleBlog=(String) request.getSession().getAttribute("titleBlog");
        if(titleBlog==null)
        {
            request.getSession().setAttribute("blogTitle","My Blog");
        }
        if(dateFormat==null)
        {
            request.getSession().setAttribute("dateFormat","HH:mm:ss dd/MM/YYYY");
        }
        filterChain.doFilter(servletRequest,servletResponse);
//        System.out.println("DefaultPage");
    }

    public void destroy() {

    }
}
