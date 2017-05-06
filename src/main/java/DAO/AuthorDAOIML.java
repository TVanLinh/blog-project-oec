package DAO;

import Entities.Author;
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
public class AuthorDAOIML implements AuthorDAO {

    @Autowired
    SessionFactory sessionFactory;
    @Transactional
    public boolean insert(Author  author) {
        Session session=sessionFactory.getCurrentSession();
        try {
            session.persist(author);
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
        Author author=session.find(Author.class,idAuthor);
        try {
            session.remove(author);
            System.out.println(" delete success");
            return true;
        }catch (Exception e)
        {
            return false;
        }
    }

    @Transactional
    public boolean update(Author author) {
        Session session=sessionFactory.getCurrentSession();
        try {
            session.saveOrUpdate(author);
            System.out.println(" update success");
            return true;
        }catch (Exception e)
        {
            return false;
        }
    }
}
