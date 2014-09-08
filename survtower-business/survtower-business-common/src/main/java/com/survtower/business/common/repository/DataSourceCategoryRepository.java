package com.survtower.business.common.repository;

import com.survtower.business.common.domain.DataSourceCategory;
import java.util.Date;
import java.util.List;
import net.sadc.business.common.GenericRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Takunda Dhlakama
 */
public interface DataSourceCategoryRepository extends GenericRepository<DataSourceCategory, Long> {

    @Query("select i from DataSourceCategory i where i.updateDate>:afterDate")
    public List<DataSourceCategory> findDataSourceCategorysUpdatedAfter(@Param("afterDate") Date afterDate);
    
    @Query("select max(i.updateDate) from DataSourceCategory i where i.updateDate>:afterDate")
    public Date findMaximumUpdateDate(@Param("afterDate") Date afterDate);
    
    @Query("select max(i.updateDate) from DataSourceCategory i")
    public Date findMaximumUpdateDate();
    
    @Query("select i from DataSourceCategory i where i.updateDate>:afterDate and i.updateDate<=:maxDate")
    public List<DataSourceCategory> findDataSourceCategorysUpdatedAfter(@Param("afterDate") Date afterDate,@Param("maxDate") Date maxDate);
    
    @Query("select i from DataSourceCategory i where i.updateDate<=:maxDate")
    public List<DataSourceCategory> findDataSourceCategorysUpdatedBefore(@Param("maxDate") Date maxDate);

    
    

}
