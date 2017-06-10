package utils.sort;

import dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.UserService;
import utils.number.NumberViewSort;
import utils.string.StringSessionUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by linhtran on 27/05/2017.
 */
@Component
public class UserSort extends Sort {

    @Autowired
    Sort sort;

    @Autowired
    UserDAO userDAO;

    @Autowired
    UserService userService;

    public Sort getSort() {
        return sort;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    public String getQuerySort(HttpServletRequest request, int ofset) {

        HttpSession session = request.getSession();
        String orderBy = request.getParameter("orderBy");
        List<SortType> orderList = (List<SortType>) session.getAttribute(StringSessionUtil.USER_TYPE_SORT);
        SortType sortItem;

        if(orderBy != null) {
            if(!checkOrderBy(orderBy)) {
                orderBy="user_name";
            }
            session.setAttribute(StringSessionUtil.CURRENT_USER_SORT,orderBy);
        } else {
            orderBy = (String) session.getAttribute(StringSessionUtil.CURRENT_USER_SORT);
            if(orderBy == null)
            {
                session.setAttribute(StringSessionUtil.CURRENT_USER_SORT,"user_name");
            }
        }

        if (orderList == null) {
            orderList = new ArrayList<SortType>();
            sortItem = new SortType();
            if (orderBy == null || orderBy.trim().equals("")) {
                sortItem.orderBy = "user_name";
            } else {
                sortItem.orderBy = orderBy;
            }
            orderList.add(sortItem);
            session.setAttribute(StringSessionUtil.USER_TYPE_SORT, orderList);
            return "select * from user order by   " + sortItem.orderBy + " " + sortItem.typeOrder + " limit " + ofset+ "," + NumberViewSort.NUMBER_VIEW;
        }

        sortItem = sort.getSortType(orderBy, orderList);
        if (sortItem == null) {
            sortItem = new SortType();
            sortItem.orderBy = orderBy;
            sortItem.typeOrder = "desc";
            orderList.add(sortItem);
        } else {
            if(request.getParameter("page") == null) {
                sortItem.toggleTypeOrder();
            }
        }
        session.setAttribute(StringSessionUtil.USER_TYPE_SORT, orderList);
        System.out.println("select * from user order by  " + sortItem.orderBy + " " + sortItem.typeOrder + " limit " +ofset + "," + NumberViewSort.NUMBER_VIEW + "--------------------------");
        return "select * from user order by  " + sortItem.orderBy + " " + sortItem.typeOrder + " limit " +ofset + "," + NumberViewSort.NUMBER_VIEW;
    }

    public String getQueryUserByRole(HttpServletRequest request,int offset)
    {
        HttpSession session = request.getSession();
        List<SortType> orderList = (List<SortType>) session.getAttribute(StringSessionUtil.USER_TYPE_SORT);
        String orderBy = request.getParameter("orderBy");
        if(orderBy != null && orderBy.equals("role")) {
            SortType sortItem = sort.getSortType(orderBy, orderList);
            if (sortItem == null) {
                sortItem = new SortType();
                sortItem.orderBy = orderBy;
                sortItem.typeOrder = "desc";
                orderList.add(sortItem);
            }else {
                if(request.getParameter("page") == null) {
                    sortItem.toggleTypeOrder();
                }
            }
            return  "select * from user,user_roles order by  user_roles.role  " + sortItem.typeOrder + " limit " +offset + "," + NumberViewSort.NUMBER_VIEW ;
        }
        return null;
    }
}
