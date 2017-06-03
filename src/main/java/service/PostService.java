package service;

import dao.AbstractDAO;
import dao.PostDAO;
import entities.Post;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.number.NumberViewSort;
import utils.sort.SortType;

import java.util.List;

/**
 * Created by linhtran on 07/05/2017.
 */

@Service
@Transactional
public class PostService extends AbstractDAO<Post> {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    PostDAO postDAO;


    public PostService() {
    }

    public List<Post> getAllPNotApprove() {
        String str = "select * from post where approve = 0";
        return this.postDAO.getAllPost(str);
    }

    public int getCountNotApprove() {
        return this.getAllPNotApprove().size();
    }

    public List<Post> getContainsTitle(SortType sortType, String querySearch, int approve, int offset) {
        String string = "  select * from  post where approve = :approve and title like :querySearch  order by " + sortType.orderBy + " " + sortType.typeOrder + " limit " + offset + "," + NumberViewSort.NUMBER_VIEW;
        Query query = sessionFactory.getCurrentSession().createNativeQuery(string, Post.class);
        query.setParameter("querySearch", "%" + querySearch + "%");
        query.setParameter("approve", approve);
        return query.getResultList();
    }

    public int getCountContainsTitle(String querySearch, int approve) {
        String string = "  select * from  post where approve = :approve and title like :querySearch";
        Query query = sessionFactory.getCurrentSession().createNativeQuery(string, Post.class);
        query.setParameter("querySearch", "%" + querySearch + "%");
        query.setParameter("approve", approve);
        return query.getResultList().size();
    }

    public List<Post> getAllByTitle(SortType sortType, String querySearch, int offset) {
        String string = "  select * from  post where  title like :querySearch  order by " + sortType.orderBy + " " + sortType.typeOrder + " limit " + offset + "," + NumberViewSort.NUMBER_VIEW;
        Query query = sessionFactory.getCurrentSession().createNativeQuery(string, Post.class);
        query.setParameter("querySearch", "%" + querySearch + "%");
        return query.getResultList();
    }

    public int getCountAllByTitle(String querySearch) {
        String str = "select * from  post where title like :querySearch";
        Query query = sessionFactory.getCurrentSession().createNativeQuery(str, Post.class);
        query.setParameter("querySearch", "%" + querySearch + "%");
        return query.getResultList().size();
    }

    public List<Post> getByIdUserAndStatus(int status,int iduser,int offset,int limit)
    {
        String str = "select * from post where status = :status and id_user = :id_user  order by time_post desc limit "+offset +"," +limit;
        Query query =this.sessionFactory.getCurrentSession().createNativeQuery(str,Post.class);
        query.setParameter("status",status);
        query.setParameter("id_user",iduser);
        return query.getResultList();
    }

    public  int getCounByIdAndStatus(int status,int iduser)
    {
        String str = "select * from post where status = :status and id_user = :id_user";
        Query query =this.sessionFactory.getCurrentSession().createNativeQuery(str,Post.class);
        query.setParameter("status",status);
        query.setParameter("id_user",iduser);
        return query.getResultList().size();

    }
    public List<Post> finAll(String query)
    {
        return  this.postDAO.getAllPost(query);
    }

    public  int getCount()
    {
        return this.getCount(Post.class,"post");
    }

    public  void delete(int id)
    {
        this.postDAO.delete(id);
    }

    public  Post find(int id)
    {
        return  this.postDAO.find(id);
    }

    public  void save(Post post)
    {
        this.postDAO.update(post);
    }
    public  List<Post> getPublic(int offset,int limit)
    {
        return  this.postDAO.getPost(offset,limit);
    }

    public  int getCountPublic()
    {
        return this.postDAO.getAllPostPublic().size();
    }
}
