package controller;

import entities.Post;
import exceptions.AccessDenieException;
import exceptions.NotFindException;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import service.PostService;
import service.PostSortService;
import service.RequestService;
import service.UserService;
import utils.number.NumberViewSort;
import utils.sort.PortSort;
import utils.sort.SortType;
import utils.string.StringSessionUtil;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private     RequestService requestService;

    @Autowired
    private UserService userService;

    @RequestMapping("/manager-post")
    public  String managerPost(HttpServletRequest request, ModelMap modelMap,
                               @RequestParam(value = "page",required = false) String pageRequest) {
        List<Post> postList;

        int page = NumberUtils.toInt(pageRequest,1);

        postList = this.postSortService.getAllPost(request,(page-1)* NumberViewSort.getNumberView(),NumberViewSort.getNumberView());

        this.requestService.setResponse(modelMap,postList,this.postService.findAll(Post.class,"post").size(),page);
        return "manager-post";
    }


    @RequestMapping("/manager-post-delete")
    public  String managerPostDelete(HttpServletRequest request,@RequestParam(value = "page",required = false) String pageRequest,ModelMap modelMap,
                                     @RequestParam(value = "id",required = false)String id) throws NotFindException, AccessDenieException {

        this.userService.checkRole(id, (String) request.getSession().getAttribute("username"));

        int page = NumberUtils.toInt(pageRequest,1);
        this.postService.delete(Integer.valueOf(id));
        List<Post> postList = this.postSortService.getAllPost(request,(page-1)* NumberViewSort.getNumberView(),NumberViewSort.getNumberView());
        this.requestService.setResponse(modelMap,postList,this.postService.findAll(Post.class,"post").size(),page);
        modelMap.addAttribute("error",RequestService.DELETE_SUCCESS);
        return "manager-post";
    }



    @RequestMapping(value = "/manager-post-search",method = RequestMethod.GET)
    public  String searchTableAllPost(HttpServletRequest request,ModelMap modelMap,
                                      @RequestParam(value = "page",required = false)String pageRequest,
                                      @RequestParam(value ="query_search",required = false) String querySearch) {

        int page = NumberUtils.toInt(pageRequest,1);

        SortType sortType=this.portSort.getCurrentSortType(request,StringSessionUtil.CURRENT_ALL_POST);
        List<Post> postList;


        postList=this.postService.getAllByTitle(sortType,querySearch,(page-1)*NumberViewSort.getNumberView());
        this.requestService.setResponse(modelMap,postList,this.postService.getCountAllByTitle(querySearch),page,null,null,querySearch);
        return "manager-post";
    }

}
