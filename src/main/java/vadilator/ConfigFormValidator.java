package vadilator;

import forms.ConfigForm;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by linhtran on 10/06/2017.
 */

@Component
public class ConfigFormValidator extends AbstractVadidator implements Validator {

    public boolean supports(Class<?> clazz) {
        return clazz.equals(ConfigForm.class);
    }

    public void validate(Object target, Errors errors) {

        ConfigForm configForm = (ConfigForm) target;
        if (StringUtils.isEmpty(configForm.getTitle()) || StringUtils.isBlank(configForm.getTitle())) {
            errors.rejectValue("title", "validation.field.title_not_blank");

        }

        if (!utils.string.StringUtils.checkVid(configForm.getTitle())) {
            errors.rejectValue("title", "validation.field.title_not_valid");

        }

        if (configForm.getTitle().length() > 40) {
            errors.rejectValue("title", "validation.field.conf_too_length");

        }


        if (StringUtils.isEmpty(configForm.getDateFormat()) || StringUtils.isBlank(configForm.getDateFormat())) {
            errors.rejectValue("dateFormat", "validation.field.conf_not_dateformat");
        }

        int number = NumberUtils.toInt(configForm.getNumberView() + "", -1);

        if (number < 0) {
            errors.rejectValue("numberView", "number.view.more.zero");
        }
    }
}
