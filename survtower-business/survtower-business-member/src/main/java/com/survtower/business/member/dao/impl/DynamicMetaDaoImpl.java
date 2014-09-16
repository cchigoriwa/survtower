package com.survtower.business.member.dao.impl;

import com.survtower.business.common.domain.Dynamic;
import com.survtower.business.member.dao.DynamicMetaDao;
import com.survtower.business.member.domain.DynamicMeta;
import com.survtower.business.member.repository.DynamicMetaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Charles Chigoriwa
 */
@Repository
public class DynamicMetaDaoImpl implements DynamicMetaDao{
    
    @Autowired
    private DynamicMetaRepository dynamicMetaRepository;

    @Override
    public DynamicMeta findByDynamic(Dynamic dynamic) {
        return dynamicMetaRepository.findByDynamic(dynamic);
    }

    @Override
    public DynamicMeta save(DynamicMeta dynamicMeta) {
       return dynamicMetaRepository.save(dynamicMeta);
    }

    @Override
    public List<DynamicMeta> findAll() {
        return dynamicMetaRepository.findAll();
    }

    @Override
    public DynamicMeta find(Long id) {
       return dynamicMetaRepository.findOne(id);
    }

    @Override
    public DynamicMeta findByUuid(String uuid) {
        return dynamicMetaRepository.findByUuid(uuid);
    }
    
}
