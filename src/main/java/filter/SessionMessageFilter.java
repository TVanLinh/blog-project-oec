package filter;


import model.RequestEntities;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by linhtran on 12/06/2017.
 */
public class SessionMessageFilter  implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpSession session = request.getSession();
//        RequestEntities entites = (RequestEntities) session.getAttribute(RequestService.MESSAGE);
//        if(entites != null){
////            setRequest(request,entites);
////            session.removeAttribute(RequestService.MESSAGE);
////            System.out.println(entites.getPage()+"-                   - --------------------- ---------------- ");
//        }
        filterChain.doFilter(request,servletResponse);
    }

    public void setRequest(HttpServletRequest request, RequestEntities entites) {
//        request.setAttribute(RequestService.MESSAGE,entites.getMsg());
//        request.setAttribute("page",entites.getPage());
    }
    public void destroy() {

    }
}