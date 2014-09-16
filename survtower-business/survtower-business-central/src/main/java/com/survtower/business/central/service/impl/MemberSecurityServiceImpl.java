package com.survtower.business.central.service.impl;

import com.survtower.business.central.dao.MemberSecurityDao;
import com.survtower.business.central.domain.MemberSecurity;
import com.survtower.business.central.service.MemberSecurityService;
import com.survtower.business.common.domain.Member;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Charles Chigoriwa
 */
@Service("memberSecurityService")
@Transactional(readOnly = true)
public class MemberSecurityServiceImpl implements MemberSecurityService {

    @Autowired
    private MemberSecurityDao memberSecurityDao;

    @Transactional
    @Override
    public synchronized MemberSecurity save(MemberSecurity memberSecurity) {
        memberSecurity.setUpdateDate(new Date());
        if (findByUuid(memberSecurity.getUuid()) == null) {
            //if this is a new member security
            //To be improved later so far based on UUID --hackable
            memberSecurity.setMemberID(UUID.randomUUID().toString());
            memberSecurity.setMemberKey(UUID.randomUUID().toString());
            memberSecurity.setPassword("test" + new Random().nextInt());
        }
        return memberSecurityDao.save(memberSecurity);
    }

    @Override
    public List<MemberSecurity> findAll() {
        return memberSecurityDao.findAll();
    }

    @Override
    public MemberSecurity find(Long id) {
        return memberSecurityDao.find(id);
    }

    @Override
    public MemberSecurity findByUuid(String uuid) {
        return memberSecurityDao.findByUuid(uuid);
    }

    @Override
    public MemberSecurity findByMember(Member member) {
        return memberSecurityDao.findByMember(member);
    }

    @Override
    public MemberSecurity findByEmailAddress(String emailAddress) {
        return memberSecurityDao.findByEmailAddress(emailAddress);
    }

    @Override
    public int updatePassword(String password, String emailAddress) {
        return memberSecurityDao.updatePassword(password, emailAddress);
    }

    @Override
    public int updateMemberKey(String memberKey, String emailAddress) {
        return memberSecurityDao.updateMemberKey(memberKey, emailAddress);
    }

    @Override
    public MemberSecurity findByMemberID(String MemberID) {
        return memberSecurityDao.findByMemberID(MemberID);
    }

    // -------------------------------------------------------------------------
    // Get Current user Implementation
    // -------------------------------------------------------------------------
    @Override
    public MemberSecurity getCurrentUser() {
        String username = getCurrentUsername();

        if (username == null) {
            return null;
        }

        MemberSecurity user = memberSecurityDao.findByEmailAddress(username);

        if (user == null) {
            return null;
        }
        return user;
    }

    @Override
    public String getCurrentUsername() {

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

    @Override
    public List<String> getCurrentUserRoles() {
        if (getCurrentUser() != null) {
            return getCurrentUser().getRoles();
        } else {
            return new ArrayList<>();
        }
    }
}
