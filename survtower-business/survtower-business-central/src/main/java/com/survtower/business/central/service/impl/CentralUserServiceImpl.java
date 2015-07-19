package com.survtower.business.central.service.impl;

import com.survtower.business.central.dao.CentralUserDao;
import com.survtower.business.central.domain.CentralUser;
import com.survtower.business.central.service.CentralUserService;
import com.survtower.business.central.service.EmailHelper;
import com.survtower.business.common.EmailExistException;
import com.survtower.business.common.service.EmailConfiguration;
import com.survtower.business.common.service.PasswordGeneratorService;
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

    @Autowired(required = false)
    private EmailHelper emailHelper;

    @Transactional
    @Override
    public synchronized CentralUser save(CentralUser centralUser) {

        if (emailExists(centralUser)) {
            throw new EmailExistException();
        }

        if (centralUser.getId() == null) {
            String rawPassword = centralUser.getPassword();
            if (rawPassword == null) {
                rawPassword = passwordGeneratorService.generatePassword();
            }
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
        return emailHelper.createCentralUserChangePasswordTextMessage(username, password);
    }

    private synchronized boolean emailExists(CentralUser centralUser) {
        CentralUser existingCentralUser = findByEmail(centralUser.getEmail());
        if (existingCentralUser == null) {
            return false;
        } else {
            return !existingCentralUser.equals(centralUser);
        }
    }

    @Override
    public CentralUser findByEmail(String email) {
        return centralUserDao.findByEmail(email);
    }

}
