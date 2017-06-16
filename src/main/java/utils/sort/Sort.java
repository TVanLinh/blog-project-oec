package utils.sort;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by linhtran on 27/05/2017.
 */

@Component
public class Sort {
    public SortType getCurrentSortType(HttpServletRequest request, String current) {

        HttpSession session = request.getSession();
        SortType sortType = (SortType) session.getAttribute(current);
        if (sortType != null) {
            return sortType;
        }
        return new SortType();
    }

    public void checkValid(String page, String orderBy, SortType sortType, String orderDefault) {
        if ((StringUtils.isBlank(orderBy) || StringUtils.isEmpty(orderBy)) && page == null || (orderBy != null && !this.checkOrderBy(orderBy))) {
            orderBy = orderDefault;
            sortType.orderBy = orderBy;
        } else {
            if (orderBy != null) {
                if (sortType.orderBy.equals(orderBy) && page == null) {
                    sortType.toggleTypeOrder();
                }
                sortType.orderBy = orderBy;
            }
        }
    }

    public SortType getSortType(HttpServletRequest request, String nameCurrentSortType, String defaultSort) {
        HttpSession session = request.getSession();
        String orderBy = request.getParameter("orderBy");
        String page = request.getParameter("page");
        SortType sortItem = this.getCurrentSortType(request, nameCurrentSortType);
        this.checkValid(page, orderBy, sortItem, defaultSort);
        session.setAttribute(nameCurrentSortType, sortItem);
        return sortItem;
    }

    public boolean checkOrderBy(String orderBy) {
        Set<String> set = new HashSet<String>();
        set.add("title");
        set.add("id");
        set.add("time_post");
        set.add("number_view");
        set.add("number_like");
        set.add("status");
        set.add("approve");
        set.add("id_user");
        set.add("user_name");
        set.add("role");

        if (set.contains(orderBy)) {
            return true;
        }
        return false;
    }

}
