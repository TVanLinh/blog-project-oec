package dao;

import entities.Post;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by linhtran on 06/05/2017.
 */

@Repository
@Transactional
public class PostDAOIML implements PostDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void delete(int idPost) {
        Session session = sessionFactory.getCurrentSession();
        Post post = session.find(Post.class, idPost);
        session.remove(post);
    }

    public void update(Post post) {
        Session session = sessionFactory.getCurrentSession();
        Date date = Calendar.getInstance().getTime();
        post.setUpdateTime(date);
        session.saveOrUpdate(post);
    }

    public Post find(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.find(Post.class, id);
    }

    public List<Post> getAllPost() {
        Session session = sessionFactory.getCurrentSession();
        return session.createNativeQuery("select * from post order by time_post ", Post.class).getResultList();
    }

    public List<Post> getAllPost(String query) {
        Session session = sessionFactory.getCurrentSession();
        return session.createNativeQuery(query, Post.class).getResultList();
    }

    public List<Post> getAllPostPublic() {
        Session session = sessionFactory.getCurrentSession();
        String query = "select * from post where approve = 1 and status = 1  order by time_post";
        return session.createNativeQuery(query, Post.class).getResultList();
    }

    public List<Post> getPost(int offset, int limit) {
        Session session = sessionFactory.getCurrentSession();
        String str = "select * from post where approve=1 and status=1  order by time_post desc  limit :offset,:limit";
        Query<Post> query = session.createNativeQuery(str, Post.class);
        query.setParameter("offset", offset);
        query.setParameter("limit", limit);
        return query.getResultList();
    }

    public List<Post> getPostByIdUser(int idUser, int offset, int limit) {
        Session session = sessionFactory.getCurrentSession();
        String str = "select * from post where id_user = :idUser order by time_post desc limit :offset,:limit";
        Query<Post> query = session.createNativeQuery(str, Post.class);
        query.setParameter("idUser", idUser);
        query.setParameter("offset", offset);
        query.setParameter("limit", limit);
        return query.getResultList();
    }

    public List<Post> getPostByIdUser(int idUser) {
        Session session = sessionFactory.getCurrentSession();
        String str = "select * from post where  id_user = :idUser  order by time_post desc";
        Query<Post> query = session.createNativeQuery(str, Post.class);
        query.setParameter("idUser", idUser);
        return query.getResultList();
    }
}
