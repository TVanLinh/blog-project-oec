package dao;

import entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by linhtran on 06/05/2017.
 */

@Repository
@Transactional
public class UserDAOIML implements UserDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Transactional
    public void delete(int idAuthor) {
        Session session = sessionFactory.getCurrentSession();
        User user  = this.find(idAuthor);
        session.remove(user);
        System.out.println(" delete User success");
    }

    @Transactional
    public void update(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(user);
        System.out.println(" update User success");
    }

    @Transactional
    public User find(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query  =  session.createNativeQuery("select * from user where user.id  =  :id",User.class);
        query.setParameter("id", id);
        return (User) query.getSingleResult();
    }

    @Transactional
    public List<User> getAllUser() {
        Session session = sessionFactory.getCurrentSession();
        List<User> list = session.createNativeQuery("select * from user",User.class).getResultList();
        return list;
    }

    public  List<User> getAllUser(String query) {
        Session session = sessionFactory.getCurrentSession();
        List<User> list = session.createNativeQuery(query,User.class).getResultList();
        return list;
    }

    public User getUserByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        List<User> list = session.createNativeQuery("select * from user where user_name='"+name+"' limit 0,1",User.class).getResultList();
        if(list.size() == 0) {
            return null;
        }
        return  list.get(0);
    }
}
