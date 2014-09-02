package com.survtower.business.member.integration;

import java.io.Serializable;

/**
 *
 * @author Charles Chigoriwa
 */
public interface IndicatorIntegrator extends Serializable{
    
    public boolean pull();
    
}
