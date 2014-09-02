package com.survtower.business.common;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Charles Chigoriwa
 */
public interface BusinessComponent<T> extends Serializable{
    
    public T save(T t);
    public List<T>findAll();
    public T find(Long id);
    public T findByUuid(String uuid);
    
}
