package com.survtower.business.common.dao.impl;

import com.survtower.business.common.dao.MemberDao;
import com.survtower.business.common.domain.Member;
import com.survtower.business.common.repository.MemberRepository;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Charles Chigoriwa
 * @author Takunda Dhlakama
 */
@Repository
public class MemberDaoImpl implements MemberDao{
    
    @Autowired
    private MemberRepository memberRepository;

    @Override
    public Member save(Member member) {
       return memberRepository.save(member);
    }

    @Override
    public List<Member> findAll() {
       return memberRepository.findAll();
    }

    @Override
    public Member find(Long id) {
        return memberRepository.findOne(id);
    }

    @Override
    public Member findByUuid(String uuid) {
       return memberRepository.findByUuid(uuid);
    }

    @Override
    public List<Member> findMembersUpdatedAfter(Date afterDate) {
        return memberRepository.findMembersUpdatedAfter(afterDate);
    }

    @Override
    public Date findMaximumUpdateDate(Date afterDate) {
       return memberRepository.findMaximumUpdateDate(afterDate);
    }

    @Override
    public List<Member> findMembersUpdatedAfter(Date afterDate, Date maxDate) {
        return memberRepository.findMembersUpdatedAfter(afterDate, maxDate);
    }

    @Override
    public Date findMaximumUpdateDate() {
        return memberRepository.findMaximumUpdateDate();
    }

    @Override
    public List<Member> findMembersUpdatedBefore(Date maxDate) {
      return memberRepository.findMembersUpdatedBefore(maxDate);
    }
    
}
