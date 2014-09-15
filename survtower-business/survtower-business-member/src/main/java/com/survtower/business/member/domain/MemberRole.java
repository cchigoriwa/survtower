package com.survtower.business.member.domain;

/**
 *
 * @author Takunda Dhlakama
 */
public enum MemberRole {

    //Roles in the system
    ROLE_COUNTRY_ADMINISTRATOR("ROLE_COUNTRY_ADMINISTRATOR"),
    ROLE_HEALTH_INFORMATION_OFFICER("ROLE_HEALTH_INFORMATION_OFFICER");

    private MemberRole(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
    private final String name;
}
