package com.survtower.business.member.domain;

import com.survtower.business.common.AppUserDetails;
import com.survtower.business.common.BaseEntity;
import com.survtower.business.common.domain.Program;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
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
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"uuid"})})
public class MemberUser extends BaseEntity {

    private String username;
    private String password;
    private Boolean deactivated = Boolean.FALSE;
    private static final long serialVersionUID = 1L;
    @ElementCollection(targetClass = MemberRole.class)
    @Enumerated(EnumType.STRING)
    private Set<MemberRole> memberRoles = new HashSet<MemberRole>();
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Program> programs = new HashSet<Program>();
    @Transient
    private List<MemberRole> roles;
    @Transient
    private List<Program> programList;

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

    public Set<MemberRole> getMemberRoles() {
        return memberRoles;
    }

    public void setMemberRoles(Set<MemberRole> memberRoles) {
        this.memberRoles = memberRoles;
    }

    public Set<Program> getPrograms() {
        return programs;
    }

    public void setPrograms(Set<Program> programs) {
        this.programs = programs;
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

    public List<MemberRole> getRoles() {
        return new ArrayList<MemberRole>(getMemberRoles());
    }

    public List<Program> getProgramList() {
        return new ArrayList<Program>(getPrograms());
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
        for (MemberRole role : getMemberRoles()) {
            list.add(new SimpleGrantedAuthority(role.name()));
        }
        userDetails.setAuthorities(list);
        return userDetails;
    }

}
