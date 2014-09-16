package com.survtower.business.central.dao;

import com.survtower.business.central.domain.MemberUserRole;
import com.survtower.business.common.GenericDao;
import java.util.List;

/**
 *
 * @author Takunda Dhlakama
 */
public interface MemberUserRoleDao extends GenericDao<MemberUserRole> {

    public List<MemberUserRole> findMemberRolesbyUser(String memberSecurity);


}
