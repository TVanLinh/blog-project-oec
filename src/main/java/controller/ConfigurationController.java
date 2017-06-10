package controller;

import entities.Configuration;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.ConfigurationService;
import utils.page.DefaultPages;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by linhtran on 06/06/2017.
 */
@Controller
public class ConfigurationController {

    @Autowired
    DefaultPages defaultPage;

    @Autowired
    ConfigurationService configurationService;

    @RequestMapping(value = "/configuration")
    public  String configurarion(HttpServletRequest request,ModelMap modelMap) {
        this.defaultPage.setDaultPage(request);
        if(request.getSession().getAttribute("error")!=null)
        {
            modelMap.addAttribute("error",request.getSession().getAttribute("error"));
            request.getSession().removeAttribute("error");
        }
        modelMap.addAttribute("conf",this.configurationService.getAllConfiguration().get(0));
        return "configuration";
    }

    @RequestMapping("/processConfigurarion")
    public  String processConfigurarion(HttpServletRequest request, ModelMap modelMap, @RequestParam(value = "titleBlog" ,required=false) String title,
                                        @RequestParam(value = "formatTime",required = false) String formatTime,
                                        @RequestParam(value = "numberPost",required = false) String numberPost) {
        int numberView = NumberUtils.toInt(numberPost,this.configurationService.getAllConfiguration().get(0).getNumberViewPost());

        if(!utils.string.StringUtils.checkVid(title)) {
            this.setResultConfig(modelMap,"Not valid!");
            return "configuration";
        }

        if(StringUtils.isEmpty(title) ||StringUtils.isBlank(title)|| StringUtils.isEmpty(formatTime) ||StringUtils.isBlank(formatTime ) || !utils.string.StringUtils.checkVid(title)){
            this.setResultConfig(modelMap,"Title or format time  not valid!");
            return "configuration";
        }

        Configuration configuration = this.configurationService.getAllConfiguration().get(0);

        try {
            if(configuration == null) {
                configuration = new Configuration();
            }
            configuration.setNumberViewPost(numberView);
            configuration.setWebTitle(title);
            configuration.setDateFormat(formatTime);
            this.configurationService.save(configuration);

            this.setResultConfig(modelMap,"Successfully !");
            request.getSession().setAttribute("error","Successfully !");
        }catch (IndexOutOfBoundsException e)
        {
            return "configuration";
        }

        this.defaultPage.setDaultPage(request);
        return "redirect:/configuration";
    }

    protected void  setResultConfig(ModelMap modelMap,String err)
    {
        modelMap.addAttribute("error",err);
        modelMap.addAttribute("conf",this.configurationService.getAllConfiguration().get(0));
    }

}
