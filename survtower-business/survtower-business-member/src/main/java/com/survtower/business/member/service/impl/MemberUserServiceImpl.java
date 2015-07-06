package com.survtower.business.member.service.impl;

import com.survtower.business.common.domain.Program;
import com.survtower.business.common.service.EmailConfiguration;
import com.survtower.business.common.service.PasswordGeneratorService;
import com.survtower.business.member.dao.MemberUserDao;
import com.survtower.business.member.domain.MemberUser;
import com.survtower.business.member.domain.MemberUserRole;
import com.survtower.business.member.domain.Region;
import com.survtower.business.member.service.EmailHelper;
import com.survtower.business.member.service.MemberUserService;
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
 * @author Daniel Nkhoma
 * @author Charles Chigoriwa
 */
@Service("memberUserService")
@Transactional(readOnly = true)
public class MemberUserServiceImpl implements MemberUserService {

    @Autowired
    private MemberUserDao memberUserDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailConfiguration emailConfiguration;
    
    @Autowired
    private  PasswordGeneratorService passwordGeneratorService;
    
    @Autowired(required = false)
    private EmailHelper emailHelper;

    @Transactional
    @Override
    public MemberUser save(MemberUser memberUser) {
        if (memberUser.getId() == null) {
            String rawPassword = passwordGeneratorService.generatePassword();
            memberUser = createNewUser(memberUser, rawPassword);
            try {
                sendEmail(memberUser, rawPassword);
            } catch (Exception ex) {
                //log exception
                //ignore and proceed ; dont rollback
            }
        } else {
            memberUser = memberUserDao.save(memberUser);
        }
        return memberUser;
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

    @Override
    public List<Region> getCurrentUserRegions() {
        if (getCurrentUser() != null) {
            return getCurrentUser().getRegionList();
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
            Set<MemberUserRole> memberUserRoles = new HashSet<>();
            MemberUser memberUser = new MemberUser();
            memberUser.setUsername("admin");
            memberUser.setDeactivated(Boolean.FALSE);
            for (String role : getMemberRoles()) {
                MemberUserRole memberUserRole = new MemberUserRole();
                memberUserRole.setMemberRole(role);
                memberUserRole.setDeactivated(Boolean.FALSE);
                memberUserRoles.add(memberUserRole);
            }
            memberUser.setMemberUserRoles(memberUserRoles);
            save(memberUser);
        }
    }

    @Transactional
    public void resetPassword(MemberUser memberUser) {
        String rawPassword = RandomStringUtils.randomAlphanumeric(10);
        String encriptedPassword = passwordEncoder.encodePassword(rawPassword, memberUser.getUuid());
        memberUserDao.updatePassword(encriptedPassword, memberUser.getUsername());
        sendEmail(memberUser, rawPassword);
    }

    private MemberUser createNewUser(MemberUser memberUser, String rawPassword) {
        String encriptedPassword = passwordEncoder.encodePassword(rawPassword, memberUser.getEmail());
        memberUser.setPassword(encriptedPassword);
        memberUser = memberUserDao.save(memberUser);
        return memberUser;
    }

    private void sendEmail(final MemberUser memberUser, final String rawPassword) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {

                mimeMessage.setRecipient(Message.RecipientType.TO,
                        new InternetAddress(memberUser.getEmail()));
                mimeMessage.setFrom(new InternetAddress("xyz@xyz.co.zw"));
                mimeMessage.setSubject("New Member State Account");
                mimeMessage.setText(createTextMessage(memberUser.getEmail(), rawPassword));
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
       return emailHelper.createTextMessage(username, password);
    }

    @Override
    public MemberUser findByEmail(String email) {
        return memberUserDao.findByEmail(email);
    }
}
