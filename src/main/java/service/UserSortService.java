package service;

import entities.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.sort.Sort;
import utils.sort.SortType;
import utils.string.StringSessionUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by linhtran on 08/06/2017.
 */

@Service
@Transactional
public class UserSortService {
    @Autowired
    Sort sort;

    @Autowired
    SessionFactory sessionFactory;

    public List<User> getUser(HttpServletRequest request, int offset, int limit) {

        HttpSession session = request.getSession();
        String orderBy = request.getParameter("orderBy");
        String page = request.getParameter("page");

        SortType sortItem = this.sort.getCurrentSortType(request, StringSessionUtil.CURRENT_USER_SORT);

        this.sort.checkValid(page,orderBy,sortItem,"user_name");

        session.setAttribute(StringSessionUtil.CURRENT_USER_SORT, sortItem);

        String str =  "select * from user order by  " + sortItem.orderBy + " " + sortItem.typeOrder + " limit " +offset+ "," + limit;
        Query<User> query = this.sessionFactory.getCurrentSession().createNativeQuery(str, User.class);
        return  query.getResultList();
    }
}
