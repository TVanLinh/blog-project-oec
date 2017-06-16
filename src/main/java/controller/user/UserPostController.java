package controller.user;

import entities.Post;
import entities.User;
import forms.UserForm;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.PostService;
import service.PostSortService;
import service.RequestService;
import service.UserService;
import utils.session.SessionUtils;
import utils.sort.PortSort;
import utils.sort.SortType;
import utils.string.StringSessionUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

/**
 * Created by linhtran on 06/05/2017.
 */

@Controller
public class UserPostController {

    @Autowired
    private     UserService userService;

    @Autowired
    private     PostService postService;

    @Autowired
    private     PortSort portSort;

    @Autowired
    private     PostSortService postSortSerVice;

    @ModelAttribute
    public UserForm initUserForm(){
        UserForm userForm =  new UserForm();
        return userForm;
    }

    @RequestMapping(value = "/user")
    public  String userPage(Principal principal, HttpServletRequest request,
                            ModelMap modelMap,
                            @RequestParam(value = "page", required = false) String pageRequest,
                            @RequestParam(value = "number", required = false) String numberView) {

        int limit = this.postService.getLimit(numberView);
        modelMap.addAttribute("userDAO", this.userService);
        List<Post> postList;
        User user = userService.getUserByName(principal.getName());
        int page = NumberUtils.toInt(pageRequest,1);

        SortType sortType = this.portSort.getSortType(request,StringSessionUtil.CURRENT_POST_ALL_TYPE_SORT_BY_USER,"title");
        postList = postSortSerVice.getAllPostByUser(sortType, user, (page - 1) * limit, limit);
        RequestService.setResponse(modelMap, limit, postList, this.postService.getCountById(user.getId()));
        modelMap.addAttribute("typeOrder", sortType.typeOrder);
        return "author";
    }

    @RequestMapping(value = "/user-delete-post")
    public  String deletePost(HttpServletRequest request, @RequestParam(value = "page",required = false) String pageRequest,
                              @RequestParam(value = "id",required = false)String id,
                                RedirectAttributes redirectAttributes) {
        HttpSession session =request.getSession();
        User user = (User) session.getAttribute(SessionUtils.USER_LOGIN);
        try {
            this.postService.delete(id, user.getUserName());
            RequestService.setResponse(redirectAttributes,pageRequest,RequestService.DELETE_SUCCESS);
        }catch (Exception  ex) {
            RequestService.setResponse(redirectAttributes,pageRequest,RequestService.POST_DELETE_NOT_SUCCESS);
        }
        return "redirect:/user";
    }

    @RequestMapping(value = "/user-post-search",method = RequestMethod.GET)
    public String searchTableAllPost(HttpServletRequest request, ModelMap modelMap, @RequestParam(value = "page", required = false) String pageRequest,
                                     @RequestParam(value = "query_search", required = false) String querySearch,
                                     @RequestParam(value = "number", required = false) String numberView) {

        int limit = this.postService.getLimit(numberView);
        SortType sortType=this.portSort.getCurrentSortType(request,StringSessionUtil.CURRENT_POST_ALL_TYPE_SORT_BY_USER);
        User user = (User) request.getSession().getAttribute(SessionUtils.USER_LOGIN);
        int page = NumberUtils.toInt(pageRequest,1);
        List<Post> postLists = this.postService.getPostByIdUser(sortType, querySearch, user.getId(), (page - 1) * limit, limit);
        RequestService.setResponse(modelMap, limit, postLists, this.postService.getCountByIdUser(user.getId(), querySearch));
        modelMap.addAttribute("typeOrder", sortType.typeOrder);
        return "author";
    }


    @RequestMapping(value = "/change-pass-word",method = RequestMethod.GET)
    public String pageChangePassWord(HttpServletRequest request )
    {
        request.setAttribute("user", request.getSession().getAttribute(SessionUtils.USER_LOGIN));
        return "change-pass-word";
    }

}
