package com.survtower.business.central.service;

import com.survtower.business.central.domain.ResetMemberSecurityPasswordRequest;
import java.io.Serializable;

/**
 *
 * @author Daniel Nkhoma
 */
public interface ResetMemberSecurityPasswordRequestService extends Serializable {

    public ResetMemberSecurityPasswordRequest save(ResetMemberSecurityPasswordRequest resetMemberSecurityPasswordRequest);

    public ResetMemberSecurityPasswordRequest find(Long id);

    public ResetMemberSecurityPasswordRequest findByFirstTag(String firstTag);

    public ResetMemberSecurityPasswordRequest findBySecondTag(String secondTag);

}
