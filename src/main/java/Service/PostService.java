package Service;

import Entities.Post;
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
public class PostService {

    @Autowired
    SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public PostService() {
    }

    public Post find(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.find(Post.class, id);
    }

    public List<Post> getAllPost() {
        Session session = sessionFactory.getCurrentSession();
        List<Post> list = session.createNativeQuery("select * from post order by time_post ", Post.class).getResultList();
        return list;
    }

    public List<Post> getAllPost(String query) {
        Session session = sessionFactory.getCurrentSession();
        List<Post> list = session.createNativeQuery(query, Post.class).getResultList();
        return list;
    }

    public List<Post> getAllPostPublic() {
        Session session = sessionFactory.getCurrentSession();
        String query = "select * from post where approve=1 and status=1  order by time_post";
        List<Post> list = session.createNativeQuery(query, Post.class).getResultList();
        return list;
    }

    public List<Post> getPost(int from, int limit) {
        Session session = sessionFactory.getCurrentSession();
        String str = "select * from post where approve=1 and status=1  order by time_post desc limit " + from + "," + limit + "";
        List<Post> list = session.createNativeQuery(str, Post.class).getResultList();
        return list;
    }

    public List<Post> getPostByIdUser(int idUser, int from, int limit) {
        Session session = sessionFactory.getCurrentSession();
        String str = "select * from post where id_user=" + idUser + "  order by time_post desc limit " + from + "," + limit + "";
        List<Post> list = session.createNativeQuery(str, Post.class).getResultList();
        return list;
    }

    public List<Post> getPostByIdUser(int idUser) {
        Session session = sessionFactory.getCurrentSession();
        String str = "select * from post where id_user=" + idUser + "  order by time_post desc";
        List<Post> list = session.createNativeQuery(str, Post.class).getResultList();
        return list;
    }
}
