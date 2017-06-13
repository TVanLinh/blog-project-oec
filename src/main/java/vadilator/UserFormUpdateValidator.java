package vadilator;

import entities.User;
import forms.UserForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import utils.string.StringUtils;

/**
 * Created by linhtran on 09/06/2017.
 */

@Component
public class UserFormUpdateValidator extends UserFormValidator implements Validator{

    public void validate(Object target, Errors errors) {
        this.validate((UserForm) target, errors);
    }

    public void validate(UserForm target, Errors errors) {
        if(!StringUtils.checkVid(target.getUser().getUserName()))
        {
            errors.rejectValue("user.userName","validation.field.user_name_not_vid");
            return;
        }

        User user = this.userService.getUserByName(target.getUser().getUserName());
        if (user != null && target.getUser().getId()!= user.getId()) {
            errors.rejectValue("user.userName", "validation.field.existed_username");
            return;
        }

        if((org.apache.commons.lang3.StringUtils.isNotBlank(target.getRePassWord())
                || org.apache.commons.lang3.StringUtils.isNotBlank(target.getUser().getPassWord()))
                && !checkOverLapPassWord(target))
        {
            errors.rejectValue("user.passWord","validation.field.overlap_password");
            return;
        }

        if(!this.checkValidRole(target.getUser().getRoleList()))
        {
            errors.rejectValue("user.roleList","validation.field.role_not_blank");
        }

    }


}
