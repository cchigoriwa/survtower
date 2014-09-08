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
public interface SurveillanceAuditRepository extends GenericRepository<MemberUser, Long> {

}
