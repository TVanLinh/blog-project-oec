package Controller;

import DAO.PostDAO;
import Entities.Configuration;
import Entities.Post;
import JsonViews.Views;
import Model.PostRestBody;
import Model.UserRestBody;
import Service.ConfigurationService;
import Service.PostService;
import Utils.CookieUtils;
import com.fasterxml.jackson.annotation.JsonView;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
;

/**
 * Created by linhtran on 09/05/2017.
 */

@RestController
public class AjaxController {

    @Autowired
    PostService postService;

    @Autowired
    PostDAO postDAO;

    @Autowired
    ConfigurationService configurationService;

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
        String str=CookieUtils.remove(cookieLike.getValue(),post.getId());
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

    @RequestMapping(value = "/get-post")
    @JsonView(Views.Public.class)
    public PostRestBody nextPostSlideBar(@RequestBody PostRestBody postRestBody, HttpServletRequest request)
    {
        Configuration conf=configurationService.find(1);
        int numberPage=postRestBody.getNumberPage();
        postRestBody.setNumberConf(conf.getNumberViewPost());
        if(numberPage<0)
        {
            postRestBody.setPosts(new ArrayList<Post>());
            return postRestBody;
        }
        postRestBody.setPosts(postService.getPost(numberPage*conf.getNumberViewPost(),conf.getNumberViewPost()));
//        System.out.println(postService.getPost(numberPage*conf.getNumberViewPost(),conf.getNumberViewPost()).get(0).getImage().getLink());
        postRestBody.setNumberPage(numberPage+1);
        System.out.println(numberPage);
        return  postRestBody;
    }

    @RequestMapping(value = "/admin-post")
    @JsonView(Views.Public.class)
    public  PostRestBody nextPostAdmin(@RequestBody PostRestBody postRestBody,HttpServletRequest request)
    {
        String action=request.getParameter("action");
        System.out.println(postRestBody.getMsg()+"--------------------");
        String id=request.getParameter("id");
        if(action==null||id==null)
        {
            return postRestBody;
        }
        List<Post> postList;
        Post post;
        try
        {
            Date date;
            Calendar calendar=Calendar.getInstance();
            if(action.equals("approve"))
            {
                date=calendar.getTime();
                System.out.println(postRestBody.getMsg());
                post=postService.find(Integer.valueOf(id));
                if(post!=null)
                {
                    post.setApprovedTime(date);
                    post.setApprove(1);
                    postDAO.update(post);
                    postList =postService.getAllPost("select * from post where approve=0");
                    postRestBody.setNumberApprove(postList.size());
                    postRestBody.setPosts(postList);
                    return postRestBody;
                }

            }

            if(action.equals("delete"))
            {
                postDAO.delete(Integer.valueOf(id));
                postList =postService.getAllPost("select * from post where approve=0");
                postRestBody.setNumberApprove(postList.size());
                postRestBody.setPosts(postList);
                return postRestBody;
            }

        }catch (Exception e)
        {
            Logger.getLogger(this.getClass().getName()).error("Id or action not valid");
            return  postRestBody;
        }
        return postRestBody;
    }
}
