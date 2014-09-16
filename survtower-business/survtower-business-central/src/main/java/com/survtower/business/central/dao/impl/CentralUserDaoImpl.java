package com.survtower.business.central.dao.impl;

import com.survtower.business.central.dao.CentralUserDao;
import com.survtower.business.central.domain.CentralUser;
import com.survtower.business.central.repository.CentralUserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Takunda Dhlakama
 */
@Repository
public class CentralUserDaoImpl implements CentralUserDao {

    @Autowired
    private CentralUserRepository centralUserRepository;

    @Override
    public CentralUser save(CentralUser centralUser) {
        return centralUserRepository.save(centralUser);
    }

    @Override
    public List<CentralUser> findAll() {
        return centralUserRepository.findAll();
    }

    @Override
    public CentralUser find(Long id) {
        return centralUserRepository.findOne(id);
    }

    @Override
    public CentralUser findByUuid(String uuid) {
        return centralUserRepository.findByUuid(uuid);
    }

    @Override
    public CentralUser findByUserName(String username) {
        return centralUserRepository.findByUserName(username);
    }

    @Override
    public int updatePassword(String password, String username) {
        return centralUserRepository.updatePassword(password, username);
    }

}
