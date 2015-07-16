package com.survtower.business.member.integration.impl;

import com.survtower.business.common.SurvtowerRuntimeException;
import com.survtower.business.common.domain.DataSource;
import com.survtower.business.common.domain.DataSourceCategory;
import com.survtower.business.common.domain.Frequency;
import com.survtower.business.common.domain.Lookup;
import com.survtower.business.common.service.DataSourceCategoryService;
import com.survtower.business.common.service.DataSourceService;
import com.survtower.business.common.service.FrequencyService;
import com.survtower.business.member.domain.LookupMeta;
import com.survtower.business.member.integration.DataSourceIntegrator;
import com.survtower.business.member.integration.IntegrationService;
import com.survtower.business.member.service.LookupMetaService;
import com.survtower.ws.api.DataSourceWebService;
import com.survtower.ws.api.domain.DataSourcePayload;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Charles Chigoriwa
 */
@Component("dataSourceIntegrator")
public class DataSourceIntegratorImpl implements DataSourceIntegrator {

    @Autowired
    private LookupMetaService lookupMetaService;
    @Autowired
    private DataSourceService dataSourceService;
    @Autowired
    private IntegrationService integrationService;
    @Autowired
    private DataSourceCategoryService dataSourceCategoryService;
    @Autowired
    private FrequencyService frequencyService;

    @Override
    public synchronized LookupMeta pull() {

        LookupMeta dataSourceLookupMeta = lookupMetaService.findByLookup(Lookup.DATA_SOURCE);

        Long lastUpdateNo = null;

        if (dataSourceLookupMeta != null) {
            lastUpdateNo = dataSourceLookupMeta.getLastServerUpdateNo();
        }

        DataSourceWebService dataSourceWebService = integrationService.getDataSourceWebService();

        DataSourcePayload dataSourcePayload = dataSourceWebService.getData(lastUpdateNo);

        List<DataSource> dataSources = dataSourcePayload.getDataSourceBody().getDataSources();
        if (dataSources != null && !dataSources.isEmpty()) {
            for (DataSource dataSource : dataSources) {
                DataSource existing = dataSourceService.findByUuid(dataSource.getUuid());
                if (existing != null) {
                    //ID is not send
                    dataSource.setId(existing.getId());
                } else {
                    //it must be a new object
                    dataSource.setId(null);
                }
                
                DataSourceCategory serverDataSourceCategory = dataSource.getDataSourceCategory();
                if (serverDataSourceCategory != null) {
                    DataSourceCategory localDataSourceCategory = dataSourceCategoryService.findByUuid(serverDataSourceCategory.getUuid());
                    if(localDataSourceCategory==null){
                        throw new SurvtowerRuntimeException(String.format("LocalDataSourceCategory with global uuid %s not found",serverDataSourceCategory.getUuid()));
                    }else{
                        dataSource.setDataSourceCategory(localDataSourceCategory);
                    }
                }
                
                Frequency serverFrequency=dataSource.getFrequency();
                if(serverFrequency!=null){
                    Frequency localFrequency=frequencyService.findByUuid(serverFrequency.getUuid());
                    if(localFrequency==null){
                        throw new SurvtowerRuntimeException(String.format("LocalFrequency with global uuid %s not found",serverFrequency.getUuid()));
                    }else{
                        dataSource.setFrequency(localFrequency);
                    }
                }
                
                dataSourceService.save(dataSource);
            }
        }

        if (dataSourceLookupMeta == null) {
            dataSourceLookupMeta = new LookupMeta(Lookup.DATA_SOURCE);
        }

        if (dataSourcePayload.getResponseHead() != null && dataSourcePayload.getResponseHead().getLastUpdateNo() != null) {
            dataSourceLookupMeta.setLastServerUpdateNo(dataSourcePayload.getResponseHead().getLastUpdateNo());
        }

        dataSourceLookupMeta.setUpdateDate(new Date());
        lookupMetaService.save(dataSourceLookupMeta);

        return dataSourceLookupMeta;
    }
}
