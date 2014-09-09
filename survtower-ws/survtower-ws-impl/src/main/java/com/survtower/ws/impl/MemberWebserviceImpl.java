package com.survtower.ws.impl;

import com.survtower.business.common.domain.Member;
import com.survtower.business.common.service.MemberService;
import com.survtower.ws.api.MemberWebservice;
import com.survtower.ws.api.domain.MemberCollectionPayload;
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
public class MemberWebserviceImpl implements MemberWebservice {

    @Autowired
    private MemberService memberService;

    public MemberCollectionPayload getMembers(RequestMetaData requestMetaData) {

        List<Member> members;
        
        Date startDate = null;
        Date endDate;
        if (requestMetaData != null) {
            if (requestMetaData.getMinimumDate() != null) {
                startDate = requestMetaData.getMinimumDate();
            }
        }

        if (startDate != null) {
            endDate = memberService.findMaximumUpdateDate(startDate);
        }else{
            endDate=memberService.findMaximumUpdateDate();
        }
        
        if(endDate!=null){
            if(startDate==null){
                //inclusive endDate (before or as at)-name is a bit misleading
                members=memberService.findMembersUpdatedBefore(endDate);
            }else{
                //exclusive startDate and inclusive endDate
                members=memberService.findMembersUpdatedAfter(startDate, endDate);
            }
        }else{
            members=new ArrayList<Member>();
        }
        
        ResponseMetaData responseMetaData=new ResponseMetaData();
        responseMetaData.setMaximumDate(endDate);
        
        MemberCollectionPayload memberCollectionPayload=new MemberCollectionPayload();
        memberCollectionPayload.setPayloadMetaData(responseMetaData);
        memberCollectionPayload.setMembers(members);
        
        return memberCollectionPayload;
        
        
        

    }
}
