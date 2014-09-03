package com.survtower.business.member.service;

import com.survtower.business.common.GenericService;
import com.survtower.business.common.domain.Lookup;
import com.survtower.business.member.domain.LookupMeta;

/**
 *
 * @author Charles Chigoriwa
 */
public interface LookupMetaService extends GenericService<LookupMeta> {

    public LookupMeta findByLookup(Lookup lookup);
}
