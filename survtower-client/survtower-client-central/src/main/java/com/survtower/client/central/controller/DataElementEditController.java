package com.survtower.client.central.controller;

import com.survtower.business.common.domain.DataElement;
import com.survtower.business.common.domain.DataSource;
import com.survtower.business.common.service.DataElementService;
import com.survtower.business.common.service.DataSourceService;
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
public class DataElementEditController {

    @ManagedProperty(value = "#{dataElementService}")
    private DataElementService dataElementService;

    @ManagedProperty(value = "#{dataSourceService}")
    private DataSourceService dataSourceService;

    @ManagedProperty(value = "#{param.uuid}")
    private String uuid;

    private DataElement dataElement;

    public DataElement getDataElement() {
        return dataElement;
    }

    public String save() {
        dataElementService.save(dataElement);
        return "dataElementList?faces-redirect=true&src=edit";
    }

    public DataElementService getDataElementService() {
        return dataElementService;
    }

    public void setDataElementService(DataElementService dataElementService) {
        this.dataElementService = dataElementService;
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

    public List<DataSource> getDataSources() {
        return this.dataSourceService.findAll();
    }

    @PostConstruct
    public void postConstruct() {
        if (uuid == null) {
            dataElement = new DataElement();
        } else {
            dataElement = dataElementService.findByUuid(uuid);
            if (dataElement == null) {
                dataElement = new DataElement();
            }
        }
    }

}
