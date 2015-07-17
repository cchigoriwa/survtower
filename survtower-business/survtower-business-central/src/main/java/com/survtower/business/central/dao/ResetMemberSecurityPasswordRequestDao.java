package com.survtower.business.central.dao;

import com.survtower.business.central.domain.ResetMemberSecurityPasswordRequest;

/**
 *
 * @author hitrac
 */
public interface ResetMemberSecurityPasswordRequestDao {

    public ResetMemberSecurityPasswordRequest save(ResetMemberSecurityPasswordRequest resetMemberSecurityPasswordRequest);

    public ResetMemberSecurityPasswordRequest find(Long id);

    public ResetMemberSecurityPasswordRequest findByFirstTag(String firstTag);

    public ResetMemberSecurityPasswordRequest findBySecondTag(String secondTag);

}
