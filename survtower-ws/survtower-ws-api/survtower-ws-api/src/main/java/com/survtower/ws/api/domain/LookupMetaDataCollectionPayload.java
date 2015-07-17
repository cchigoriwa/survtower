package com.survtower.ws.api.domain;

import com.survtower.business.common.domain.Lookup;
import java.io.Serializable;
import java.util.ArrayList;
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

    private List<ServerLookupMetaData> lookupMetaDataList = new ArrayList<>();

    @XmlElementWrapper(name = "lookupMetaDataList")
    @XmlElement(name = "lookupMetaData")
    public List<ServerLookupMetaData> getLookupMetaDataList() {
        return lookupMetaDataList;
    }

    public void setLookupMetaDataList(List<ServerLookupMetaData> lookupMetaDataList) {
        this.lookupMetaDataList = lookupMetaDataList;
    }

    public void add(Lookup lookup, Long lastUpdateNo) {
        if (lookup != null && lastUpdateNo != null) {
            this.add(new ServerLookupMetaData(lookup, lastUpdateNo));
        }
    }

    public void add(ServerLookupMetaData lookupMetaData) {
        if (lookupMetaDataList != null) {
            lookupMetaDataList.add(lookupMetaData);
        }
    }

}
