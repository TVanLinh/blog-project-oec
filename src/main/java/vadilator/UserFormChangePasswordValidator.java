package vadilator;

import forms.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by linhtran on 13/06/2017.
 */
@Component
public class UserFormChangePasswordValidator extends UserFormValidator implements Validator {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public void validate(Object target, Errors errors) {
        UserForm userForm = (UserForm) target;
        if (userForm.getUser() != null && !passwordEncoder.matches(userForm.getOldPassWord(), userForm.getUser().getPassWord())) {
            errors.rejectValue("user.passWord", UserFormValidator.VALIDATION_FIELD_PASS_NOT_RIGHT);
        }

        if (org.apache.commons.lang3.StringUtils.isBlank(userForm.getNewPassWord())) {
            errors.rejectValue("newPassWord", UserFormValidator.VALIDATION_PASS_BLANK);
        }

        if (!userForm.getNewPassWord().equalsIgnoreCase(userForm.getRePassWord())) {
            errors.rejectValue("newPassWord", UserFormValidator.VALIDATION_PASS_NOT_OVERLAP);
        }

    }
}
