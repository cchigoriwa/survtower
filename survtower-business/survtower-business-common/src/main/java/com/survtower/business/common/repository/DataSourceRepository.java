package com.survtower.business.common.repository;

import com.survtower.business.common.domain.DataSource;
import java.util.Date;
import java.util.List;
import net.sadc.business.common.GenericRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Takunda Dhlakama
 */
public interface DataSourceRepository extends GenericRepository<DataSource, Long> {

    @Query("select i from DataSource i where i.updateDate>:afterDate")
    public List<DataSource> findDataSourcesUpdatedAfter(@Param("afterDate") Date afterDate);
    
    @Query("select max(i.updateDate) from DataSource i where i.updateDate>:afterDate")
    public Date findMaximumUpdateDate(@Param("afterDate") Date afterDate);
    
    @Query("select max(i.updateDate) from DataSource i")
    public Date findMaximumUpdateDate();
    
    @Query("select i from DataSource i where i.updateDate>:afterDate and i.updateDate<=:maxDate")
    public List<DataSource> findDataSourcesUpdatedAfter(@Param("afterDate") Date afterDate,@Param("maxDate") Date maxDate);
    
    @Query("select i from DataSource i where i.updateDate<=:maxDate")
    public List<DataSource> findDataSourcesUpdatedBefore(@Param("maxDate") Date maxDate);

    
    

}
