package controller;

import entities.Post;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import service.ConfigurationService;
import service.PostService;
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

    @RequestMapping(value = "/admin**", method = RequestMethod.GET)
    public ModelAndView adminPage(HttpServletRequest request,ModelMap modelMap) {
        this.defaultPage.setDaultPage(request);
        ModelAndView model = new ModelAndView();
        List<Post> postList;
        String page=request.getParameter("page");

        if(page == null || page.trim().equals("") || !StringUtils.isNumeric(page) || Integer.valueOf(page) == 0) {
            deletePost(request);
            aprrovePost(request);
            postList=this.postService.finAll(this.portSort.getQuerySortAllPostAprrove(request,0,true));
            this.postService.setListPost(modelMap,postList,this.postService.getCountNotApprove());
            request.setAttribute("page",1);
            model.setViewName("admin");
            request.setAttribute("active","admin");
            return model;
        }

        deletePost(request);
        aprrovePost(request);

        postList=this.postService.finAll(this.portSort.getQuerySortAllPostAprrove(request,(Integer.valueOf(page)-1)* NumberViewSort.NUMBER_VIEW,true));
        this.postService.setListPost(modelMap,postList,this.postService.getCountNotApprove());
        model.setViewName("admin");
        request.setAttribute("page",Integer.valueOf(page));
        request.setAttribute("active","admin");
        return model;
    }


    @RequestMapping(value = "/admin-search-post-approve",method = RequestMethod.GET)
    public  String searchTableApprovePost(HttpServletRequest request,ModelMap modelMap) {
        this.defaultPage.setDaultPage(request);
        String page=request.getParameter("page");
        String querySearch=request.getParameter("query_search");
        SortType sortType=this.portSort.getCurrentSortType(request, StringSessionUtil.POST_APPROVE_TYPE_SORT,StringSessionUtil.CURRENT_APPROVE_POST);
        List<Post> postList;

        if(sortType == null)
        {
            sortType = new SortType();
        }
        if(page == null || page.trim().equals("") || !StringUtils.isNumeric(page) || Integer.valueOf(page) == 0 || querySearch == null || querySearch.trim().equals("'") || querySearch.trim().equals(""))
        {

            postList=this.postService.getContainsTitle(sortType,querySearch,0,0);
            this.setListPost(modelMap,postList);
            setResponseSeacchPostAdmin(modelMap,querySearch,"admin",1);
            return "admin";
        }

        postList=this.postService.getContainsTitle(sortType,querySearch,0,(Integer.valueOf(page)-1)*NumberViewSort.NUMBER_VIEW);
        setListPost(modelMap,postList);
        setResponseSeacchPostAdmin(modelMap,querySearch,"admin",Integer.valueOf(page));
        return "admin";
    }

    public  void setResponseSeacchPostAdmin(ModelMap modelMap,String querySearch,String pageActive,int page)
    {
        modelMap.addAttribute("page",page);
        modelMap.addAttribute("querySearch",querySearch);
        modelMap.addAttribute("totalList",this.postService.getCountContainsTitle(querySearch,0));
        modelMap.addAttribute("active",pageActive);
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
