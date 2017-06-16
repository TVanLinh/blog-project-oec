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
    public String configuration(ModelMap modelMap, HttpServletRequest request) {

        modelMap.addAttribute("conf", this.configurationService.getConfig());
        if (request.getAttribute("errors") != null) {
            modelMap.addAttribute("error", this.configurationService.getConfig());
        }
        if (request.getAttribute("conf") != null) {
            modelMap.addAttribute("conf", this.configurationService.getConfig());
        }
        return "configuration";
    }

    @RequestMapping("/process-configuration")
    public String processConfiguration(HttpServletRequest request,
                                       @ModelAttribute(value = "configForm") ConfigForm conf,
                                       BindingResult bindingResult,
                                       RedirectAttributes redirectAttributes) throws NotFindException {

        this.configFormValidator.validate(conf, bindingResult);

        HashMap<String, Configuration> hashMap = (HashMap<String, Configuration>) this.configurationService.getConfig();

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errors", this.configFormValidator.getCodeErrors(bindingResult));
            redirectAttributes.addFlashAttribute("conf", hashMap);
            return "redirect:/configuration";
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
