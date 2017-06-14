package dao;

import entities.Image;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by linhtran on 06/05/2017.
 */

@Repository
@Transactional
public class ImageDAOIML implements ImageDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public void delete(int idAuthor) {
        Session session = sessionFactory.getCurrentSession();
        Image image  = session.find(Image.class,idAuthor);
        session.remove(image);
    }

    @Transactional
    public void deleteByIdPost(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query<Image> query = session.createNativeQuery("DELETE  from  postimage where id_post =:id",Image.class);
        query.setParameter("id",id).executeUpdate();
    }

    @Transactional
    public void update(Image image) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(image);
    }

    public Image find(int id) {
        Session session = sessionFactory.getCurrentSession();
        Image usr = session.find(Image.class,id);
        return  usr;
    }

    public List<Image> getAllImage() {
        Session session = sessionFactory.getCurrentSession();
        List<Image> list = session.createNativeQuery("select * from postimage",Image.class).getResultList();
        return list;
    }
}
