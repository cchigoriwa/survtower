package com.survtower.business.member.service;

import com.survtower.business.common.GenericService;
import com.survtower.business.member.domain.CentralSecurity;

/**
 *
 * @author Charles Chigoriwa
 */
public interface CentralSecurityService extends GenericService<CentralSecurity> {

    public CentralSecurity find();
}
