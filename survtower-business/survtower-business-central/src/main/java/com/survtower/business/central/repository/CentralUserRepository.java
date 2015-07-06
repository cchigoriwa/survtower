package com.survtower.business.central.repository;

import com.survtower.business.central.domain.CentralUser;
import net.sadc.business.common.GenericRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Takunda Dhlakama
 */
public interface CentralUserRepository extends GenericRepository<CentralUser, Long> {

    @Query("select i from CentralUser i where i.username=:username")
    public CentralUser findByUserName(@Param("username") String username);

    @Modifying
    @Transactional
    @Query("update CentralUser i set i.password = ?1 where i.username = ?2")
    public int updatePassword(String password, String username);
    
    public CentralUser findByEmail(String email);

}
