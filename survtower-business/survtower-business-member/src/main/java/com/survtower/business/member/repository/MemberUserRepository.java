package com.survtower.business.member.repository;

import com.survtower.business.member.domain.MemberUser;
import java.util.Date;
import java.util.List;
import net.sadc.business.common.GenericRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Takunda Dhlakama
 */
public interface MemberUserRepository extends GenericRepository<MemberUser, Long> {

    @Query("select i from MemberUser i where i.updateDate>:afterDate")
    public List<MemberUser> findMemberUsersUpdatedAfter(@Param("afterDate") Date afterDate);
    
    @Query("select max(i.updateDate) from MemberUser i where i.updateDate>:afterDate")
    public Date findMaximumUpdateDate(@Param("afterDate") Date afterDate);
    
    @Query("select max(i.updateDate) from MemberUser i")
    public Date findMaximumUpdateDate();
    
    @Query("select i from MemberUser i where i.updateDate>:afterDate and i.updateDate<=:maxDate")
    public List<MemberUser> findMemberUsersUpdatedAfter(@Param("afterDate") Date afterDate,@Param("maxDate") Date maxDate);
    
    @Query("select i from MemberUser i where i.updateDate<=:maxDate")
    public List<MemberUser> findMemberUsersUpdatedBefore(@Param("maxDate") Date maxDate);

    
    

}
