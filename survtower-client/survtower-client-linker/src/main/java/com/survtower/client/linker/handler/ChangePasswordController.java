package com.survtower.client.linker.handler;

import com.survtower.business.central.domain.MemberSecurity;
import com.survtower.business.central.service.MemberSecurityService;
import com.survtower.client.linker.util.AppUserUtil;
import java.security.Principal;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Charles Chigoriwa
 */
@Controller
public class ChangePasswordController {

    @Autowired
    private MemberSecurityService memberSecurityService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/member/change_password", method = RequestMethod.GET)
    public String showChangePasswordForm(Model model, Principal principal) {
        String emailAddress = principal.getName();
        MemberSecurity memberSecurity=this.memberSecurityService.findByEmailAddress(emailAddress);
        model.addAttribute("memberSecurity", memberSecurity);
        model.addAttribute("appUserUtil", new AppUserUtil());
        return "changePassword";
    }

    @RequestMapping(value = "/member/change_password", method = RequestMethod.POST)
    public String processChangePasswordForm(@Valid AppUserUtil appUserUtil, BindingResult result, Principal principal,Model model) {

        String password = appUserUtil.getPassword();
        String confirmPassword = appUserUtil.getConfirmPassword();

        if (!password.equals(confirmPassword)) {
            result.rejectValue("confirmPassword", "password", "Password and confirm password not the same!");

        }

        String emailAddress = principal.getName();
        MemberSecurity memberSecurity=this.memberSecurityService.findByEmailAddress(emailAddress);
        String oldHashedPassword = memberSecurity.getPassword();
        String newHashedPassword = passwordEncoder.encodePassword(password,memberSecurity.getUuid());

        if (oldHashedPassword.equals(newHashedPassword)) {
            result.rejectValue("password", "password", "Your new password is the same as your old one!");
        }

        if (result.hasErrors()) {
            return "changePassword";
        } else {
            this.memberSecurityService.updatePassword(newHashedPassword, principal.getName());
            return "redirect:/member/change_password_confirmation";
        }
    }

    @RequestMapping(value = "/member/change_password_confirmation", method = RequestMethod.GET)
    public String changePasswordConfirmation(Model model, Principal principal) {
        String emailAddress = principal.getName();
        MemberSecurity memberSecurity=this.memberSecurityService.findByEmailAddress(emailAddress);
        model.addAttribute("memberSecurity", memberSecurity);
        return "changePasswordConfirmation";
    }

}
