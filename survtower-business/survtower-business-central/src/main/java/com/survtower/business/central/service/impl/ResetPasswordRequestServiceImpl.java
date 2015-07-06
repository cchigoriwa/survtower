package com.survtower.business.central.service.impl;

import com.survtower.business.central.domain.CentralUser;
import com.survtower.business.central.domain.ResetPasswordRequest;
import com.survtower.business.central.repository.CentralUserRepository;
import com.survtower.business.central.repository.ResetPasswordRequestRepository;
import com.survtower.business.central.service.ResetPasswordRequestService;
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
@Service("resetPasswordRequestService")
public class ResetPasswordRequestServiceImpl implements ResetPasswordRequestService {

    @Autowired
    private CentralUserRepository centralUserRepository;
    @Autowired
    private ResetPasswordRequestRepository resetPasswordRequestRepository;
    @Autowired
    private EmailConfiguration emailConfiguration;

    @Resource
    protected Environment environment;

    @Override
    public ResetPasswordRequest createNewPasswordRequest(String emailAddress) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 24);
        CentralUser centralUser = this.centralUserRepository.findByEmail(emailAddress);
        ResetPasswordRequest resetPasswordRequest = new ResetPasswordRequest();
        resetPasswordRequest.setFirstTag(UUID.randomUUID().toString());
        resetPasswordRequest.setSecondTag(UUID.randomUUID().toString());
        resetPasswordRequest.setCentralUser(centralUser);
        resetPasswordRequest.setTimeRequested(new Date());
        resetPasswordRequest.setTimeOfExpiry(calendar.getTime());

        this.resetPasswordRequestRepository.save(resetPasswordRequest);        
        sendEmail(resetPasswordRequest);

        return resetPasswordRequest;

    }

    private void sendEmail(final ResetPasswordRequest resetPasswordRequest) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {

                mimeMessage.setRecipient(Message.RecipientType.TO,
                        new InternetAddress(resetPasswordRequest.getCentralUser().getEmail()));
                mimeMessage.setFrom(new InternetAddress("xyz@xyz.co.zw"));
                mimeMessage.setSubject("Password Reset Request");
                mimeMessage.setText(createTextMessage(resetPasswordRequest));
            }
        };

        this.emailConfiguration.getJavaMailSender().send(preparator);

    }

    private String createTextMessage(ResetPasswordRequest resetPasswordRequest) {
        StringBuilder sb = new StringBuilder();
        sb.append("Changing your password is simple. Please use the link below");
        sb.append(" within 24 hours.  ");
        //String webUrl=environment.getRequiredProperty("transunion.client.website.url");
        String webUrl = "http://localhost:8080/";
        sb.append(webUrl);
        sb.append("survtower-client-central/faces/resetPassword.xhtml?reset=");
        sb.append(resetPasswordRequest.getFirstTag());
        sb.append("&tok=");
        sb.append(resetPasswordRequest.getSecondTag());
        return sb.toString();
    }

    @Override
    public ResetPasswordRequest findByFirstTag(String firstTag) {
        return resetPasswordRequestRepository.findByFirstTag(firstTag);
    }

    @Override
    public ResetPasswordRequest findBySecondTag(String secondTag) {
        return resetPasswordRequestRepository.findBySecondTag(secondTag);
    }

}
