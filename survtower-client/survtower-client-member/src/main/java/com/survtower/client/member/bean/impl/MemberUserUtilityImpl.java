package com.survtower.client.member.bean.impl;

import com.survtower.business.member.domain.MemberUser;
import com.survtower.business.member.service.MemberUserService;
import com.survtower.client.member.bean.MemberUserUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 *
 * @author Charles Chigoriwa
 */
@Component("memberUserUtility")
public class MemberUserUtilityImpl implements MemberUserUtility{
    
    @Autowired
    private MemberUserService memberUserService;

    @Override
    public MemberUser getCurrentUser() {
       String username = getCurrentUsername();
        if (username == null) {
            return null;
        }
        MemberUser user = memberUserService.findByUserName(username);
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
