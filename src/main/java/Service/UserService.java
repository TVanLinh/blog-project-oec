package Service;

import Entities.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by linhtran on 07/05/2017.
 */

@Service
public class UserService {

    @Autowired
    SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public UserService() {
    }

    public User find(int id) {
        return sessionFactory.openSession().find(User.class,id);
    }

    public List<User> getAllUser()
    {
        return (List<User>)sessionFactory.openSession().createNativeQuery("select * from user").getResultList();
    }

    public User getUserByName(String name)
    {
        List<User> list=(List<User>) sessionFactory.openSession().createNativeQuery("select * from user where user_name='"+name+"'",User.class).getResultList();
        if(list==null)
        {
            return  null;
        }
        return list.get(0);
    }
}
