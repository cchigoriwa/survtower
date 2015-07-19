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

    public CentralUser findByEmail(String email);

}
