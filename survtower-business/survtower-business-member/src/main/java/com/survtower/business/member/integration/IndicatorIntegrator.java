package com.survtower.business.member.integration;

import com.survtower.business.member.domain.LookupMeta;
import java.io.Serializable;

/**
 *
 * @author Charles Chigoriwa
 */
public interface IndicatorIntegrator extends Serializable{
    
    public LookupMeta pull();
    
}
