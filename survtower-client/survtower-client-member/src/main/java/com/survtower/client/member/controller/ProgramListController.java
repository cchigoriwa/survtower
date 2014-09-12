package com.survtower.client.member.controller;

import com.survtower.business.common.domain.Program;
import com.survtower.business.common.service.ProgramService;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Charles Chigoriwa
 */
@ManagedBean
@RequestScoped
public class ProgramListController {
    
    @ManagedProperty(value = "#{programService}")
    private ProgramService programService;
    
    public List<Program> getPrograms(){
        return programService.findAll();
    }

    public ProgramService getProgramService() {
        return programService;
    }
    
    public void setProgramService(ProgramService programService) {
        this.programService = programService;
    }
    
}
