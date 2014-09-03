package com.survtower.business.member.dao;

import com.survtower.business.common.GenericDao;
import com.survtower.business.common.domain.Lookup;
import com.survtower.business.member.domain.LookupMeta;

/**
 *
 * @author Charles Chigoriwa
 */
public interface LookupMetaDao extends GenericDao<LookupMeta>{
    
    public LookupMeta findByLookup(Lookup lookup);
}
