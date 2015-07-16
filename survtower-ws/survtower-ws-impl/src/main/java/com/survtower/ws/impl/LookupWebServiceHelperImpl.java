package com.survtower.ws.impl;

import com.survtower.ws.helper.LookupResult;
import com.survtower.ws.helper.LookupWebServiceHelper;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Service;

/**
 *
 * @author Charles Chigoriwa
 */
@Service
public class LookupWebServiceHelperImpl implements LookupWebServiceHelper{

    @PersistenceContext
    private EntityManager entityManager;

    public LookupResult findLookupResult(String $Entity, Long lastUpdatedNo) {

        LookupResult lookupResult = new LookupResult();

        String maxQueryStatement = "select max(d.updateNo) from " + $Entity + " d ";
        Long serverLastUpdateNo;
        if (lastUpdatedNo != null) {
            maxQueryStatement += " where d.updateNo>:lastUpdatedNo ";
        }

        Query maxQuery = entityManager.createQuery(maxQueryStatement);
        if (lastUpdatedNo != null) {
            maxQuery.setParameter("lastUpdatedNo", lastUpdatedNo);
        }

        Object serverLastUpdateNoObject = maxQuery.getSingleResult();
        serverLastUpdateNo = serverLastUpdateNoObject == null ? null : (Long) serverLastUpdateNoObject;

        if (serverLastUpdateNo != null) {
            String queryDataStatement = "select d from " + $Entity + " d where ";
            if (lastUpdatedNo != null) {
                queryDataStatement += " d.updateNo>:lastUpdatedNo and ";
            }

            queryDataStatement += " d.updateNo<=:serverLastUpdateNo ";

            Query dataQuery = entityManager.createQuery(queryDataStatement);

            if (lastUpdatedNo != null) {
                dataQuery.setParameter("lastUpdatedNo", lastUpdatedNo);
            }

            dataQuery.setParameter("serverLastUpdateNo", serverLastUpdateNo);

            List dataList = dataQuery.getResultList();

            lookupResult.setServerLastUpdateNo(serverLastUpdateNo);
            lookupResult.setResultList(dataList);

        }

        return lookupResult;

    }

}
