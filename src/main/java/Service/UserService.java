package Service;

import Entities.User;
import org.hibernate.Session;
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
        Session session=sessionFactory.openSession();
        User usr=session.find(User.class,id);
        session.close();
        return  usr;
    }

    public List<User> getAllUser()
    {
        Session session=sessionFactory.openSession();
        List<User> list=session.createNativeQuery("select * from user",User.class).getResultList();
        session.close();
        return list;
    }


    public User getUserByName(String name)
    {
        Session session=sessionFactory.openSession();
        List<User> list=(List<User>) session.createNativeQuery("select * from user where user_name='"+name+"'",User.class).getResultList();
        session.close();
        if(list==null)
        {
            return  null;
        }
        return list.get(0);
    }

}
