package com.survtower.business.central.repository;

import com.survtower.business.central.domain.ResetCentralUserPasswordRequest;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Charles Chigoriwa
 */
public interface ResetCentralUserPasswordRequestRepository extends CrudRepository<ResetCentralUserPasswordRequest, Long> {

    public ResetCentralUserPasswordRequest findByFirstTag(String firstTag);

    public ResetCentralUserPasswordRequest findBySecondTag(String secondTag);

}
