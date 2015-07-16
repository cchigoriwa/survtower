package com.survtower.ws.api.domain;

import com.survtower.business.common.domain.DataSourceCategory;
import java.util.List;

/**
 *
 * @author Daniel Nkhoma
 */
public class DataSourceCategoryBody {

    private List<DataSourceCategory> dataSourceCategories;

    public List<DataSourceCategory> getDataSourceCategories() {
        return dataSourceCategories;
    }

    public void setDataSourceCategories(List<DataSourceCategory> dataSourceCategories) {
        this.dataSourceCategories = dataSourceCategories;
    }
}
