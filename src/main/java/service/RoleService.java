package service;

import entities.Role;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by linhtran on 18/05/2017.
 */

@Service
public class RoleService {
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
}
