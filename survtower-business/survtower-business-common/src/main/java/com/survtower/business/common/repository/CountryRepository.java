package com.survtower.business.common.repository;

import com.survtower.business.common.domain.Country;
import java.util.Date;
import java.util.List;
import net.sadc.business.common.GenericRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Charles Chigoriwa
 */
public interface CountryRepository extends GenericRepository<Country, Long> {

    @Query("select i from Country i where i.updateDate>:afterDate")
    public List<Country> findCountrysUpdatedAfter(@Param("afterDate") Date afterDate);
    
    @Query("select max(i.updateDate) from Country i where i.updateDate>:afterDate")
    public Date findMaximumUpdateDate(@Param("afterDate") Date afterDate);
    
    @Query("select max(i.updateDate) from Country i")
    public Date findMaximumUpdateDate();
    
    @Query("select i from Country i where i.updateDate>:afterDate and i.updateDate<=:maxDate")
    public List<Country> findCountrysUpdatedAfter(@Param("afterDate") Date afterDate,@Param("maxDate") Date maxDate);
    
    @Query("select i from Country i where i.updateDate<=:maxDate")
    public List<Country> findCountrysUpdatedBefore(@Param("maxDate") Date maxDate);

    
    

}
