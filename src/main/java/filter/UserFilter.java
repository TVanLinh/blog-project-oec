package filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by linhtran on 07/05/2017.
 */
public class UserFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;
        HttpSession session=request.getSession(false);
//        if(session==null)
//        {
//            response.sendRedirect("/home");
//            return;
//        }
//        if(session.getAttribute("username")==null)
//        {
//            response.sendRedirect("/home");
//            return;
//        }
        filterChain.doFilter(request,response);
    }

    public void destroy() {

    }
}
