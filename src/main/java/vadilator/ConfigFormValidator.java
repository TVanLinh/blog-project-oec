package vadilator;

import entities.Configuration;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by linhtran on 10/06/2017.
 */

@Component
public class ConfigFormValidator extends AbstractVadidator implements Validator {
    public boolean supports(Class<?> clazz) {
        return clazz.equals(Configuration.class);
    }

    public void validate(Object target, Errors errors) {

        Configuration configuration = (Configuration)target;
        if(!utils.string.StringUtils.checkVid(configuration.getWebTitle()) || StringUtils.isEmpty(configuration.getWebTitle()) || StringUtils.isBlank(configuration.getWebTitle())) {
            errors.rejectValue("webTitle","validation.field.post_title_not_blank");
        }

        if(configuration.getWebTitle().length()>40)
        {
            errors.rejectValue("webTitle","validation.field.conf_too_length");
        }


        if(StringUtils.isEmpty(configuration.getDateFormat()) ||StringUtils.isBlank(configuration.getDateFormat() )){
            errors.rejectValue("dateFormat","validation.field.conf_not_dateformat");
        }

    }
}
