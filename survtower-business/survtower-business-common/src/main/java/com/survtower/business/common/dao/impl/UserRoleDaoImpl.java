package com.survtower.business.common.dao.impl;

import com.survtower.business.common.dao.UserRoleDao;
import com.survtower.business.common.domain.UserRole;
import com.survtower.business.common.repository.UserRoleRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Daniel Nkhoma
 */
@Repository
public class UserRoleDaoImpl implements UserRoleDao {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public UserRole save(UserRole userRole) {
        return userRoleRepository.save(userRole);
    }

    @Override
    public List<UserRole> findAll() {
        return userRoleRepository.findAll();
    }

    @Override
    public UserRole find(Long id) {
        return userRoleRepository.findOne(id);
    }

    @Override
    public UserRole findByUuid(String uuid) {
        return userRoleRepository.findByUuid(uuid);
    }

    @Override
    public UserRole findByRole(String role) {
       return userRoleRepository.findByRole(role);
    }

}
