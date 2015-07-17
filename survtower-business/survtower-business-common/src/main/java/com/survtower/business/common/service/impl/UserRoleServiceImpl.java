package com.survtower.business.common.service.impl;

import com.survtower.business.common.dao.UserRoleDao;
import com.survtower.business.common.domain.UserRole;
import com.survtower.business.common.service.UserRoleService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Charles Chigoriwa
 */
@Service("userRoleService")
@Transactional(readOnly = true)
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleDao userRoleDao;

    @Transactional
    @Override
    public UserRole save(UserRole userRole) {       
        userRole.setUpdateDate(new Date());
        return userRoleDao.save(userRole);
    }

    @Override
    public List<UserRole> findAll() {
        return userRoleDao.findAll();
    }

    @Override
    public UserRole find(Long id) {
        return userRoleDao.find(id);
    }

    @Override
    public UserRole findByUuid(String uuid) {
        return userRoleDao.findByUuid(uuid);
    }

    @Override
    public UserRole findByRole(String role) {
       return userRoleDao.findByRole(role);
    }
    
   
}
