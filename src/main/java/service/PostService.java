package service;

import dao.PostDAO;
import entities.Post;
import exceptions.AccessDenieException;
import exceptions.NotFindException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.number.NumberViewSort;
import utils.sort.SortType;

import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by linhtran on 07/05/2017.
 */

@Service
@Transactional
public class PostService extends AbstractService<Post> {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    PostDAO postDAO;

    @Autowired
    UserService userService;
    public PostService() {
    }

    public BigInteger getCountNotApprove() {
        return (BigInteger) sessionFactory.getCurrentSession().createNativeQuery("select count(*) from post where approve = 0 ").getSingleResult();
    }

    public List<Post> getContainsTitle(SortType sortType, String querySearch, int approve, int offset, int limit) {
        String string = " SELECT DISTINCT * from user inner join post on user.id = post.id_user where   approve = :approve and  ((title like :querySearch)  or  (user.user_name like :querySearch)) order by " + sortType.orderBy + " " + sortType.typeOrder + " limit " + offset + "," + limit;
        Query<Post> query = sessionFactory.getCurrentSession().createNativeQuery(string, Post.class);
        query.setParameter("querySearch", "%" + querySearch + "%");
        query.setParameter("approve", approve);
        return query.getResultList();
    }


    public BigInteger getCountContainsTitle(String querySearch, int approve) {
        String string = "  SELECT DISTINCT count(*) from user inner join post on user.id = post.id_user where  approve = :approve and  (title like :querySearch  or user.user_name like :querySearch)  ";
        Query<BigInteger> query = sessionFactory.getCurrentSession().createNativeQuery(string);
        query.setParameter("querySearch", "%" + querySearch + "%");
        query.setParameter("approve", approve);
        return query.getSingleResult();
    }

    public List<Post> getAllByTitle(SortType sortType, String querySearch, int offset, int limit) {
        String string = "  select * from  post inner join user on post.id_user = user.id where title like :querySearch or user.user_name like :querySearch  order by " + sortType.orderBy + " " + sortType.typeOrder + " limit " + offset + "," + limit;
        Query<Post> query = sessionFactory.getCurrentSession().createNativeQuery(string, Post.class);
        query.setParameter("querySearch", "%" + querySearch + "%");
        return query.getResultList();
    }


    public BigInteger getCountAllByTitle(String querySearch) {
        String str = " select count(*) from post inner join user on post.id_user = user.id where title like :querySearch or user.user_name like :querySearch ";
        Query<BigInteger> query = sessionFactory.getCurrentSession().createNativeQuery(str);
        query.setParameter("querySearch", "%" + querySearch + "%");
        return query.getSingleResult();
    }

    public List<Post> getPostByIdUser(int id) {
        return this.postDAO.getPostByIdUser(id);
    }

    public BigInteger getCountById(int idUser) {
        Session session = sessionFactory.getCurrentSession();
        String str = "select count(*) from post where  id_user = :idUser  order by time_post desc";
        return (BigInteger) session.createNativeQuery(str).setParameter("idUser", idUser).getSingleResult();
    }


    public BigInteger getCountByIdUser(int id, String querySearch) {
        String str = "select count(*) from  post where post.id_user = :idUser and title like :querySearch";
        Query<BigInteger> query = sessionFactory.getCurrentSession().createNativeQuery(str);
        query.setParameter("querySearch", "%" + querySearch + "%");
        query.setParameter("idUser", id);
        return query.getSingleResult();
    }

    public List<Post> getPostByIdUser(int id, int offset, int limit) {
        return this.postDAO.getPostByIdUser(id, offset, limit);
    }

    public List<Post> getPostByIdUser(SortType sortType, String querySearch, int userId, int offset, int limit) {
        String string = "  select * from  post where id_user = :id_user and title like :querySearch  order by " + sortType.orderBy + " " + sortType.typeOrder + " limit " + offset + "," + limit;
        Query<Post> query = sessionFactory.getCurrentSession().createNativeQuery(string, Post.class);
        query.setParameter("querySearch", "%" + querySearch + "%");
        query.setParameter("id_user", userId);
        return query.getResultList();
    }

    public List<Post> getPostPublicByTitle(SortType sortType, String querySearch, int offset, int limit) {
        String string = "select * from   user inner join post on user.id = post.id_user  where approve = 1 and status = 1 and (title like :querySearch or user.user_name like :querySearch)  order by post." + sortType.orderBy + " " + sortType.typeOrder + " limit " + offset + "," + limit;
        Query<Post> query = sessionFactory.getCurrentSession().createNativeQuery(string, Post.class);
        query.setParameter("querySearch", "%" + querySearch + "%");
        return query.getResultList();
    }

    public List<Post> getPostPublicByTitle(SortType sortType, String querySearch) {
        String string = "select * from  user inner join post on user.id = post.id_user   where approve = 1  and status = 1 and (title like :querySearch or user.user_name like :querySearch)   order by post." + sortType.orderBy + " " + sortType.typeOrder;
        Query<Post> query = sessionFactory.getCurrentSession().createNativeQuery(string, Post.class);
        query.setParameter("querySearch", "%" + querySearch + "%");
        return query.getResultList();
    }

