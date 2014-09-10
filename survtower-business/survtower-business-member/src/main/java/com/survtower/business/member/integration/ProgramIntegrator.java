package com.survtower.business.member.integration;

import com.survtower.business.member.domain.LookupMeta;
import java.io.Serializable;

/**
 *
 * @author Charles Chigoriwa
 */
public interface ProgramIntegrator extends Serializable{
    
    public LookupMeta pull();
    
}
