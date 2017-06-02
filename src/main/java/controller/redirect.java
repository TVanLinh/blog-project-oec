package controller;

import dao.ConfigurationDAO;
import dao.PostDAO;
import dao.UserDAO;
import entities.Post;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import utils.page.DefaultPage;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by linhtran on 06/05/2017.
 */
@Controller
public class redirect {
    final static Logger logger = Logger.getLogger(ProcessPost.class);

    @Autowired
    UserDAO userDAO;


    @Autowired
    PostDAO postDAO;

    @Autowired
    ConfigurationDAO  configDAO;

    @Autowired
    DefaultPage defaultPage;

    @RequestMapping(value = "/write")
    public  String viewWriter(HttpServletRequest request) {
        this.defaultPage.setDaultPage(request);
        request.setAttribute("active","write");
        return "write";
    }

    @RequestMapping(value={"/","/home"})
    public String homePage(HttpServletRequest request) {
        this.defaultPage.setDaultPage(request);
        String page=request.getParameter("page");
        List<Post> postList;

        int limit = this.configDAO.getAllConfiguration().get(0).getNumberViewPost();
        request.setAttribute("userDAO",this.userDAO);
        if(page == null|| !StringUtils.isNumeric(page) || page.trim().equals("")) {
            postList = this.postDAO.getPost(0,limit);
            setResponeHome(request,postList,1);
            return "home";
        }

        postList = this.postDAO.getPost((Integer.valueOf(page)-1)*limit,limit);
        setResponeHome(request,postList,Integer.valueOf(page));
        request.setAttribute("page",Integer.valueOf(page));
        return "home";
    }

    private void setResponeHome(HttpServletRequest request,List<Post> postList,int page)
    {
        request.setAttribute("page",page);
        request.setAttribute("postList",postList);
        request.setAttribute("totalList",this.postDAO.getAllPostPublic().size());
        request.setAttribute("limit",this.configDAO.getAllConfiguration().get(0).getNumberViewPost());
        request.setAttribute("active","home");
    }

    @RequestMapping(value = "/post")
    public  String viewPost(HttpServletRequest request) {
        this.defaultPage.setDaultPage(request);

        String id = request.getParameter("id");
        List<Post> postSlideBar = this.postDAO.getAllPost("select * from post  where status=1 and approve=1 order by time_post desc limit 0,5");
        request.setAttribute("postSlideBar",postSlideBar);
        if(id != null) {
            try {
                Post post=this.postDAO.find(Integer.valueOf(id));
                if(post != null) {
                    post.setNumberView(post.getNumberView()+1);
                    this.postDAO.update(post);
                    request.setAttribute("post",post);
                }
                request.setAttribute("active","post");
                return "post";
            }catch (Exception e) {
                return "/home";
            }
        }
        request.setAttribute("active","home");
        return "/home";
    }

    @RequestMapping(value = "/tanso")
    public String tanso(HttpServletRequest request)
    {
        this.defaultPage.setDaultPage(request);
        System.out.println(this.postDAO.getStatisticByMonth());
        return "tanso";
    }

}
