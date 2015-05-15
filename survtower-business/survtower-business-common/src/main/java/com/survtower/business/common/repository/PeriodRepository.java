package com.survtower.business.common.repository;

import com.survtower.business.common.domain.Period;
import com.survtower.business.common.domain.Program;
import java.util.Date;
import java.util.List;
import net.sadc.business.common.GenericRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Charles Chigoriwa
 */
public interface PeriodRepository extends GenericRepository<Period, Long> {

    @Query("select i from Period i where i.updateDate>:afterDate")
    public List<Period> findPeriodsUpdatedAfter(@Param("afterDate") Date afterDate);

    @Query("select max(i.updateDate) from Period i where i.updateDate>:afterDate")
    public Date findMaximumUpdateDate(@Param("afterDate") Date afterDate);

    @Query("select max(i.updateDate) from Period i")
    public Date findMaximumUpdateDate();

    @Query("select i from Period i where i.updateDate>:afterDate and i.updateDate<=:maxDate")
    public List<Period> findPeriodsUpdatedAfter(@Param("afterDate") Date afterDate, @Param("maxDate") Date maxDate);

    @Query("select i from Period i where i.updateDate<=:maxDate")
    public List<Period> findPeriodsUpdatedBefore(@Param("maxDate") Date maxDate);

    @Query("select i from Period i where i.active = true and i.deleted = false order by i.name asc")
    public List<Period> fetchActive();

    @Query("select i from Period i where i.active = false and i.deleted = false order by i.name asc")
    public List<Period> fetchInActive();

    @Query("select i from Period i where i.deleted = false order by i.name asc")
    public List<Period> fetchAllAscending();

    @Query("select i from Period i where i.deleted = false order by i.name desc")
    public List<Period> fetchAllDescending();

}
