package com.survtower.business.common.service;

import com.survtower.business.common.BaseEntity;
import java.io.Serializable;

/**
 *
 * @author Charles Chigoriwa
 * @param <T>
 */
public interface ILookupService<T extends BaseEntity> extends Serializable {

    public T save(T t);

}
