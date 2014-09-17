/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.survtower.client.member.utility;

import com.survtower.business.common.domain.DataElement;
import com.survtower.business.common.domain.Indicator;
import com.survtower.business.common.service.DataElementService;
import com.survtower.business.common.service.IndicatorService;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author tdhlakama
 */
@ManagedBean
@RequestScoped
public class AutoCompleteController implements Serializable {

    @ManagedProperty(value = "#{indicatorService}")
    private IndicatorService indicatorService;
    @ManagedProperty(value = "#{dataElementService}")
    private DataElementService dataElementService;

    public void setIndicatorService(IndicatorService indicatorService) {
        this.indicatorService = indicatorService;
    }

    public void setDataElementService(DataElementService dataElementService) {
        this.dataElementService = dataElementService;
    }

    public List<DataElement> dataElementAutoComplete(String searchTerm) {
        return dataElementService.getDataElementAutoComplete(searchTerm);
    }

    public List<Indicator> dataIndicatorAutoComplete(String searchTerm) {
        return indicatorService.getIndicatorAutoComplete(searchTerm);
    }
}
