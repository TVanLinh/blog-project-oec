package controller.admin;

import entities.Configuration;
import exceptions.NotFindException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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


    @ModelAttribute
    public Configuration initConfig()
    {
        return new Configuration();
    }

    @RequestMapping(value = "/configuration")
    public  String configuration(HttpServletRequest request,ModelMap modelMap) {
        modelMap.addAttribute("conf",this.configurationService.getAllConfiguration().get(0));
        return "configuration";
    }

    @RequestMapping("/processConfiguration")
    public  String processConfiguration(HttpServletRequest request,
                                        ModelMap modelMap,@ModelAttribute(value = "configuration")Configuration conf,
                                        BindingResult bindingResult,
                                        RedirectAttributes redirectAttributes
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
        redirectAttributes.addFlashAttribute(RequestService.SUCCESS,RequestService.UPDATE_SUCCESS);
        this.defaultPage.setDaultPage(request);
        return "redirect:/configuration";
    }

}
