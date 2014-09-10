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
    
    private Member member;

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
    
    
    
    
    
}
