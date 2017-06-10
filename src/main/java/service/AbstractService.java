package service;

import entities.AbstractEntity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by linhtran on 02/06/2017.
 */
@Repository
@Transactional
public abstract class AbstractService<E extends AbstractEntity> {

    @Autowired
    SessionFactory sessionFactory;

    public   E  find(Class<E> type ,String tableName,int id) {
        return  sessionFactory.getCurrentSession().createNativeQuery("select * from "+tableName +" where id = "+id,type).getSingleResult();
    }


    public  void delete(Class<E> type,String tableName,int id) {
        E entity = this.find(type,tableName,id);
        if(entity != null)
        {
            sessionFactory.getCurrentSession().delete(entity);
        }
    }

    public   void save(E e)
    {
        sessionFactory.getCurrentSession().saveOrUpdate(e);
    }

    public  List<E> findAll(Class<E> type,String tableName)
    {
        return sessionFactory.getCurrentSession().createNativeQuery("select * from "+tableName,type).getResultList();
    }


    public int getCount(Class<E> type, String tableName)
    {
        return this.findAll(type,tableName).size();
    }

    public void flushSession() {
        this.sessionFactory.getCurrentSession().flush();
    }
}
