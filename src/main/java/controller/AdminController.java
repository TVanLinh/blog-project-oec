package controller;

import entities.Configuration;
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

    @RequestMapping(value = "/configuration")
    public  String configurarion(HttpServletRequest request) {
        this.defaultPage.setDaultPage(request);
        if(request.getSession().getAttribute("error")!=null)
        {
            request.setAttribute("error",request.getSession().getAttribute("error"));
            request.getSession().removeAttribute("error");
        }
        request.setAttribute("conf",this.configurationService.getAllConfiguration().get(0));
        return "configuration";
    }

    @RequestMapping("/processConfigurarion")
    public  String processConfigurarion(HttpServletRequest request,ModelMap modelMap) {
        this.defaultPage.setDaultPage(request);

        String title=request.getParameter("titleBlog");
        String formatTime =request.getParameter("formatTime");
        String numberPost =request.getParameter("numberPost");
        System.out.println(title+"  \t"+formatTime+"\t"+numberPost);

        if(title == null || formatTime == null || numberPost == null || !utils.string.StringUtils.checkVid(title)) {
            this.setResultConfig(modelMap,"Not valid!");
            return "configuration";
        }

        if(title.trim().equals("") || formatTime.trim().equals("") || numberPost.trim().equals("")) {
            this.setResultConfig(modelMap,"Title ,format time not valid!");
            return "configuration";
        }

        if(Integer.valueOf(numberPost)<0) {
            this.setResultConfig(modelMap,"Number Post must great than 0.!");
            return "configuration";
        }

        Configuration configuration = this.configurationService.getAllConfiguration().get(0);

        try {
           int result = 0;
           if(configuration == null)
           {
               configuration = new Configuration();
           }else {
               result=1;
           }

           configuration.setNumberViewPost(Integer.valueOf(numberPost));
           configuration.setWebTitle(title);
           configuration.setDateFormat(formatTime);

           if(result == 0) {
               this.configurationService.save(configuration);
           }
           if(result == 1) {
               this.configurationService.save(configuration);
               this.defaultPage.setDaultPage(request);
           }
            this.setResultConfig(modelMap,"Successfully !");
            request.getSession().setAttribute("error","Successfully !");
       }catch (Exception e)
       {
           return "configuration";
       }
        return "redirect:/configuration";
    }

    private void  setResultConfig(ModelMap modelMap,String err)
    {
        modelMap.addAttribute("error",err);
        modelMap.addAttribute("conf",this.configurationService.getAllConfiguration().get(0));
    }

    @RequestMapping("/manager-post")
    public  String managerPost(HttpServletRequest request,ModelMap modelMap) {
        this.defaultPage.setDaultPage(request);
        List<Post> postList;

        String page = request.getParameter("page");

         if(page == null||page.trim().equals("") || !StringUtils.isNumeric(page)||Integer.valueOf(page )== 0) {
                deletePost(request);
                postList=this.postService.finAll(this.portSort.getQuerySortAllPost(request,0));
                setListPost(modelMap,postList);
                request.setAttribute("page",1);
                return "manager-post";
         }

        deletePost(request);
        postList = this.postService.finAll(this.portSort.getQuerySortAllPost(request,(Integer.valueOf(page)-1)*NumberViewSort.NUMBER_VIEW));
        setListPost(modelMap,postList);
        request.setAttribute("page", Integer.valueOf(page));
        return "manager-post";
    }

    @RequestMapping(value = "/manager-post-search",method = RequestMethod.GET)
    public  String searchTableAllPost(HttpServletRequest request,ModelMap modelMap) {
        this.defaultPage.setDaultPage(request);
        String page=request.getParameter("page");
        String querySearch=request.getParameter("query_search");
        SortType sortType=this.portSort.getCurrentSortType(request,StringSessionUtil.POST_ALL_TYPE_SORT,StringSessionUtil.CURRENT_ALL_POST);
        List<Post> postList;

        if(sortType == null) {
            sortType=new SortType();
        }

        if(page == null||page.trim().equals("") || !StringUtils.isNumeric(page) || Integer.valueOf(page)==0 || querySearch == null || querySearch.trim().equals("'") || querySearch.trim().equals("")) {

            postList=this.postService.getAllByTitle(sortType,querySearch,0);
            setListPost(modelMap,postList);
            setResultManagerPost(modelMap,querySearch,1);
            return "manager-post";
        }

        postList=this.postService.getAllByTitle(sortType,querySearch, (Integer.valueOf(page)-1)*NumberViewSort.NUMBER_VIEW);
        setListPost(modelMap,postList);
        setResultManagerPost(modelMap,querySearch,Integer.valueOf(page));
        return "manager-post";
    }

    private void setResultManagerPost(ModelMap modelMap,String querySearch,int page)
    {
        modelMap.addAttribute("page",page);
        modelMap.addAttribute("querySearch",querySearch);
        modelMap.addAttribute("totalList",this.postService.getCountAllByTitle(querySearch));
    }

    private void setListPost(ModelMap modelMap,List<Post> postList) {
        if(postList == null) {
            postList = new ArrayList<Post>();
        }
        modelMap.addAttribute("postList",postList);
        modelMap.addAttribute("totalList",this.postService.getCount());
    }

    private void deletePost(HttpServletRequest request) {
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

    public void aprrovePost(HttpServletRequest request)
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
