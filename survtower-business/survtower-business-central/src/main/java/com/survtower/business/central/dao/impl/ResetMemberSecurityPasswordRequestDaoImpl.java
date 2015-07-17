package com.survtower.business.central.dao.impl;

import com.survtower.business.central.dao.ResetMemberSecurityPasswordRequestDao;
import com.survtower.business.central.domain.ResetMemberSecurityPasswordRequest;
import com.survtower.business.central.repository.ResetMemberSecurityPasswordRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Daniel Nkhoma
 */
@Repository
public class ResetMemberSecurityPasswordRequestDaoImpl implements ResetMemberSecurityPasswordRequestDao {

    @Autowired
    private ResetMemberSecurityPasswordRequestRepository resetMemberSecurityPasswordRequestRepository;

    @Override
    public ResetMemberSecurityPasswordRequest findByFirstTag(String firstTag) {
        return resetMemberSecurityPasswordRequestRepository.findByFirstTag(firstTag);
    }

    @Override
    public ResetMemberSecurityPasswordRequest findBySecondTag(String secondTag) {
        return resetMemberSecurityPasswordRequestRepository.findBySecondTag(secondTag);
    }

    @Override
    public ResetMemberSecurityPasswordRequest save(ResetMemberSecurityPasswordRequest resetMemberSecurityPasswordRequest) {
        return resetMemberSecurityPasswordRequestRepository.save(resetMemberSecurityPasswordRequest);
    }

    @Override
    public ResetMemberSecurityPasswordRequest find(Long id) {
        return resetMemberSecurityPasswordRequestRepository.findOne(id);
    }

}
