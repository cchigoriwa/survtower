package com.survtower.client.central.utility;

import com.survtower.business.central.service.CentralUserService;
import com.survtower.client.central.bean.CentralUserUtility;
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
    @ManagedProperty(value = "#{centralUserUtility}")
    private CentralUserUtility centralUserUtility;

    public void setCentralUserService(CentralUserService centralUserService) {
        this.centralUserService = centralUserService;
    }

    public void setCentralUserUtility(CentralUserUtility centralUserUtility) {
        this.centralUserUtility = centralUserUtility;
    }

}
