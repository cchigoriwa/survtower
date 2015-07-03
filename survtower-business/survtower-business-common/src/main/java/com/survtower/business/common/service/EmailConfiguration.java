package com.survtower.business.common.service;

import java.io.Serializable;
import org.springframework.mail.javamail.JavaMailSender;

/**
 *
 * @author Charles Chigoriwa
 */
public interface EmailConfiguration extends Serializable {

    public JavaMailSender getJavaMailSender();

}
