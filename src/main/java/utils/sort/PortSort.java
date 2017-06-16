package utils.sort;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by linhtran on 26/05/2017.
 */

@Component
public class PortSort extends Sort {

    public boolean checkOrderBy(String orderBy) {
        Set<String> set = new HashSet<String>();
        set.add("title");
        set.add("id");
        set.add("time_post");
        set.add("number_view");
        set.add("number_like");
        set.add("status");
        set.add("approve");
        set.add("user_name");
        set.add("id_user");
        if (set.contains(orderBy)) {
            return true;
        }
        return false;
    }
}

