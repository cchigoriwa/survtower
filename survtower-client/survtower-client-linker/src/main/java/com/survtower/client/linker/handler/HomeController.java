package com.survtower.client.linker.handler;

import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Charles Chigoriwa
 */
@Controller
public class HomeController {
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String viewProfile(Model model, Principal principal) {
        model.addAttribute("loggedIn", principal!=null);
        return "home";
    }
}
