package controller.admin;

import entities.Post;
import entities.User;
import exceptions.NotFindException;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.PostService;
import service.PostSortService;
import service.RequestService;
import utils.session.SessionUtils;
import utils.sort.PortSort;
import utils.sort.SortType;
import utils.string.StringSessionUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by linhtran on 06/06/2017.
 */
@Controller
public class ManagerPost {

    @Autowired
    private     PortSort portSort;

    @Autowired
    private     PostService postService;


    @Autowired
    private     PostSortService postSortService;

    @RequestMapping("/manager-post")
    public  String managerPost(HttpServletRequest request, ModelMap modelMap,
                               @RequestParam(value = "page", required = false) String pageRequest,
                               @RequestParam(value = "number", required = false) String numberView) {

        int page = NumberUtils.toInt(pageRequest,1);
        int limit = this.postService.getLimit(numberView);
        SortType sortType = this.portSort.getSortType(request, StringSessionUtil.CURRENT_ALL_POST, "user_name");
        List<Post> postList = this.postSortService.getAllPost(sortType, (page - 1) * limit, limit);

        RequestService.setResponse(modelMap, limit, postList, this.postService.getCount());
        modelMap.addAttribute("typeOrder", sortType.typeOrder);
        return "manager-post";
    }


    @RequestMapping("/manager-post-delete")
    public  String managerPostDelete(HttpServletRequest request, @RequestParam(value = "page",required = false) String pageRequest,
                                     @RequestParam(value = "id",required = false)String id,
                                     RedirectAttributes redirectAttributes) throws NotFindException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(SessionUtils.USER_LOGIN);
        try {
            this.postService.delete(id, user.getUserName());
            RequestService.setResponse(redirectAttributes,pageRequest,RequestService.DELETE_SUCCESS);
        }catch (Exception  ex) {
            RequestService.setResponse(redirectAttributes,pageRequest,RequestService.POST_DELETE_NOT_SUCCESS);
        }
        return "redirect:/manager-post";
    }



    @RequestMapping(value = "/manager-post-search",method = RequestMethod.GET)
    public  String searchTableAllPost(HttpServletRequest request, ModelMap modelMap,
                                      @RequestParam(value = "page",required = false)String pageRequest,
                                      @RequestParam(value = "query_search", required = false) String querySearch,
                                      @RequestParam(value = "number", required = false) String numberView) {

        int page = NumberUtils.toInt(pageRequest,1);
        int limit = this.postService.getLimit(numberView);

        SortType sortType = this.portSort.getCurrentSortType(request,StringSessionUtil.CURRENT_ALL_POST);
        List<Post> postList = this.postService.getAllByTitle(sortType, querySearch, (page - 1) * limit, limit);
        RequestService.setResponse(modelMap, limit, postList, this.postService.getCountAllByTitle(querySearch));
        modelMap.addAttribute("typeOrder", sortType.typeOrder);
        return "manager-post";
    }

}
