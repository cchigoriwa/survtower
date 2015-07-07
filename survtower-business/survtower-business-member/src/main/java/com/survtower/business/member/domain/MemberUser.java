package com.survtower.business.member.domain;

import com.survtower.business.common.AppUserDetails;
import com.survtower.business.common.BaseEntity;
import com.survtower.business.common.domain.AppUserRole;
import com.survtower.business.common.domain.Program;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author Takunda Dhlakama
 */
@Entity
@XmlRootElement
public class MemberUser extends BaseEntity {

    @Transient
    public static String ROLE_COUNTRY_ADMINISTRATOR = "ROLE_COUNTRY_ADMINISTRATOR";
    @Transient
    public static String ROLE_HEALTH_INFORMATION_OFFICER = "ROLE_HEALTH_INFORMATION_OFFICER";
    @Transient
    public static String ROLE_COUNTRY_DISEASE_MANAGER = "ROLE_COUNTRY_DISEASE_MANAGER";
    @Transient
    public static final String SADC_CD_MANAGER = "SADC_CD_MANAGER";
    private String username;
    private String password;
    @Column(unique=true)
    private String email;
    private Boolean deactivated = Boolean.FALSE;
    private static final long serialVersionUID = 1L;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "MemberUser_Program", joinColumns = {
        @JoinColumn(name = "member_user_id")}, inverseJoinColumns = {
        @JoinColumn(name = "program_id")})
    private Set<Program> programs = new HashSet<Program>();
    @JoinTable(name = "MemberUser_Role", joinColumns = {
        @JoinColumn(name = "member_user_id")}, inverseJoinColumns = {
        @JoinColumn(name = "member_role")})
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<MemberUserRole> memberUserRoles = new HashSet<MemberUserRole>();
    @Transient
    private List<String> roles;
    @Transient
    private List<Program> programList;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "MemberUser_Region", joinColumns = {
        @JoinColumn(name = "member_user_id")}, inverseJoinColumns = {
        @JoinColumn(name = "program_id")})
    private Set<Region> regions = new HashSet<Region>();
    @Transient
    private List<Region> regionList;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    

    public Set<Program> getPrograms() {
        return programs;
    }

    public void setPrograms(Set<Program> programs) {
        this.programs = programs;
    }

    public Set<Region> getRegions() {
        return regions;
    }

    public void setRegions(Set<Region> regions) {
        this.regions = regions;
    }

    public List<Region> getRegionList() {
        return new ArrayList<>(getRegions());
    }

    @Override
    public String toString() {
        return getUsername();
    }

    public Boolean getDeactivated() {
        return deactivated;
    }

    public void setDeactivated(Boolean deactivated) {
        this.deactivated = deactivated;
    }

    public List<String> getRoles() {
        Set<String> memberRoles = new HashSet<>();
        for (MemberUserRole userRole : getMemberUserRoles()) {
            memberRoles.add(userRole.getMemberRole());
        }
        return new ArrayList<>(memberRoles);
    }

    public List<Program> getProgramList() {
        return new ArrayList<>(getPrograms());
    }

    public Set<MemberUserRole> getMemberUserRoles() {
        return memberUserRoles;
    }

    public void setMemberUserRoles(Set<MemberUserRole> memberUserRoles) {
        this.memberUserRoles = memberUserRoles;
    }

    public UserDetails toUserDetails() {
        AppUserDetails userDetails = new AppUserDetails();
        userDetails.setUsername(this.username);
        userDetails.setPassword(this.password);
        userDetails.setUuid(this.uuid);
        userDetails.setEnabled(!this.deactivated);
        userDetails.setAccountNonLocked(!this.deactivated);
        userDetails.setCredentialsNonExpired(!this.deactivated);
        userDetails.setAccountNonExpired(!this.deactivated);

        List<GrantedAuthority> list = new ArrayList<>();
        for (String role : getRoles()) {
            list.add(new SimpleGrantedAuthority(role));
        }
        userDetails.setAuthorities(list);
        return userDetails;
    }
    
    public static List<AppUserRole> getAppUserRoles(){
        return new ArrayList<AppUserRole>(){
            {
              add(new AppUserRole(ROLE_COUNTRY_ADMINISTRATOR, "Member State Administrator"));
              add(new AppUserRole(ROLE_HEALTH_INFORMATION_OFFICER, "Health Information Officer"));
              add(new AppUserRole(ROLE_COUNTRY_DISEASE_MANAGER, "Country Disease Manager"));
              
            }
        };
    }

}
