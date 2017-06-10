package vadilator;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by linhtran on 10/06/2017.
 */
public abstract class AbstractVadidator {
    public List<String> getCodeErrors(BindingResult br)
    {
        List<String> list = new ArrayList<String>();
        if(br.hasErrors()) {
            for (FieldError f:br.getFieldErrors()) {
                list.add(f.getCode());
            }
        }
        return list;
    }
}
