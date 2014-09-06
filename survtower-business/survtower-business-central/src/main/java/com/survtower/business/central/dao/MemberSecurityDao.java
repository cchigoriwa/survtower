package com.survtower.business.central.dao;

import com.survtower.business.central.domain.MemberSecurity;
import com.survtower.business.common.GenericDao;
import com.survtower.business.common.domain.Member;

/**
 *
 * @author Charles Chigoriwa
 */
public interface MemberSecurityDao extends GenericDao<MemberSecurity>{
    public MemberSecurity findByMember(Member member);
}