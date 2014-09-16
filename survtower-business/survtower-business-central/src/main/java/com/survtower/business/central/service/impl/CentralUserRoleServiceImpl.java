package com.survtower.business.central.service.impl;

import com.survtower.business.central.dao.CentralUserRoleDao;
import com.survtower.business.central.domain.CentralUserRole;
import com.survtower.business.central.service.CentralUserRoleService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Takunda Dhlakama
 */
@Service("centralUserRoleService")
@Transactional(readOnly = true)
public class CentralUserRoleServiceImpl implements CentralUserRoleService {

    @Autowired
    private CentralUserRoleDao centralUserRoleDao;

    @Transactional
    @Override
    public CentralUserRole save(CentralUserRole centralUserRole) {
        centralUserRole.setUpdateDate(new Date());
        return centralUserRoleDao.save(centralUserRole);
    }

    @Override
    public List<CentralUserRole> findAll() {
        return centralUserRoleDao.findAll();
    }

    @Override
    public CentralUserRole find(Long id) {
        return centralUserRoleDao.find(id);
    }

    @Override
    public CentralUserRole findByUuid(String uuid) {
        return centralUserRoleDao.findByUuid(uuid);
    }

}
