package com.survtower.business.common.service.impl;

import com.survtower.business.common.EmailSetUpNotSetException;
import com.survtower.business.common.domain.EmailSetup;
import com.survtower.business.common.service.EmailConfiguration;
import com.survtower.business.common.service.EmailSetupService;
import java.util.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

/**
 *
 * @author Charles Chigoriwa
 */
@Service
public class EmailConfigurationImpl implements EmailConfiguration {

    @Autowired
    private EmailSetupService emailSetupService;

    @Override
    public JavaMailSender getJavaMailSender() {
        EmailSetup emailSetup = emailSetupService.find();
        if (emailSetup == null) {
            throw new EmailSetUpNotSetException("Email configurations have not been set up for Member state hence we cannot send your request. Please contact Administrator");
        }
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        //core attributes
        mailSender.setHost(emailSetup.getHost().trim());
        mailSender.setPort(Integer.valueOf(emailSetup.getPort().trim()));
        mailSender.setUsername(emailSetup.getUsername());
        mailSender.setPassword(emailSetup.getPassword());

        //javaMailProperties
        Properties javaMailProperties = new Properties();
        if (!isEmpty(emailSetup.getMailSmtpAuth())) {
            javaMailProperties.setProperty("mail.smtp.auth", emailSetup.getMailSmtpAuth());
        }

        if (!isEmpty(emailSetup.getMailSmtpStartTlsEnable())) {
            javaMailProperties.setProperty("mail.smtp.starttls.enable", emailSetup.getMailSmtpStartTlsEnable());
        }

        //TODO: Daniel to add other properties
        if (!isEmpty(emailSetup.getMailSmtpUser())) {
            javaMailProperties.setProperty("mail.smtp.user", emailSetup.getMailSmtpUser());
        }

        if (!isEmpty(emailSetup.getMailSmtpFrom())) {
            javaMailProperties.setProperty("mail.smtp.from", emailSetup.getMailSmtpFrom());
        }

        if (!isEmpty(emailSetup.getMailSmtpLocalHost())) {
            javaMailProperties.setProperty("mail.smtp.localhost", emailSetup.getMailSmtpLocalHost());
        }

        if (!isEmpty(emailSetup.getMailSmtpHost())) {
            javaMailProperties.setProperty("mmail.smtp.host", emailSetup.getMailSmtpHost());
        }

        mailSender.setJavaMailProperties(javaMailProperties);

        return mailSender;
    }

    private boolean isEmpty(String string) {
        return string == null || string.trim().equals("");
    }
}
