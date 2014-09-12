package com.survtower.client.member.converter;

import com.survtower.business.common.domain.Program;
import com.survtower.business.common.service.ProgramService;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author Charles Chigoriwa
 */
@ManagedBean
@RequestScoped
public class ProgramConverter implements Converter {

    @ManagedProperty(value = "#{programService}")
    private ProgramService programService;

    public ProgramService getProgramService() {
        return programService;
    }

    public void setProgramService(ProgramService programService) {
        this.programService = programService;
    }

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string == null || string.isEmpty()) {
            return null;
        } else {
            return programService.find(Long.valueOf(string));
        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o == null) {
            return "";
        }
        Program program = (Program) o;
        return program.getId() == null ? "" : program.getId().toString();
    }

}
