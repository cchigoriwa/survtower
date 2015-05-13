package com.survtower.client.member.controller;


import com.survtower.business.member.domain.Region;
import com.survtower.business.member.service.RegionService;
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
public class RegionListController {
    
    @ManagedProperty(value = "#{regionService}")
    private RegionService regionService;
    
    public List<Region> getRegions(){
        return regionService.findAll();
    }

    public RegionService getRegionService() {
        return regionService;
    }
    
    public void setRegionService(RegionService regionService) {
        this.regionService = regionService;
    }
    
}
