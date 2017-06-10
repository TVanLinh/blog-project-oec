package config;

import entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import service.RoleService;
import service.UserService;

import java.util.List;

/**
 * Created by linhtran on 09/06/2017.
 */
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    private UserService service;

    @Autowired
    private RoleService roleService;

    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        entities.User u = this.service.getUserByName(s);

        if (u == null) {
            return null;
        }


        List<Role> listRole = this.roleService.getRoleByUserId(u.getId());
        System.out.println(listRole);

        return new User(u.getUserName(), u.getPassWord(), listRole);
    }
}
