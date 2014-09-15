package com.survtower.business.member.service;

import com.survtower.business.common.GenericService;
import com.survtower.business.member.domain.MemberUserRole;
import java.util.List;

/**
 *
 * @author Takunda Dhlakama
 */
public interface MemberUserRoleService extends GenericService<MemberUserRole>{
    
   public List<MemberUserRole> findMemberRolesbyUser(String memberUser);
}
