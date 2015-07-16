package com.survtower.ws.api.domain;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author charlesc
 */
@XmlRootElement
public class DataSourcePayload extends ResponsePayload{
    
    private DataSourceBody dataSourceBody;

    public DataSourceBody getDataSourceBody() {
        return dataSourceBody;
    }

    public void setDataSourceBody(DataSourceBody dataSourceBody) {
        this.dataSourceBody = dataSourceBody;
    }
    
    
}
