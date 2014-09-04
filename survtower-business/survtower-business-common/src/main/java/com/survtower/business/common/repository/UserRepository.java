package com.survtower.business.common.repository;

import com.survtower.business.common.domain.User;
import java.util.Date;
import java.util.List;
import net.sadc.business.common.GenericRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Charles Chigoriwa
 */
public interface UserRepository extends GenericRepository<User, Long> {

    @Query("select i from User i where i.updateDate>:afterDate")
    public List<User> findUsersUpdatedAfter(@Param("afterDate") Date afterDate);
    
    @Query("select max(i.updateDate) from User i where i.updateDate>:afterDate")
    public Date findMaximumUpdateDate(@Param("afterDate") Date afterDate);
    
    @Query("select max(i.updateDate) from User i")
    public Date findMaximumUpdateDate();
    
    @Query("select i from User i where i.updateDate>:afterDate and i.updateDate<=:maxDate")
    public List<User> findUsersUpdatedAfter(@Param("afterDate") Date afterDate,@Param("maxDate") Date maxDate);
    
    @Query("select i from User i where i.updateDate<=:maxDate")
    public List<User> findUsersUpdatedBefore(@Param("maxDate") Date maxDate);

    
    

}
