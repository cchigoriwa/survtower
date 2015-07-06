package com.survtower.business.central.service;

import com.survtower.business.central.domain.CentralUser;
import com.survtower.business.common.GenericService;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Takunda Dhlakama
 */
public interface CentralUserService extends GenericService<CentralUser> {

    public CentralUser findByUserName(String username);

    public int updatePassword(String password, String username);

    public List<CentralUser> findCentralUsersUpdatedAfter(Date afterDate);

    /**
     * @return the username of the currently logged in user. If no user is
     * logged in null is returned.
     */
    String getCurrentUsername();

    /**
     * @return the currently logged in user. If no user is logged in null is
     * returned if not active.
     */
    CentralUser getCurrentUser();

    /**
     * @return a list of role of the current logged in
     */
    List<String> getCurrentUserRoles();
    
    public CentralUser findByEmail(String email);

}
