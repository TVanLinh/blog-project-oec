package controller.admin;

import entities.Configuration;
import exceptions.NotFindException;
import forms.ConfigForm;
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
import java.util.HashMap;

/**
 * Created by linhtran on 06/06/2017.
 */
@Controller
public class ConfigurationController {

    @Autowired
    private DefaultPages defaultPage;

    @Autowired
    private ConfigurationService configurationService;

    @Autowired
    private ConfigFormValidator configFormValidator;


    @ModelAttribute
    public ConfigForm initConfig() {
        return new ConfigForm();
    }

    @RequestMapping(value = "/configuration")
    public String configuration(ModelMap modelMap) {
        modelMap.addAttribute("conf", this.configurationService.getConfig());
        return "configuration";
    }

    @RequestMapping("/process-configuration")
    public String processConfiguration(HttpServletRequest request,
                                       ModelMap modelMap, @ModelAttribute(value = "configForm") ConfigForm conf,
                                       BindingResult bindingResult,
                                       RedirectAttributes redirectAttributes) throws NotFindException {

        this.configFormValidator.validate(conf, bindingResult);

        HashMap<String, Configuration> hashMap = (HashMap<String, Configuration>) this.configurationService.getConfig();

        if (bindingResult.hasErrors()) {
            modelMap.addAttribute("errors", this.configFormValidator.getCodeErrors(bindingResult));
            modelMap.addAttribute("conf", hashMap);
            return "configuration";
        }

        if (hashMap.isEmpty()) {
            throw new NotFindException();
        }

        this.configurationService.save(this.configurationService.getConfig(hashMap, conf));

        redirectAttributes.addFlashAttribute(RequestService.SUCCESS, RequestService.UPDATE_SUCCESS);
        this.defaultPage.setDaultPage(request);
        return "redirect:/configuration";
    }

}
