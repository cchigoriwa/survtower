package com.survtower.business.central.dao;

import com.survtower.business.central.domain.CentralUser;
import com.survtower.business.common.GenericDao;

/**
 *
 * @author Takunda Dhlakama
 */
public interface CentralUserDao extends GenericDao<CentralUser> {

    public CentralUser findByUserName(String username);

    public int updatePassword(String password, String username);
    
    public CentralUser findByEmail(String email);

}
