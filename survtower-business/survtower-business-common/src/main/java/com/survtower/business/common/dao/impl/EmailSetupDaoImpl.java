package com.survtower.business.common.dao.impl;

import com.survtower.business.common.dao.EmailSetupDao;
import com.survtower.business.common.domain.EmailSetup;
import com.survtower.business.common.repository.EmailSetupRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Daniel Nkhoma
 */
@Repository
public class EmailSetupDaoImpl implements EmailSetupDao {

    @Autowired
    private EmailSetupRepository emailSetupRepository;

    @Override
    public EmailSetup save(EmailSetup emailSetup) {
        return emailSetupRepository.save(emailSetup);
    }

    @Override
    public List<EmailSetup> findAll() {
        return emailSetupRepository.findAll();
    }

    @Override
    public EmailSetup find(Long id) {
        return emailSetupRepository.findOne(id);
    }

    @Override
    public EmailSetup findByUuid(String uuid) {
        return emailSetupRepository.findByUuid(uuid);
    }

}
