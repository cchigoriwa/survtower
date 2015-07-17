package com.survtower.business.central.repository;

import com.survtower.business.central.domain.ResetMemberSecurityPasswordRequest;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Daniel Nkhoma
 */
public interface ResetMemberSecurityPasswordRequestRepository extends CrudRepository<ResetMemberSecurityPasswordRequest, Long> {

    public ResetMemberSecurityPasswordRequest findByFirstTag(String firstTag);

    public ResetMemberSecurityPasswordRequest findBySecondTag(String secondTag);

}
