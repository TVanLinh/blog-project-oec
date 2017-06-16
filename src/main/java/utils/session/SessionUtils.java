package utils.session;

import entities.User;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import utils.string.StringSessionUtil;

import javax.servlet.http.HttpSession;

/**
 * Created by linhtran on 16/06/2017.
 */
public abstract class SessionUtils {
    public static HttpSession getCurrentSession() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession(true);
    }

    public static User getCurrentUser() {
        return (User) SessionUtils.getCurrentSession().getAttribute(StringSessionUtil.USER_LOGIN);
    }
}
