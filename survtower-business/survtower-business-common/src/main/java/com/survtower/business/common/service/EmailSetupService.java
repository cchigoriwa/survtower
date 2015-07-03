package com.survtower.business.common.service;

import com.survtower.business.common.GenericService;
import com.survtower.business.common.domain.EmailSetup;

/**
 *
 * @author Daniel Nkhoma
 */
public interface EmailSetupService extends GenericService<EmailSetup>{
    
        public EmailSetup find();
}
