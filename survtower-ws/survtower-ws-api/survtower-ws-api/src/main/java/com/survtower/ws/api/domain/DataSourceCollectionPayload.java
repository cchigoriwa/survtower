package com.survtower.ws.api.domain;

import com.survtower.business.common.domain.DataSource;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Takunda Dhlakama
 
 */
@XmlRootElement
public class DataSourceCollectionPayload extends ResponsePayload{
    
    private List<DataSource> dataSources;

    public List<DataSource> getDataSources() {
        return dataSources;
    }

    public void setDataSources(List<DataSource> dataSources) {
        this.dataSources = dataSources;
    }
    
    
    
}
