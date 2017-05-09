package Service;

import Entities.Image;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by linhtran on 07/05/2017.
 */

@Service
public class ImageService {

    @Autowired
    SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public ImageService() {
    }

    public Image find(int id) {
        Session session=sessionFactory.openSession();
        Image usr=session.find(Image.class,id);
        session.close();
        return  usr;
    }

    public List<Image> getAllImage()
    {
        Session session=sessionFactory.openSession();
        List<Image> list=session.createNativeQuery("select * from postimage",Image.class).getResultList();
        session.close();
        return list;
    }


    public Image getImageByName(String name)
    {
        Session session=sessionFactory.openSession();
        List<Image> list= session.createNativeQuery("select * from user where user_name='"+name+"'",Image.class).getResultList();
        session.close();
        if(list==null)
        {
            return  null;
        }
        return list.get(0);
    }

}
