package dao;

import entities.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by linhtran on 06/05/2017.
 */

@Repository
public class RoleDAOIML implements RoleDAO {

    @Autowired
    SessionFactory sessionFactory;


    public void delete(int idRole) {
        Session session = sessionFactory.getCurrentSession();
        Role role  = session.find(Role.class,idRole);
        session.remove(role);
    }


    public void update(Role role) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(role);
    }


    public void delete(String userName) {
        Session session = sessionFactory.getCurrentSession();
       Query query= session.createNativeQuery("DELETE  from user_roles WHERE user_name  = :userName");
       query.setParameter("userName",userName);
       query.executeUpdate();
    }


}
