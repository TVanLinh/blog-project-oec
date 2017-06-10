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

    public  static  final  String  VALIDATION_FIELD_PASS_NOT_RIGHT = "validation.field.password_not_right";
    public  static  final  String  VALIDATION_PASS_NOT_OVERLAP= "validation.field.overlap_password";
    public  static  final  String  VALIDATION_PASS_BLANK= "validation.field.password_not_blank";

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


    public  static  boolean checkOverlapPassWord(String pass1,String pass2) {
        if(!pass1.equals(pass2)) {
            return false;
        }
        return true;
    }

    public static  boolean checkPassWordBlank(String pass1,String pass2)
    {
        if (pass1 == null || pass2 == null) {
            return false;
        }

        if (org.apache.commons.lang3.StringUtils.isBlank(pass1) && org.apache.commons.lang3.StringUtils.isBlank(pass2)) {
            return false;
        }
        return true;
    }

}
