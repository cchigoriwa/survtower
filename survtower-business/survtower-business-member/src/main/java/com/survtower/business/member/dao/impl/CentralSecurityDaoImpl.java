package com.survtower.business.member.dao.impl;

import com.survtower.business.member.dao.CentralSecurityDao;
import com.survtower.business.member.domain.CentralSecurity;
import com.survtower.business.member.repository.CentralSecurityRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Charles Chigoriwa
 */
@Repository
public class CentralSecurityDaoImpl implements CentralSecurityDao{
    
    @Autowired
    private CentralSecurityRepository centralSecurityRepository;


    @Override
    public CentralSecurity save(CentralSecurity centralSecurity) {
       return centralSecurityRepository.save(centralSecurity);
    }

    @Override
    public List<CentralSecurity> findAll() {
        return centralSecurityRepository.findAll();
    }

    @Override
    public CentralSecurity find(Long id) {
       return centralSecurityRepository.findOne(id);
    }

    @Override
    public CentralSecurity findByUuid(String uuid) {
        return centralSecurityRepository.findByUuid(uuid);
    }
    
}
