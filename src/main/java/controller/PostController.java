package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import service.PostService;

/**
 * Created by linhtran on 07/05/2017.
 */

@Component
public class PostController {

    @Autowired
    PostService postService;
    @RequestMapping(value = "/view")
    public  String  write()
    {
        System.out.println(postService.getPost(4,8));
        return "view";
    }

}
