package com.survtower.business.common.service;

import com.survtower.business.common.GenericService;
import com.survtower.business.common.domain.UserRole;

/**
 *
 * @author Charles Chigoriwa
 */
public interface UserRoleService extends GenericService<UserRole>{
    
    public UserRole findByRole(String role);
}
