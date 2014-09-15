package com.survtower.business.member.service.impl;

import com.survtower.business.member.domain.MemberUser;
import com.survtower.business.member.service.MemberUserDetailService;
import com.survtower.business.member.service.MemberUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 *
 * @author Takunda Dhlakama
 */
@Component("memberUserDetailService")
public class MemberUserDetailServiceImpl implements MemberUserDetailService {

    @Autowired
    private MemberUserService memberUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberUser memberUser = memberUserService.findByUserName(username);
        if (memberUser == null) {
            throw new UsernameNotFoundException("User " + username + " not found.");
        } else {
            return memberUser.toUserDetails();
        }

    }

}
