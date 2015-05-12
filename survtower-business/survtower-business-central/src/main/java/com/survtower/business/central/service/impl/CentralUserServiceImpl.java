package com.survtower.business.central.service.impl;

import com.survtower.business.common.domain.Program;
import com.survtower.business.central.dao.CentralUserDao;
import com.survtower.business.central.domain.CentralUser;
import com.survtower.business.central.domain.CentralUserRole;
import com.survtower.business.central.service.CentralUserService;
import com.survtower.business.common.service.impl.PasswordEncoderImpl;
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
@Service("centralUserService")
@Transactional(readOnly = true)
public class CentralUserServiceImpl implements CentralUserService {

    @Autowired
    private CentralUserDao centralUserDao;

    @Autowired
    private PasswordEncoderImpl passwordEncoder;

    @Transactional
    @Override
    public CentralUser save(CentralUser centralUser) {
        centralUser.setUpdateDate(new Date());
        return centralUserDao.save(centralUser);
    }

    @Override
    public List<CentralUser> findAll() {
        return centralUserDao.findAll();
    }

    @Override
    public CentralUser find(Long id) {
        return centralUserDao.find(id);
    }

    @Override
    public CentralUser findByUuid(String uuid) {
        return centralUserDao.findByUuid(uuid);
    }

    @Override
    public CentralUser findByUserName(String username) {
        return centralUserDao.findByUserName(username);
    }

    @Override
    public int updatePassword(String password, String username) {
        return centralUserDao.updatePassword(password, username);
    }

    @Override
    public List<CentralUser> findCentralUsersUpdatedAfter(Date afterDate) {
        return null;
    }

    // -------------------------------------------------------------------------
    // Get Current user Implementation
    // -------------------------------------------------------------------------
    @Override
    public CentralUser getCurrentUser() {
        String username = getCurrentUsername();

        if (username == null) {
            return null;
        }

        CentralUser user = centralUserDao.findByUserName(username);

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

    public List<String> getMemberRoles() {
        List<String> list = new ArrayList<String>();
        list.add(CentralUser.ROLE_GLOBAL_ADMINISTRATOR);
        return list;
    }

    @PostConstruct
    public void init() {
        Set<CentralUserRole> centralUserRoles = new HashSet<CentralUserRole>();
        if (centralUserDao.findAll().isEmpty()) {
            CentralUser centralUser = new CentralUser();
            centralUser.setUsername("admin");
            centralUser.setPassword("centraluser");
            centralUser.setDeactivated(Boolean.FALSE);
            for (String role : getMemberRoles()) {
                CentralUserRole centralUserRole = new CentralUserRole();
                centralUserRole.setMemberRole(role);
                centralUserRole.setDeactivated(Boolean.FALSE);
                centralUserRoles.add(centralUserRole);
            }
            centralUser.setPassword(passwordEncoder.encodePassword(centralUser.getPassword(), centralUser.getUuid()));
            centralUser.setCentralUserRoles(centralUserRoles);
            centralUserDao.save(centralUser);
        }

    }

}
