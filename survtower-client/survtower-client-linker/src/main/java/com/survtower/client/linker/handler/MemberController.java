package com.survtower.client.linker.handler;

import com.survtower.business.central.domain.MemberSecurity;
import com.survtower.business.central.service.MemberSecurityService;
import java.security.Principal;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Charles Chigoriwa
 */
@Controller
public class MemberController {
    
    @Autowired
    private MemberSecurityService memberSecurityService;
    
    @RequestMapping(value = "/member/dashboard", method = RequestMethod.GET)
    public String viewProfile(Model model, Principal principal) {
        String emailAddress = principal.getName();
        MemberSecurity memberSecurity=this.memberSecurityService.findByEmailAddress(emailAddress);
        model.addAttribute("memberSecurity", memberSecurity);
        return "member";
    }
    
    
}
