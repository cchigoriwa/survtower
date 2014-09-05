package com.survtower.business.common.repository;

import com.survtower.business.common.domain.Member;
import java.util.Date;
import java.util.List;
import net.sadc.business.common.GenericRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Charles Chigoriwa
 */
public interface MemberRepository extends GenericRepository<Member, Long> {

    @Query("select i from  Member i where i.updateDate>:afterDate")    
    public List<Member> findMembersUpdatedAfter(@Param("afterDate") Date afterDate);
    
    @Query("select max(i.updateDate) from Member i where i.updateDate>:afterDate")
    public Date findMaximumUpdateDate(@Param("afterDate") Date afterDate);
    
    @Query("select max(i.updateDate) from Member i")
    public Date findMaximumUpdateDate();
    
    @Query("select i from Member i where i.updateDate>:afterDate and i.updateDate<=:maxDate")
    public List<Member> findMembersUpdatedAfter(@Param("afterDate") Date afterDate,@Param("maxDate") Date maxDate);
    
    @Query("select i from Member i where i.updateDate<=:maxDate")
    public List<Member> findMembersUpdatedBefore(@Param("maxDate") Date maxDate);

    
    

}
