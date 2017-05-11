package Service;

import Entities.Post;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.OrderBy;
import java.util.Date;
import java.util.List;

/**
 * Created by linhtran on 07/05/2017.
 */

@Service
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
        Session session=sessionFactory.openSession();
        Post usr=session.find(Post.class,id);
        session.close();
        return  usr;
    }

    public List<Post> getAllPost()
    {
        Session session=sessionFactory.openSession();
        List<Post> list=session.createNativeQuery("select * from post ",Post.class).getResultList();
        session.close();
        return list;
    }

    public  List<Post> getAllPost(String query)
    {
        Session session=sessionFactory.openSession();
        List<Post> list=session.createNativeQuery(query,Post.class).getResultList();
        session.close();
        return list;
    }

    public  List<Post> getAllPostPublic()
    {
        Session session=sessionFactory.openSession();
        String query="select * from post where approve=1 and status=1  order by time_post";
        List<Post> list=session.createNativeQuery(query,Post.class).getResultList();
        session.close();
        return list;
    }

    public List<Post> getPost(int from,int end)
    {
        Session session=sessionFactory.openSession();
        String str="select * from post where approve=1 and status=1  order by time_post desc limit "+from+","+end +"";
        List<Post> list=session.createNativeQuery(str,Post.class).getResultList();
        session.close();
        return list;
    }
}
