package filter;

import org.springframework.context.support.ResourceBundleMessageSource;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Locale;

/**
 * Created by linhtran on 17/05/2017.
 */
public class FilterLanguage implements Filter{
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        if(request.getSession().getAttribute("messageSource")==null)
        {
            setReSource(request);
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    public void destroy() {

    }

    public  void setReSource(HttpServletRequest request)
    {
        ResourceBundleMessageSource messageSource=new ResourceBundleMessageSource();
        messageSource.setBasename("blog_vn_VN");
        messageSource.setDefaultEncoding("UTF-8");
        System.out.println("request.getRequestUR   "+request.getRequestURI());
        request.getSession().setAttribute("messageSource",messageSource);
        request.getSession().setAttribute("locale",new Locale("vn","VN"));
    }
}
