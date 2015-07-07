package com.survtower.business.central.service;

import com.survtower.business.central.domain.ResetPasswordRequest;
import java.io.Serializable;

/**
 *
 * @author Charles Chigoriwa
 */
public interface EmailHelper extends Serializable{
    
     public String createTextMessage(ResetPasswordRequest resetPasswordRequest);
     
     public String createTextMessage(String username, String password);
    
}
