package com.survtower.business.central.service;

import com.survtower.business.central.domain.MemberSecurity;
import com.survtower.business.common.GenericService;
import com.survtower.business.common.domain.Member;

/**
 *
 * @author Charles Chigoriwa
 */
public interface MemberSecurityService extends GenericService<MemberSecurity> {

    public MemberSecurity findByMember(Member member);

    public MemberSecurity findByEmailAddress(String emailAddress);

    public int updatePassword(String password, String emailAddress);

    public int updateMemberKey(String memberKey, String emailAddress);

    public MemberSecurity findByMemberID(String memberID);

}
