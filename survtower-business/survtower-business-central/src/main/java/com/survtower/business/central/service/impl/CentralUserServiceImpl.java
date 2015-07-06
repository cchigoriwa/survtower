package com.survtower.business.central.service.impl;

import com.survtower.business.central.dao.CentralUserDao;
import com.survtower.business.central.domain.CentralUser;
import com.survtower.business.central.domain.CentralUserRole;
import com.survtower.business.central.service.CentralUserService;
import com.survtower.business.common.service.EmailConfiguration;
import com.survtower.business.common.service.PasswordGeneratorService;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.security.authentication.encoding.PasswordEncoder;
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
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailConfiguration emailConfiguration;

    @Autowired
    private PasswordGeneratorService passwordGeneratorService;

    @Transactional
    @Override
    public CentralUser save(CentralUser centralUser) {

        if (centralUser.getId() == null) {
            String rawPassword = passwordGeneratorService.generatePassword();
            centralUser = createNewUser(centralUser, rawPassword);
            try {
                sendEmail(centralUser, rawPassword);
            } catch (Exception ex) {
                //log exception
                //ignore and proceed ; dont rollback
            }
        } else {
            centralUser = centralUserDao.save(centralUser);
        }
        return centralUser;
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

    private CentralUser createNewUser(CentralUser centralUser, String rawPassword) {
        String encriptedPassword = passwordEncoder.encodePassword(rawPassword, centralUser.getEmail());
        centralUser.setPassword(encriptedPassword);
        centralUser.setUpdateDate(new Date());
        centralUser = centralUserDao.save(centralUser);
        return centralUser;
    }
    
    @Transactional
    public void resetPassword(CentralUser centralUser) {
        String rawPassword = RandomStringUtils.randomAlphanumeric(10);
        String encriptedPassword = passwordEncoder.encodePassword(rawPassword, centralUser.getUuid());
        centralUserDao.updatePassword(encriptedPassword, centralUser.getUsername());
        sendEmail(centralUser, rawPassword);
    }
    
    private void sendEmail(final CentralUser centralUser, final String rawPassword) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {

                mimeMessage.setRecipient(Message.RecipientType.TO,
                        new InternetAddress(centralUser.getEmail()));
                mimeMessage.setFrom(new InternetAddress("xyz@xyz.co.zw"));
                mimeMessage.setSubject("New Central Account");
                mimeMessage.setText(createTextMessage(centralUser.getEmail(), rawPassword));
            }
        };
        try {
            this.emailConfiguration.getJavaMailSender().send(preparator);
        } catch (MailException ex) {
            // simply log it and go on...
            System.err.println(ex.getMessage());
        }
    }
    
    private String createTextMessage(String username, String password) {
        StringBuilder sb = new StringBuilder();
        sb.append("Your Central/SADC Secretariat account was successfully created");
        sb.append("Your login details: Username: ");
        sb.append(username);
        sb.append(" Password: ");
        sb.append(password);
        sb.append(" You are encouraged to change this password upon first login.");
        return sb.toString();
    }

    @PostConstruct
    public void init() {
        Set<CentralUserRole> centralUserRoles = new HashSet<CentralUserRole>();
        if (centralUserDao.findAll().isEmpty()) {
            CentralUser centralUser = new CentralUser();
            centralUser.setUsername("admin");
            centralUser.setDeactivated(Boolean.FALSE);
            for (String role : getMemberRoles()) {
                CentralUserRole centralUserRole = new CentralUserRole();
                centralUserRole.setMemberRole(role);
                centralUserRole.setDeactivated(Boolean.FALSE);
                centralUserRoles.add(centralUserRole);
            }
            centralUser.setCentralUserRoles(centralUserRoles);
            save(centralUser);
        }

    }

    @Override
    public CentralUser findByEmail(String email) {
        return centralUserDao.findByEmail(email);
    }

}
