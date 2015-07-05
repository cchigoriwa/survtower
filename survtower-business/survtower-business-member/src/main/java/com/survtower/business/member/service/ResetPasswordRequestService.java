package com.survtower.business.member.service;

import com.survtower.business.member.domain.ResetPasswordRequest;
import java.io.Serializable;

/**
 *
 * @author Charles Chigoriwa
 */
public interface ResetPasswordRequestService extends Serializable {

    public ResetPasswordRequest createNewPasswordRequest(String emailAddress);
    
    public ResetPasswordRequest findByFirstTag(String firstTag);
    
    public ResetPasswordRequest findBySecondTag(String secondTag);
   
}
