package com.survtower.business.central.dao;

import com.survtower.business.central.domain.ResetCentralUserPasswordRequest;

/**
 *
 * @author hitrac
 */
public interface ResetCentralUserPasswordRequestDao {

    public ResetCentralUserPasswordRequest save(ResetCentralUserPasswordRequest resetCentralUserPasswordRequest);

    public ResetCentralUserPasswordRequest find(Long id);

    public ResetCentralUserPasswordRequest findByFirstTag(String firstTag);

    public ResetCentralUserPasswordRequest findBySecondTag(String secondTag);

}
