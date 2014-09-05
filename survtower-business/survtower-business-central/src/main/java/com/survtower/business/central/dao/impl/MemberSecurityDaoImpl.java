package com.survtower.business.central.dao.impl;

import com.survtower.business.central.dao.MemberSecurityDao;
import com.survtower.business.central.domain.MemberSecurity;
import com.survtower.business.central.repository.MemberSecurityRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Charles Chigoriwa
 */
@Repository
public class MemberSecurityDaoImpl implements MemberSecurityDao{
    
    @Autowired
    private MemberSecurityRepository memberSecurityRepository;

    @Override
    public MemberSecurity save(MemberSecurity memberSecurity) {
        return memberSecurityRepository.save(memberSecurity);
    }

    @Override
    public List<MemberSecurity> findAll() {
        return memberSecurityRepository.findAll();
    }

    @Override
    public MemberSecurity find(Long id) {
        return memberSecurityRepository.findOne(id);
    }

    @Override
    public MemberSecurity findByUuid(String uuid) {
        return memberSecurityRepository.findByUuid(uuid);
    }
    
}
