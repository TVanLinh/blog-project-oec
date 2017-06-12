package controller;

import entities.Configuration;
import exceptions.NotFindException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import service.ConfigurationService;
import service.RequestService;
import utils.page.DefaultPages;
import vadilator.ConfigFormValidator;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by linhtran on 06/06/2017.
 */
@Controller
public class ConfigurationController {

    @Autowired
    private     DefaultPages defaultPage;

    @Autowired
    private     ConfigurationService configurationService;

    @Autowired
    private     ConfigFormValidator configFormValidator;

    @Autowired
    private     RequestService requestService;

    @ModelAttribute
    public Configuration initConfig()
    {
        return new Configuration();
    }

    @RequestMapping(value = "/configuration")
    public  String configuration(HttpServletRequest request,ModelMap modelMap) {
        if(request.getSession().getAttribute(requestService.SUCCESS)!=null)
        {
            modelMap.addAttribute(requestService.SUCCESS,request.getSession().getAttribute(requestService.SUCCESS));
            request.getSession().removeAttribute(requestService.SUCCESS);
        }
        modelMap.addAttribute("conf",this.configurationService.getAllConfiguration().get(0));
        return "configuration";
    }

    @RequestMapping("/processConfiguration")
    public  String processConfiguration(HttpServletRequest request,
                                        ModelMap modelMap,@ModelAttribute(value = "configuration")Configuration conf,
                                        BindingResult bindingResult
                                        ) throws NotFindException {
        this.configFormValidator.validate(conf, bindingResult);

        if (bindingResult.hasErrors()) {
            modelMap.addAttribute("errors", this.configFormValidator.getCodeErrors(bindingResult));
            modelMap.addAttribute("conf",conf);
            return "configuration";
        }

        Configuration configuration = this.configurationService.getAllConfiguration().get(0);

        if (configuration == null){
            throw  new NotFindException("Have not config");
        }

        configuration.setNumberViewPost(conf.getNumberViewPost());
        configuration.setWebTitle(conf.getWebTitle());
        configuration.setDateFormat(conf.getDateFormat());
        this.configurationService.save(configuration);

        request.getSession().setAttribute(requestService.SUCCESS,"request.update_success");
        this.defaultPage.setDaultPage(request);
        return "redirect:/configuration";
    }

}
