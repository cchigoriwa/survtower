package com.survtower.client.member.controller;

import com.survtower.business.member.domain.Region;
import com.survtower.business.member.service.RegionService;
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
public class RegionEditController {

    @ManagedProperty(value = "#{regionService}")
    private RegionService regionService;

    @ManagedProperty(value = "#{param.uuid}")
    private String uuid;

    private Region region;

    public Region getRegion() {
        return region;
    }

    public String save() {
        regionService.save(region);
        return "regionList?faces-redirect=true&src=edit";
    }

    public RegionService getRegionService() {
        return regionService;
    }

    public void setRegionService(RegionService regionService) {
        this.regionService = regionService;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @PostConstruct
    public void postConstruct() {
        if (uuid == null) {
            region = new Region();
        } else {
            region = regionService.findByUuid(uuid);
            if (region == null) {
                region = new Region();
            }
        }
    }

}
