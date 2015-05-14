/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.survtower.client.member.utility;

import com.survtower.business.common.domain.Program;
import com.survtower.business.member.domain.Region;
import java.io.Serializable;

/**
 *
 * @author tdhlakama
 */
public class ProgramRegion implements Serializable {

    private Region region;
    private Program program;

    public ProgramRegion(Region region, Program program) {
        this.region = region;
        this.program = program;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }
}
