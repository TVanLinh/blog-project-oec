package DAO;

import Entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by linhtran on 06/05/2017.
 */

@Repository
public class UserDAOIML implements UserDAO {

    @Autowired
    SessionFactory sessionFactory;
    @Transactional
    public boolean insert(User user) {
        Session session=sessionFactory.getCurrentSession();
        try {
            session.persist(user);
            System.out.println(" insert User success");
            return true;
        }catch (Exception e)
        {
            return false;
        }
    }

    @Transactional
    public boolean delete(int idAuthor) {
        Session session=sessionFactory.getCurrentSession();
        User user =session.find(User.class,idAuthor);
        try {
            session.remove(user);
            System.out.println(" delete User success");
            return true;
        }catch (Exception e)
        {
            return false;
        }
    }

    @Transactional
    public boolean update(User user) {
        Session session=sessionFactory.getCurrentSession();
        try {
            session.merge(user);
            System.out.println(" update User success");
            return true;
        }catch (Exception e)
        {
            return false;
        }
    }


}
