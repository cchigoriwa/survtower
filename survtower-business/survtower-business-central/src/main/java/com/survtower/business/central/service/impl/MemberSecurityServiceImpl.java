package com.survtower.business.central.service.impl;

import com.survtower.business.central.dao.MemberSecurityDao;
import com.survtower.business.central.domain.MemberSecurity;
import com.survtower.business.central.service.MemberSecurityService;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Charles Chigoriwa
 */
@Service
@Transactional(readOnly = true)
public class MemberSecurityServiceImpl implements MemberSecurityService{
    
    private MemberSecurityDao memberSecurityDao;

    @Transactional
    @Override
    public MemberSecurity save(MemberSecurity memberSecurity) {
        memberSecurity.setUpdateDate(new Date());
       return memberSecurityDao.save(memberSecurity);
    }

    @Override
    public List<MemberSecurity> findAll() {
       return memberSecurityDao.findAll();
    }

    @Override
    public MemberSecurity find(Long id) {
        return memberSecurityDao.find(id);
    }

    @Override
    public MemberSecurity findByUuid(String uuid) {
        return memberSecurityDao.findByUuid(uuid);
    }
    
}
