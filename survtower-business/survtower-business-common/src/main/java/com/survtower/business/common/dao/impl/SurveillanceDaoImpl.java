package com.survtower.business.common.dao.impl;

import com.survtower.business.common.dao.SurveillanceDao;
import com.survtower.business.common.domain.Surveillance;
import com.survtower.business.common.repository.SurveillanceRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Takunda Dhlakama
 */
@Repository
public class SurveillanceDaoImpl implements SurveillanceDao{
    
    @Autowired
    private SurveillanceRepository surveillanceRepository;

    @Override
    public Surveillance save(Surveillance surveillance) {
       return surveillanceRepository.save(surveillance);
    }

    @Override
    public List<Surveillance> findAll() {
       return surveillanceRepository.findAll();
    }

    @Override
    public Surveillance find(Long id) {
        return surveillanceRepository.findOne(id);
    }

    @Override
    public Surveillance findByUuid(String uuid) {
       return surveillanceRepository.findByUuid(uuid);
    }
    
}
