package com.survtower.ws.impl;

import com.survtower.business.central.service.MemberSecurityService;
import com.survtower.business.common.domain.Member;
import com.survtower.ws.api.MemberWebService;
import com.survtower.ws.api.domain.MemberBody;
import com.survtower.ws.api.domain.MemberPayload;
import com.survtower.ws.api.domain.ResponseHead;
import javax.ws.rs.QueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 *
 * @author Takunda Dhlakama
 * @author Charles Chigoriwa
 *
 * This getMembers is a bit different from other webservices We only want to get
 * specific member details of a member querying this. We don't want to get
 * details of other members.
 *
 */
@Component
public class MemberWebServiceImpl implements MemberWebService {

    @Autowired
    private MemberSecurityService memberSecurityService;

    public MemberPayload getData(@QueryParam(value = "lastUpdatedNo") Long lastUpdateNo) {

        MemberPayload memberPayload = new MemberPayload();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String memberID = auth.getName();
        Member member;
        member = memberSecurityService.findByMemberID(memberID).getMember();

        ResponseHead head = new ResponseHead();
        if (lastUpdateNo == null || member.getUpdateNo() > lastUpdateNo) {
            head.setLastUpdateNo(member.getUpdateNo());
        } else {
            member = null;
        }

        memberPayload.setResponseHead(head);

        MemberBody memberBody = new MemberBody();
        memberBody.setMember(member);
        memberPayload.setMemberBody(memberBody);

        return memberPayload;

    }
}
