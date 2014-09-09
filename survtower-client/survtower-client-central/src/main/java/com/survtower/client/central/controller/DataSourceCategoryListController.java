package com.survtower.client.central.controller;

import com.survtower.business.common.domain.DataSourceCategory;
import com.survtower.business.common.service.DataSourceCategoryService;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Takunda Dhlakama
 */
@ManagedBean
@RequestScoped
public class DataSourceCategoryListController {

    @ManagedProperty(value = "#{dataSourceCategoryService}")
    private DataSourceCategoryService dataSourceCategoryService;

    public List<DataSourceCategory> getDataSourceCategorys() {
        return dataSourceCategoryService.findAll();
    }

    public DataSourceCategoryService getDataSourceCategoryService() {
        return dataSourceCategoryService;
    }

    public void setDataSourceCategoryService(DataSourceCategoryService dataSourceCategoryService) {
        this.dataSourceCategoryService = dataSourceCategoryService;
    }

}
