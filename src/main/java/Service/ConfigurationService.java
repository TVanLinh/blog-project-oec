package Service;

import Entities.Configuration;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by linhtran on 07/05/2017.
 */

@Service
@Transactional
public class ConfigurationService {

    @Autowired
    SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public ConfigurationService() {
    }

    public Configuration find(int id) {
        Session session=sessionFactory.getCurrentSession();
        Configuration conf=session.find(Configuration.class,id);
        if(conf==null)
        {
            return getAllConfiguration().get(0);
        }
        return  conf;
    }

    public List<Configuration> getAllConfiguration()
    {
        Session session=sessionFactory.getCurrentSession();

        List<Configuration> list= session.createNativeQuery("select * from configuration",Configuration.class).getResultList();
        return list;
    }


}
