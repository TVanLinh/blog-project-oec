package service;

import dao.PostDAO;
import entities.Post;
import exceptions.AccessDenieException;
import exceptions.NotFindException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.number.NumberViewSort;
import utils.sort.SortType;

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

    public List<Post> getAllPNotApprove() {
        String str = "select * from post where approve = 0";
        return sessionFactory.getCurrentSession().createNativeQuery(str, Post.class).getResultList();
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

    public List<Post> getPostByIdUser(int id) {
        return this.postDAO.getPostByIdUser(id);
    }

    public List<Post> getPostByIdUser(int id, int offset, int limit) {
        return this.postDAO.getPostByIdUser(id, offset, limit);
    }

    public List<Post> getPostByIdUser(SortType sortType, String querySearch, int userId, int offset) {
        String string = "  select * from  post where id_user = :id_user and title like :querySearch  order by " + sortType.orderBy + " " + sortType.typeOrder + " limit " + offset + "," + NumberViewSort.NUMBER_VIEW;
        Query query = sessionFactory.getCurrentSession().createNativeQuery(string, Post.class);
        query.setParameter("querySearch", "%" + querySearch + "%");
        query.setParameter("id_user", userId);
        return query.getResultList();
    }

    public List<Post> getPostPublicByTitle(SortType sortType, String querySearch, int offset, int limit) {
        String string = "  select * from  post where approve = 1 and status = 1 and title like :querySearch  order by " + sortType.orderBy + " " + sortType.typeOrder + " limit " + offset + "," + limit;
        Query query = sessionFactory.getCurrentSession().createNativeQuery(string, Post.class);
        query.setParameter("querySearch", "%" + querySearch + "%");
        return query.getResultList();
    }

    public List<Post> getPostPublicByTitle(SortType sortType, String querySearch) {
        String string = "  select * from  post where approve = 1 and status = 1 and title like :querySearch  order by " + sortType.orderBy + " " + sortType.typeOrder;
        Query query = sessionFactory.getCurrentSession().createNativeQuery(string, Post.class);
        query.setParameter("querySearch", "%" + querySearch + "%");
        return query.getResultList();
    }

    public List<Post> finAll(String query) {
        return this.postDAO.getAllPost(query);
    }

    public int getCount() {
        return this.getCount(Post.class, "post");
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

    public int getCountPublic() {
        return this.postDAO.getAllPostPublic().size();
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
        Query query = sessionFactory.getCurrentSession().createNativeQuery(str, Post.class);
        query.setParameter("id_user", idUser);
        query.setParameter("status", status);
        query.setParameter("approve", approve);
        query.setParameter("orderBy", sortType.orderBy);
        query.setParameter("typeOrder", sortType.typeOrder);

        return query.getResultList();
    }

    public List<Post> getPost(int idUser, int status, int approve, SortType sortType, int offset, int limit) {
        String str = "select * from post where id_user =:id_user and status =:status and approve =:approve order by  :orderBy :typeOrder limit :offset,:limit";
        Query query = sessionFactory.getCurrentSession().createNativeQuery(str, Post.class);
        query.setParameter("id_user", idUser);
        query.setParameter("status", status);
        query.setParameter("approve", approve);
        query.setParameter("orderBy", sortType.orderBy);
        query.setParameter("typeOrder", sortType.typeOrder);
        query.setParameter("offset", offset);
        query.setParameter("limit", limit);
        return query.getResultList();
    }

    public int getCount(int idUser, int status, int approve) {
        return this.getPost(idUser, status, approve, new SortType()).size();
    }

    public int getLimit(String numberView) {
        return NumberUtils.toInt(numberView, NumberViewSort.NUMBER_VIEW);
    }
}

