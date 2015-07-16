package com.survtower.business.central.service.impl;

import com.survtower.business.common.BaseEntity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Charles Chigoriwa
 */
@Aspect
@Service
public class LookupDataAspectImpl {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Around("execution(* com.survtower.business.common.service.ILookupService.save(*)) && args(baseEntity)")
    public synchronized void updateNo(ProceedingJoinPoint pjp, BaseEntity baseEntity) throws Throwable {
        long nextUpdateNo = 0;
        String query = "select max(e.updateNo) from " + baseEntity.getClass().getSimpleName() + " e";
        Object maxUpdateNo = entityManager.createQuery(query).getSingleResult();
        if (maxUpdateNo != null) {
            nextUpdateNo = (Long) maxUpdateNo + 1l;
        }

        baseEntity.setUpdateNo(nextUpdateNo);

        pjp.proceed();

    }

}
