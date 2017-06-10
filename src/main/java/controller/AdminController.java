package controller;

import entities.Post;
import exceptions.AccessDenieException;
import exceptions.NotFindException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import service.PostService;
import service.PostSortService;
import service.RequestService;
import service.UserService;
import utils.number.NumberViewSort;
import utils.page.DefaultPages;
import utils.sort.PortSort;
import utils.sort.SortType;
import utils.string.StringSessionUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by linhtran on 11/05/2017.
 */

@Controller
public class AdminController
{

    @Autowired
    private     DefaultPages defaultPage;

    @Autowired
    private     PortSort portSort;

    @Autowired
    private     PostService postService;

    @Autowired
    private     RequestService requestService;

    @Autowired
    private     PostSortService postSortSerVice;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/admin**", method = RequestMethod.GET)
    public ModelAndView adminPage(HttpServletRequest request, ModelMap modelMap,
                                  @RequestParam(value = "page",required = false) String pageRequest)
                                    throws AccessDenieException, NotFindException {

        this.defaultPage.setDaultPage(request);

        ModelAndView model = new ModelAndView();
        List<Post> postList;
        int  page= NumberUtils.toInt(pageRequest,1);

        postList  = this.postSortSerVice.getAllPostNotApprove(request, (page-1)*NumberViewSort.getNumberView(),NumberViewSort.getNumberView());
        this.requestService.setResponse(modelMap,postList,this.postService.getCountNotApprove(),page,"admin",null);
        model.setViewName("admin");
        return model;
    }


    @RequestMapping(value = "/admin-delete-post")
    public  String deletePost(HttpServletRequest request,@RequestParam(value = "page",required = false) String pageRequest,ModelMap modelMap,
                              @RequestParam(value = "id",required = false)String id) throws NotFindException, AccessDenieException {

        this.userService.checkRole(id, (String) request.getSession().getAttribute("username"));

        int page = NumberUtils.toInt(pageRequest,1);
        this.postService.delete(Integer.valueOf(id));
        List<Post> postList  = this.postSortSerVice.getAllPostNotApprove(request, (page-1)*NumberViewSort.getNumberView(),NumberViewSort.getNumberView());
        this.requestService.setResponse(modelMap,postList,this.postService.getCountNotApprove(),page,"admin",null);
        return "admin";
    }



    @RequestMapping(value = "/admin-approve-post")
    public  String approvePost(HttpServletRequest request,@RequestParam(value = "page",required = false) String pageRequest,ModelMap modelMap,
                              @RequestParam(value = "id",required = false)String id) throws NotFindException, AccessDenieException {

        this.userService.checkRole(id, (String) request.getSession().getAttribute("username"));

        int page = NumberUtils.toInt(pageRequest,1);
        this.postService.approvePost(Integer.valueOf(id));
        List<Post> postList  = this.postSortSerVice.getAllPostNotApprove(request, (page-1)*NumberViewSort.getNumberView(),NumberViewSort.getNumberView());
        this.requestService.setResponse(modelMap,postList,this.postService.getCountNotApprove(),page,"admin",null);
        return "admin";
    }


    @RequestMapping(value = "/admin-search-post-approve",method = RequestMethod.GET)
    public  String searchTableApprovePost(HttpServletRequest request, ModelMap modelMap, @RequestParam(value = "page",required = false) String pageRequest,
                                          @RequestParam(value = "query_search",required = false)String querySearch) {
        this.defaultPage.setDaultPage(request);

        SortType sortType=this.portSort.getCurrentSortType(request,StringSessionUtil.CURRENT_APPROVE_POST);
        List<Post> postList;

        int page = NumberUtils.toInt(pageRequest,1);
        postList=this.postService.getContainsTitle(sortType,querySearch,0,(page-1)*NumberViewSort.getNumberView());

        this.requestService.setResponse(modelMap,postList,this.postService.getCountContainsTitle(querySearch,0),page,"admin",null,querySearch);
        return "admin";
    }

    protected  void deletePost(HttpServletRequest request) {
        String action = request.getParameter("action");
        String id  = request.getParameter("id");

        if(action != null &&action.equals("delete")) {
            if(id != null && StringUtils.isNumeric(id)) {
                if(this.postService.find(Integer.valueOf(id)) != null) {
                    this.postService.delete(Integer.valueOf(id));
                }
            }
        }
    }

}
