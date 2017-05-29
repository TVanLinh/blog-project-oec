package dao;

import entities.Post;
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
    public void insert(Post post) {
        Session session=sessionFactory.getCurrentSession();
        session.persist(post);
        System.out.println(" insert post success");
    }

    @Transactional
    public void delete(int idPost) {
        Session session=sessionFactory.getCurrentSession();
        Post post=session.find(Post.class,idPost);
        session.remove(post);
        System.out.println(" delete post success");
    }

    @Transactional
    public void update(Post post) {
        Session session=sessionFactory.getCurrentSession();
        session.merge(post);
        System.out.println(" update post success");
    }

}
