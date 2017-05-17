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
    public void insert(User user) {
        Session session=sessionFactory.getCurrentSession();
        session.persist(user);
        System.out.println(" insert User success");
    }

    @Transactional
    public void delete(int idAuthor) {
        Session session=sessionFactory.getCurrentSession();
        User user =session.find(User.class,idAuthor);
        session.remove(user);
        System.out.println(" delete User success");
    }

    @Transactional
    public void update(User user) {
        Session session=sessionFactory.getCurrentSession();
        session.merge(user);
        System.out.println(" update User success");
    }


}
