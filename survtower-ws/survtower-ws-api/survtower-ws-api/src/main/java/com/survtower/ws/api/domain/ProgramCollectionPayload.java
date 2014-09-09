package com.survtower.ws.api.domain;

import com.survtower.business.common.domain.Program;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Takunda Dhlakama
 
 */
@XmlRootElement
public class ProgramCollectionPayload extends ResponsePayload{
    
    private List<Program> programs;

    public List<Program> getPrograms() {
        return programs;
    }

    public void setPrograms(List<Program> programs) {
        this.programs = programs;
    }
    
    
    
}
