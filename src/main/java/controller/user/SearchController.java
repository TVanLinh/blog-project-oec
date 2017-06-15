package controller.user;

import entities.Post;
import entities.User;
import exceptions.NotFindException;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.ConfigurationService;
import service.PostService;
import service.RequestService;
import service.UserService;
import utils.sort.SortType;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by linhtran on 19/05/2017.
 */

@Controller
public class SearchController {

    @Autowired
    private     PostService postService;

    @Autowired
    private     ConfigurationService configurationService;

    @Autowired
    private     UserService userService;

    @RequestMapping(value = "/view-search")
    public  String  processSearchAll(ModelMap modelMap,
                                     @RequestParam(value = "page",required = false) String pageRequest,
                                     @RequestParam(value = "query_search", required = false) String query_search) {
        int limit = this.configurationService.getAllConfiguration().get(0).getNumberViewPost();
        int  page = NumberUtils.toInt(pageRequest,1);
        RequestService.setResponse(modelMap, limit, this.postService.getPostPublicByTitle(new SortType(),
                query_search, (page - 1) * limit, limit),
                this.postService.getCountPublicByTitle(query_search));
        return "view-search";
    }

    @RequestMapping(value = "/list-post-by-user")
    public String getPostByUser(HttpServletRequest request,
                                ModelMap modelMap,
                                @RequestParam(value = "username",required = false) String username,
                                @RequestParam(value = "page",required = false) String pageRequest ) throws NotFindException {
        int limit = this.configurationService.getAllConfiguration().get(0).getNumberViewPost();

        modelMap.addAttribute("userDAO",this.userService);

        int  page = NumberUtils.toInt(pageRequest,1);

        User user = this.userService.getUserByName(username);
        if(user == null) {
            throw new NotFindException(NotFindException.USER_NOT_FOUND);
        }

        List<Post> posts;
        int totalList;
        SortType sortType = new SortType();
        sortType.orderBy = "time_post";
        User userCurrent = (User) request.getSession().getAttribute("userLogin");

        if (userCurrent != null && userCurrent.getId() == user.getId()) {
            posts = this.postService.getPostByIdUser(user.getId(), (page - 1) * limit, limit);
            totalList = this.postService.getPostByIdUser(user.getId()).size();
        } else {
            posts = this.postService.getPost(user.getId(), 1, 1, sortType, (page - 1) * limit, limit);
            totalList = this.postService.getCount(user.getId(), 1, 1);
        }
        RequestService.setResponse(modelMap, limit, posts, totalList);
        return  "post-by-user";
    }
}
