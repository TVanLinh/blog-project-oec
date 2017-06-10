package config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by linhtran on 09/06/2017.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath:bean.xml", "classpath:spring-security.xml"})
@Transactional
public class MyUserDetailServiceTest {
    @Autowired
    private MyUserDetailService myUserDetailService;
    @Test
    public void loadUserByUsername() throws Exception {
        UserDetails u = this.myUserDetailService.loadUserByUsername("qdrsefsfsdfs");
        System.out.println(u.getUsername());


    }

}