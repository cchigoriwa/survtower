package com.survtower.business.member.repository;

import com.survtower.business.member.domain.MemberUser;
import java.util.Date;
import java.util.List;
import net.sadc.business.common.GenericRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Takunda Dhlakama
 */
public interface MemberUserRepository extends GenericRepository<MemberUser, Long> {

    @Query("select i from MemberUser i where i.username=:username")
    public MemberUser findByUserName(@Param("username") String username);

    @Modifying
    @Transactional
    @Query("update MemberUser i set i.password = ?1 where i.username = ?2")
    public int updatePassword(String password, String username);

}
