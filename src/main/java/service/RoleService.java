package service;

import dao.AbstractDAO;
import dao.RoleDAO;
import entities.Role;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by linhtran on 18/05/2017.
 */

@Service
@Transactional
public class RoleService  extends AbstractDAO<Role>{

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    RoleDAO roleDAO;

    public  String getStringFromListRole(List<Role> list) {
        StringBuilder builder = new StringBuilder("");
        for (Role role : list) {
            builder.append(role.getRole());
            builder.append("  ");
        }
        return builder.toString();
    }

    public List<Role> getListRole(String[] listRole) {
        List<Role> list = new ArrayList<Role>();
        for (int i = 0 ; i < listRole.length ; i++)
        {
            list.add(new Role(listRole[i]));
        }
        return list;
    }

    public  void save(Role role)
    {
        this.roleDAO.update(role);
    }

    public  void delete(int id)
    {
        this.roleDAO.delete(id);
    }

    public   void delete(String userName)
    {
        this.roleDAO.delete(userName);
    }
}
