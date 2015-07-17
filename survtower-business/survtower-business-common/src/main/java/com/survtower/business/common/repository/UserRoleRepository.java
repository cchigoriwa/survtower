package com.survtower.business.common.repository;

import com.survtower.business.common.domain.UserRole;
import net.sadc.business.common.GenericRepository;

/**
 *
 * @author Charles Chigoriwa
 * @author Daniel Nkhoma
 */
public interface UserRoleRepository extends GenericRepository<UserRole, Long> {
   
    public UserRole findByRole(String role);
   
}
