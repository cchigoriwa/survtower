package com.survtower.client.central.controller;

import com.survtower.business.central.domain.CentralUser;
import com.survtower.business.central.service.CentralUserService;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Takunda Dhlakama
 */
@ManagedBean
@RequestScoped
public class CentralUserListController {
    
    @ManagedProperty(value = "#{centralUserService}")
    private CentralUserService centralUserService;
    
    public List<CentralUser> getCentralUsers(){
        return centralUserService.findAll();
    }

    public CentralUserService getCentralUserService() {
        return centralUserService;
    }
    
    public void setCentralUserService(CentralUserService centralUserService) {
        this.centralUserService = centralUserService;
    }
    
    public String createNewCentralUser() {
        return "centralUserEdit?faces-redirect=true";
    }
    
}
