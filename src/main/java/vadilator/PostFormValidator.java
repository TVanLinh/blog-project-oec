package vadilator;

import entities.Post;
import forms.PostForm;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import utils.string.StringUtils;

/**
 * Created by linhtran on 10/06/2017.
 */
public class PostFormValidator implements Validator {
    public boolean supports(Class<?> clazz) {
        return clazz.equals(Post.class);
    }

    public void validate(Object target, Errors errors) {
        PostForm postForm = (PostForm) target;
        if (org.apache.commons.lang3.StringUtils.isBlank(postForm.getPost().getTitle()) || !StringUtils.checkVid(postForm.getPost().getTitle())) {
            errors.rejectValue("post.title", "validation.field.post_title_not_blank");
        }

    }
}
