package com.survtower.client.linker.handler;

import com.survtower.business.central.domain.MemberSecurity;
import com.survtower.business.central.domain.ResetMemberSecurityPasswordRequest;
import com.survtower.business.central.service.MemberSecurityService;
import com.survtower.business.central.service.ResetMemberSecurityPasswordRequestService;
import com.survtower.business.central.service.ResetPasswordProcess;
import com.survtower.client.linker.util.AppUserUtil;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Daniel Nkhoma
 */
@Controller
public class PasswordResetController {

    @Autowired
    private MemberSecurityService memberSecurityService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ResetMemberSecurityPasswordRequestService resetMemberSecurityPasswordRequestService;
    @Autowired
    private ResetPasswordProcess resetPasswordProcess;

    @RequestMapping(value = "/request-password-reset", method = RequestMethod.GET)
    public String showRequestPasswordForm(Model model) {
        return "enterEmail";
    }

    @RequestMapping(value = "/request-password-reset", method = RequestMethod.POST)
    public String processRequestPasswordForm(@RequestParam("emailAddress") String emailAddress, Model model) {
        MemberSecurity memberSecurity = memberSecurityService.findByEmailAddress(emailAddress);
        if (memberSecurity == null) {
            String error = "Email Address not found";
            model.addAttribute("error", error);
            return "enterEmail";
        } else {

            ResetMemberSecurityPasswordRequest resetPasswordRequest = resetPasswordProcess.createNewLinkerPasswordRequest(emailAddress);

            return "redirect:/emailsent/" + resetPasswordRequest.getFirstTag();
        }

    }

    @RequestMapping(value = "/emailsent/{firstTag}", method = RequestMethod.GET)
    public String showEmailSentReport(@PathVariable("firstTag") String firstTag, Model model) {
        ResetMemberSecurityPasswordRequest resetPasswordRequest = resetMemberSecurityPasswordRequestService.findByFirstTag(firstTag);
        if (resetPasswordRequest == null || resetPasswordRequest.isExpired()) {
            return "redirect:/";
        } else {
            return "emailSentReport";
        }
    }

    @RequestMapping(value = "/reset-password/{firstTag}", method = RequestMethod.GET)
    public String showChangePasswordForm(Model model, @PathVariable("firstTag") String firstTag, @RequestParam(value = "tok", required = true) String tok) {
        ResetMemberSecurityPasswordRequest resetPasswordRequest = resetMemberSecurityPasswordRequestService.findByFirstTag(firstTag);
        if (resetPasswordRequest == null || !resetPasswordRequest.getSecondTag().equals(tok) || resetPasswordRequest.isExpired()) {
            return "redirect:/";
        }

        model.addAttribute("appUserUtil", new AppUserUtil());
        return "resetPassword";
    }

    @RequestMapping(value = "/reset-password/{firstTag}", method = RequestMethod.POST)
    public String processResetPasswordForm(@Valid AppUserUtil appUserUtil, BindingResult result, @PathVariable("firstTag") String firstTag, @RequestParam(value = "tok", required = true) String tok) {
        ResetMemberSecurityPasswordRequest resetPasswordRequest = resetMemberSecurityPasswordRequestService.findByFirstTag(firstTag);
        if (resetPasswordRequest == null || !resetPasswordRequest.getSecondTag().equals(tok) || resetPasswordRequest.isExpired()) {
            return "redirect:/";
        }

        String password = appUserUtil.getPassword();
        String confirmPassword = appUserUtil.getConfirmPassword();

        if (!password.equals(confirmPassword)) {
            result.rejectValue("confirmPassword", "password", "Password and confirm password not the same!");

        }

        MemberSecurity memberSecurity = resetPasswordRequest.getMemberSecurity();
        String oldHashedPassword = memberSecurity.getPassword();
        String newHashedPassword = passwordEncoder.encodePassword(password, memberSecurity.getEmailAddress());

        if (oldHashedPassword.equals(newHashedPassword)) {
            result.rejectValue("password", "password", "Your new password is the same as your old one!");
        }

        if (result.hasErrors()) {
            return "resetPassword";
        } else {
            //TODO put in a service class            
            memberSecurityService.updatePassword(newHashedPassword, memberSecurity.getEmailAddress());

            return "redirect:/";
        }
    }
}
