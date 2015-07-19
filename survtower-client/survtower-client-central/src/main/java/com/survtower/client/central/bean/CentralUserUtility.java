package com.survtower.client.central.bean;

import com.survtower.business.central.domain.CentralUser;
import java.io.Serializable;

/**
 *
 * @author Daniel Nkhoma
 */
public interface CentralUserUtility extends Serializable {

    public CentralUser getCurrentUser();

}
