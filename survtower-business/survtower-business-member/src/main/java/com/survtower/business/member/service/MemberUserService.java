package com.survtower.business.member.service;

import com.survtower.business.common.GenericService;
import com.survtower.business.member.domain.MemberUser;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Takunda Dhlakama
 * @author Charles Chigoriwa
 */
public interface MemberUserService extends GenericService<MemberUser> {

    public MemberUser findByUserName(String username);

    public int updatePassword(String password, String username);

    public List<MemberUser> findMemberUsersUpdatedAfter(Date afterDate);

    public MemberUser findByEmail(String email);

}
