package utils;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by linhtran on 26/05/2017.
 */

@Component
public class PortSort {


    public String getQuerySortAllPost(HttpServletRequest request,int ofset) {

        HttpSession session = request.getSession();
        String orderBy = request.getParameter("orderBy");
        List<SortType> orderList = (List<SortType>) session.getAttribute(StringSessionUtil.POST_ALL_TYPE_SORT);
        SortType sortItem;

        if(orderBy!=null)
        {
            if(!checkOrderBy(orderBy))
            {
                orderBy="id_user";
            }
            session.setAttribute(StringSessionUtil.CurentALLPost,orderBy);
        }
        else
        {
            orderBy= (String) session.getAttribute(StringSessionUtil.CurentALLPost);
            if(orderBy==null)
            {
                session.setAttribute(StringSessionUtil.CurentALLPost,"id_user");
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
            session.setAttribute(StringSessionUtil.POST_ALL_TYPE_SORT, orderList);
            return "select * from post order by   " + sortItem.orderBy + " " + sortItem.typeOrder + " limit " + ofset+ "," + NumberViewSort.NUMBER_VIEW;
        }

        sortItem = this.getSortType(orderBy, orderList);
        if (sortItem == null) {
            sortItem = new SortType();
            sortItem.orderBy = orderBy;
            sortItem.typeOrder = "desc";
            orderList.add(sortItem);
        } else {
            if(request.getParameter("page")==null)
            {
                sortItem.toggleTypeOrder();
            }
        }
        session.setAttribute(StringSessionUtil.POST_ALL_TYPE_SORT, orderList);
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


    public String getQuerySortAllPostAprrove(HttpServletRequest request,int ofset,boolean option) {

        HttpSession session = request.getSession();
        String orderBy = request.getParameter("orderBy");
        List<SortType> orderList = (List<SortType>) session.getAttribute(StringSessionUtil.POST_APPROVE_TYPE_SORT);
        SortType sortItem;

        if(orderBy!=null)
        {
            if(!checkOrderBy(orderBy))
            {
                orderBy="id_user";
            }
            session.setAttribute(StringSessionUtil.CurentApprovePost,orderBy);
        }
        else
        {
            orderBy= (String) session.getAttribute(StringSessionUtil.CurentApprovePost);
            if(orderBy==null&&option==true)
            {
                session.setAttribute(StringSessionUtil.CurentApprovePost,"id_user");
            }
        }

        if (orderList == null) {
            orderList = new ArrayList<SortType>();
            sortItem = new SortType();
            if ((orderBy == null || orderBy.trim() == "")) {
                sortItem.orderBy = "id_user";
            }else if (option==false&&orderBy==null&&session.getAttribute(StringSessionUtil.CurentApprovePost)!=null) {
                 sortItem.orderBy = (String) session.getAttribute(StringSessionUtil.CurentApprovePost);
            }else if(option==false&&orderBy==null&&session.getAttribute(StringSessionUtil.CurentApprovePost)==null)
            {
                sortItem.orderBy="id_user";
            }
            else {
                sortItem.orderBy = orderBy;
            }
            orderList.add(sortItem);
            session.setAttribute(StringSessionUtil.POST_APPROVE_TYPE_SORT, orderList);
            return "select * from post where approve = 0 order by   " + sortItem.orderBy + " " + sortItem.typeOrder + " limit " + ofset+ "," + NumberViewSort.NUMBER_VIEW;
        }

        sortItem = this.getSortType(orderBy, orderList);
        if (sortItem == null) {
            sortItem = new SortType();
            sortItem.orderBy = orderBy;
            sortItem.typeOrder = "desc";
            orderList.add(sortItem);
        } else {
            if(((request.getParameter("page"))==null)&&option==true)
            {
                sortItem.toggleTypeOrder();
            }
        }
        session.setAttribute(StringSessionUtil.POST_APPROVE_TYPE_SORT, orderList);
        System.out.println("select * from post  where approve = 0  order by  " + sortItem.orderBy + " " + sortItem.typeOrder + " limit " +ofset + "," + NumberViewSort.NUMBER_VIEW + "--------------------------");
        return "select * from post  where approve = 0  order by  " + sortItem.orderBy + " " + sortItem.typeOrder + " limit " +ofset + "," + NumberViewSort.NUMBER_VIEW;
    }

    public  boolean checkOrderBy(String orderBy)
    {
        Set<String> set=new HashSet<String>();
        set.add("title");
        set.add("id");
        set.add("time_post");
        set.add("number_view");
        set.add("number_like");
        set.add("status");
        set.add("approve");
        set.add("id_user");

        if(set.contains(orderBy))
        {
            return true;
        }
        return  false;
    }

//    boolean checkedAction(HttpServletRequest request)
//    {
//        String action=request.getParameter("action");
//        String page=request.getParameter("page");
//        String orderBy=request.getParameter("orderBy");
//        if(page==null&&action==null)
//        {
//            return true;
//        }
//        if(action!=null &&(action.equals("delete")||action.equals("approve"))||action.equals("insert"))
//        {
//            return  false;
//        }
//        return false;
//    }

}

