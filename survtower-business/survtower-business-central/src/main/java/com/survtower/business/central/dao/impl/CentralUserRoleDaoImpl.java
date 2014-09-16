package com.survtower.business.central.dao.impl;

import com.survtower.business.central.dao.CentralUserRoleDao;
import com.survtower.business.central.domain.CentralUserRole;
import com.survtower.business.central.repository.CentralUserRoleRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Takunda Dhlakama
 */
@Repository
public class CentralUserRoleDaoImpl implements CentralUserRoleDao {

    @Autowired
    private CentralUserRoleRepository centralUserRoleRepository;

    @Override
    public CentralUserRole save(CentralUserRole centralUserRole) {
        return centralUserRoleRepository.save(centralUserRole);
    }

    @Override
    public List<CentralUserRole> findAll() {
        return centralUserRoleRepository.findAll();
    }

    @Override
    public CentralUserRole find(Long id) {
        return centralUserRoleRepository.findOne(id);
    }

    @Override
    public CentralUserRole findByUuid(String uuid) {
        return centralUserRoleRepository.findByUuid(uuid);
    }

}
