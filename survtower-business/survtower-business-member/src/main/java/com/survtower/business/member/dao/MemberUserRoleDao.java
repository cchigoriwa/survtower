package com.survtower.business.member.dao;

import com.survtower.business.common.GenericDao;
import com.survtower.business.member.domain.MemberUserRole;
import java.util.List;

/**
 *
 * @author Takunda Dhlakama
 */
public interface MemberUserRoleDao extends GenericDao<MemberUserRole> {

    public List<MemberUserRole> findMemberRolesbyUser(String memberUser);


}
