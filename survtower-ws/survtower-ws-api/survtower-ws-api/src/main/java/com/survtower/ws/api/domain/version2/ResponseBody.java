package com.survtower.ws.api.domain.version2;

import com.survtower.business.common.BaseEntity;
import java.util.List;

/**
 *
 * @author charlesc
 */
public abstract class ResponseBody<T extends BaseEntity> {
    
   private List<T> dataList;

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }
   
   
}
