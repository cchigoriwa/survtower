/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.survtower.client.central.utility;

import com.survtower.business.common.domain.Member;
import com.survtower.business.common.domain.Program;
import java.io.Serializable;

/**
 *
 * @author tdhlakama
 */
public class MemberProgram implements Serializable {

    private Program prorgam;
    private Member member;

    public MemberProgram(Program prorgam, Member member) {
        this.prorgam = prorgam;
        this.member = member;
    }

    public Program getProrgam() {
        return prorgam;
    }

    public void setProrgam(Program prorgam) {
        this.prorgam = prorgam;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

}
