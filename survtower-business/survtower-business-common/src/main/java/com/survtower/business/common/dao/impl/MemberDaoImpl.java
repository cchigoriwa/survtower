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
 */
@Repository
public class MemberDaoImpl implements MemberDao{
    
    @Autowired
    private MemberRepository countryRepository;

    @Override
    public Member save(Member country) {
       return countryRepository.save(country);
    }

    @Override
    public List<Member> findAll() {
       return countryRepository.findAll();
    }

    @Override
    public Member find(Long id) {
        return countryRepository.findOne(id);
    }

    @Override
    public Member findByUuid(String uuid) {
       return countryRepository.findByUuid(uuid);
    }

    @Override
    public List<Member> findMembersUpdatedAfter(Date afterDate) {
        return countryRepository.findMembersUpdatedAfter(afterDate);
    }

    @Override
    public Date findMaximumUpdateDate(Date afterDate) {
       return countryRepository.findMaximumUpdateDate(afterDate);
    }

    @Override
    public List<Member> findMembersUpdatedAfter(Date afterDate, Date maxDate) {
        return countryRepository.findMembersUpdatedAfter(afterDate, maxDate);
    }

    @Override
    public Date findMaximumUpdateDate() {
        return countryRepository.findMaximumUpdateDate();
    }

    @Override
    public List<Member> findMembersUpdatedBefore(Date maxDate) {
      return countryRepository.findMembersUpdatedBefore(maxDate);
    }
    
}
