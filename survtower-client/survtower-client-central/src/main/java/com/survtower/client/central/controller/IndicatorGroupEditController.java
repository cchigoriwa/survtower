package com.survtower.client.central.controller;

import com.survtower.business.common.domain.IndicatorGroup;
import com.survtower.business.common.domain.Program;
import com.survtower.business.common.service.IndicatorGroupService;
import com.survtower.business.common.service.ProgramService;
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
public class IndicatorGroupEditController {

    @ManagedProperty(value = "#{indicatorGroupService}")
    private IndicatorGroupService indicatorGroupService;
    
    @ManagedProperty(value = "#{programService}")
    private ProgramService programService;

    @ManagedProperty(value = "#{param.uuid}")
    private String uuid;

    private IndicatorGroup indicatorGroup;

    public IndicatorGroup getIndicatorGroup() {
        return indicatorGroup;
    }

    public String save() {
        indicatorGroupService.save(indicatorGroup);
        return "indicatorGroupList?faces-redirect=true&src=edit";
    }

    public IndicatorGroupService getIndicatorGroupService() {
        return indicatorGroupService;
    }

    public void setIndicatorGroupService(IndicatorGroupService indicatorGroupService) {
        this.indicatorGroupService = indicatorGroupService;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public ProgramService getProgramService() {
        return programService;
    }

    public void setProgramService(ProgramService programService) {
        this.programService = programService;
    }
    
    public List<Program> getPrograms(){
        return this.programService.findAll();
    }
        
    @PostConstruct
    public void postConstruct(){
        if(uuid==null){
            indicatorGroup=new IndicatorGroup();
        }else{
            indicatorGroup=indicatorGroupService.findByUuid(uuid);
            if(indicatorGroup==null){
                indicatorGroup=new IndicatorGroup();
            }
        }
    }

}
