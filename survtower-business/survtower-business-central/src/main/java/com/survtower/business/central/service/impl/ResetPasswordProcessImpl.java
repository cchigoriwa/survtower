package com.survtower.business.central.service.impl;

import com.survtower.business.central.domain.CentralUser;
import com.survtower.business.central.domain.MemberSecurity;
import com.survtower.business.central.domain.ResetCentralUserPasswordRequest;
import com.survtower.business.central.domain.ResetMemberSecurityPasswordRequest;
import com.survtower.business.central.service.CentralUserService;
import com.survtower.business.central.service.EmailHelper;
import com.survtower.business.central.service.MemberSecurityService;
import com.survtower.business.central.service.ResetCentralUserPasswordRequestService;
import com.survtower.business.central.service.ResetMemberSecurityPasswordRequestService;
import com.survtower.business.central.service.ResetPasswordProcess;
import com.survtower.business.common.service.EmailConfiguration;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

/**
 *
 * @author Daniel Nkhoma
 */
@Service("resetPasswordProcess")
public class ResetPasswordProcessImpl implements ResetPasswordProcess {

    @Autowired
    private CentralUserService centralUserService;
    @Autowired
    private MemberSecurityService memberSecurityService;
    @Autowired
    private ResetCentralUserPasswordRequestService resetCentralUserPasswordRequestService;
    @Autowired
    private ResetMemberSecurityPasswordRequestService resetMemberSecurityPasswordRequestService;
    @Resource
    protected Environment environment;
    @Autowired
    private EmailConfiguration emailConfiguration;
    @Autowired(required = false)
    private EmailHelper emailHelper;

    @Override
    public ResetCentralUserPasswordRequest createNewCentralUserPasswordRequest(String emailAddress) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 24);
        CentralUser centralUser = this.centralUserService.findByEmail(emailAddress);
        ResetCentralUserPasswordRequest resetPasswordRequest = new ResetCentralUserPasswordRequest();
        resetPasswordRequest.setFirstTag(UUID.randomUUID().toString());
        resetPasswordRequest.setSecondTag(UUID.randomUUID().toString());
        resetPasswordRequest.setCentralUser(centralUser);
        resetPasswordRequest.setTimeRequested(new Date());
        resetPasswordRequest.setTimeOfExpiry(calendar.getTime());
        resetCentralUserPasswordRequestService.save(resetPasswordRequest);
        sendCentralUserRequestEmail(resetPasswordRequest);
        return resetPasswordRequest;

    }

    @Override
    public ResetMemberSecurityPasswordRequest createNewLinkerPasswordRequest(String emailAddress) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 24);
        MemberSecurity memberSecurity = memberSecurityService.findByEmailAddress(emailAddress);
        ResetMemberSecurityPasswordRequest resetPasswordRequest = new ResetMemberSecurityPasswordRequest();
        resetPasswordRequest.setFirstTag(UUID.randomUUID().toString());
        resetPasswordRequest.setSecondTag(UUID.randomUUID().toString());
        resetPasswordRequest.setMemberSecurity(memberSecurity);
        resetPasswordRequest.setTimeRequested(new Date());
        resetPasswordRequest.setTimeOfExpiry(calendar.getTime());
        resetMemberSecurityPasswordRequestService.save(resetPasswordRequest);

        sendMemberSecurityRequestEmail(resetPasswordRequest);
        return resetPasswordRequest;

    }

    private void sendCentralUserRequestEmail(final ResetCentralUserPasswordRequest resetPasswordRequest) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {

                mimeMessage.setRecipient(Message.RecipientType.TO,
                        new InternetAddress(resetPasswordRequest.getCentralUser().getEmail()));
                mimeMessage.setFrom(new InternetAddress("xyz@xyz.co.zw"));
                mimeMessage.setSubject("Password Reset Request");
                mimeMessage.setText(createCentralUserResetPasswordTextMessage(resetPasswordRequest));
            }
        };

        emailConfiguration.getJavaMailSender().send(preparator);

    }

    private void sendMemberSecurityRequestEmail(final ResetMemberSecurityPasswordRequest resetPasswordRequest) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {

                mimeMessage.setRecipient(Message.RecipientType.TO,
                        new InternetAddress(resetPasswordRequest.getMemberSecurity().getEmailAddress()));
                mimeMessage.setFrom(new InternetAddress("xyz@xyz.co.zw"));
                mimeMessage.setSubject("Password Reset Request");
                mimeMessage.setText(createMemberSecurityResetPasswordTextMessage(resetPasswordRequest));
            }
        };

        emailConfiguration.getJavaMailSender().send(preparator);

    }

    private String createCentralUserResetPasswordTextMessage(ResetCentralUserPasswordRequest resetPasswordRequest) {
        return emailHelper.createCentralUserResetPasswordTextMessage(resetPasswordRequest);
    }

    private String createMemberSecurityResetPasswordTextMessage(ResetMemberSecurityPasswordRequest resetPasswordRequest) {
        return emailHelper.createMemberSecurityTextMessage(resetPasswordRequest);
    }

}
