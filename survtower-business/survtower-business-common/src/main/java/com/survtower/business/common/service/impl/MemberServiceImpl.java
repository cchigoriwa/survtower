package com.survtower.business.common.service.impl;

import com.survtower.business.common.SurvtowerRuntimeException;
import com.survtower.business.common.dao.MemberDao;
import com.survtower.business.common.domain.Member;
import com.survtower.business.common.service.MemberService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Charles Chigoriwa
 * @author Takunda Dhlakama
 */
@Service("memberService")
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDao memberDao;

    @Transactional
    @Override
    public Member save(Member country) {
        country.setUpdateDate(new Date());
        return memberDao.save(country);
    }

    @Override
    public List<Member> findAll() {
        return memberDao.findAll();
    }

    @Override
    public Member find(Long id) {
        return memberDao.find(id);
    }

    @Override
    public Member findByUuid(String uuid) {
        return memberDao.findByUuid(uuid);
    }

    @Override
    public List<Member> findMembersUpdatedAfter(Date afterDate) {
        return memberDao.findMembersUpdatedAfter(afterDate);
    }

    @Override
    public Date findMaximumUpdateDate(Date afterDate) {
        return memberDao.findMaximumUpdateDate(afterDate);
    }

    @Override
    public List<Member> findMembersUpdatedAfter(Date afterDate, Date maxDate) {
        return memberDao.findMembersUpdatedAfter(afterDate, maxDate);
    }

    @Override
    public Date findMaximumUpdateDate() {
        return memberDao.findMaximumUpdateDate();
    }

    @Override
    public List<Member> findMembersUpdatedBefore(Date maxDate) {
        return memberDao.findMembersUpdatedBefore(maxDate);
    }

    @Override
    public Member getCurrentMember() {
        Member member = null;
        List<Member> list = findAll();
        if (list.size() > 1) {
            throw new SurvtowerRuntimeException("Members in a member state app should not exceed 1");
        } else if (list.size() == 1) {
            member = list.get(0);
        }
        return member;
    }

}
