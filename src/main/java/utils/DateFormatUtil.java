package utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by linhtran on 08/05/2017.
 */

public class DateFormatUtil {
    public  static String format(Date date,String format)
    {
        if(date==null)
        {
            return "";
        }
        DateFormat dateFormat=new SimpleDateFormat(format);
        return dateFormat.format(date);
    }
    public static void main(String[] args) {
        Date date= Calendar.getInstance().getTime();
        System.out.println(DateFormatUtil.format(date,"HH:mm:ss dd/MM/yyyy"));
    }
    public  static String  show()
    {
        return "ok";
    }
}
