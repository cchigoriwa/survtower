package com.survtower.business.member.dao;

import com.survtower.business.common.GenericDao;
import com.survtower.business.member.domain.MemberUser;

/**
 *
 * @author Takunda Dhlakama
 */
public interface MemberUserDao extends GenericDao<MemberUser> {

    public MemberUser findByUserName(String username);

    public int updatePassword(String password, String username);

    public MemberUser findByEmail(String email);

}
