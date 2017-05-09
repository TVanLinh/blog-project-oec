package Utils;

import Entities.Configuration;
import Service.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        DateFormat dateFormat=new SimpleDateFormat(format);
        return dateFormat.format(date);
    }
    public static void main(String[] args) {
        Date date= Calendar.getInstance().getTime();
        System.out.println(DateFormatUtil.format(date,"HH:mm:ss dd/MM/yyyy"));
    }
}
