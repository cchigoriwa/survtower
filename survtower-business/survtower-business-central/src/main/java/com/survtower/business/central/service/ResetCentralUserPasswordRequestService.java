package com.survtower.business.central.service;

import com.survtower.business.central.domain.ResetCentralUserPasswordRequest;
import java.io.Serializable;

/**
 *
 * @author Daniel Nkhoma
 */
public interface ResetCentralUserPasswordRequestService extends Serializable {

    public ResetCentralUserPasswordRequest save(ResetCentralUserPasswordRequest resetCentralUserPasswordRequest);

    public ResetCentralUserPasswordRequest find(Long id);

    public ResetCentralUserPasswordRequest findByFirstTag(String firstTag);

    public ResetCentralUserPasswordRequest findBySecondTag(String secondTag);

}
