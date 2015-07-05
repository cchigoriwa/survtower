package com.survtower.client.member.controller;

import com.survtower.business.common.domain.EmailSetup;
import com.survtower.business.common.service.EmailSetupService;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Daniel Nkhoma
 */
@ManagedBean
@RequestScoped
public class EmailSetupController {
    
    @ManagedProperty(value = "#{emailSetupService}")
    private EmailSetupService emailSetupService;
    
    private EmailSetup emailSetup;

    public EmailSetup getEmailSetup() {
        return emailSetup;
    }

    public void setEmailSetup(EmailSetup emailSetup) {
        this.emailSetup = emailSetup;
    }
    
    public EmailSetupService getEmailSetupService() {
        return emailSetupService;
    }

    public void setEmailSetupService(EmailSetupService emailSetupService) {
        this.emailSetupService = emailSetupService;
    }
    
    public String save(){
        emailSetupService.save(emailSetup);
        return "index";
    }
    
    @PostConstruct
    public void postConstruct(){
        emailSetup=emailSetupService.find();
        emailSetup=emailSetup==null?new EmailSetup():emailSetup;
    }
    
}
