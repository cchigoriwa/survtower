package com.survtower.business.central.service;

import com.survtower.business.central.domain.ResetPasswordRequest;
import java.io.Serializable;

/**
 *
 * @author Daniel Nkhoma
 */
public interface ResetPasswordRequestService extends Serializable {

    public ResetPasswordRequest createNewPasswordRequest(String emailAddress);
    
    public ResetPasswordRequest findByFirstTag(String firstTag);
    
    public ResetPasswordRequest findBySecondTag(String secondTag);
   
}
