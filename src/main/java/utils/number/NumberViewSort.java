package utils.number;

import org.springframework.stereotype.Component;

/**
 * Created by linhtran on 26/05/2017.
 */

@Component
public class NumberViewSort {
    public static int NUMBER_VIEW = 10;

    public static int getNumberView() {
        return NUMBER_VIEW;
    }

    public NumberViewSort() {
    }
}
