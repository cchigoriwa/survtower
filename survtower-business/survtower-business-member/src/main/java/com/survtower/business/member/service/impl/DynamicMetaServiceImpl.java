package com.survtower.business.member.service.impl;

import com.survtower.business.common.SurvtowerRuntimeException;
import com.survtower.business.common.domain.Dynamic;
import com.survtower.business.member.dao.DynamicMetaDao;
import com.survtower.business.member.domain.DynamicMeta;
import com.survtower.business.member.service.DynamicMetaService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Charles Chigoriwa
 */
@Service("dynamicMetaService")
@Transactional(readOnly = true)
public class DynamicMetaServiceImpl implements DynamicMetaService{
    
    @Autowired
    private DynamicMetaDao dynamicMetaDao;

    @Transactional
    @Override
    public synchronized DynamicMeta save(DynamicMeta dynamicMeta) {
        DynamicMeta existing=this.findByDynamic(dynamicMeta.getDynamic());
        if(existing!=null){
            if(!existing.identityEquals(dynamicMeta)){
                throw new SurvtowerRuntimeException("This record will cause a duplicate::");
            }
        }
        dynamicMeta.setUpdateDate(new Date());
        return dynamicMetaDao.save(dynamicMeta);
    }

    @Override
    public List<DynamicMeta> findAll() {
        return dynamicMetaDao.findAll();
    }

    @Override
    public DynamicMeta find(Long id) {
       return dynamicMetaDao.find(id);
    }

    @Override
    public DynamicMeta findByUuid(String uuid) {
       return dynamicMetaDao.findByUuid(uuid);
    }

    @Override
    public DynamicMeta findByDynamic(Dynamic dynamic) {
        return dynamicMetaDao.findByDynamic(dynamic);
    }
    
}
