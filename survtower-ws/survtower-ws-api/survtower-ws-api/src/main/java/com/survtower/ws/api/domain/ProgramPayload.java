package com.survtower.ws.api.domain;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Charles Chigoriwa
 */
@XmlRootElement
public class ProgramPayload extends ResponsePayload {

    private ProgramBody programBody;

    public ProgramBody getProgramBody() {
        return programBody;
    }

    public void setProgramBody(ProgramBody programBody) {
        this.programBody = programBody;
    }

}
