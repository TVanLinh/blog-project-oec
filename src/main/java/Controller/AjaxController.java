package Controller;

import DAO.PostDAO;
import Entities.Post;
import JsonViews.Views;
import Model.UserRestBody;
import Service.PostService;
import Utils.CookieUtils;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by linhtran on 09/05/2017.
 */

@RestController
public class AjaxController {

    @Autowired
    PostService postService;

    @Autowired
    PostDAO postDAO;

    @RequestMapping(value = "/like")
    @JsonView(Views.Public.class)
    public synchronized UserRestBody like(@RequestBody UserRestBody userRestBody, HttpServletRequest request, HttpServletResponse response) {
//        System.out.println(userRestBody.getMsg());
//        System.out.println(userRestBody.getCode());
//        System.out.println("id:" +userRestBody.getId());
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
           post = postService.find(Integer.valueOf(userRestBody.getId()));
        }catch (Exception e)
        {
            return userRestBody;
        }

        if (cookieLike==null || (cookieLike!=null&& !CookieUtils.isLike(post.getId(), cookieLike.getValue())))
        {
            cookieLike=new Cookie("status_like_post",userRestBody.getId()+",");

            post.setNumberLike(post.getNumberLike()+1);
            postDAO.update(post);

            userRestBody.setNumberLike(post.getNumberLike());
            userRestBody.setCode("200");
            userRestBody.setMsg("Thanh cong");

            cookieLike.setMaxAge(365*360*24);
            response.addCookie(cookieLike);
            System.out.println(cookieLike.getValue());
            System.out.println("------------------cookies---nulll---------------------------------");
            return  userRestBody;
        }

        String str=CookieUtils.remove(cookieLike.getValue(),post.getId());
        cookieLike.setValue(str);

        post.setNumberLike(post.getNumberLike()-1);
        postDAO.update(post);

        response.addCookie(cookieLike);
        cookieLike.setMaxAge(365*360*24);

        System.out.println("dislike like ");
        System.out.println("cooke"+cookieLike.getValue());

        userRestBody.setNumberLike(post.getNumberLike());
        userRestBody.setCode("200");
        userRestBody.setMsg("Thanh cong");
        return userRestBody;
    }
}
