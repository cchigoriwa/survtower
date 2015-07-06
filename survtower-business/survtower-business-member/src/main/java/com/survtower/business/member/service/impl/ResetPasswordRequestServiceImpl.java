package com.survtower.business.member.service.impl;

import com.survtower.business.common.service.EmailConfiguration;
import com.survtower.business.member.domain.MemberUser;
import com.survtower.business.member.domain.ResetPasswordRequest;
import com.survtower.business.member.repository.MemberUserRepository;
import com.survtower.business.member.repository.ResetPasswordRequestRepository;
import com.survtower.business.member.service.EmailHelper;
import com.survtower.business.member.service.ResetPasswordRequestService;
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
 * @author Charles Chigoriwa
 */
@Service("resetPasswordRequestService")
public class ResetPasswordRequestServiceImpl implements ResetPasswordRequestService {

    @Autowired
    private MemberUserRepository memberUserRepository;
    @Autowired
    private ResetPasswordRequestRepository resetPasswordRequestRepository;
    @Autowired
    private EmailConfiguration emailConfiguration;
    @Autowired(required = false)
    private EmailHelper emailHelper;
    @Resource
    protected Environment environment;

    @Override
    public ResetPasswordRequest createNewPasswordRequest(String emailAddress) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 24);
        MemberUser memberUser = this.memberUserRepository.findByEmail(emailAddress);
        ResetPasswordRequest resetPasswordRequest = new ResetPasswordRequest();
        resetPasswordRequest.setFirstTag(UUID.randomUUID().toString());
        resetPasswordRequest.setSecondTag(UUID.randomUUID().toString());
        resetPasswordRequest.setMemberUser(memberUser);
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
                        new InternetAddress(resetPasswordRequest.getMemberUser().getEmail()));
                mimeMessage.setFrom(new InternetAddress("xyz@xyz.co.zw"));
                mimeMessage.setSubject("Password Reset Request");
                mimeMessage.setText(createTextMessage(resetPasswordRequest));
            }
        };

        this.emailConfiguration.getJavaMailSender().send(preparator);

    }

    private String createTextMessage(ResetPasswordRequest resetPasswordRequest) {
     return emailHelper.createTextMessage(resetPasswordRequest);
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
