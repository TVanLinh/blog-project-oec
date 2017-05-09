package DAO;

import Entities.Configuration;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by linhtran on 08/05/2017.
 */
public class ConfigurationDAOIML  implements ConfigurationDAO{
    @Autowired
    SessionFactory sessionFactory;
    @Transactional
    public boolean insert(Configuration conf) {
        Session session=sessionFactory.getCurrentSession();
        try {
            session.persist(conf);
            System.out.println(" insert success");
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
            System.out.println(" delete success");
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
            System.out.println(" update success");
            return true;
        }catch (Exception e)
        {
            return false;
        }
    }


}
