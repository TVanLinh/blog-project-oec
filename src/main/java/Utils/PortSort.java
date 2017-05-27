package Utils;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by linhtran on 26/05/2017.
 */

@Component
public class PortSort {


    public String getQuerySortAllPost(HttpServletRequest request,int ofset) {

        HttpSession session = request.getSession();
        String orderBy = request.getParameter("orderBy");
        List<SortType> orderList = (List<SortType>) session.getAttribute("POST_ALL_TYPE_SORT");
        SortType sortItem;

        if(orderBy!=null)
        {
            session.setAttribute("current_sort_all_post",orderBy);
        }
        else
        {
            orderBy= (String) session.getAttribute("current_sort_all_post");
            if(orderBy==null)
            {
                session.setAttribute("current_sort_all_post","id_user");
            }
        }

        if (orderList == null) {
            orderList = new ArrayList<SortType>();
            sortItem = new SortType();
            if (orderBy == null || orderBy.trim() == "") {
                sortItem.orderBy = "id_user";
            } else {
                sortItem.orderBy = orderBy;
            }
            orderList.add(sortItem);
            session.setAttribute("POST_ALL_TYPE_SORT", orderList);
            return "select * from post order by   " + sortItem.orderBy + " " + sortItem.typeOrder + " limit " + ofset+ "," + NumberViewSort.NUMBER_VIEW;
        }

        sortItem = this.getSortType(orderBy, orderList);
        if (sortItem == null) {
            sortItem = new SortType();
            sortItem.orderBy = orderBy;
            sortItem.typeOrder = "desc";
            orderList.add(sortItem);
        } else {
            if (sortItem.typeOrder.equals("asc")) {
                sortItem.typeOrder = "desc";
            } else if (sortItem.typeOrder.equals("desc")) {
                sortItem.typeOrder = "asc";
            }
        }
        session.setAttribute("POST_ALL_TYPE_SORT", orderList);
        System.out.println("select * from post order by  " + sortItem.orderBy + " " + sortItem.typeOrder + " limit " +ofset + "," + NumberViewSort.NUMBER_VIEW + "--------------------------");
        return "select * from post order by  " + sortItem.orderBy + " " + sortItem.typeOrder + " limit " +ofset + "," + NumberViewSort.NUMBER_VIEW;
    }

  public   SortType getSortType(String name, List<SortType> list) {
        for (SortType sort : list) {
            if (sort.orderBy.trim().equals(name)) {
                return sort;
            }
        }
        return null;
    }

    public SortType getCurrentSortType(HttpServletRequest request)
    {

        HttpSession session=request.getSession();
        List<SortType> orderList= (List<SortType>) session.getAttribute("POST_ALL_TYPE_SORT");
        String current= (String) session.getAttribute("current_sort_all_post");
        if(current!=null)
        {
            return getSortType(current,orderList);
        }
        return  new SortType();
    }
}

