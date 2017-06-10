package controller;

import entities.Post;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import service.ConfigurationService;
import service.PostService;
import service.PostSortService;
import service.RequestService;
import utils.number.NumberViewSort;
import utils.page.DefaultPages;
import utils.sort.PortSort;
import utils.sort.SortType;
import utils.string.StringSessionUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by linhtran on 11/05/2017.
 */

@Controller
public class AdminController
{

    @Autowired
    ConfigurationService configurationService;

    @Autowired
    DefaultPages defaultPage;

    @Autowired
    PortSort portSort;

    @Autowired
    PostService postService;

    @Autowired
    RequestService requestService;

    @Autowired
    PostSortService postSortSerVice;


    @RequestMapping(value = "/admin**", method = RequestMethod.GET)
    public ModelAndView adminPage(HttpServletRequest request, ModelMap modelMap, @RequestParam(value = "page",required = false) String pageRequest) {
        this.defaultPage.setDaultPage(request);

        ModelAndView model = new ModelAndView();
        List<Post> postList;
        int  page= NumberUtils.toInt(pageRequest,1);

        deletePost(request);
        aprrovePost(request);

         postList  = this.postSortSerVice.getAllPostNotApprove(request, (page-1)*NumberViewSort.getNumberView(),NumberViewSort.getNumberView());
        this.requestService.setResponse(modelMap,postList,this.postService.getCountNotApprove(),page,"admin",null);
        model.setViewName("admin");
        return model;
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

    protected  void aprrovePost(HttpServletRequest request)
    {
        String action = request.getParameter("action");
        String id = request.getParameter("id");
        if(action != null && action.equals("approve")) {
            Date date;
            Calendar calendar=Calendar.getInstance();
            Post post;
            date = calendar.getTime();
            post = this.postService.find(Integer.valueOf(id));
            if(post != null) {

                post.setApprovedTime(date);
                post.setApprove(1);
                this.postService.save(post);
            }
        }
    }


}
