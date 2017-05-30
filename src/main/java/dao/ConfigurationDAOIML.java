package dao;

import entities.Configuration;
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
    public void delete(int idAuthor) {
        Session session = sessionFactory.getCurrentSession();
        Configuration conf  = session.find(Configuration.class,idAuthor);
        session.remove(conf);
        System.out.println(" delete Configuration success");
    }

    @Transactional
    public void update(Configuration conf) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(conf);
        System.out.println(" update Configuration success");
    }


}
