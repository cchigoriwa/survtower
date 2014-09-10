package com.survtower.ws.impl;

import com.survtower.business.central.service.MemberSecurityService;
import com.survtower.business.common.domain.Member;
import com.survtower.ws.api.MemberWebservice;
import com.survtower.ws.api.domain.MemberCollectionPayload;
import com.survtower.ws.api.domain.RequestMetaData;
import com.survtower.ws.api.domain.ResponseMetaData;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 *
 * @author Takunda Dhlakama
 * @author Charles Chigoriwa
 * 
 * This getMembers is a bit different from other webservices
 * We only want to get specific member details of a member querying this.
 * We don't want to get details of other members.
 * 
 */
@Component
public class MemberWebserviceImpl implements MemberWebservice {

    @Autowired
    private MemberSecurityService memberSecurityService;

    public MemberCollectionPayload getMembers(RequestMetaData requestMetaData) {

        Date startDate = null;
        if (requestMetaData != null) {
            if (requestMetaData.getMinimumDate() != null) {
                startDate = requestMetaData.getMinimumDate();
            }
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String memberID = auth.getName();
        Member member;
        member = memberSecurityService.findByMemberID(memberID).getMember();

        ResponseMetaData responseMetaData = new ResponseMetaData();
        if (startDate == null || member.getUpdateDate().after(startDate)) {
            responseMetaData.setMaximumDate(member.getUpdateDate());
        }else{
            member=null;
        }
        
        MemberCollectionPayload memberCollectionPayload = new MemberCollectionPayload();
        memberCollectionPayload.setPayloadMetaData(responseMetaData);
        memberCollectionPayload.setMember(member);

        return memberCollectionPayload;

    }
}
