package controller;

import com.fasterxml.jackson.annotation.JsonView;
import dao.PostDAO;
import entities.Post;
import jsonviews.Views;
import model.StatisticPost;
import model.UserRestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utils.cookie.CookieUtils;
import utils.sort.PortSort;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by linhtran on 30/05/2017.
 */

@RestController
public class AjaxController {

    @JsonView(Views.Public.class)
    @RequestMapping("/getStatisticPost")
    public StatisticPost statisticPost(@RequestBody StatisticPost statisticPost)
    {
        System.out.println("okkkkkkkkkkkkkkkkkkkkkk---------------------------------------------------------------");
        System.out.println(statisticPost.getMsg());
        statisticPost.setStatisticPostByMonth(postDAO.getStatisticByMonth());
        return statisticPost;
    }





    @Autowired
    PostDAO postDAO;


    @Autowired
    PortSort portSort;

    @RequestMapping(value = "/like")
    @JsonView(Views.Public.class)
    public synchronized UserRestBody like(@RequestBody UserRestBody userRestBody, HttpServletRequest request, HttpServletResponse response) {

        Cookie cookie[]=request.getCookies();
        Cookie cookieLike=null;
        for(int i=0;i<cookie.length;i++)
        {
            if (cookie[i].getName().equals("status_like_post"))
            {
                cookieLike =cookie[i];
                break;
            }
        }

        Post post;
        try
        {
            post = postDAO.find(Integer.valueOf(userRestBody.getId()));
        }catch (Exception e)
        {
            return userRestBody;
        }

        if (cookieLike==null)
        {
            cookieLike=new Cookie("status_like_post",userRestBody.getId()+",");

            post.setNumberLike(post.getNumberLike()+1);
            postDAO.update(post);

            userRestBody.setNumberLike(post.getNumberLike());
            userRestBody.setCode("200");
            userRestBody.setMsg("Thanh cong");
            userRestBody.setStatusImg("public/asserts/images/like.png");
            cookieLike.setMaxAge(365*360*24);
            response.addCookie(cookieLike);
            System.out.println(cookieLike.getValue());
            System.out.println("------------------cookies---nulll---------------------------------");
            return  userRestBody;
        }

        if( (cookieLike!=null&& !CookieUtils.isLike(post.getId(), cookieLike.getValue())))
        {
            post.setNumberLike(post.getNumberLike()+1);
            postDAO.update(post);

            userRestBody.setNumberLike(post.getNumberLike());
            userRestBody.setCode("200");
            userRestBody.setMsg("Thanh cong");
            userRestBody.setStatusImg("public/asserts/images/like.png");

            cookieLike.setValue(cookieLike.getValue()+post.getId()+",");
            cookieLike.setMaxAge(365*360*24);
            response.addCookie(cookieLike);
            System.out.println(cookieLike.getValue());
            System.out.println("------------------cookies---#not---------------------------------");
            return  userRestBody;
        }
        String str= CookieUtils.remove(cookieLike.getValue(),post.getId());
        cookieLike.setValue(str);

        post.setNumberLike(post.getNumberLike()-1);
        postDAO.update(post);

        response.addCookie(cookieLike);
        cookieLike.setMaxAge(365*360*24);

        System.out.println("dislike like ");
        System.out.println("cooke"+cookieLike.getValue());
        userRestBody.setStatusImg("public/asserts/images/notlike.png");
        userRestBody.setNumberLike(post.getNumberLike());
        userRestBody.setCode("200");
        userRestBody.setMsg("Thanh cong");
        return userRestBody;
    }

    // get post in slider bar page post


}
