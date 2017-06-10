package vadilator;

import entities.Role;
import forms.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.UserService;

import java.util.List;

/**
 * Created by linhtran on 09/06/2017.
 */
@Component
public class UserFormValidator extends AbstractVadidator {

    @Autowired
    protected UserService userService;


    public boolean checkOverLapUserName(UserForm userForm)
    {
        if(this.userService.getUserByName(userForm.getUser().getUserName())==null)
        {
            return false;
        }
        return true;
    }

    public  boolean checkOverLapPassWord(UserForm userForm)
    {
        if(userForm.getUser().getPassWord().equals(userForm.getRePassWord()))
        {
            return  true;
        }
        return false;
    }

    public  boolean checkValidRole(List<Role> roles)
    {
        if (roles == null || roles.size() == 0) {
            return false;
        }
        return true;
    }

    public boolean supports(Class<?> clazz) {
        return clazz.equals(UserForm.class);
    }


}
