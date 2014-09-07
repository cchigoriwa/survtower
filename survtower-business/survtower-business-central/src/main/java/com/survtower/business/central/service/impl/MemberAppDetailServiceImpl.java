package com.survtower.business.central.service.impl;

import com.survtower.business.central.domain.MemberSecurity;
import com.survtower.business.central.service.MemberAppDetailService;
import com.survtower.business.central.service.MemberSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 *
 * @author Charles Chigoriwa
 */
@Component("memberAppDetailService")
public class MemberAppDetailServiceImpl implements MemberAppDetailService {

    @Autowired
    private MemberSecurityService memberSecurityService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberSecurity memberSecurity = memberSecurityService.findByMemberID(username);
        if (memberSecurity == null) {
            throw new UsernameNotFoundException("User " + username + " not found.");
        } else {
            return memberSecurity.toAppUserDetailsForIntegration();
        }

    }

}
