package com.survtower.client.central.controller;

import com.survtower.business.common.domain.DataElement;
import com.survtower.business.common.domain.Indicator;
import com.survtower.business.common.domain.IndicatorGroup;
import com.survtower.business.common.domain.IndicatorType;
import com.survtower.business.common.service.DataElementService;
import com.survtower.business.common.service.IndicatorGroupService;
import com.survtower.business.common.service.IndicatorService;
import com.survtower.business.common.service.IndicatorTypeService;
import com.survtower.client.central.utility.MessageInfor;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Charles Chigoriwa
 */
@ManagedBean
@RequestScoped
public class IndicatorEditController {

    @ManagedProperty(value = "#{indicatorService}")
    private IndicatorService indicatorService;

    @ManagedProperty(value = "#{dataElementService}")
    private DataElementService dataElementService;

    @ManagedProperty(value = "#{indicatorGroupService}")
    private IndicatorGroupService indicatorGroupService;

    @ManagedProperty(value = "#{indicatorTypeService}")
    private IndicatorTypeService indicatorTypeService;

    @ManagedProperty(value = "#{param.uuid}")
    private String uuid;

    private Indicator indicator;

    public Indicator getIndicator() {
        return indicator;
    }

    public String save() {
        if (indicator.getIndicatorGroup() == null) {
            MessageInfor.errorMessages("Select Indicator Group");
            return null;
        }
        if (indicator.getNumeratorDataElement() == null) {
            MessageInfor.errorMessages("Select Numerator");
            return null;
        }
        if (indicator.getDenominatorDataElement() == null) {
            MessageInfor.errorMessages("Select Denominator");
            return null;
        }
        if (indicator.getDenominatorDataElement() == indicator.getNumeratorDataElement()) {
            MessageInfor.errorMessages("Numerator and Denominator Cannot be the Same");
            return null;
        }
        indicatorService.save(indicator);
        return "indicatorList?faces-redirect=true";
    }

    public IndicatorService getIndicatorService() {
        return indicatorService;
    }

    public void setIndicatorService(IndicatorService indicatorService) {
        this.indicatorService = indicatorService;
    }

    public void setDataElementService(DataElementService dataElementService) {
        this.dataElementService = dataElementService;
    }

    public void setIndicatorGroupService(IndicatorGroupService indicatorGroupService) {
        this.indicatorGroupService = indicatorGroupService;
    }

    public void setIndicatorTypeService(IndicatorTypeService indicatorTypeService) {
        this.indicatorTypeService = indicatorTypeService;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public List<DataElement> getDataElements() {
        return dataElementService.findAll();
    }

    public List<IndicatorGroup> getIndicatorGroups() {
        return indicatorGroupService.findAll();
    }

    public List<IndicatorType> getIndicatorTypes() {
        return indicatorTypeService.findAll();
    }

    public List<DataElement> dataElementAutoComplete(String searchTerm) {
        return dataElementService.getDataElementAutoComplete(searchTerm);
    }

    @PostConstruct
    public void postConstruct() {
        if (uuid == null) {
            indicator = new Indicator();
        } else {
            indicator = indicatorService.findByUuid(uuid);
            if (indicator == null) {
                indicator = new Indicator();
            }
        }
    }

}
