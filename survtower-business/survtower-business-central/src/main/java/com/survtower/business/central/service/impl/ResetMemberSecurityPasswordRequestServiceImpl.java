package com.survtower.business.central.service.impl;

import com.survtower.business.central.dao.ResetMemberSecurityPasswordRequestDao;
import com.survtower.business.central.domain.ResetMemberSecurityPasswordRequest;
import com.survtower.business.central.service.ResetMemberSecurityPasswordRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Daniel Nkhoma
 */
@Service("resetMemberSecurityPasswordRequestService")
public class ResetMemberSecurityPasswordRequestServiceImpl implements ResetMemberSecurityPasswordRequestService {

    @Autowired
    private ResetMemberSecurityPasswordRequestDao resetMemberSecurityPasswordRequestDao;

    @Override
    public ResetMemberSecurityPasswordRequest findByFirstTag(String firstTag) {
        return resetMemberSecurityPasswordRequestDao.findByFirstTag(firstTag);
    }

    @Override
    public ResetMemberSecurityPasswordRequest findBySecondTag(String secondTag) {
        return resetMemberSecurityPasswordRequestDao.findBySecondTag(secondTag);
    }

    @Override
    public ResetMemberSecurityPasswordRequest save(ResetMemberSecurityPasswordRequest resetMemberSecurityPasswordRequest) {
        return resetMemberSecurityPasswordRequestDao.save(resetMemberSecurityPasswordRequest);
    }

    @Override
    public ResetMemberSecurityPasswordRequest find(Long id) {
        return resetMemberSecurityPasswordRequestDao.find(id);
    }

}
