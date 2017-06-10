package controller;

import entities.Post;
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
import utils.number.NumberViewSort;
import utils.page.DefaultPages;
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
    DefaultPages defaultPage;

    @Autowired
    PortSort portSort;

    @Autowired
    PostService postService;

    @Autowired
    AdminController adminController;

    @Autowired
    PostSortService postSortService;

    @Autowired
    RequestService requestService;

    @RequestMapping("/manager-post")
    public  String managerPost(HttpServletRequest request, ModelMap modelMap, @RequestParam(value = "page",required = false) String pageRequest) {
        this.defaultPage.setDaultPage(request);
        List<Post> postList;

        int page = NumberUtils.toInt(pageRequest,1);

        this.adminController.deletePost(request);
        postList = this.postSortService.getAllPost(request,(page-1)* NumberViewSort.getNumberView(),NumberViewSort.getNumberView());

        this.requestService.setResponse(modelMap,postList,this.postService.findAll(Post.class,"post").size(),page);
        return "manager-post";
    }

    @RequestMapping(value = "/manager-post-search",method = RequestMethod.GET)
    public  String searchTableAllPost(HttpServletRequest request,ModelMap modelMap,
                                      @RequestParam(value = "page",required = false)String pageRequest,
                                      @RequestParam(value ="query_search",required = false) String querySearch) {
        this.defaultPage.setDaultPage(request);

        int page = NumberUtils.toInt(pageRequest,1);

        SortType sortType=this.portSort.getCurrentSortType(request,StringSessionUtil.CURRENT_ALL_POST);
        List<Post> postList;


        postList=this.postService.getAllByTitle(sortType,querySearch,(page-1)*NumberViewSort.getNumberView());

        this.requestService.setResponse(modelMap,postList,this.postService.getCountAllByTitle(querySearch),page,null,null,querySearch);
        return "manager-post";
    }

}
