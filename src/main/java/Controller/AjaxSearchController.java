package Controller;

import Entities.Post;
import Entities.User;
import JsonViews.Views;
import Model.PostRestBody;
import Model.UserRestBody;
import Service.PostService;
import Service.UserService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by linhtran on 18/05/2017.
 */

@RestController
public class AjaxSearchController {
    @Autowired
    UserService userService;

    @Autowired
    PostService postService;


    @JsonView(Views.Public.class)
    @RequestMapping(value = "/search-user")
    public UserRestBody getUserSearch(@RequestBody UserRestBody userRestBody)
    {
        List<User> list=userService.getAllUser("select * from user where user_name like '%"+userRestBody.getMsg()+"%'");
        userRestBody.setUserList(list);
        System.out.println(list.size()+"----------------getUserSearch");
        return  userRestBody;
    }

    @JsonView(Views.Public.class)
    @RequestMapping(value = "/search-all-post")
    public PostRestBody getPostSearch(@RequestBody PostRestBody postRestBody)
    {
        List<Post> list;
        if(postRestBody.getMsg().trim().equals(""))
        {
            list=postService.getAllPost("select * from post order by time_post limit 0,10");
            postRestBody.setPosts(list);
            return postRestBody;
        }
        list=postService.getAllPost("select * from post where title like '%"+postRestBody.getMsg()+"%'");
        postRestBody.setPosts(list);
        System.out.println(list.size()+"----------------getPostSearch");
        return  postRestBody;
    }

    @JsonView(Views.Public.class)
    @RequestMapping(value = "/search-all-post-approve")
    public PostRestBody getPostAprroveSearch(@RequestBody PostRestBody postRestBody)
    {
        List<Post> list;
        if(postRestBody.getMsg().trim().equals(""))
        {
            list=postService.getAllPost("select * from post where approve=0 order by time_post limit 0,10");
            postRestBody.setPosts(list);
            return postRestBody;
        }
        list=postService.getAllPost("select * from post where approve=0 and title like '%"+postRestBody.getMsg()+"%'");
        postRestBody.setPosts(list);
        System.out.println(list.size()+"----------------getPostSearch");
        return  postRestBody;
    }
}
