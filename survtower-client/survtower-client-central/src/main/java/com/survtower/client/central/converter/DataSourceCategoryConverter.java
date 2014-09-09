package com.survtower.client.central.converter;

import com.survtower.business.common.domain.DataSourceCategory;
import com.survtower.business.common.service.DataSourceCategoryService;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author Takunda Dhlakama
 */
@ManagedBean
@RequestScoped
public class DataSourceCategoryConverter implements Converter {

    @ManagedProperty(value = "#{dataSourceCategoryService}")
    private DataSourceCategoryService dataSourceCategoryService;

    public DataSourceCategoryService getDataSourceCategoryService() {
        return dataSourceCategoryService;
    }

    public void setDataSourceCategoryService(DataSourceCategoryService dataSourceCategoryService) {
        this.dataSourceCategoryService = dataSourceCategoryService;
    }

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string == null || string.isEmpty()) {
            return null;
        } else {
            return dataSourceCategoryService.find(Long.valueOf(string));
        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o == null) {
            return "";
        }
        DataSourceCategory dataSourceCategory = (DataSourceCategory) o;
        return dataSourceCategory.getId() == null ? "" : dataSourceCategory.getId().toString();
    }

}
