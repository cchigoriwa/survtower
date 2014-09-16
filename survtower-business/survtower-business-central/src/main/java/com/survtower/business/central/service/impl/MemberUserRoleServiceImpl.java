package com.survtower.business.central.service.impl;

import com.survtower.business.central.dao.MemberUserRoleDao;
import com.survtower.business.central.domain.MemberUserRole;
import com.survtower.business.central.service.MemberUserRoleService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Takunda Dhlakama
 */
@Service("memberUserRoleService")
@Transactional(readOnly = true)
public class MemberUserRoleServiceImpl implements MemberUserRoleService {

    @Autowired
    private MemberUserRoleDao memberUserRoleDao;

    @Transactional
    @Override
    public MemberUserRole save(MemberUserRole memberUserRole) {
        memberUserRole.setUpdateDate(new Date());
        return memberUserRoleDao.save(memberUserRole);
    }

    @Override
    public List<MemberUserRole> findAll() {
        return memberUserRoleDao.findAll();
    }

    @Override
    public MemberUserRole find(Long id) {
        return memberUserRoleDao.find(id);
    }

    @Override
    public MemberUserRole findByUuid(String uuid) {
        return memberUserRoleDao.findByUuid(uuid);
    }

    @Override
    public List<MemberUserRole> findMemberRolesbyUser(String memberSecurity) {
        return memberUserRoleDao.findMemberRolesbyUser(memberSecurity);
    }

}
