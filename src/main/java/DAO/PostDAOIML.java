package DAO;

import Entities.Post;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by linhtran on 06/05/2017.
 */

@Repository
public class PostDAOIML implements PostDAO {

    @Autowired
    SessionFactory sessionFactory;
    @Transactional
    public boolean insert(Post post) {
        Session session=sessionFactory.getCurrentSession();
        try {
            session.persist(post);
            System.out.println(" insert post success");
            return true;
        }catch (Exception e)
        {
            return false;
        }
    }

    @Transactional
    public boolean delete(int idPost) {
        Session session=sessionFactory.getCurrentSession();
        Post post=session.find(Post.class,idPost);
        try {
            session.remove(post);
            System.out.println(" delete post success");
            return true;
        }catch (Exception e)
        {
            return false;
        }
    }

    @Transactional
    public boolean update(Post post) {
        Session session=sessionFactory.getCurrentSession();
        try {
            session.merge(post);
            System.out.println(" update post success");
            return true;
        }catch (Exception e)
        {
            return false;
        }
    }

}
