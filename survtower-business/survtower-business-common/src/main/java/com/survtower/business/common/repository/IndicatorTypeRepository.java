package com.survtower.business.common.repository;

import com.survtower.business.common.domain.Indicator;
import com.survtower.business.common.domain.IndicatorType;
import java.util.Date;
import java.util.List;
import net.sadc.business.common.GenericRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Takunda Dhlakama
 */
public interface IndicatorTypeRepository extends GenericRepository<IndicatorType, Long> {

    @Query("select i from IndicatorType i where i.updateDate>:afterDate")
    public List<IndicatorType> findIndicatorTypesUpdatedAfter(@Param("afterDate") Date afterDate);
    
    @Query("select max(i.updateDate) from IndicatorType i where i.updateDate>:afterDate")
    public Date findMaximumUpdateDate(@Param("afterDate") Date afterDate);
    
    @Query("select max(i.updateDate) from IndicatorType i")
    public Date findMaximumUpdateDate();
    
    @Query("select i from IndicatorType i where i.updateDate>:afterDate and i.updateDate<=:maxDate")
    public List<IndicatorType> findIndicatorTypesUpdatedAfter(@Param("afterDate") Date afterDate,@Param("maxDate") Date maxDate);
    
    @Query("select i from IndicatorType i where i.updateDate<=:maxDate")
    public List<IndicatorType> findIndicatorTypesUpdatedBefore(@Param("maxDate") Date maxDate);

    
    

}
