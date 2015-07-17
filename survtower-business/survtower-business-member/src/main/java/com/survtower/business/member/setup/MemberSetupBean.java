package com.survtower.business.member.setup;

import com.survtower.business.common.domain.UserRole;
import com.survtower.business.common.service.UserRoleService;
import com.survtower.business.member.domain.MemberUser;
import com.survtower.business.member.service.MemberUserService;
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
public class MemberSetupBean implements InitializingBean {

    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private MemberUserService memberUserService;

    @Override
    public void afterPropertiesSet() throws Exception {
        addUserRoles();
        addAdminUser();
    }

    private void addAdminUser() {
        String username = "admin";
        if (memberUserService.findByUserName(username) == null) {
            MemberUser memberUser = new MemberUser();
            memberUser.setUsername(username);
            memberUser.setDeactivated(Boolean.FALSE);
            memberUser.setEmail("admin@sadcsadc.org");
            memberUser.setPassword("memberuser");
            memberUser.setUserRoles(new ArrayList<UserRole>());
            memberUser.getUserRoles().add(userRoleService.findByRole(MemberUser.ROLE_COUNTRY_ADMINISTRATOR));      
            memberUserService.save(memberUser);
        }
    }

    private void addUserRoles() {
        if (userRoleService.findAll().isEmpty()) {
            List<UserRole> userRoles = new ArrayList<>();
            userRoles.add(new UserRole("Country Administrator", MemberUser.ROLE_COUNTRY_ADMINISTRATOR));
            userRoles.add(new UserRole("Country Disease Manager", MemberUser.ROLE_COUNTRY_DISEASE_MANAGER));
            userRoles.add(new UserRole("Health Information Officer", MemberUser.ROLE_HEALTH_INFORMATION_OFFICER));
            for (UserRole userRole : userRoles) {
                userRoleService.save(userRole);
            }
        }
    }

}
