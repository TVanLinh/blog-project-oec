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
import service.RequestService;
import utils.number.NumberViewSort;
import utils.page.DefaultPage;
import utils.sort.PortSort;
import utils.sort.SortType;
import utils.string.StringSessionUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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
    DefaultPage defaultPage;

    @Autowired
    PortSort portSort;

    @Autowired
    PostService postService;

    @Autowired
    RequestService requestService;

    @RequestMapping(value = "/admin**", method = RequestMethod.GET)
    public ModelAndView adminPage(HttpServletRequest request, ModelMap modelMap, @RequestParam(value = "page",required = false) String pageRequest) {
        this.defaultPage.setDaultPage(request);

        ModelAndView model = new ModelAndView();
        List<Post> postList;
        int  page= NumberUtils.toInt(pageRequest,1);

        deletePost(request);
        aprrovePost(request);

        postList=this.postService.finAll(this.portSort.getQuerySortAllPostAprrove(request,(page-1)*NumberViewSort.getNumberView(),true));
        this.requestService.setPostList(modelMap,postList,this.postService.getCountNotApprove(),page,"admin",null);
        model.setViewName("admin");
        return model;
    }


    @RequestMapping(value = "/admin-search-post-approve",method = RequestMethod.GET)
    public  String searchTableApprovePost(HttpServletRequest request, ModelMap modelMap, @RequestParam(value = "page",required = false) String pageRequest,
                                          @RequestParam(value = "query_search",required = false)String querySearch) {
        this.defaultPage.setDaultPage(request);

        SortType sortType=this.portSort.getCurrentSortType(request, StringSessionUtil.POST_APPROVE_TYPE_SORT,StringSessionUtil.CURRENT_APPROVE_POST);
        List<Post> postList;

        int page = NumberUtils.toInt(pageRequest,1);
        postList=this.postService.getContainsTitle(sortType,querySearch,0,(page-1)*NumberViewSort.getNumberView());

        this.requestService.setPostList(modelMap,postList,this.postService.getCountContainsTitle(querySearch,0),page,"admin",null,querySearch);
        return "admin";
    }

    protected void  setResultConfig(ModelMap modelMap,String err)
    {
        modelMap.addAttribute("error",err);
        modelMap.addAttribute("conf",this.configurationService.getAllConfiguration().get(0));
    }


    protected  void setListPost(ModelMap modelMap,List<Post> postList) {
        if(postList == null) {
            postList = new ArrayList<Post>();
        }
        modelMap.addAttribute("postList",postList);
        modelMap.addAttribute("totalList",this.postService.getCount());
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
