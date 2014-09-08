package com.survtower.business.member.service;

import com.survtower.business.common.GenericService;
import com.survtower.business.member.domain.MemberUser;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Takunda Dhlakama
 */
public interface MemberUserService extends GenericService<MemberUser>{
    
    public List<MemberUser> findMemberUsersUpdatedAfter(Date afterDate);

    public Date findMaximumUpdateDate(Date afterDate);

    public List<MemberUser> findMemberUsersUpdatedAfter(Date afterDate, Date maxDate);
    
    public Date findMaximumUpdateDate();
    
    public List<MemberUser> findMemberUsersUpdatedBefore(Date maxDate);
}
