package controller.user;

import entities.User;
import exceptions.AccessDenieException;
import forms.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.RequestService;
import service.UserService;
import utils.session.SessionUtils;
import vadilator.UserFormChangePasswordValidator;
import vadilator.UserFormValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;

/**
 * Created by linhtran on 13/06/2017.
 */
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    UserFormChangePasswordValidator changePasswordValidator;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error, @RequestParam(value = "logout", required = false) String logout) {
        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", UserFormValidator.VALIDATION_PASS_OR_USER);
        }
        model.setViewName("login");
        return model;

    }

    @RequestMapping(value = "/login-success")
    public String loginSuccess(Principal principal, HttpServletRequest request) {

        HttpSession session = request.getSession();

        if (session.getAttribute(SessionUtils.USER_LOGIN) == null) {
            session.setAttribute(SessionUtils.USER_LOGIN, this.userService.getUserByName(principal.getName()));
        }
        return "redirect:/user";
    }

    //for 403 access denied page
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String  accessDenied() throws AccessDenieException {
        throw new AccessDenieException(AccessDenieException.ACESS_NOT_ROLE_PAGE);
    }


    @RequestMapping(value = "/change-pass-word",method = RequestMethod.POST)
    public String actionChangePassWord(HttpServletRequest request, RedirectAttributes redirectAttributes,
                                       @ModelAttribute(value = "userForm") UserForm userForm, ModelMap modelMap,
                                       BindingResult bindingResult){;
        changePasswordValidator.validate(userForm,bindingResult);
        User user = (User) request.getSession().getAttribute(SessionUtils.USER_LOGIN);
        if(bindingResult.hasErrors()){
            modelMap.addAttribute("errors",changePasswordValidator.getCodeErrors(bindingResult));
            request.setAttribute("user", user);
            return "change-pass-word";
        }

        user.setPassWord(passwordEncoder.encode(userForm.getNewPassWord()));
        userService.save(user);
        redirectAttributes.addFlashAttribute(RequestService.MESSAGE,RequestService.UPDATE_SUCCESS);
        return "redirect:/change-pass-word";
    }
}
