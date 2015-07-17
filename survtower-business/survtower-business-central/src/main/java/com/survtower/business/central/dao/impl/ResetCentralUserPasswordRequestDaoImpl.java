package com.survtower.business.central.dao.impl;

import com.survtower.business.central.dao.ResetCentralUserPasswordRequestDao;
import com.survtower.business.central.domain.ResetCentralUserPasswordRequest;
import com.survtower.business.central.repository.ResetCentralUserPasswordRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Daniel Nkhoma
 */
@Repository
public class ResetCentralUserPasswordRequestDaoImpl implements ResetCentralUserPasswordRequestDao {

    @Autowired
    private ResetCentralUserPasswordRequestRepository resetCentralUserPasswordRequestRepository;

    @Override
    public ResetCentralUserPasswordRequest findByFirstTag(String firstTag) {
        return resetCentralUserPasswordRequestRepository.findByFirstTag(firstTag);
    }

    @Override
    public ResetCentralUserPasswordRequest findBySecondTag(String secondTag) {
        return resetCentralUserPasswordRequestRepository.findBySecondTag(secondTag);
    }

    @Override
    public ResetCentralUserPasswordRequest save(ResetCentralUserPasswordRequest resetCentralUserPasswordRequest) {
        return resetCentralUserPasswordRequestRepository.save(resetCentralUserPasswordRequest);
    }

    @Override
    public ResetCentralUserPasswordRequest find(Long id) {
        return resetCentralUserPasswordRequestRepository.findOne(id);
    }

}
