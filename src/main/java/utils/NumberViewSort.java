package utils;

import org.springframework.stereotype.Component;

/**
 * Created by linhtran on 26/05/2017.
 */

@Component
public class NumberViewSort {
    public  static  final int NUMBER_VIEW=20;

    public static int getNumberView() {
        return NUMBER_VIEW;
    }

    public NumberViewSort() {
    }
}
