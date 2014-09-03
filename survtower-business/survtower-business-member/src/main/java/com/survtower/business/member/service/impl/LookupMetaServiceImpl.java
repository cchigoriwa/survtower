package com.survtower.business.member.service.impl;

import com.survtower.business.common.SurvtowerRuntimeException;
import com.survtower.business.common.domain.Lookup;
import com.survtower.business.member.dao.LookupMetaDao;
import com.survtower.business.member.domain.LookupMeta;
import com.survtower.business.member.service.LookupMetaService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Charles Chigoriwa
 */
@Service("lookupMetaService")
@Transactional(readOnly = true)
public class LookupMetaServiceImpl implements LookupMetaService{
    
    @Autowired
    private LookupMetaDao lookupMetaDao;

    @Transactional
    @Override
    public synchronized LookupMeta save(LookupMeta lookupMeta) {
        LookupMeta existing=this.findByLookup(lookupMeta.getLookup());
        if(existing!=null){
            if(!existing.identityEquals(lookupMeta)){
                throw new SurvtowerRuntimeException("This record will cause a duplicate::");
            }
        }
        lookupMeta.setUpdateDate(new Date());
        return lookupMetaDao.save(lookupMeta);
    }

    @Override
    public List<LookupMeta> findAll() {
        return lookupMetaDao.findAll();
    }

    @Override
    public LookupMeta find(Long id) {
       return lookupMetaDao.find(id);
    }

    @Override
    public LookupMeta findByUuid(String uuid) {
       return lookupMetaDao.findByUuid(uuid);
    }

    @Override
    public LookupMeta findByLookup(Lookup lookup) {
        return lookupMetaDao.findByLookup(lookup);
    }
    
}
