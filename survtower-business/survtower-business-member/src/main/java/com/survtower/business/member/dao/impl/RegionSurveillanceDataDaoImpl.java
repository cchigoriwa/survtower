package com.survtower.business.member.dao.impl;

import com.survtower.business.common.domain.Surveillance;
import com.survtower.business.common.domain.SurveillanceData;
import com.survtower.business.member.dao.RegionSurveillanceDataDao;
import com.survtower.business.member.domain.Region;
import com.survtower.business.member.domain.RegionSurveillanceData;
import com.survtower.business.member.repository.RegionSurveillanceDataRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Takunda Dhlakama
 */
@Repository
public class RegionSurveillanceDataDaoImpl implements RegionSurveillanceDataDao {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private RegionSurveillanceDataRepository regionSurveillanceDataRepository;

    @Override
    public RegionSurveillanceData save(RegionSurveillanceData region) {
        return regionSurveillanceDataRepository.save(region);
    }

    @Override
    public List<RegionSurveillanceData> findAll() {
        return regionSurveillanceDataRepository.findAll();
    }

    @Override
    public RegionSurveillanceData find(Long id) {
        return regionSurveillanceDataRepository.findOne(id);
    }

    @Override
    public RegionSurveillanceData findByUuid(String uuid) {
        return regionSurveillanceDataRepository.findByUuid(uuid);
    }

    @Override
    public List<RegionSurveillanceData> findAll(SurveillanceData surveillanceData) {
        return regionSurveillanceDataRepository.findBySurveillanceData(surveillanceData);
    }

    @Override
    public RegionSurveillanceData find(SurveillanceData surveillanceData, Region region) {
        return regionSurveillanceDataRepository.findBySurveillanceDataAndRegion(surveillanceData, region);
    }

    @Override
    public Double getNumeratorCalculatedValue(SurveillanceData surveillanceData) {
        return (Double) entityManager.createQuery("select sum(r.numeratorValue) from RegionSurveillanceData r where r.surveillanceData=:surveillanceData").setParameter("surveillanceData", surveillanceData).getSingleResult();
    }

    @Override
    public Double getDenominatedCalculatedValue(SurveillanceData surveillanceData) {
        return (Double) entityManager.createQuery("select sum(r.denominatorValue) from RegionSurveillanceData r where r.surveillanceData=:surveillanceData").setParameter("surveillanceData", surveillanceData).getSingleResult();
    }

    @Override
    public List<RegionSurveillanceData> findAll(Surveillance surveillance, Region region) {
        return entityManager.createQuery("select DISTINCT r from RegionSurveillanceData r where r.surveillanceData.surveillance=:surveillance and r.region=:region").setParameter("surveillance", surveillance).setParameter("region", region).getResultList();
    }

}
