package DAO;

import Entities.Role;
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
    public boolean insert(Role role) {
        Session session=sessionFactory.getCurrentSession();
        try {
            session.persist(role);
            System.out.println(" insert success");
            return true;
        }catch (Exception e)
        {
            return false;
        }
    }

    @Transactional
    public boolean delete(int idRole) {
        Session session=sessionFactory.getCurrentSession();
        Role role =session.find(Role.class,idRole);
        try {
            session.remove(role);
            System.out.println(" delete success");
            return true;
        }catch (Exception e)
        {
            return false;
        }
    }

    @Transactional
    public boolean update(Role role) {
        Session session=sessionFactory.getCurrentSession();
        try {
            session.saveOrUpdate(role);
            System.out.println(" update success");
            return true;
        }catch (Exception e)
        {
            return false;
        }
    }


}
