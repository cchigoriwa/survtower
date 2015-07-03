package com.survtower.business.common.service.impl;

import com.survtower.business.common.dao.EmailSetupDao;
import com.survtower.business.common.domain.EmailSetup;
import com.survtower.business.common.service.EmailSetupService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Daniel Nkhoma
 */
@Service("emailSetupService")
@Transactional(readOnly = true)
public class EmailSetupServiceImpl implements EmailSetupService {

    @Autowired
    private EmailSetupDao emailSetupDao;

    @Transactional
    @Override
    public EmailSetup save(EmailSetup emailSetup) {
        EmailSetup existingEmailSetup=find();
        if(emailSetup.getId()==null && existingEmailSetup!=null){
            throw new IllegalStateException("The save will result in more than one email setups");
        }else if(emailSetup.getId()!=null && !emailSetup.identityEquals(existingEmailSetup)){
              throw new IllegalStateException("The save will result in more than one email setups");
        }
        
        emailSetup.setUpdateDate(new Date());
        return emailSetupDao.save(emailSetup);
    }

    @Override
    public List<EmailSetup> findAll() {
        return emailSetupDao.findAll();
    }

    @Override
    public EmailSetup find(Long id) {
        return emailSetupDao.find(id);
    }

    @Override
    public EmailSetup findByUuid(String uuid) {
        return emailSetupDao.findByUuid(uuid);
    }
    
    @Override
    public EmailSetup find(){
        EmailSetup emailSetup=null;
        List<EmailSetup> list=findAll();
        if(list.size()>1){
            throw new IllegalStateException("More than one email setups");
        }else if(list.size()==1){
            emailSetup=list.get(0);
        }
        
        return emailSetup;
        
    }
}
