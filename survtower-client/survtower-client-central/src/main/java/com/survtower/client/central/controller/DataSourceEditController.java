package com.survtower.client.central.controller;

import com.survtower.business.common.domain.DataSource;
import com.survtower.business.common.domain.DataSourceCategory;
import com.survtower.business.common.domain.Frequency;
import com.survtower.business.common.service.DataSourceCategoryService;
import com.survtower.business.common.service.DataSourceService;
import com.survtower.business.common.service.FrequencyService;
import java.util.List;
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
public class DataSourceEditController {

    @ManagedProperty(value = "#{dataSourceService}")
    private DataSourceService dataSourceService;

    @ManagedProperty(value = "#{dataSourceCategoryService}")
    private DataSourceCategoryService dataSourceCategoryService;

    @ManagedProperty(value = "#{frequencyService}")
    private FrequencyService frequencyService;

    @ManagedProperty(value = "#{param.uuid}")
    private String uuid;

    private DataSource dataSource;

    public DataSource getDataSource() {
        return dataSource;
    }

    public String save() {
        dataSourceService.save(dataSource);
        return "dataSourceList?faces-redirect=true&src=edit";
    }

    public DataSourceService getDataSourceService() {
        return dataSourceService;
    }

    public void setDataSourceService(DataSourceService dataSourceService) {
        this.dataSourceService = dataSourceService;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public List<DataSourceCategory> getDataSourceCategorys() {
        return dataSourceCategoryService.findAll();
    }
    
     public List<Frequency> getFrequencys() {
        return frequencyService.findAll();
    }

    @PostConstruct
    public void postConstruct() {
        if (uuid == null) {
            dataSource = new DataSource();
        } else {
            dataSource = dataSourceService.findByUuid(uuid);
            if (dataSource == null) {
                dataSource = new DataSource();
            }
        }
    }

}
