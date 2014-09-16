package com.survtower.business.member.service;

import com.survtower.business.common.GenericService;
import com.survtower.business.common.domain.Dynamic;
import com.survtower.business.member.domain.DynamicMeta;

/**
 *
 * @author Charles Chigoriwa
 */
public interface DynamicMetaService extends GenericService<DynamicMeta> {

    public DynamicMeta findByDynamic(Dynamic dynamic);
}
