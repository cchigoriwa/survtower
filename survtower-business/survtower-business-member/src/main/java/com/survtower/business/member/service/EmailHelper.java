package com.survtower.business.member.service;

import com.survtower.business.member.domain.ResetPasswordRequest;
import java.io.Serializable;

/**
 *
 * @author Charles Chigoriwa
 */
public interface EmailHelper extends Serializable{
    
     public String createTextMessage(ResetPasswordRequest resetPasswordRequest);
     
     public String createTextMessage(String username, String password);
    
}
