package com.survtower.ws.api.domain;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Charles Chigoriwa
 */
@XmlRootElement
public class MemberPayload extends ResponsePayload{
    
    private MemberBody memberBody;

    public MemberBody getMemberBody() {
        return memberBody;
    }

    public void setMemberBody(MemberBody memberBody) {
        this.memberBody = memberBody;
    }
    
    
    
}
