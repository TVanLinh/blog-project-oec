package dao;

import entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import service.UserService;

/**
 * Created by linhtran on 06/05/2017.
 */

@Repository
public class UserDAOIML implements UserDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    UserService userService;

    @Transactional
    public void delete(int idAuthor) {
        Session session = sessionFactory.getCurrentSession();
        User user  = userService.find(idAuthor);
        session.remove(user);
        System.out.println(" delete User success");
    }

    @Transactional
    public void update(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(user);
        System.out.println(" update User success");
    }


}
