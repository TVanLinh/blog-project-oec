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
    public void insert(Image image) {
        Session session=sessionFactory.getCurrentSession();
        session.saveOrUpdate(image);
        System.out.println(" insert image success");
    }

    @Transactional
    public void delete(int idAuthor) {
        Session session=sessionFactory.getCurrentSession();
        Image image =session.find(Image.class,idAuthor);
        session.remove(image);
        System.out.println(" delete image success");
    }

    @Transactional
    public void update(Image image) {
        Session session=sessionFactory.getCurrentSession();
        session.merge(image);
        System.out.println(" update image success");
    }


}
