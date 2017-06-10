package utils.sort;

import org.springframework.stereotype.Component;

/**
 * Created by linhtran on 26/05/2017.
 */

@Component
public class SortType {
    public  String orderBy = "id";
    public  String  typeOrder = "desc"; //0 asc,1 desc

    public  void toggleTypeOrder() {
        if(this.typeOrder.equals("desc")) {
            this.typeOrder = "asc";
        } else {
            this.typeOrder = "desc";
        }
    }

    @Override
    public String toString() {
        return "SortType{" +
                "orderBy='" + orderBy + '\'' +
                ", typeOrder='" + typeOrder + '\'' +
                '}';
    }
}
