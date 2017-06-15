package service;

import entities.Post;
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
public class PostSortService {

    @Autowired
    private Sort portSort;

    @Autowired
    private SessionFactory sessionFactory;

    public List<Post> getAllPostByUser( SortType sortItem, User user, int offset, int limit) {
        String str= "select * from post where id_user = "+user.getId() +" order by  " + sortItem.orderBy + " " + sortItem.typeOrder + " limit "+offset+","+limit;
        Query<Post> query = this.sessionFactory.getCurrentSession().createNativeQuery(str, Post.class);
        return query.getResultList();
    }

    public List<Post> getAllPostNotApprove(SortType sortItem, int offset, int limit) {
        String str= "select * from post where approve = 0 order by  " + sortItem.orderBy + " " + sortItem.typeOrder + " limit "+offset+","+limit;
        Query<Post> query = this.sessionFactory.getCurrentSession().createNativeQuery(str, Post.class);
        return query.getResultList();
    }

    public List<Post> getAllPost(HttpServletRequest request, int offset, int limit) {

        HttpSession session = request.getSession();
        String orderBy = request.getParameter("orderBy");
        String page = request.getParameter("page");

        SortType sortItem = this.portSort.getCurrentSortType(request, StringSessionUtil.CURRENT_ALL_POST);

        this.portSort.checkValid(page,orderBy,sortItem,"title");

        session.setAttribute(StringSessionUtil.CURRENT_ALL_POST, sortItem);

        String str =  "select * from post order by   " + sortItem.orderBy + " " + sortItem.typeOrder + " limit " + offset+","+limit;
        Query<Post> query = this.sessionFactory.getCurrentSession().createNativeQuery(str, Post.class);
        return query.getResultList();
    }

}
