package dao;

import entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by linhtran on 06/05/2017.
 */

@Repository
public class UserDAOIML implements  UserDAO ,Serializable{

    @Autowired
    private SessionFactory sessionFactory;


    public void delete(int idAuthor) {
        Session session = sessionFactory.getCurrentSession();
        User user  = this.find(idAuthor);
        session.remove(user);
    }


    public void update(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(user);
    }


    public User find(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query<User> query  =  session.createNativeQuery("select * from user where user.id  =  :id",User.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    public List<User> getAllUser() {
        Session session = sessionFactory.getCurrentSession();
        List<User> list = session.createNativeQuery("select * from user",User.class).getResultList();
        return list;
    }

    public  List<User> getAllUser(String query) {
        Session session = sessionFactory.getCurrentSession();
        return session.createNativeQuery(query,User.class).getResultList();
    }

    public User getUserByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query<User> query = session.createNativeQuery("select * from user where user_name =:name",User.class);
        query.setParameter("name",name);
        List<User> users = query.getResultList();
        if (users.size() == 0) {
            return null;
        }
        return users.get(0);
    }
}
