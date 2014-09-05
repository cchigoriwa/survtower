package com.survtower.business.common.dao;

import com.survtower.business.common.GenericDao;
import com.survtower.business.common.domain.Member;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Charles Chigoriwa
 */
public interface MemberDao extends GenericDao<Member> {

    public List<Member> findCountrysUpdatedAfter(Date afterDate);

    public Date findMaximumUpdateDate(Date afterDate);

    public List<Member> findCountrysUpdatedAfter(Date afterDate, Date maxDate);

    public Date findMaximumUpdateDate();
    
    public List<Member> findCountrysUpdatedBefore(Date maxDate);

}
