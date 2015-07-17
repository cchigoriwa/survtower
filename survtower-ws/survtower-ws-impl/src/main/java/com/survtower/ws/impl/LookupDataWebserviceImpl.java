package com.survtower.ws.impl;

import com.survtower.business.central.domain.MemberSecurity;
import com.survtower.business.central.service.MemberSecurityService;
import com.survtower.business.common.domain.Lookup;
import com.survtower.ws.api.LookupDataWebService;
import com.survtower.ws.api.domain.LookupMetaDataCollectionPayload;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 *
 * @author Charles Chigoriwa
 */
@Component
public class LookupDataWebserviceImpl implements LookupDataWebService {

    @Autowired
    private MemberSecurityService memberSecurityService;

    @PersistenceContext
    private EntityManager entityManager;

    public LookupMetaDataCollectionPayload getLookupMetaDataList() {
        LookupMetaDataCollectionPayload payload = new LookupMetaDataCollectionPayload();
        payload.add(Lookup.MEMBER, findMaximumUpdateNoForMember());
        payload.add(Lookup.PROGRAM, findMaximumUpdateNo("Program"));
        payload.add(Lookup.PERIOD, findMaximumUpdateNo("Period"));
        payload.add(Lookup.FREQUENCY, findMaximumUpdateNo("Frequency"));
        payload.add(Lookup.DATA_SOURCE_CATEGORY, findMaximumUpdateNo("DataSourceCategory"));
        payload.add(Lookup.DATA_SOURCE, findMaximumUpdateNo("DataSource"));
        payload.add(Lookup.INDICATOR_TYPE, findMaximumUpdateNo("IndicatorType"));
        payload.add(Lookup.DATA_ELEMENT, findMaximumUpdateNo("DataElement"));
        payload.add(Lookup.INDICATOR_GROUP, findMaximumUpdateNo("IndicatorGroup"));
        payload.add(Lookup.INDICATOR, findMaximumUpdateNo("Indicator"));
        return payload;
    }

    private Long findMaximumUpdateNo(String $Entity) {
        String queryStatement = "select max(e.updateNo) from " + $Entity + " e";
        Object object = entityManager.createQuery(queryStatement).getSingleResult();
        return object != null ? (Long) object : null;
    }

    private Long findMaximumUpdateNoForMember() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String memberID = auth.getName();
        MemberSecurity memberSecurity = memberSecurityService.findByMemberID(memberID);
        return memberSecurity.getMember().getUpdateNo();
    }

}
