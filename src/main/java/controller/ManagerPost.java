package controller;

import entities.Post;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.PostService;
import utils.number.NumberViewSort;
import utils.page.DefaultPage;
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
    DefaultPage defaultPage;

    @Autowired
    PortSort portSort;

    @Autowired
    PostService postService;

    @Autowired
    AdminController adminController;

    @RequestMapping("/manager-post")
    public  String managerPost(HttpServletRequest request, ModelMap modelMap) {
        this.defaultPage.setDaultPage(request);
        List<Post> postList;

        String page = request.getParameter("page");

        if(page == null||page.trim().equals("") || !StringUtils.isNumeric(page)||Integer.valueOf(page )== 0) {
            this.adminController.deletePost(request);
            postList=this.postService.finAll(this.portSort.getQuerySortAllPost(request,0));
            this.adminController.setListPost(modelMap,postList);
            request.setAttribute("page",1);
            return "manager-post";
        }

        this.adminController.deletePost(request);
        postList = this.postService.finAll(this.portSort.getQuerySortAllPost(request,(Integer.valueOf(page)-1)* NumberViewSort.NUMBER_VIEW));
        this.adminController.setListPost(modelMap,postList);
        request.setAttribute("page", Integer.valueOf(page));
        return "manager-post";
    }

    @RequestMapping(value = "/manager-post-search",method = RequestMethod.GET)
    public  String searchTableAllPost(HttpServletRequest request,ModelMap modelMap) {
        this.defaultPage.setDaultPage(request);
        String page=request.getParameter("page");
        String querySearch=request.getParameter("query_search");
        SortType sortType=this.portSort.getCurrentSortType(request, StringSessionUtil.POST_ALL_TYPE_SORT,StringSessionUtil.CURRENT_ALL_POST);
        List<Post> postList;

        if(sortType == null) {
            sortType=new SortType();
        }

        if(page == null||page.trim().equals("") || !StringUtils.isNumeric(page) || Integer.valueOf(page)==0 || querySearch == null || querySearch.trim().equals("'") || querySearch.trim().equals("")) {

            postList=this.postService.getAllByTitle(sortType,querySearch,0);
            this.adminController.setListPost(modelMap,postList);
            setResultManagerPost(modelMap,querySearch,1);
            return "manager-post";
        }

        postList=this.postService.getAllByTitle(sortType,querySearch, (Integer.valueOf(page)-1)*NumberViewSort.NUMBER_VIEW);
        this.adminController.setListPost(modelMap,postList);
        setResultManagerPost(modelMap,querySearch,Integer.valueOf(page));
        return "manager-post";
    }

    private void setResultManagerPost(ModelMap modelMap,String querySearch,int page)
    {
        modelMap.addAttribute("page",page);
        modelMap.addAttribute("querySearch",querySearch);
        modelMap.addAttribute("totalList",this.postService.getCountAllByTitle(querySearch));
    }
}
