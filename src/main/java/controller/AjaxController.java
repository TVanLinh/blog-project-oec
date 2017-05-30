package controller;

import com.fasterxml.jackson.annotation.JsonView;
import dao.PostDAO;
import jsonviews.Views;
import model.StatisticPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by linhtran on 30/05/2017.
 */

@RestController
public class AjaxController {

    @Autowired
    PostDAO postDAO;

    @JsonView(Views.Public.class)
    @RequestMapping("/getStatisticPost")
    public StatisticPost statisticPost(@RequestBody StatisticPost statisticPost)
    {
        System.out.println("okkkkkkkkkkkkkkkkkkkkkk---------------------------------------------------------------");
        System.out.println(statisticPost.getMsg());
        statisticPost.setStatisticPostByMonth(postDAO.getStatisticByMonth());
        return statisticPost;
    }
}
