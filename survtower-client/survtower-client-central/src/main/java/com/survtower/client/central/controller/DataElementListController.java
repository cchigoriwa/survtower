package com.survtower.client.central.controller;

import com.survtower.business.common.domain.DataElement;
import com.survtower.business.common.service.DataElementService;
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
public class DataElementListController {
    
    @ManagedProperty(value = "#{dataElementService}")
    private DataElementService dataElementService;
    
    public List<DataElement> getDataElements(){
        return dataElementService.findAll();
    }

    public DataElementService getDataElementService() {
        return dataElementService;
    }
    
    public void setDataElementService(DataElementService dataElementService) {
        this.dataElementService = dataElementService;
    }
    
}
