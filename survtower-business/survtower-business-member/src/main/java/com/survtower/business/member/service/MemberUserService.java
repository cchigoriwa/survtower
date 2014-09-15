package com.survtower.business.member.service;

import com.survtower.business.common.GenericService;
import com.survtower.business.member.domain.MemberUser;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Takunda Dhlakama
 */
public interface MemberUserService extends GenericService<MemberUser>{
    
    public MemberUser findByUserName(String username);

    public int updatePassword(String password, String username);  public List<MemberUser> findMemberUsersUpdatedAfter(Date afterDate);

    /**
     * @return the username of the currently logged in user. If no user is
     * logged in or the auto access admin is active, null is returned.
     */
    String getCurrentUsername();

    /**
     * @return the currently logged in user. If no user is logged in or the auto
     * access admin is active, null is returned.
     */
    MemberUser getCurrentUser();

    /**
     * @return true if the current logged in user has the ALL priviliges set,
     * false otherwise.
     */
}
