package com.survtower.business.member.service;

import com.survtower.business.common.GenericService;
import com.survtower.business.member.domain.IndicatorMetaData;

/**
 *
 * @author Charles Chigoriwa
 */
public interface IndicatorMetaDataService extends GenericService<IndicatorMetaData>{
    public IndicatorMetaData find();
}
