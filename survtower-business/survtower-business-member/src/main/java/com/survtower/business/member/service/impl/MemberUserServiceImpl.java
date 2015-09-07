package com.survtower.business.member.service.impl;

import com.survtower.business.common.EmailExistException;
import com.survtower.business.common.service.EmailConfiguration;
import com.survtower.business.common.service.PasswordGeneratorService;
import com.survtower.business.member.dao.MemberUserDao;
import com.survtower.business.member.domain.MemberUser;
import com.survtower.business.member.service.EmailHelper;
import com.survtower.business.member.service.MemberUserService;
import java.util.Date;
import java.util.List;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.security.authentication.encoding.PasswordEncoder;
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
    private PasswordGeneratorService passwordGeneratorService;

    @Autowired(required = false)
    private EmailHelper emailHelper;

    @Transactional
    @Override
    public synchronized MemberUser save(MemberUser memberUser) {

        if (emailExists(memberUser)) {
            throw new EmailExistException();
        }

        if (memberUser.getId() == null) {
            String rawPassword = memberUser.getPassword();
            if (rawPassword == null) {
                rawPassword = passwordGeneratorService.generatePassword();
            }
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
        memberUser.setUpdateDate(new Date());
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
                mimeMessage.setText(createTextMessage(memberUser.getUsername(), rawPassword));
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

    private synchronized boolean emailExists(MemberUser memberUser) {
        MemberUser existingMemberUser = findByEmail(memberUser.getEmail());
        if (existingMemberUser == null) {
            return false;
        } else {
            return !existingMemberUser.equals(memberUser);
        }
    }

    @Override
    public MemberUser findByEmail(String email) {
        return memberUserDao.findByEmail(email);
    }
}
