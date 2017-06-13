package utils.sort;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by linhtran on 26/05/2017.
 */

@Component
public class PortSort  extends  Sort{

    public   SortType getSortType(String name, List<SortType> list) {
        for (SortType sort : list) {
            if (sort.orderBy.trim().equals(name)) {
                return sort;
            }
        }
        return null;
    }

    public   boolean checkOrderBy(String orderBy) {
        Set<String> set  = new HashSet<String>();
        set.add("title");
        set.add("id");
        set.add("time_post");
        set.add("number_view");
        set.add("number_like");
        set.add("status");
        set.add("approve");
        set.add("id_user");
        if(set.contains(orderBy)) {
            return true;
        }
        return  false;
    }



}

