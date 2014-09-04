package com.survtower.business.common.repository;

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
public interface ProgramRepository extends GenericRepository<Program, Long> {

    @Query("select i from Program i where i.updateDate>:afterDate")
    public List<Program> findProgramsUpdatedAfter(@Param("afterDate") Date afterDate);
    
    @Query("select max(i.updateDate) from Program i where i.updateDate>:afterDate")
    public Date findMaximumUpdateDate(@Param("afterDate") Date afterDate);
    
    @Query("select max(i.updateDate) from Program i")
    public Date findMaximumUpdateDate();
    
    @Query("select i from Program i where i.updateDate>:afterDate and i.updateDate<=:maxDate")
    public List<Program> findProgramsUpdatedAfter(@Param("afterDate") Date afterDate,@Param("maxDate") Date maxDate);
    
    @Query("select i from Program i where i.updateDate<=:maxDate")
    public List<Program> findProgramsUpdatedBefore(@Param("maxDate") Date maxDate);

    
    

}
