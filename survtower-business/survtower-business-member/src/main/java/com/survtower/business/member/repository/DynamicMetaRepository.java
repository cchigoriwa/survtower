package com.survtower.business.member.repository;

import com.survtower.business.common.domain.Dynamic;
import com.survtower.business.member.domain.DynamicMeta;
import net.sadc.business.common.GenericRepository;

/**
 *
 * @author Charles Chigoriwa
 */
public interface DynamicMetaRepository extends GenericRepository<DynamicMeta, Long>{
    
    public DynamicMeta findByDynamic(Dynamic dynamic);
    
}
