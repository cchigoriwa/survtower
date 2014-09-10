package com.survtower.business.member.integration.impl;

import com.survtower.business.common.domain.DataSourceCategory;
import com.survtower.business.common.domain.Lookup;
import com.survtower.business.common.service.DataSourceCategoryService;
import com.survtower.business.member.domain.LookupMeta;
import com.survtower.business.member.integration.DataSourceCategoryIntegrator;
import com.survtower.business.member.integration.IntegrationService;
import com.survtower.business.member.service.LookupMetaService;
import com.survtower.ws.api.DataSourceCategoryWebservice;
import com.survtower.ws.api.domain.DataSourceCategoryCollectionPayload;
import com.survtower.ws.api.domain.RequestMetaData;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Charles Chigoriwa
 */
@Component("dataSourceCategoryIntegrator")
public class DataSourceCategoryIntegratorImpl implements DataSourceCategoryIntegrator {

    @Autowired
    private LookupMetaService lookupMetaService;
    @Autowired
    private DataSourceCategoryService dataSourceCategoryService;
    @Autowired
    private IntegrationService integrationService;

    @Override
    public synchronized LookupMeta pull() {

        LookupMeta dataSourceCategoryLookupMeta=lookupMetaService.findByLookup(Lookup.DATA_SOURCE_CATEGORY);

        Date startDate = null;

        if (dataSourceCategoryLookupMeta != null) {
            startDate = dataSourceCategoryLookupMeta.getLastServerTimestamp();
        }

        RequestMetaData requestMetaData = new RequestMetaData();
        requestMetaData.setMinimumDate(startDate);
        DataSourceCategoryWebservice dataSourceCategoryWebservice = integrationService.getDataSourceCategoryWebservice();

        DataSourceCategoryCollectionPayload dataSourceCategoryCollectionPayload = dataSourceCategoryWebservice.getDataSourceCategorys(requestMetaData);

        List<DataSourceCategory> dataSourceCategorys = dataSourceCategoryCollectionPayload.getDataSourceCategorys();
        if (dataSourceCategorys != null && !dataSourceCategorys.isEmpty()) {
            for (DataSourceCategory dataSourceCategory : dataSourceCategorys) {
                DataSourceCategory existing = dataSourceCategoryService.findByUuid(dataSourceCategory.getUuid());
                if (existing != null) {
                    //ID is not send
                    dataSourceCategory.setId(existing.getId());
                }else{
                    //it must be a new object
                    dataSourceCategory.setId(null);
                }
                dataSourceCategoryService.save(dataSourceCategory);
            }
        }

        if (dataSourceCategoryLookupMeta == null) {
            dataSourceCategoryLookupMeta = new LookupMeta(Lookup.DATA_SOURCE_CATEGORY);
        }

        if (dataSourceCategoryCollectionPayload.getPayloadMetaData() != null && dataSourceCategoryCollectionPayload.getPayloadMetaData().getMaximumDate() != null) {
            dataSourceCategoryLookupMeta.setLastServerTimestamp(dataSourceCategoryCollectionPayload.getPayloadMetaData().getMaximumDate());
        }
        
        dataSourceCategoryLookupMeta.setUpdateDate(new Date());
        lookupMetaService.save(dataSourceCategoryLookupMeta);

        return dataSourceCategoryLookupMeta;
    }
}
