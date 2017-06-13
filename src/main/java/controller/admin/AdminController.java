package controller.admin;

import entities.Post;
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
import utils.number.NumberViewSort;
import utils.sort.PortSort;
import utils.sort.SortType;
import utils.string.StringSessionUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by linhtran on 11/05/2017.
 */

@Controller
public class AdminController
{

    @Autowired
    private     PortSort portSort;

    @Autowired
    private     PostService postService;

    @Autowired
    private     PostSortService postSortSerVice;


    @RequestMapping(value = "/admin**", method = RequestMethod.GET)
    public String adminPage(HttpServletRequest request, ModelMap modelMap,
                                  @RequestParam(value = "page",required = false) String pageRequest) {
        List<Post> postList;
        int  page= NumberUtils.toInt(pageRequest,1);
        SortType sortType = this.portSort.getSortType(request,StringSessionUtil.CURRENT_APPROVE_POST,"title");
        postList  = this.postSortSerVice.getAllPostNotApprove(sortType, (page-1)*NumberViewSort.getNumberView(),NumberViewSort.getNumberView());
        RequestService.setResponse(modelMap,NumberViewSort.getNumberView(),postList,this.postService.getCountNotApprove());
        return "admin";
    }


    @RequestMapping(value = "/admin-delete-post")
    public  String deletePost(HttpServletRequest request,@RequestParam(value = "page",required = false) String pageRequest,
                              @RequestParam(value = "id",required = false)String id,
                              RedirectAttributes redirectAttributes) {
        HttpSession session =request.getSession();
        try {
            this.postService.delete(id, (String) session.getAttribute("username"));
            RequestService.setResponse(redirectAttributes,pageRequest,RequestService.DELETE_SUCCESS);
        }catch (Exception  ex) {
            RequestService.setResponse(redirectAttributes,pageRequest,RequestService.POST_DELETE_NOT_SUCCESS);
        }
        return "redirect:/admin";
    }

    @RequestMapping(value = "/admin-approve-post")
    public  String approvePost(HttpServletRequest request, @RequestParam(value = "page",required = false) String pageRequest, ModelMap modelMap,
                               @RequestParam(value = "id",required = false)String id,
                               RedirectAttributes redirectAttributes){
        try{
             if(!this.postService.approvePost(id, (String) request.getSession().getAttribute("username"))){
                 RequestService.setResponse(redirectAttributes,pageRequest,RequestService.POST_APPROVED);
             }else {
                RequestService.setResponse(redirectAttributes,pageRequest,RequestService.POST_APPROVE_SUCCESS);
             }
        }catch (Exception ex){
            RequestService.setResponse(redirectAttributes,pageRequest,RequestService.POST_APPROVE_NOT_SUCCESS);
        }
        return "redirect:/admin";
    }

    @RequestMapping(value = "/admin-search-post-approve",method = RequestMethod.GET)
    public  String searchTableApprovePost(HttpServletRequest request, ModelMap modelMap, @RequestParam(value = "page",required = false) String pageRequest,
                                          @RequestParam(value = "query_search",required = false)String querySearch) {
        SortType sortType=this.portSort.getCurrentSortType(request,StringSessionUtil.CURRENT_APPROVE_POST);
        List<Post> postList;
        int page = NumberUtils.toInt(pageRequest,1);
        postList=this.postService.getContainsTitle(sortType,querySearch,0,(page-1)*NumberViewSort.getNumberView());
        RequestService.setResponse(modelMap,NumberViewSort.getNumberView(),postList,this.postService.getCountContainsTitle(querySearch,0));
        return "admin";
    }


}
