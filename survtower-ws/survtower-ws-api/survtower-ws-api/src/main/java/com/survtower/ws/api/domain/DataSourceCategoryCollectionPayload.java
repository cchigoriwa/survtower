package com.survtower.ws.api.domain;

import com.survtower.business.common.domain.DataSourceCategory;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Takunda Dhlakama
 
 */
@XmlRootElement
public class DataSourceCategoryCollectionPayload extends ResponsePayload{
    
    private List<DataSourceCategory> dataSourceCategorys;

    public List<DataSourceCategory> getDataSourceCategorys() {
        return dataSourceCategorys;
    }

    public void setDataSourceCategorys(List<DataSourceCategory> dataSourceCategorys) {
        this.dataSourceCategorys = dataSourceCategorys;
    }
    
    
    
}
