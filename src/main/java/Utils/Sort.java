package Utils;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by linhtran on 27/05/2017.
 */

@Component
public class   Sort {
    public   SortType getSortType(String name, List<SortType> list) {
        for (SortType sort : list) {
            if (sort.orderBy.trim().equals(name)) {
                return sort;
            }
        }
        return null;
    }

    public SortType getCurrentSortType(HttpServletRequest request,String nameListCurrent,String orderByCurrent)
    {

        HttpSession session=request.getSession();
        List<SortType> orderList= (List<SortType>) session.getAttribute(nameListCurrent);
        String current= (String) session.getAttribute(orderByCurrent);
        if(current!=null)
        {
            return getSortType(current,orderList);
        }
        return  new SortType();
    }
}
