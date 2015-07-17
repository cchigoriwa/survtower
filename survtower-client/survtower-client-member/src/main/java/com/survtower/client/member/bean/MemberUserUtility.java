package com.survtower.client.member.bean;

import com.survtower.business.member.domain.MemberUser;
import java.io.Serializable;

/**
 *
 * @author Charles Chigoriwa
 */
public interface MemberUserUtility extends Serializable{
    
     public MemberUser getCurrentUser();
     
    
}
