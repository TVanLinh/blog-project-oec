package utils.cookie;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by linhtran on 10/05/2017.
 */
public class CookieUtils {
    public static boolean isLike(int id, String str) {
        if (str.trim().equals("")) {
            return false;
        }

        Set<Integer> set = new HashSet<Integer>();
        String list[] = str.trim().split(",");
        if (list.length == 0) {
            return false;
        }
        for (int i = 0; i < list.length; i++) {
            if (!list[i].equals("") || !list[i].equals(",")) {
                set.add(Integer.valueOf(list[i]));
            }
        }
        if (set.contains(id)) {
            return true;
        }
        return false;
    }

    public static String format(Object arr[], String st) {
        StringBuilder result = new StringBuilder("");
        for (int i = 0; i < arr.length; i++) {
            result.append(arr[i] + ",");
        }
        return result.toString();
    }

    public static String remove(String str, int id) {
        Set<Object> set = new HashSet<Object>();
        String list[] = str.split(",");
        for (int i = 0; i < list.length; i++) {
            set.add((list[i]));
        }
        set.remove(id + "");
        Object values[] = set.toArray();
        return CookieUtils.format(values, ",");
    }
}
