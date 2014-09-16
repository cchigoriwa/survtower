package com.survtower.business.central.service;

import com.survtower.business.central.domain.MemberSecurity;
import com.survtower.business.common.GenericService;
import com.survtower.business.common.domain.Member;
import java.util.List;

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

    /**
     * @return the username of the currently logged in user. If no user is
     * logged in null is returned.
     */
    String getCurrentUsername();

    /**
     * @return the currently logged in user. If no user is logged in null is
     * returned if not active.
     */
    MemberSecurity getCurrentUser();

    /**
     * @return a list of role of the current logged in
     */
    List<String> getCurrentUserRoles();
}
