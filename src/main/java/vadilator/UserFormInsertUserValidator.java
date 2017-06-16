package vadilator;

import forms.UserForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import utils.string.StringUtils;

/**
 * Created by linhtran on 10/06/2017.
 */

@Component
public class UserFormInsertUserValidator extends UserFormValidator implements Validator {

    public void validate(Object target, Errors errors) {
        validate((UserForm) target, errors);
    }

    public void validate(UserForm target, Errors errors) {

        if (!StringUtils.checkVid(target.getUser().getUserName())) {
            errors.rejectValue("user.userName", "validation.field.user_name_not_vid");
        }

        if (this.checkOverLapUserName(target)) {
            errors.rejectValue("user.userName", "validation.field.existed_username");
        }
        if (org.apache.commons.lang3.StringUtils.isBlank(target.getUser().getPassWord())) {
            errors.rejectValue("user.passWord", "validation.field.password_not_blank");
        }

        if (!this.checkOverLapPassWord(target)) {
            errors.rejectValue("user.passWord", "validation.field.overlap_password");
        }

        if (!this.checkValidRole(target.getUser().getRoleList())) {
            errors.rejectValue("user.roleList", "validation.field.role_not_blank");
        }

    }
}
