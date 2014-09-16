package com.survtower.business.member.dao;

import com.survtower.business.common.GenericDao;
import com.survtower.business.common.domain.Dynamic;
import com.survtower.business.member.domain.DynamicMeta;

/**
 *
 * @author Charles Chigoriwa
 */
public interface DynamicMetaDao extends GenericDao<DynamicMeta>{
    
    public DynamicMeta findByDynamic(Dynamic dynamic);
}
