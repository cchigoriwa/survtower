package com.survtower.client.central.bean.impl;

import com.survtower.business.central.domain.CentralUser;
import com.survtower.business.central.service.CentralUserService;
import com.survtower.client.central.bean.CentralUserUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 *
 * @author Daniel Nkhoma
 */
@Component("centralUserUtility")
public class CentralUserUtilityImpl implements CentralUserUtility {

    @Autowired
    private CentralUserService centralUserService;

    @Override
    public CentralUser getCurrentUser() {
        String username = getCurrentUsername();
        if (username == null) {
            return null;
        }
        CentralUser user = centralUserService.findByUserName(username);
        if (user == null) {
            return null;
        }
        return user;
    }

    private String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal() == null) {
            return null;
        }
        /*
         * If getPrincipal returns a string, it means that the user has been
         * authenticated anonymous (String == anonymousUser).
         */
        if (authentication.getPrincipal() instanceof String) {
            String principal = (String) authentication.getPrincipal();

            if (principal.compareTo("anonymousUser") != 0) {
                return null;
            }

            return principal;
        }

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return userDetails.getUsername();
    }

}
