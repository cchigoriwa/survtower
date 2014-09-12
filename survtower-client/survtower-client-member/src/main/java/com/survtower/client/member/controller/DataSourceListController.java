package com.survtower.client.member.controller;

import com.survtower.business.common.domain.DataSource;
import com.survtower.business.common.service.DataSourceService;
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
public class DataSourceListController {

    @ManagedProperty(value = "#{dataSourceService}")
    private DataSourceService dataSourceService;

    public List<DataSource> getDataSources() {
        return dataSourceService.findAll();
    }

    public DataSourceService getDataSourceService() {
        return dataSourceService;
    }

    public void setDataSourceService(DataSourceService dataSourceService) {
        this.dataSourceService = dataSourceService;
    }

}
