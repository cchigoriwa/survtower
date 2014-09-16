package com.survtower.business.central.service.impl;

import com.survtower.business.central.domain.CentralUser;
import com.survtower.business.central.service.CentralUserDetailService;
import com.survtower.business.central.service.CentralUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 *
 * @author Takunda Dhlakama
 */
@Component("centralUserDetailService")
public class CentralUserDetailServiceImpl implements CentralUserDetailService {

    @Autowired
    private CentralUserService centralUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CentralUser centralUser = centralUserService.findByUserName(username);
        if (centralUser == null) {
            throw new UsernameNotFoundException("User " + username + " not found.");
        } else {
            return centralUser.toUserDetails();
        }

    }

}
