package com.survtower.business.member.dao.impl;

import com.survtower.business.member.dao.MemberUserRoleDao;
import com.survtower.business.member.domain.MemberUserRole;
import com.survtower.business.member.repository.MemberUserRoleRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Takunda Dhlakama
 */
@Repository
public class MemberUserRoleDaoImpl implements MemberUserRoleDao {

    @Autowired
    private MemberUserRoleRepository memberUserRoleRepository;

    @Override
    public MemberUserRole save(MemberUserRole memberUserRole) {
        return memberUserRoleRepository.save(memberUserRole);
    }

    @Override
    public List<MemberUserRole> findAll() {
        return memberUserRoleRepository.findAll();
    }

    @Override
    public MemberUserRole find(Long id) {
        return memberUserRoleRepository.findOne(id);
    }

    @Override
    public MemberUserRole findByUuid(String uuid) {
        return memberUserRoleRepository.findByUuid(uuid);
    }

}
