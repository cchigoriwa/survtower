package com.survtower.business.common.repository;

import com.survtower.business.common.domain.IndicatorGroup;
import java.util.Date;
import java.util.List;
import net.sadc.business.common.GenericRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Charles Chigoriwa
 */
public interface IndicatorGroupRepository extends GenericRepository<IndicatorGroup, Long> {

    @Query("select i from IndicatorGroup i where i.updateDate>:afterDate")
    public List<IndicatorGroup> findIndicatorGroupsUpdatedAfter(@Param("afterDate") Date afterDate);
    
    @Query("select max(i.updateDate) from IndicatorGroup i where i.updateDate>:afterDate")
    public Date findMaximumUpdateDate(@Param("afterDate") Date afterDate);
    
    @Query("select max(i.updateDate) from IndicatorGroup i")
    public Date findMaximumUpdateDate();
    
    @Query("select i from IndicatorGroup i where i.updateDate>:afterDate and i.updateDate<=:maxDate")
    public List<IndicatorGroup> findIndicatorGroupsUpdatedAfter(@Param("afterDate") Date afterDate,@Param("maxDate") Date maxDate);
    
    @Query("select i from IndicatorGroup i where i.updateDate<=:maxDate")
    public List<IndicatorGroup> findIndicatorGroupsUpdatedBefore(@Param("maxDate") Date maxDate);

    
    

}
