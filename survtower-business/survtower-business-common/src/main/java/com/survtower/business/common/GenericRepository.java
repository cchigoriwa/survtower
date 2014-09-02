package net.sadc.business.common;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Charles Chigoriwa
 */
public interface GenericRepository<T,ID extends Serializable> extends JpaRepository<T, ID>{
   
    public T findByUuid(String uuid);
}
 