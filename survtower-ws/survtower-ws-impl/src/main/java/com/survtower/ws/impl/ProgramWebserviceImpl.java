package com.survtower.ws.impl;

import com.survtower.business.common.domain.Program;
import com.survtower.business.common.service.ProgramService;
import com.survtower.ws.api.ProgramWebservice;
import com.survtower.ws.api.domain.ProgramCollectionPayload;
import com.survtower.ws.api.domain.RequestMetaData;
import com.survtower.ws.api.domain.ResponseMetaData;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Takunda Dhlakama
 */
@Component
public class ProgramWebserviceImpl implements ProgramWebservice {

    @Autowired
    private ProgramService programService;

    public ProgramCollectionPayload getPrograms(RequestMetaData requestMetaData) {

        List<Program> programs;
        
        Date startDate = null;
        Date endDate;
        if (requestMetaData != null) {
            if (requestMetaData.getMinimumDate() != null) {
                startDate = requestMetaData.getMinimumDate();
            }
        }

        if (startDate != null) {
            endDate = programService.findMaximumUpdateDate(startDate);
        }else{
            endDate=programService.findMaximumUpdateDate();
        }
        
        if(endDate!=null){
            if(startDate==null){
                //inclusive endDate (before or as at)-name is a bit misleading
                programs=programService.findProgramsUpdatedBefore(endDate);
            }else{
                //exclusive startDate and inclusive endDate
                programs=programService.findProgramsUpdatedAfter(startDate, endDate);
            }
        }else{
            programs=new ArrayList<Program>();
        }
        
        ResponseMetaData responseMetaData=new ResponseMetaData();
        responseMetaData.setMaximumDate(endDate);
        
        ProgramCollectionPayload programCollectionPayload=new ProgramCollectionPayload();
        programCollectionPayload.setPayloadMetaData(responseMetaData);
        programCollectionPayload.setPrograms(programs);
        
        return programCollectionPayload;
        
        
        

    }
}
