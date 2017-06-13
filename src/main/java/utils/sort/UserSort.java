package utils.sort;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by linhtran on 27/05/2017.
 */
@Component
public class UserSort extends Sort {
    public   boolean checkOrderBy(String orderBy) {
        Set<String> set  = new HashSet<String>();
        set.add("id");
        set.add("user_name");
        if(set.contains(orderBy)) {
            return true;
        }
        return  false;
    }

}
