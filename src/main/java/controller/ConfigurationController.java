package controller;

import entities.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import service.ConfigurationService;
import utils.page.DefaultPage;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by linhtran on 06/06/2017.
 */
@Controller
public class ConfigurationController {
    @Autowired
    AdminController adminController;

    @Autowired
    DefaultPage defaultPage;

    @Autowired
    ConfigurationService configurationService;

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
            this.adminController.setResultConfig(modelMap,"Not valid!");
            return "configuration";
        }

        if(title.trim().equals("") || formatTime.trim().equals("") || numberPost.trim().equals("")) {
            this.adminController.setResultConfig(modelMap,"Title ,format time not valid!");
            return "configuration";
        }

        if(Integer.valueOf(numberPost)<0) {
            this.adminController.setResultConfig(modelMap,"Number Post must great than 0.!");
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
            this.adminController.setResultConfig(modelMap,"Successfully !");
            request.getSession().setAttribute("error","Successfully !");
        }catch (Exception e)
        {
            return "configuration";
        }
        return "redirect:/configuration";
    }

}
