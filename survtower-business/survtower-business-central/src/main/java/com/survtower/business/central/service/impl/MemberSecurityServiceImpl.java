package com.survtower.business.central.service.impl;

import com.survtower.business.central.dao.MemberSecurityDao;
import com.survtower.business.central.domain.MemberSecurity;
import com.survtower.business.central.service.MemberSecurityService;
import com.survtower.business.common.domain.Member;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Charles Chigoriwa
 */
@Service("memberSecurityService")
@Transactional(readOnly = true)
public class MemberSecurityServiceImpl implements MemberSecurityService{
    
    @Autowired
    private MemberSecurityDao memberSecurityDao;

    @Transactional
    @Override
    public synchronized MemberSecurity save(MemberSecurity memberSecurity) {
        memberSecurity.setUpdateDate(new Date());
        if(findByUuid(memberSecurity.getUuid())==null){
            //if this is a new member security
            //To be improved later so far based on UUID --hackable
            memberSecurity.setMemberID(UUID.randomUUID().toString());
            memberSecurity.setMemberKey(UUID.randomUUID().toString());
            memberSecurity.setPassword("test"+new Random().nextInt());            
        }
       return memberSecurityDao.save(memberSecurity);
    }

    @Override
    public List<MemberSecurity> findAll() {
       return memberSecurityDao.findAll();
    }

    @Override
    public MemberSecurity find(Long id) {
        return memberSecurityDao.find(id);
    }

    @Override
    public MemberSecurity findByUuid(String uuid) {
        return memberSecurityDao.findByUuid(uuid);
    }

    @Override
    public MemberSecurity findByMember(Member member) {
        return memberSecurityDao.findByMember(member);
    }
    
}
