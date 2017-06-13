package controller.user;

import com.fasterxml.jackson.annotation.JsonView;
import entities.Post;
import exceptions.NotFindException;
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
    private PostService postService;
    public  static  final String  STATUS_LIKE  = "status_like_post";
    public static  final  String IMAGE_LIKE = "public/asserts/images/like.png";
    public static final String IMAGE_DISLIKE = "public/asserts/images/notlike.png";

    @RequestMapping(value = "/like")
    @JsonView(Views.Public.class)
    public synchronized UserRestBody like(@RequestBody UserRestBody userRestBody, HttpServletRequest request, HttpServletResponse response) throws NotFindException {

        Cookie cookies[] = request.getCookies();
        Cookie cookieLike = null;
        for (Cookie cookie:cookies) {
            if (cookie.getName().equals(STATUS_LIKE)) {
                cookieLike = cookie;
                break;
            }
        }

        Post post;
        post = this.postService.find(userRestBody.getId());
        userRestBody.setStatusImg(IMAGE_LIKE);
        if (cookieLike == null) {
            cookieLike = new Cookie(STATUS_LIKE, userRestBody.getId() + ",");
            post.setNumberLike(post.getNumberLike() + 1);
        } else if (!CookieUtils.isLike(post.getId(), cookieLike.getValue())) {
            post.setNumberLike(post.getNumberLike() + 1);
            cookieLike.setValue(cookieLike.getValue()+post.getId()+",");
        } else
        {
            cookieLike.setValue(CookieUtils.remove(cookieLike.getValue(),post.getId()));
            post.setNumberLike(post.getNumberLike() - 1);
            userRestBody.setStatusImg(IMAGE_DISLIKE);
        }

        this.postService.save(post);

        userRestBody.setCode("200");
        userRestBody.setMsg("Thanh cong");
        cookieLike.setMaxAge(365*360*24);
        response.addCookie(cookieLike);
        userRestBody.setNumberLike(post.getNumberLike());

        return userRestBody;
    }

}
