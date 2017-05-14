package DAO;

import Entities.Configuration;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by linhtran on 08/05/2017.
 */

@Repository
public class ConfigurationDAOIML  implements ConfigurationDAO{
    @Autowired
    SessionFactory sessionFactory;
    @Transactional
    public boolean insert(Configuration conf) {
        Session session=sessionFactory.getCurrentSession();
        try {
            session.persist(conf);
            System.out.println(" insert Configuration success");
            return true;
        }catch (Exception e)
        {
            return false;
        }
    }

    @Transactional
    public boolean delete(int idAuthor) {
        Session session=sessionFactory.getCurrentSession();
        Configuration conf =session.find(Configuration.class,idAuthor);
        try {
            session.remove(conf);
            System.out.println(" delete Configuration success");
            return true;
        }catch (Exception e)
        {
            return false;
        }
    }

    @Transactional
    public boolean update(Configuration conf) {
        Session session=sessionFactory.getCurrentSession();
        try {
            session.merge(conf);
            System.out.println(" update Configuration success");
            return true;
        }catch (Exception e)
        {
            return false;
        }
    }


}
