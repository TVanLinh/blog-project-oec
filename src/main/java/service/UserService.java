package service;

import dao.AbstractDAO;
import dao.UserDAO;
import entities.Role;
import entities.User;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import utils.number.NumberViewSort;
import utils.sort.SortType;

import java.util.List;

/**
 * Created by linhtran on 07/05/2017.
 */

@Service
@Transactional
public class UserService  extends AbstractDAO<User> {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    UserDAO userDAO;

    public UserService() {
    }

    public boolean checkUserValidUpdate(ModelMap modelMap,User userNew) {
        if (userNew.getUserName() == null || userNew.getUserName().trim().equals("")) {
            modelMap.addAttribute("error", " User name not null!");
            return false;
        }

       // User usr = this.userDAO.getUserByName(userNew.getUserName());
//        if(!userCurrent.getUserName().equalsIgnoreCase(userNew.getUserName()) && usr!=null)
//        {
//            modelMap.addAttribute("error","User name available!");
//            return false;
//        }
        if (userNew.getPassWord() == null || userNew.getPassWord().trim().equals("")) {
            modelMap.addAttribute("error", " pass word not nulll!");
            return false;
        }
        return true;
    }


    public boolean checkUserInsert(ModelMap modelMap, User user, String[] arr) {

        if (!StringUtils.isNotEmpty(user.getUserName()) || !StringUtils.isNotBlank(user.getUserName())) {
            modelMap.addAttribute("error", " user name not null!");
            return false;
        }

        if (this.userDAO.getUserByName(user.getUserName().trim()) != null) {
            modelMap.addAttribute("error", " user name exits available !");
            return false;
        }

        if (!StringUtils.isNotEmpty(user.getPassWord()) || !StringUtils.isNotBlank(user.getPassWord())) {
            modelMap.addAttribute("error", " pass word not null!");
            return false;
        }


        if (arr == null) {
            modelMap.addAttribute("error", "Not choice role!");
            return false;
        }

        return true;
    }

    public List<User> getUserBeginByUserName(String condition, SortType sortType, int offset)
    {
        String str = "select * from user where user_name like :condition  order by "+sortType.orderBy +" "+sortType.typeOrder+" limit "+offset+","+NumberViewSort.NUMBER_VIEW;
        Query query = sessionFactory.getCurrentSession().createNativeQuery(str,User.class);
        query.setParameter("condition","%"+condition+"%");
        return ( List<User>)query.getResultList();
    }

    public  int getCountBeginUserName(String searchQuery)
    {
        String str= "select * from user where user_name like :condition";
        Query query = sessionFactory.getCurrentSession().createNativeQuery(str,User.class);
       return query.setParameter("condition","%"+searchQuery+"%").getResultList().size();
    }

    public  void save(User user)
    {
        this.userDAO.update(user);
    }

    public  void delete(int id)
    {
        this.delete(User.class,"user",id);
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
        List<Role> role=user.getRoleList();
        if(role == null && role.size()  == 0) {
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
}
