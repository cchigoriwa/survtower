package com.survtower.ws.api.domain;

import com.survtower.business.common.domain.Lookup;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Charles Chigoriwa
 */
@XmlRootElement
public class LookupMetaDataCollectionPayload implements Serializable {

    
    private List<LookupMetaData> lookupMetaDataList = new ArrayList<>();

    @XmlElementWrapper(name="lookupMetaDataList")
    @XmlElement(name="lookupMetaData")
    public List<LookupMetaData> getLookupMetaDataList() {
        return lookupMetaDataList;
    }

    public void setLookupMetaDataList(List<LookupMetaData> lookupMetaDataList) {
        this.lookupMetaDataList = lookupMetaDataList;
    }
    
    public void add(Lookup lookup,Date lastUpdateTimestamp){
        this.add(new LookupMetaData(lookup, lastUpdateTimestamp));
    }

    public void add(LookupMetaData lookupMetaData) {
        if (lookupMetaDataList != null) {
            lookupMetaDataList.add(lookupMetaData);
        }
    }

}
