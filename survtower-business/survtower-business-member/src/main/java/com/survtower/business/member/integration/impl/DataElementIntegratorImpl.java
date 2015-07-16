package com.survtower.business.member.integration.impl;

import com.survtower.business.common.SurvtowerRuntimeException;
import com.survtower.business.common.domain.DataElement;
import com.survtower.business.common.domain.DataSource;
import com.survtower.business.common.domain.Lookup;
import com.survtower.business.common.service.DataElementService;
import com.survtower.business.common.service.DataSourceService;
import com.survtower.business.member.domain.LookupMeta;
import com.survtower.business.member.integration.DataElementIntegrator;
import com.survtower.business.member.integration.IntegrationService;
import com.survtower.business.member.service.LookupMetaService;
import com.survtower.ws.api.DataElementWebService;
import com.survtower.ws.api.domain.DataElementPayload;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Charles Chigoriwa
 */
@Component("dateElementIntegrator")
public class DataElementIntegratorImpl implements DataElementIntegrator {

    @Autowired
    private LookupMetaService lookupMetaService;
    @Autowired
    private DataElementService dataElementService;
    @Autowired
    private IntegrationService integrationService;
    @Autowired
    private DataSourceService dataSourceService;
    

    @Override
    public synchronized LookupMeta pull() {

        LookupMeta dateElementLookupMeta=lookupMetaService.findByLookup(Lookup.DATA_ELEMENT);

        Long startUpdateNo = null;

        if (dateElementLookupMeta != null) {
            startUpdateNo = dateElementLookupMeta.getLastServerUpdateNo();
        }

        
     
        DataElementWebService dateElementWebservice = integrationService.getDataElementWebService();

        DataElementPayload dateElementPayload = dateElementWebservice.getData(startUpdateNo);

        List<DataElement> dateElements = dateElementPayload.getDataElementBody().getDataElements();
        if (dateElements != null && !dateElements.isEmpty()) {
            for (DataElement dataElement : dateElements) {
                DataElement existing = dataElementService.findByUuid(dataElement.getUuid());
                if (existing != null) {
                    //ID is not send
                    dataElement.setId(existing.getId());
                }else{
                    //it must be a new object
                    dataElement.setId(null);
                }
                
                DataSource serverDataSource=dataElement.getDataSource();
                if(serverDataSource!=null){
                    DataSource localDataSource=dataSourceService.findByUuid(serverDataSource.getUuid());
                    if(localDataSource==null){
                        throw new SurvtowerRuntimeException(String.format("localDataSource with global uuid %s not found",serverDataSource.getUuid()));
                    }else{
                        dataElement.setDataSource(localDataSource);
                    }
                }
                
                dataElementService.save(dataElement);
            }
        }

        if (dateElementLookupMeta == null) {
            dateElementLookupMeta = new LookupMeta(Lookup.DATA_ELEMENT);
        }

        if (dateElementPayload.getResponseHead() != null && dateElementPayload.getResponseHead().getLastUpdateNo() != null) {
            dateElementLookupMeta.setLastServerUpdateNo(dateElementPayload.getResponseHead().getLastUpdateNo());
        }
        
        dateElementLookupMeta.setUpdateDate(new Date());
        lookupMetaService.save(dateElementLookupMeta);

        return dateElementLookupMeta;
    }
}
