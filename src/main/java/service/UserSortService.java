package service;

import entities.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.sort.SortType;

import java.util.List;

/**
 * Created by linhtran on 08/06/2017.
 */

@Service
@Transactional
public class UserSortService {

    @Autowired
    SessionFactory sessionFactory;

    public List<User> getUser(SortType sortItem, int offset, int limit) {
        String str = "select * from user order by  " + sortItem.orderBy + " " + sortItem.typeOrder + " limit " + offset + "," + limit;
        Query<User> query = this.sessionFactory.getCurrentSession().createNativeQuery(str, User.class);
        return query.getResultList();
    }

    public List<User> getUsers(SortType sortItem, int offset, int limit) {
        String str = "select * from user   order by  " + sortItem.orderBy + " " + sortItem.typeOrder + " limit " + offset + "," + limit;

        Query<User> query = this.sessionFactory.getCurrentSession().createNativeQuery(str, User.class);
        return query.getResultList();
    }
}
