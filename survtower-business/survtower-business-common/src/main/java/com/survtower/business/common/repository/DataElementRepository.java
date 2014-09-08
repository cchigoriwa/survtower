package com.survtower.business.common.repository;

import com.survtower.business.common.domain.DataElement;
import java.util.Date;
import java.util.List;
import net.sadc.business.common.GenericRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Takunda Dhlakama
 */
public interface DataElementRepository extends GenericRepository<DataElement, Long> {

    @Query("select i from DataElement i where i.updateDate>:afterDate")
    public List<DataElement> findDataElementsUpdatedAfter(@Param("afterDate") Date afterDate);
    
    @Query("select max(i.updateDate) from DataElement i where i.updateDate>:afterDate")
    public Date findMaximumUpdateDate(@Param("afterDate") Date afterDate);
    
    @Query("select max(i.updateDate) from DataElement i")
    public Date findMaximumUpdateDate();
    
    @Query("select i from DataElement i where i.updateDate>:afterDate and i.updateDate<=:maxDate")
    public List<DataElement> findDataElementsUpdatedAfter(@Param("afterDate") Date afterDate,@Param("maxDate") Date maxDate);
    
    @Query("select i from DataElement i where i.updateDate<=:maxDate")
    public List<DataElement> findDataElementsUpdatedBefore(@Param("maxDate") Date maxDate);

    
    

}
