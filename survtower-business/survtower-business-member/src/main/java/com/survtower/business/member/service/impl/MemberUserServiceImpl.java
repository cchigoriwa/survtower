package com.survtower.business.member.service.impl;

import com.survtower.business.common.domain.Program;
import com.survtower.business.common.service.impl.PasswordEncoderImpl;
import com.survtower.business.member.dao.MemberUserDao;
import com.survtower.business.member.domain.MemberUser;
import com.survtower.business.member.domain.MemberUserRole;
import com.survtower.business.member.service.MemberUserService;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Takunda Dhlakama
 */
@Service("memberUserService")
@Transactional(readOnly = true)
public class MemberUserServiceImpl implements MemberUserService {

    @Autowired
    private MemberUserDao memberUserDao;

    @Autowired
    private PasswordEncoderImpl passwordEncoder;

    @Transactional
    @Override
    public MemberUser save(MemberUser memberUser) {
        memberUser.setUpdateDate(new Date());
        return memberUserDao.save(memberUser);
    }

    @Override
    public List<MemberUser> findAll() {
        return memberUserDao.findAll();
    }

    @Override
    public MemberUser find(Long id) {
        return memberUserDao.find(id);
    }

    @Override
    public MemberUser findByUuid(String uuid) {
        return memberUserDao.findByUuid(uuid);
    }

    @Override
    public MemberUser findByUserName(String username) {
        return memberUserDao.findByUserName(username);
    }

    @Override
    public int updatePassword(String password, String username) {
        return memberUserDao.updatePassword(password, username);
    }

    @Override
    public List<MemberUser> findMemberUsersUpdatedAfter(Date afterDate) {
        return null;
    }

    // -------------------------------------------------------------------------
    // Get Current user Implementation
    // -------------------------------------------------------------------------
    @Override
    public MemberUser getCurrentUser() {
        String username = getCurrentUsername();

        if (username == null) {
            return null;
        }

        MemberUser user = memberUserDao.findByUserName(username);

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

    @Override
    public List<Program> getCurrentUserPrograms() {
        if (getCurrentUser() != null) {
            return getCurrentUser().getProgramList();
        } else {
            return new ArrayList<>();
        }
    }

    public List<String> getMemberRoles() {
        List<String> list = new ArrayList<String>();
        list.add(MemberUser.ROLE_COUNTRY_ADMINISTRATOR);
        return list;
    }

    @PostConstruct
    public void init() {
        if (memberUserDao.findAll().isEmpty()) {
            Set<MemberUserRole> memberUserRoles = new HashSet<MemberUserRole>();
            MemberUser memberUser = new MemberUser();
            memberUser.setUsername("admin");
            memberUser.setPassword("memberuser");
            memberUser.setDeactivated(Boolean.FALSE);
            for (String role : getMemberRoles()) {
                MemberUserRole memberUserRole = new MemberUserRole();
                memberUserRole.setMemberRole(role);
                memberUserRole.setDeactivated(Boolean.FALSE);
                memberUserRoles.add(memberUserRole);
            }
            memberUser.setPassword(passwordEncoder.encodePassword(memberUser.getPassword(), memberUser.getUuid()));
            memberUser.setMemberUserRoles(memberUserRoles);
            memberUserDao.save(memberUser);
        }
    }
}
