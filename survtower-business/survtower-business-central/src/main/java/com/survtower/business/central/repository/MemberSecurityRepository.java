package com.survtower.business.central.repository;

import com.survtower.business.central.domain.MemberSecurity;
import com.survtower.business.common.domain.Member;
import net.sadc.business.common.GenericRepository;

/**
 *
 * @author Charles Chigoriwa
 */
public interface MemberSecurityRepository extends GenericRepository<MemberSecurity, Long> {

    public MemberSecurity findByMember(Member member);
}
