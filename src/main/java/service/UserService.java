package service;

import dao.UserDAO;
import entities.Post;
import entities.Role;
import entities.User;
import exceptions.AccessDenieException;
import exceptions.NotFindException;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.sort.SortType;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by linhtran on 07/05/2017.
 */

@Service
@Transactional
public class UserService  extends AbstractService<User> {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    UserDAO userDAO;

    @Autowired
    PostService postService;

    public UserService() {
    }

    public List<User> getUserBeginByUserName(String condition, SortType sortType, int offset, int limit)
    {
        String str = "select * from user where user_name like :condition  order by " + sortType.orderBy + " " + sortType.typeOrder + " limit " + offset + "," + limit;
        Query query = sessionFactory.getCurrentSession().createNativeQuery(str,User.class);
        query.setParameter("condition","%"+condition+"%");
        return ( List<User>)query.getResultList();
    }

    public BigInteger getCount() {
        return (BigInteger) this.sessionFactory.getCurrentSession().createNativeQuery("select count(*) from user").getSingleResult();
    }

    public BigInteger getCountBeginUserName(String searchQuery)
    {
        String str = "select count(*) from user where user_name like :condition";
        Query query = sessionFactory.getCurrentSession().createNativeQuery(str);
        return (BigInteger) query.setParameter("condition", "%" + searchQuery + "%").getSingleResult();
    }

    public  void save(User user)
    {
        this.userDAO.update(user);
    }

    public  void delete(int id)
    {
        this.delete(id);
    }

    public  void delete(String id,String userName) throws AccessDenieException, NotFindException {
        if(!this.isRoleAdmin(this.getUserByName(userName))){
            throw new AccessDenieException(AccessDenieException.ACESS_NOT_ROLE_PAGE);
        }
        if(!StringUtils.isNumeric(id) || this.find(Integer.valueOf(id)) == null) {
            throw new NotFindException(NotFindException.USER_NOT_FOUND);
        }
        this.delete(Integer.valueOf(id));
    }

    public Class<User> getClassTable() {
        return User.class;
    }

    public  User find(int id)
    {
        return  this.userDAO.find(id);
    }

    public  User getUserByName(String name)
    {
        return  userDAO.getUserByName(name);
    }
    public  List<User> findAll(String query)
    {
        return this.userDAO.getAllUser(query);
    }

    public  boolean isRoleAdmin(User user)
    {
        if(user == null){
            return false;
        }
        List<Role> role=user.getRoleList();
        if(role == null || role.size()  == 0) {
            return false;
        }
        for (Role role1:role)
        {
            if(role1.getRole().equals("ROLE_ADMIN")) {
                return true;
            }
        }
        return false;
    }



    public  boolean isRoleUser(User user)
    {
        if(user == null){
            return false;
        }
        List<Role> role=user.getRoleList();
        if(role == null || role.size()  == 0) {
            return false;
        }
        for (Role role1:role)
        {
            if(role1.getRole().equals("ROLE_USER")) {
                return true;
            }
        }
        return false;
    }

   public boolean isEditPost(User user, Post post) {
        if(user == null ||post == null || (!this.isRoleAdmin(user) && user.getId() != post.getUser().getId()) || user.getId() != post.getUser().getId())
        {
            return false;
        }
        return true;
    }
    public boolean isEditPostByUser(User user, Post post) throws AccessDenieException {
        if( this.isRoleAdmin(user) || user.getId() == post.getId()){
            return true;
        }
        throw new AccessDenieException(AccessDenieException.ACCESS_NOT_ROLE_POST);
    }


    public boolean isExists(String id) throws NotFindException {
        if(!StringUtils.isNumeric(id) || StringUtils.isNumeric(id) && this.postService.find(Integer.valueOf(id)) == null) {
            throw new NotFindException(NotFindException.POST_NOT_FOUND);
        }
        return true;
    }
}
