package com.survtower.business.member.service.impl;

import com.survtower.business.common.SurvtowerRuntimeException;
import com.survtower.business.common.domain.Lookup;
import com.survtower.business.member.dao.CentralSecurityDao;
import com.survtower.business.member.domain.CentralSecurity;
import com.survtower.business.member.service.CentralSecurityService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Charles Chigoriwa
 */
@Service("centralSecurityService")
@Transactional(readOnly = true)
public class CentralSecurityServiceImpl implements CentralSecurityService {

    @Autowired
    private CentralSecurityDao centralSecurityDao;

    @Transactional
    @Override
    public synchronized CentralSecurity save(CentralSecurity centralSecurity) {
        List<CentralSecurity> list = findAll();

        if (list.size() > 1) {
            throw new SurvtowerRuntimeException("Two or more CentralSecuritys");
        }

        if (!list.isEmpty()) {
            CentralSecurity existing = list.get(0);
            if (!(existing.identityEquals(centralSecurity))) {
                throw new SurvtowerRuntimeException("Existing MetaData and the record to be save are different");
            }
        }

        return centralSecurityDao.save(centralSecurity);
    }

    @Override
    public List<CentralSecurity> findAll() {
        return centralSecurityDao.findAll();
    }

    @Override
    public CentralSecurity find(Long id) {
        return centralSecurityDao.find(id);
    }

    @Override
    public CentralSecurity findByUuid(String uuid) {
        return centralSecurityDao.findByUuid(uuid);
    }

    public CentralSecurity find() {
        List<CentralSecurity> list = findAll();

        if (list.size() > 1) {
            throw new SurvtowerRuntimeException("Two or more CentralSecuritys");
        }

        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }

}
