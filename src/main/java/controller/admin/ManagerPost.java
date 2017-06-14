package controller.admin;

import entities.Post;
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
        List<Post> postList = this.postSortService.getAllPost(request, (page - 1) * limit, limit);

        RequestService.setResponse(modelMap, limit, postList, this.postService.findAll(Post.class, "post").size());
        return "manager-post";
    }


    @RequestMapping("/manager-post-delete")
    public  String managerPostDelete(HttpServletRequest request, @RequestParam(value = "page",required = false) String pageRequest,
                                     @RequestParam(value = "id",required = false)String id,
                                     RedirectAttributes redirectAttributes) throws NotFindException {

        HttpSession session = request.getSession();
        try {
            this.postService.delete(id, (String) session.getAttribute("username"));
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

        System.out.println("--------------------" + limit + "---------------------" + postList.size());
        return "manager-post";
    }

}
