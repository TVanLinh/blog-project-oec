package model;

import com.fasterxml.jackson.annotation.JsonView;
import jsonviews.Views;

import java.math.BigInteger;
import java.util.List;


/**
 * Created by linhtran on 30/05/2017.
 */

public class StatisticPost {
    @JsonView(Views.Public.class)
    private int msg;

    @JsonView(Views.Public.class)
    private List<BigInteger> statisticPostByMonth;

    public StatisticPost() {
    }

    public List<BigInteger> getStatisticPostByMonth() {
        return statisticPostByMonth;
    }

    public void setStatisticPostByMonth(List<BigInteger> statisticPostByMonth) {
        this.statisticPostByMonth = statisticPostByMonth;
    }

    public int getMsg() {
        return msg;
    }

    public void setMsg(int msg) {
        this.msg = msg;
    }
}
