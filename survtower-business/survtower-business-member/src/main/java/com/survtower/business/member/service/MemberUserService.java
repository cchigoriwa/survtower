package com.survtower.business.member.service;

import com.survtower.business.common.GenericService;
import com.survtower.business.common.domain.Program;
import com.survtower.business.member.domain.MemberUser;
import com.survtower.business.member.domain.Region;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Takunda Dhlakama
 */
public interface MemberUserService extends GenericService<MemberUser> {

    public MemberUser findByUserName(String username);

    public int updatePassword(String password, String username);

    public List<MemberUser> findMemberUsersUpdatedAfter(Date afterDate);

    /**
     * @return the username of the currently logged in user. If no user is
     * logged in null is returned.
     */
    String getCurrentUsername();

    /**
     * @return the currently logged in user. If no user is logged in null is
     * returned if not active.
     */
    MemberUser getCurrentUser();

    /**
     * @return a list of role of the current logged in
     */
    List<String> getCurrentUserRoles();

    /**
     * @return a list of programs of the current logged in user
     */
    List<Program> getCurrentUserPrograms();

    /**
     * @return a list of regions of the current logged in user
     */
    List<Region> getCurrentUserRegions();

    public MemberUser findByEmail(String email);

}
