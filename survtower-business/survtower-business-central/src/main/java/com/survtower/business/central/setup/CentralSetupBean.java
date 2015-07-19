package com.survtower.business.central.setup;

import com.survtower.business.central.domain.CentralUser;
import com.survtower.business.central.service.CentralUserService;
import com.survtower.business.common.domain.UserRole;
import com.survtower.business.common.service.UserRoleService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Charles Chigoriwa
 */
@Component
public class CentralSetupBean implements InitializingBean {

    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private CentralUserService centralUserService;

    @Override
    public void afterPropertiesSet() throws Exception {
        addUserRoles();
        addAdminUser();
    }

    private void addAdminUser() {
        String username = "admin";
        if (centralUserService.findByUserName(username) == null) {
            CentralUser centralUser = new CentralUser();
            centralUser.setUsername(username);
            centralUser.setDeactivated(Boolean.FALSE);
            centralUser.setEmail("admin@sadcsadc.org");
            centralUser.setPassword("centraluser");
            centralUser.setUserRoles(new ArrayList<UserRole>());
            centralUser.getUserRoles().add(userRoleService.findByRole(CentralUser.ROLE_GLOBAL_ADMINISTRATOR));
            centralUserService.save(centralUser);
        }
    }

    private void addUserRoles() {
        if (userRoleService.findAll().isEmpty()) {
            List<UserRole> userRoles = new ArrayList<>();
            userRoles.add(new UserRole("Global Administrator", CentralUser.ROLE_GLOBAL_ADMINISTRATOR));
            userRoles.add(new UserRole("Data Manager", CentralUser.ROLE_SADC_DATA_MANAGER));
            for (UserRole userRole : userRoles) {
                userRoleService.save(userRole);
            }
        }
    }

}
