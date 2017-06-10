package dao;

import entities.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import service.RoleService;

import java.util.List;

/**
 * Created by linhtran on 09/06/2017.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath:bean.xml", "classpath:spring-security.xml"})
@Transactional
public class RoleDAOIMLTest {
    @Autowired
    private RoleService roleService;
    @Test
    public void getRoleByUsername() throws Exception {
        List<Role> list = this.roleService.getRoleByUsername("qdrsefsfsdfs");
        System.out.println(list);
    }

}