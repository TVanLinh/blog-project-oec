package dao;

import entities.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by linhtran on 06/05/2017.
 */

@Repository
public class RoleDAOIML implements RoleDAO {

    @Autowired
    SessionFactory sessionFactory;
    @Transactional
    public void insert(Role role) {
        Session session=sessionFactory.getCurrentSession();
        session.persist(role);
        System.out.println(" insert Role success");
    }

    @Transactional
    public void delete(int idRole) {
        Session session=sessionFactory.getCurrentSession();
        Role role =session.find(Role.class,idRole);
        session.remove(role);
        System.out.println(" delete Role success");
    }

    @Transactional
    public void update(Role role) {
        Session session=sessionFactory.getCurrentSession();
        session.saveOrUpdate(role);
        System.out.println(" update Role success");
    }

    @Transactional
    public void delete(String userName) {
        Session session=sessionFactory.getCurrentSession();
        session.createNativeQuery("DELETE  from user_roles WHERE user_name = '"+userName+"'").executeUpdate();
        System.out.println(" delete Role success-----------------------------------------");
    }


}
