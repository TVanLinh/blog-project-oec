package dao;

import entities.Configuration;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by linhtran on 08/05/2017.
 */

@Repository
@Transactional
public class ConfigurationDAOIML  implements ConfigurationDAO{
    @Autowired
    SessionFactory sessionFactory;

    @Transactional
    public void delete(int idAuthor) {
        Session session = sessionFactory.getCurrentSession();
        Configuration conf  = session.find(Configuration.class,idAuthor);
        session.remove(conf);
    }

    @Transactional
    public void update(Configuration conf) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(conf);
    }

    public Configuration find(int id) {
        Session session = sessionFactory.getCurrentSession();
        Configuration conf = session.find(Configuration.class,id);
        if(conf == null) {
            return getAllConfiguration().get(0);
        }
        return  conf;
    }

    public List<Configuration> getAllConfiguration() {
        Session session = sessionFactory.getCurrentSession();

        List<Configuration> list = session.createNativeQuery("select * from configuration",Configuration.class).getResultList();
        return list;
    }
}
