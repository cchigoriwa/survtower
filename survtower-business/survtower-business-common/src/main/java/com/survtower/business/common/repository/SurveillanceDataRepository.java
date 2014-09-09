package com.survtower.business.common.repository;

import com.survtower.business.common.domain.SurveillanceData;
import java.util.Date;
import java.util.List;
import net.sadc.business.common.GenericRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Takunda Dhlakama
 */
public interface SurveillanceDataRepository extends GenericRepository<SurveillanceData, Long> {
  
}
