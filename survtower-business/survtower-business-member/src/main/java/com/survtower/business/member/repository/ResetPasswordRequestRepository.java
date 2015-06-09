package com.survtower.business.member.repository;

import com.survtower.business.member.domain.ResetPasswordRequest;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Charles Chigoriwa
 */
public interface ResetPasswordRequestRepository extends CrudRepository<ResetPasswordRequest, Long>{
    
    public ResetPasswordRequest findByFirstTag(String firstTag);
    public ResetPasswordRequest findBySecondTag(String secondTag);
    
}
