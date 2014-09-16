package com.survtower.business.central.service;

import com.survtower.business.central.domain.MemberUserRole;
import com.survtower.business.common.GenericService;
import java.util.List;

/**
 *
 * @author Takunda Dhlakama
 */
public interface MemberUserRoleService extends GenericService<MemberUserRole>{
    
   public List<MemberUserRole> findMemberRolesbyUser(String memberSecurity);
}
