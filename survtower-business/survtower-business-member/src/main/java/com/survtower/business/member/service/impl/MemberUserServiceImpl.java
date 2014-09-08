package com.survtower.business.member.service.impl;

import com.survtower.business.member.dao.MemberUserDao;
import com.survtower.business.member.domain.MemberUser;
import com.survtower.business.member.service.MemberUserService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Takunda Dhlakama
 */
@Service("memberUserService")
@Transactional(readOnly = true)
public class MemberUserServiceImpl implements MemberUserService {

    @Autowired
    private MemberUserDao memberUserDao;

    @Transactional
    @Override
    public MemberUser save(MemberUser memberUser) {
        memberUser.setUpdateDate(new Date());
        return memberUserDao.save(memberUser);
    }

    @Override
    public List<MemberUser> findAll() {
        return memberUserDao.findAll();
    }

    @Override
    public MemberUser find(Long id) {
        return memberUserDao.find(id);
    }

    @Override
    public MemberUser findByUuid(String uuid) {
        return memberUserDao.findByUuid(uuid);
    }

    @Override
    public List<MemberUser> findMemberUsersUpdatedAfter(Date afterDate) {
        return memberUserDao.findMemberUsersUpdatedAfter(afterDate);
    }

    @Override
    public Date findMaximumUpdateDate(Date afterDate) {
        return memberUserDao.findMaximumUpdateDate(afterDate);
    }

    @Override
    public List<MemberUser> findMemberUsersUpdatedAfter(Date afterDate, Date maxDate) {
        return memberUserDao.findMemberUsersUpdatedAfter(afterDate, maxDate);
    }

    @Override
    public Date findMaximumUpdateDate() {
        return memberUserDao.findMaximumUpdateDate();
    }

    @Override
    public List<MemberUser> findMemberUsersUpdatedBefore(Date maxDate) {
        return memberUserDao.findMemberUsersUpdatedBefore(maxDate);
    }
}
