package com.survtower.client.central.utility;

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
public class LoggedOnRoleListController {

    @ManagedProperty(value = "#{centralUserService}")
    private CentralUserService centralUserService;

    public void setCentralUserService(CentralUserService centralUserService) {
        this.centralUserService = centralUserService;
    }

    public List<String> getRoles() {
        return centralUserService.getCurrentUserRoles();
    }

}
