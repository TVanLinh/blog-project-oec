package service;

import entities.AbstractEntity;
import exceptions.NotFindException;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
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

    public abstract Class<E> getClassTable();

    public E find(int id) throws NotFindException {
        Query<E> query = sessionFactory.getCurrentSession().createQuery("from p " + this.getClassTable().getClass().getName() + "  where p.id = :id", this.getClassTable());
        query.setParameter("id",id);
        return query.getSingleResult();
    }


    public void delete(int id) throws NotFindException {
        E entity = this.find(id);
        if (entity != null) {
            sessionFactory.getCurrentSession().delete(entity);
        }
    }

    public void save(E e) {
        sessionFactory.getCurrentSession().saveOrUpdate(e);
    }

    public List<E> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from " + this.getClassTable().getName(), this.getClassTable()).getResultList();
    }

    public void flushSession() {
        this.sessionFactory.getCurrentSession().flush();
    }
}
