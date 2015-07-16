package com.survtower.business.member.integration.impl;

import com.survtower.business.common.domain.Lookup;
import com.survtower.business.common.domain.Member;
import com.survtower.business.common.service.MemberService;
import com.survtower.business.member.domain.LookupMeta;
import com.survtower.business.member.integration.IntegrationService;
import com.survtower.business.member.integration.MemberIntegrator;
import com.survtower.business.member.service.LookupMetaService;
import com.survtower.ws.api.MemberWebService;
import com.survtower.ws.api.domain.MemberPayload;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Charles Chigoriwa
 */
@Component("memberIntegrator")
public class MemberIntegratorImpl implements MemberIntegrator {

    @Autowired
    private LookupMetaService lookupMetaService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private IntegrationService integrationService;

    @Override
    public LookupMeta pull() {
        LookupMeta memberLookupMeta = lookupMetaService.findByLookup(Lookup.MEMBER);

        Long lastUpdateNo = null;

        if (memberLookupMeta != null) {
            lastUpdateNo = memberLookupMeta.getLastServerUpdateNo();
        }

        MemberWebService memberWebservice = integrationService.getMemberWebService();

        MemberPayload memberPayload = memberWebservice.getData(lastUpdateNo);

        Member member = memberPayload.getMemberBody().getMember();
        if (member != null) {
            Member existing = memberService.findByUuid(member.getUuid());
            if (existing != null) {
                //ID is not send
                member.setId(existing.getId());
            } else {
                //it must be a new object
                member.setId(null);
            }
            memberService.save(member);

        }

        if (memberLookupMeta == null) {
            memberLookupMeta = new LookupMeta(Lookup.MEMBER);
        }

        if (memberPayload.getResponseHead() != null && memberPayload.getResponseHead().getLastUpdateNo() != null) {
            memberLookupMeta.setLastServerUpdateNo(memberPayload.getResponseHead().getLastUpdateNo());
        }

        memberLookupMeta.setUpdateDate(new Date());
        memberLookupMeta = lookupMetaService.save(memberLookupMeta);

        return memberLookupMeta;
    }

}
