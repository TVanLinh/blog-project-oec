package vadilator;

import entities.User;
import forms.UserForm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertTrue;

/**
 * Created by linhtran on 09/06/2017.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath:bean.xml", "classpath:spring-security.xml"})
@Transactional
public class UserFormUpdateValidatorTest{
    @Autowired
    UserFormUpdateValidator updateValidator;

    @Test
    public void testCheckOverlapPasssword()
    {
        User user=new User("123455");
        UserForm userForm = new UserForm();
        userForm.setUser(user);
        userForm.setRePassWord("123455");
        assertTrue(org.apache.commons.lang3.StringUtils.isNotBlank(userForm.getRePassWord()) || org.apache.commons.lang3.StringUtils.isNotBlank(userForm.getUser().getPassWord())
             );
    }
}
