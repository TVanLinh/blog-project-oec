package controller;

import com.fasterxml.jackson.annotation.JsonView;
import entities.Post;
import jsonviews.Views;
import model.UserRestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.PostService;
import utils.cookie.CookieUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by linhtran on 30/05/2017.
 */

@RestController
public class AjaxController {

    @Autowired
    PostService postService;

//    @JsonView(Views.Public.class)
//    @RequestMapping("/getStatisticPost")
//    public StatisticPost statisticPost(@RequestBody StatisticPost statisticPost)
//    {
//        System.out.println(statisticPost.getMsg());
//        statisticPost.setStatisticPostByMonth(this.postService.getStatisticByMonth());
//        return statisticPost;
//    }

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
            post = this.postService.find(Integer.valueOf(userRestBody.getId()));
        }catch (Exception e)
        {
            return userRestBody;
        }

        if (cookieLike==null)
        {
            cookieLike=new Cookie("status_like_post",userRestBody.getId()+",");

            post.setNumberLike(post.getNumberLike()+1);
            this.postService.save(post);

            userRestBody.setNumberLike(post.getNumberLike());
            userRestBody.setCode("200");
            userRestBody.setMsg("Thanh cong");
            userRestBody.setStatusImg("public/asserts/images/like.png");
            cookieLike.setMaxAge(365*360*24);
            response.addCookie(cookieLike);
            return  userRestBody;
        }

        if( (cookieLike!=null&& !CookieUtils.isLike(post.getId(), cookieLike.getValue())))
        {
            post.setNumberLike(post.getNumberLike()+1);
            this.postService.save(post);

            userRestBody.setNumberLike(post.getNumberLike());
            userRestBody.setCode("200");
            userRestBody.setMsg("Thanh cong");
            userRestBody.setStatusImg("public/asserts/images/like.png");

            cookieLike.setValue(cookieLike.getValue()+post.getId()+",");
            cookieLike.setMaxAge(365*360*24);
            response.addCookie(cookieLike);
            return  userRestBody;
        }
        String str=CookieUtils.remove(cookieLike.getValue(),post.getId());
        cookieLike.setValue(str);

        post.setNumberLike(post.getNumberLike()-1);
        this.postService.save(post);

        response.addCookie(cookieLike);
        cookieLike.setMaxAge(365*360*24);

        userRestBody.setStatusImg("public/asserts/images/notlike.png");
        userRestBody.setNumberLike(post.getNumberLike());
        userRestBody.setCode("200");
        userRestBody.setMsg("Thanh cong");
        return userRestBody;
    }

}
