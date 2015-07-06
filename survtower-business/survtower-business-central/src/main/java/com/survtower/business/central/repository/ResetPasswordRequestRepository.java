package com.survtower.business.central.repository;

import com.survtower.business.central.domain.ResetPasswordRequest;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Charles Chigoriwa
 */
public interface ResetPasswordRequestRepository extends CrudRepository<ResetPasswordRequest, Long>{
    
    public ResetPasswordRequest findByFirstTag(String firstTag);
    public ResetPasswordRequest findBySecondTag(String secondTag);
    
}
