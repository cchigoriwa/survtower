package com.survtower.business.member.dao.impl;

import com.survtower.business.common.domain.Lookup;
import com.survtower.business.member.dao.LookupMetaDao;
import com.survtower.business.member.domain.LookupMeta;
import com.survtower.business.member.repository.LookupMetaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Charles Chigoriwa
 */
@Repository
public class LookupMetaDaoImpl implements LookupMetaDao{
    
    @Autowired
    private LookupMetaRepository lookupMetaRepository;

    @Override
    public LookupMeta findByLookup(Lookup lookup) {
        return lookupMetaRepository.findByLookup(lookup);
    }

    @Override
    public LookupMeta save(LookupMeta lookupMeta) {
       return lookupMetaRepository.save(lookupMeta);
    }

    @Override
    public List<LookupMeta> findAll() {
        return lookupMetaRepository.findAll();
    }

    @Override
    public LookupMeta find(Long id) {
       return lookupMetaRepository.findOne(id);
    }

    @Override
    public LookupMeta findByUuid(String uuid) {
        return lookupMetaRepository.findByUuid(uuid);
    }
    
}
