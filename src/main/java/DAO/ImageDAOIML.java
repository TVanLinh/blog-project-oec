package DAO;

import Entities.Image;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by linhtran on 06/05/2017.
 */

@Repository
public class ImageDAOIML implements ImageDAO {

    @Autowired
    SessionFactory sessionFactory;
    @Transactional
    public boolean insert(Image image) {
        Session session=sessionFactory.getCurrentSession();
        try {
            session.persist(image);
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
        Image image =session.find(Image.class,idAuthor);
        try {
            session.remove(image);
            System.out.println(" delete success");
            return true;
        }catch (Exception e)
        {
            return false;
        }
    }

    @Transactional
    public boolean update(Image image) {
        Session session=sessionFactory.getCurrentSession();
        try {
            session.merge(image);
            System.out.println(" update success");
            return true;
        }catch (Exception e)
        {
            return false;
        }
    }


}
