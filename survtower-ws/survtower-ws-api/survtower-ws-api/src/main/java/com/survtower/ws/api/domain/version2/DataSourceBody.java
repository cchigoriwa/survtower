/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.survtower.ws.api.domain.version2;

import com.survtower.business.common.domain.DataSource;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author charlesc
 */
@XmlRootElement
public class DataSourceBody {

    private List<DataSource> dataSources;

    public List<DataSource> getDataSources() {
        return dataSources;
    }

    public void setDataSources(List<DataSource> dataSources) {
        this.dataSources = dataSources;
    }

}
