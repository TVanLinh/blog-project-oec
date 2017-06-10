package utils.sort;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by linhtran on 26/05/2017.
 */

@Component
public class PortSort  extends  Sort{

    @Autowired
    SessionFactory sessionFactory;

     public     SortType getSortType(String name, List<SortType> list) {
        for (SortType sort : list) {
            if (sort.orderBy.trim().equals(name)) {
                return sort;
            }
        }
        return null;
    }





}

