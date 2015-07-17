package com.survtower.business.common.dao;

import com.survtower.business.common.GenericDao;
import com.survtower.business.common.domain.UserRole;

/**
 *
 * @author Charles Chigoriwa
 */
public interface UserRoleDao extends GenericDao<UserRole> {

    public UserRole findByRole(String role);
    
    

}
