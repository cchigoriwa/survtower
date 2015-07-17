package com.survtower.ws.api.domain;

import com.survtower.business.common.domain.DataSourceCategory;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Daniel Nkhoma
 */
@XmlRootElement
public class DataSourceCategoryBody {

    private List<DataSourceCategory> dataSourceCategories;

    public List<DataSourceCategory> getDataSourceCategories() {
        return dataSourceCategories;
    }

    public void setDataSourceCategories(List<DataSourceCategory> dataSourceCategories) {
        this.dataSourceCategories = dataSourceCategories;
    }
}
