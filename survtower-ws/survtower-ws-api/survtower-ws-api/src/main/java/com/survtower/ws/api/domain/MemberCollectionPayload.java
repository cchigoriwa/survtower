package com.survtower.ws.api.domain;

import com.survtower.business.common.domain.Member;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Takunda Dhlakama
 
 */
@XmlRootElement
public class MemberCollectionPayload extends ResponsePayload{
    
    private List<Member> members;

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }
    
    
    
}
