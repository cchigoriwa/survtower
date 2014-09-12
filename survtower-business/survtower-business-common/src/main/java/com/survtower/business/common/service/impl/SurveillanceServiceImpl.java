package com.survtower.business.common.service.impl;

import com.survtower.business.common.dao.SurveillanceDao;
import com.survtower.business.common.domain.Member;
import com.survtower.business.common.domain.Period;
import com.survtower.business.common.domain.Program;
import com.survtower.business.common.domain.Surveillance;
import com.survtower.business.common.service.SurveillanceService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Takunda Dhlakama
 */
@Service("surveillanceService")
@Transactional(readOnly = true)
public class SurveillanceServiceImpl implements SurveillanceService {

    @Autowired
    private SurveillanceDao surveillanceDao;

    @Transactional
    @Override
    public Surveillance save(Surveillance surveillance) {
        surveillance.setUpdateDate(new Date());
        return surveillanceDao.save(surveillance);
    }

    @Override
    public List<Surveillance> findAll() {
        return surveillanceDao.findAll();
    }

    @Override
    public Surveillance find(Long id) {
        return surveillanceDao.find(id);
    }

    @Override
    public Surveillance findByUuid(String uuid) {
        return surveillanceDao.findByUuid(uuid);
    }

    @Override
    public Surveillance get(Program program, Period period, Member member){
    return surveillanceDao.get(program, period, member);
    }
    
    @Override
    public List<Surveillance> getSurviellances(Program program, Period period){
    return surveillanceDao.getSurviellances(program, period);
    }
}
