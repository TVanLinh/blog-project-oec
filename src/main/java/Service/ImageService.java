package Service;

import Entities.Image;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by linhtran on 07/05/2017.
 */

@Service
@Transactional
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
        Session session=sessionFactory.getCurrentSession();
        Image usr=session.find(Image.class,id);
        return  usr;
    }

    public List<Image> getAllImage()
    {
        Session session=sessionFactory.getCurrentSession();
        List<Image> list=session.createNativeQuery("select * from postimage",Image.class).getResultList();
        return list;
    }


    public Image getImageByName(String name)
    {
        Session session=sessionFactory.getCurrentSession();
        List<Image> list= session.createNativeQuery("select * from user where user_name='"+name+"'",Image.class).getResultList();
        if(list==null)
        {
            return  null;
        }
        return list.get(0);
    }

}
