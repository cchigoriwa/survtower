package com.survtower.business.central.service.impl;

import com.survtower.business.central.dao.MemberSecurityDao;
import com.survtower.business.central.domain.MemberSecurity;
import com.survtower.business.central.service.MemberSecurityService;
import com.survtower.business.common.domain.Member;
import com.survtower.business.common.service.PasswordGeneratorService;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Charles Chigoriwa
 */
@Service("memberSecurityService")
@Transactional(readOnly = true)
public class MemberSecurityServiceImpl implements MemberSecurityService {

    @Autowired
    private MemberSecurityDao memberSecurityDao;
    @Autowired
    private PasswordGeneratorService passwordGeneratorService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public synchronized MemberSecurity save(MemberSecurity memberSecurity) {

        if (findByUuid(memberSecurity.getUuid()) == null) {
            //if this is a new member security
            //To be improved later so far based on UUID --hackable
            memberSecurity.setMemberID(UUID.randomUUID().toString());
            memberSecurity.setMemberKey(UUID.randomUUID().toString());

            String rawPassword = memberSecurity.getPassword();
            if (rawPassword == null) {
                rawPassword = passwordGeneratorService.generatePassword();
            }
            memberSecurity = createNewUser(memberSecurity, "test1234");
        } else {
            memberSecurity = memberSecurityDao.save(memberSecurity);
        }
        return memberSecurity;
    }

    private MemberSecurity createNewUser(MemberSecurity memberSecurity, String rawPassword) {
        String encriptedPassword = passwordEncoder.encodePassword(rawPassword, memberSecurity.getEmailAddress());
        memberSecurity.setPassword(encriptedPassword);
        memberSecurity.setUpdateDate(new Date());
        memberSecurity = memberSecurityDao.save(memberSecurity);
        return memberSecurity;
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

    @Override
    public MemberSecurity findByEmailAddress(String emailAddress) {
        return memberSecurityDao.findByEmailAddress(emailAddress);
    }

    @Override
    public int updatePassword(String password, String emailAddress) {
        return memberSecurityDao.updatePassword(password, emailAddress);
    }

    @Override
    public int updateMemberKey(String memberKey, String emailAddress) {
        return memberSecurityDao.updateMemberKey(memberKey, emailAddress);
    }

    @Override
    public MemberSecurity findByMemberID(String MemberID) {
        return memberSecurityDao.findByMemberID(MemberID);
    }

}
