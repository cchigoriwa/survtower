package com.survtower.business.common.service.impl;

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
 */
@Service("memberService")
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDao countryDao;

    @Transactional
    @Override
    public Member save(Member country) {
        country.setUpdateDate(new Date());
        return countryDao.save(country);
    }

    @Override
    public List<Member> findAll() {
        return countryDao.findAll();
    }

    @Override
    public Member find(Long id) {
        return countryDao.find(id);
    }

    @Override
    public Member findByUuid(String uuid) {
        return countryDao.findByUuid(uuid);
    }

    @Override
    public List<Member> findCountrysUpdatedAfter(Date afterDate) {
        return countryDao.findCountrysUpdatedAfter(afterDate);
    }

    @Override
    public Date findMaximumUpdateDate(Date afterDate) {
        return countryDao.findMaximumUpdateDate(afterDate);
    }

    @Override
    public List<Member> findCountrysUpdatedAfter(Date afterDate, Date maxDate) {
        return countryDao.findCountrysUpdatedAfter(afterDate, maxDate);
    }

    @Override
    public Date findMaximumUpdateDate() {
        return countryDao.findMaximumUpdateDate();
    }

    @Override
    public List<Member> findCountrysUpdatedBefore(Date maxDate) {
        return countryDao.findCountrysUpdatedBefore(maxDate);
    }
}
