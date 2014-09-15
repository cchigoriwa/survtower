package com.survtower.business.member.dao.impl;

import com.survtower.business.member.dao.MemberUserDao;
import com.survtower.business.member.domain.MemberUser;
import com.survtower.business.member.repository.MemberUserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Takunda Dhlakama
 */
@Repository
public class MemberUserDaoImpl implements MemberUserDao {

    @Autowired
    private MemberUserRepository memberUserRepository;

    @Override
    public MemberUser save(MemberUser memberUser) {
        return memberUserRepository.save(memberUser);
    }

    @Override
    public List<MemberUser> findAll() {
        return memberUserRepository.findAll();
    }

    @Override
    public MemberUser find(Long id) {
        return memberUserRepository.findOne(id);
    }

    @Override
    public MemberUser findByUuid(String uuid) {
        return memberUserRepository.findByUuid(uuid);
    }

    @Override
    public MemberUser findByUserName(String username) {
        return memberUserRepository.findByUserName(username);
    }

    @Override
    public int updatePassword(String password, String username) {
        return memberUserRepository.updatePassword(password, username);
    }

}