    public BigInteger getCountPublicByTitle(String querySearch) {
        String string = "select count(*) from  user inner join post on user.id = post.id_user   where approve = 1  and status = 1 and (title like :querySearch or user.user_name like :querySearch) ";
        Query query = sessionFactory.getCurrentSession().createNativeQuery(string);
        query.setParameter("querySearch", "%" + querySearch + "%");
        return (BigInteger) query.getSingleResult();
    }

    public List<Post> finAll(String query) {
        return this.postDAO.getAllPost(query);
    }



    public void delete(int id)  {
        if(this.postDAO.find(id)!=null) {
            this.postDAO.delete(id);
        }
    }

    public void delete(String id,String userName) throws NotFindException, AccessDenieException {
        Post post;
        if(!StringUtils.isNumeric(id)  || (post = this.postDAO.find(Integer.valueOf(id))) == null){
            throw  new NotFindException(NotFindException.POST_NOT_FOUND);
        }
        if(this.userService.isEditPostByUser(this.userService.getUserByName(userName),post)){
            this.postDAO.delete(Integer.valueOf(id));
        }
    }

    public Class<Post> getClassTable() {
        return Post.class;
    }

    @Override
    public Post find(int id) throws NotFindException {
        Post post = this.postDAO.find(id);
        if(post == null){
            throw  new NotFindException(NotFindException.POST_NOT_FOUND);
        }
        return post;
    }

    public void save(Post post) {
        this.postDAO.update(post);
    }

    public List<Post> getPublic(int offset, int limit) {
        return this.postDAO.getPost(offset, limit);
    }

    public BigInteger getCountPublic() {
        return (BigInteger) this.sessionFactory.getCurrentSession().createNativeQuery("select count(*) from post where approve = 1 and status =1 ").getSingleResult();
    }

    public   boolean approvePost(String   id,String userName) throws NotFindException, AccessDenieException {
        Post post;
        if(!StringUtils.isNumeric(id) ||(post = this.postDAO.find(Integer.valueOf(id))) == null){
            throw new NotFindException(NotFindException.POST_NOT_FOUND);
        }

        if(!this.userService.isRoleAdmin(this.userService.getUserByName(userName))){
            throw  new AccessDenieException(AccessDenieException.ACCESS_NOT_ROLE_POST);
        }

        if(post.getApprove() == 1){
            return false;
        }
        Date date;
        Calendar calendar=Calendar.getInstance();
        date = calendar.getTime();
        post.setApprovedTime(date);
        post.setApprove(1);
        this.save(post);
        return true;
    }

    public List<Post> getPost(int idUser, int status, int approve, SortType sortType) {
        String str = "select * from post where id_user =:id_user and status =:status and approve =:approve order by :orderBy :typeOrder ";
        Query<Post> query = sessionFactory.getCurrentSession().createNativeQuery(str, Post.class);
        query.setParameter("id_user", idUser);
        query.setParameter("status", status);
        query.setParameter("approve", approve);
        query.setParameter("orderBy", sortType.orderBy);
        query.setParameter("typeOrder", sortType.typeOrder);

        return query.getResultList();
    }

    public List<Post> getPost(int idUser, int status, int approve, SortType sortType, int offset, int limit) {
        String str = "select * from post where id_user =:id_user and status =:status and approve =:approve order by  :orderBy :typeOrder limit :offset,:limit";
        Query<Post> query = sessionFactory.getCurrentSession().createNativeQuery(str, Post.class);
        query.setParameter("id_user", idUser);
        query.setParameter("status", status);
        query.setParameter("approve", approve);
        query.setParameter("orderBy", sortType.orderBy);
        query.setParameter("typeOrder", sortType.typeOrder);
        query.setParameter("offset", offset);
        query.setParameter("limit", limit);
        return query.getResultList();
    }

    public List<Post> getPost(int idUser, SortType sortType, int offset, int limit) {
        String str = "select * from post where id_user =:id_user  order by  :orderBy :typeOrder limit :offset,:limit";
        Query<Post> query = sessionFactory.getCurrentSession().createNativeQuery(str, Post.class);
        query.setParameter("id_user", idUser);
        query.setParameter("orderBy", sortType.orderBy);
        query.setParameter("typeOrder", sortType.typeOrder);
        query.setParameter("offset", offset);
        query.setParameter("limit", limit);
        return query.getResultList();
    }

    public BigInteger getCount(int idUser, int status, int approve) {
        String str = "select count(*) from post where id_user =:id_user and status =:status and approve =:approve  ";
        Query<BigInteger> query = sessionFactory.getCurrentSession().createNativeQuery(str);
        query.setParameter("id_user", idUser);
        query.setParameter("status", status);
        query.setParameter("approve", approve);
        return query.getSingleResult();
    }


    public BigInteger getCount() {
        return (BigInteger) this.sessionFactory.getCurrentSession().createNativeQuery("select count(*) from post ").getSingleResult();
    }

    public int getLimit(String numberView) {
        return NumberUtils.toInt(numberView, NumberViewSort.NUMBER_VIEW);
    }
}

