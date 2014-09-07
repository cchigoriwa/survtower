package com.survtower.business.central.repository;

import com.survtower.business.central.domain.MemberSecurity;
import com.survtower.business.common.domain.Member;
import net.sadc.business.common.GenericRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Charles Chigoriwa
 */
public interface MemberSecurityRepository extends GenericRepository<MemberSecurity, Long> {

    public MemberSecurity findByMember(Member member);

    public MemberSecurity findByEmailAddress(String emailAddress);
    
    public MemberSecurity findByMemberID(String MemberID);

    @Modifying
    @Transactional
    @Query("update MemberSecurity t set t.password = ?1 where t.emailAddress = ?2")
    public int updatePassword(String password, String emailAddress);

    @Modifying
    @Transactional
    @Query("update MemberSecurity t set t.memberKey = ?1 where t.emailAddress = ?2")
    public int updateMemberKey(String memberKey, String emailAddress);

}
