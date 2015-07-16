package com.survtower.ws.api.domain.version2;

/**
 *
 * @author Daniel Nkhoma
 */
public class DataSourceCategoryPayload extends ResponsePayload {

    private DataSourceCategoryBody dataSourceCategoryBody;

    public DataSourceCategoryBody getDataSourceCategoryBody() {
        return dataSourceCategoryBody;
    }

    public void setDataSourceCategoryBody(DataSourceCategoryBody dataSourceCategoryBody) {
        this.dataSourceCategoryBody = dataSourceCategoryBody;
    }
}
