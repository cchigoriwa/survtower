package com.survtower.client.central.controller;

import com.survtower.business.common.domain.DataSourceCategory;
import com.survtower.business.common.service.DataSourceCategoryService;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Takunda Dhlakama
 */
@ManagedBean
@RequestScoped
public class DataSourceCategoryEditController {

    @ManagedProperty(value = "#{dataSourceCategoryService}")
    private DataSourceCategoryService dataSourceCategoryService;

    @ManagedProperty(value = "#{param.uuid}")
    private String uuid;

    private DataSourceCategory dataSourceCategory;

    public DataSourceCategory getDataSourceCategory() {
        return dataSourceCategory;
    }

    public String save() {
        dataSourceCategoryService.save(dataSourceCategory);
        return "dataSourceCategoryList?faces-redirect=true&src=edit";
    }

    public DataSourceCategoryService getDataSourceCategoryService() {
        return dataSourceCategoryService;
    }

    public void setDataSourceCategoryService(DataSourceCategoryService dataSourceCategoryService) {
        this.dataSourceCategoryService = dataSourceCategoryService;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @PostConstruct
    public void postConstruct() {
        if (uuid == null) {
            dataSourceCategory = new DataSourceCategory();
        } else {
            dataSourceCategory = dataSourceCategoryService.findByUuid(uuid);
            if (dataSourceCategory == null) {
                dataSourceCategory = new DataSourceCategory();
            }
        }
    }

}
