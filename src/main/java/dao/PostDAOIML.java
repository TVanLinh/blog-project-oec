package dao;

import entities.Post;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by linhtran on 06/05/2017.
 */

@Repository
@Transactional
public class PostDAOIML implements PostDAO {

    @Autowired
    SessionFactory sessionFactory;

    public void delete(int idPost) {
        Session session = sessionFactory.getCurrentSession();
        Post post = session.find(Post.class,idPost);
        session.remove(post);
        System.out.println(" delete post success");
    }

    public void update(Post post) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(post);
        System.out.println(" update post success");
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
        String str = "select * from post where approve=1 and status=1  order by time_post desc limit :from , :limit ";
        Query<Post> query=session.createNativeQuery(str,Post.class)
                .setParameter("from",from)
                .setParameter("limit",limit);
        return query.getResultList();
    }

    public List<Post> getPostByIdUser(int idUser, int from, int limit) {
        Session session = sessionFactory.getCurrentSession();
        String str = "select * from post where id_user = :idUser  order by time_post desc limit :from , :limit ";
        Query<Post> query=session.createNativeQuery(str,Post.class)
                .setParameter("idUser",idUser)
                .setParameter("from",from)
                .setParameter("limit",limit);
        return query.getResultList();
    }

    public List<Post> getPostByIdUser(int idUser) {
        Session session = sessionFactory.getCurrentSession();
        String str = "select * from post where id_user =  :idUser   order by time_post desc";
        Query<Post> query=session.createNativeQuery(str,Post.class)
                .setParameter("idUser",idUser);
        return query.getResultList();
    }
    public  List<BigInteger> getStatisticByMonth()
    {
        List<BigInteger> list= new ArrayList<BigInteger>();
        Session session =sessionFactory.getCurrentSession();
        Query query;
        for(int i=1;i<13;i++)
        {
            query=session.createNativeQuery("select count(*) from post where MONTH(time_post) = :i").setParameter("i",i);
            list.add((BigInteger) query.getSingleResult());
        }
        return list;
    }
}
