package com.survtower.ws.api.domain;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Daniel Nkhoma
 */
@XmlRootElement
public class DataSourceCategoryPayload extends ResponsePayload {

    private DataSourceCategoryBody dataSourceCategoryBody;

    public DataSourceCategoryBody getDataSourceCategoryBody() {
        return dataSourceCategoryBody;
    }

    public void setDataSourceCategoryBody(DataSourceCategoryBody dataSourceCategoryBody) {
        this.dataSourceCategoryBody = dataSourceCategoryBody;
    }
}
