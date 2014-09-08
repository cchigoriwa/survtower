package com.survtower.business.common.repository;

import com.survtower.business.common.domain.Frequency;
import java.util.Date;
import java.util.List;
import net.sadc.business.common.GenericRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Takunda Dhlakama
 */
public interface FrequencyRepository extends GenericRepository<Frequency, Long> {

    @Query("select i from Frequency i where i.updateDate>:afterDate")
    public List<Frequency> findFrequencysUpdatedAfter(@Param("afterDate") Date afterDate);
    
    @Query("select max(i.updateDate) from Frequency i where i.updateDate>:afterDate")
    public Date findMaximumUpdateDate(@Param("afterDate") Date afterDate);
    
    @Query("select max(i.updateDate) from Frequency i")
    public Date findMaximumUpdateDate();
    
    @Query("select i from Frequency i where i.updateDate>:afterDate and i.updateDate<=:maxDate")
    public List<Frequency> findFrequencysUpdatedAfter(@Param("afterDate") Date afterDate,@Param("maxDate") Date maxDate);
    
    @Query("select i from Frequency i where i.updateDate<=:maxDate")
    public List<Frequency> findFrequencysUpdatedBefore(@Param("maxDate") Date maxDate);

    
    

}
