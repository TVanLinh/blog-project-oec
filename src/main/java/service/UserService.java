package service;

import entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by linhtran on 07/05/2017.
 */

@Service
@Transactional
public class UserService {

    @Autowired
    SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public UserService() {
    }

    @Transactional
    public User find(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query  =  session.createNativeQuery("select * from user where user.id  =  :id",User.class);
        query.setParameter("id", id);
        return (User) query.getSingleResult();
    }

    @Transactional
    public List<User> getAllUser()
    {
        Session session = sessionFactory.getCurrentSession();
        List<User> list = session.createNativeQuery("select * from user",User.class).getResultList();
        return list;
    }

    public  List<User> getAllUser(String query)
    {
        Session session = sessionFactory.getCurrentSession();
        List<User> list = session.createNativeQuery(query,User.class).getResultList();
        return list;
    }

    public User getUserByName(String name)
    {
        Session session = sessionFactory.getCurrentSession();
        List<User> list = session.createNativeQuery("select * from user where user_name='"+name+"' limit 0,1",User.class).getResultList();
        if(list.size() == 0)
        {
            return null;
        }
        return  list.get(0);
    }
}
