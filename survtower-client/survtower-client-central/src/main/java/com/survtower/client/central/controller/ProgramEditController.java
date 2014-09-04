package com.survtower.client.central.controller;

import com.survtower.business.common.domain.Program;
import com.survtower.business.common.service.ProgramService;
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
public class ProgramEditController {

    @ManagedProperty(value = "#{programService}")
    private ProgramService programService;

    @ManagedProperty(value = "#{param.uuid}")
    private String uuid;

    private Program program;

    public Program getProgram() {
        return program;
    }

    public String save() {
        programService.save(program);
        return "programList?faces-redirect=true&src=edit";
    }

    public ProgramService getProgramService() {
        return programService;
    }

    public void setProgramService(ProgramService programService) {
        this.programService = programService;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    
    
    
    @PostConstruct
    public void postConstruct(){
        if(uuid==null){
            program=new Program();
        }else{
            program=programService.findByUuid(uuid);
            if(program==null){
                program=new Program();
            }
        }
    }

}
