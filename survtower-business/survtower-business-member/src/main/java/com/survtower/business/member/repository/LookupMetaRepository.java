package com.survtower.business.member.repository;

import com.survtower.business.common.domain.Lookup;
import com.survtower.business.member.domain.LookupMeta;
import net.sadc.business.common.GenericRepository;

/**
 *
 * @author Charles Chigoriwa
 */
public interface LookupMetaRepository extends GenericRepository<LookupMeta, Long>{
    
    public LookupMeta findByLookup(Lookup lookup);
    
}
