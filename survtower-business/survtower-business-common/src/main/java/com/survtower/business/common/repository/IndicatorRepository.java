package com.survtower.business.common.repository;

import com.survtower.business.common.domain.Indicator;
import java.util.Date;
import java.util.List;
import net.sadc.business.common.GenericRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Charles Chigoriwa
 */
public interface IndicatorRepository extends GenericRepository<Indicator, Long> {

    @Query("select i from Indicator i where i.updateDate>:afterDate")
    public List<Indicator> findIndicatorsUpdatedAfter(@Param("afterDate") Date afterDate);
    
    @Query("select max(i.updateDate) from Indicator i where i.updateDate>:afterDate")
    public Date findMaximumUpdateDate(@Param("afterDate") Date afterDate);
    
    @Query("select max(i.updateDate) from Indicator i")
    public Date findMaximumUpdateDate();
    
    @Query("select i from Indicator i where i.updateDate>:afterDate and i.updateDate<=:maxDate")
    public List<Indicator> findIndicatorsUpdatedAfter(@Param("afterDate") Date afterDate,@Param("maxDate") Date maxDate);
    
    @Query("select i from Indicator i where i.updateDate<=:maxDate")
    public List<Indicator> findIndicatorsUpdatedBefore(@Param("maxDate") Date maxDate);

    
    

}