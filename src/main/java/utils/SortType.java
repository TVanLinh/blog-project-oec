package utils;

import org.springframework.stereotype.Component;

/**
 * Created by linhtran on 26/05/2017.
 */

@Component
public class SortType {
//    public static enum PostSortByName{
//        title,id_user,time_post,status,approve,number_view,number_like
//    };

    public  String orderBy = "id_user";
    public  String  typeOrder = "desc"; //0 asc,1 desc

    public  void toggleTypeOrder() {
        if(this.typeOrder.equals("desc")) {
            this.typeOrder = "asc";
        } else {
            this.typeOrder = "desc";
        }
    }

}
