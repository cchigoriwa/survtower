package com.survtower.business.central.service.impl;

import com.survtower.business.central.dao.ResetCentralUserPasswordRequestDao;
import com.survtower.business.central.domain.ResetCentralUserPasswordRequest;
import com.survtower.business.central.service.ResetCentralUserPasswordRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Daniel Nkhoma
 */
@Service("resetCentralUserPasswordRequestService")
public class ResetCentralUserPasswordRequestServiceImpl implements ResetCentralUserPasswordRequestService {

    @Autowired
    private ResetCentralUserPasswordRequestDao resetCentralUserPasswordRequestDao;

    @Override
    public ResetCentralUserPasswordRequest findByFirstTag(String firstTag) {
        return resetCentralUserPasswordRequestDao.findByFirstTag(firstTag);
    }

    @Override
    public ResetCentralUserPasswordRequest findBySecondTag(String secondTag) {
        return resetCentralUserPasswordRequestDao.findBySecondTag(secondTag);
    }

    @Override
    public ResetCentralUserPasswordRequest save(ResetCentralUserPasswordRequest resetCentralUserPasswordRequest) {
        return resetCentralUserPasswordRequestDao.save(resetCentralUserPasswordRequest);
    }

    @Override
    public ResetCentralUserPasswordRequest find(Long id) {
        return resetCentralUserPasswordRequestDao.find(id);
    }
}
