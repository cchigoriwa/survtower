package com.survtower.client.member.controller;

import com.survtower.business.member.domain.CentralSecurity;
import com.survtower.business.member.service.CentralSecurityService;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Charles Chigoriwa
 */
@ManagedBean
@RequestScoped
public class CentralSecurityController {
    
    @ManagedProperty(value = "#{centralSecurityService}")
    private CentralSecurityService centralSecurityService;
    
    private CentralSecurity centralSecurity;

    public CentralSecurity getCentralSecurity() {
        return centralSecurity;
    }

    public void setCentralSecurity(CentralSecurity centralSecurity) {
        this.centralSecurity = centralSecurity;
    }
    
    public CentralSecurityService getCentralSecurityService() {
        return centralSecurityService;
    }

    public void setCentralSecurityService(CentralSecurityService centralSecurityService) {
        this.centralSecurityService = centralSecurityService;
    }
    
    public String save(){
        centralSecurityService.save(centralSecurity);
        return "adminHome";
    }
    
    @PostConstruct
    public void postConstruct(){
        centralSecurity=centralSecurityService.find();
        centralSecurity=centralSecurity==null?new CentralSecurity():centralSecurity;
    }
    
}
