package com.survtower.business.central.domain;

import com.survtower.business.common.AppUserDetails;
import com.survtower.business.common.BaseEntity;
import com.survtower.business.common.domain.Member;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author Charles Chigoriwa
 */
@Entity
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"uuid"}),
    @UniqueConstraint(columnNames = {"emailAddress"}),
    @UniqueConstraint(columnNames = {"memberID"})})
public class MemberSecurity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String emailAddress;
    private String password;
    @Column(updatable = false)
    private String memberID;
    @Column(updatable = false)
    private String memberKey;
    private Boolean deactivated = Boolean.FALSE;
    @OneToOne
    private Member member;
    @JoinTable(name = "MemberUser_Role", joinColumns = {
        @JoinColumn(name = "member_user_id")}, inverseJoinColumns = {
        @JoinColumn(name = "member_role")})
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<MemberUserRole> memberUserRoles = new HashSet<MemberUserRole>();
    public static final String ROLE_MEMBER = "ROLE_MEMBER";
    public static final String ROLE_GLOBAL_ADMINISTRATOR = "ROLE_GLOBAL_ADMINISTRATOR";
    public static final String ROLE_SADC_DATA_MANAGER = "ROLE_SADC_DATA_MANAGER";
    public static final String ROLE_COUNTRY_ADMINISTRATOR = "ROLE_COUNTRY_ADMINISTRATOR";
    
    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public String getMemberKey() {
        return memberKey;
    }

    public void setMemberKey(String memberKey) {
        this.memberKey = memberKey;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Boolean isDeactivated() {
        return deactivated;
    }

    public void setDeactivated(Boolean deactivated) {
        this.deactivated = deactivated;
    }

    public UserDetails toUserDetails() {
        AppUserDetails userDetails = new AppUserDetails();
        userDetails.setUsername(this.emailAddress);
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
        return userDetails;
    }

    public UserDetails toAppUserDetailsForIntegration() {
        AppUserDetails userDetails = new AppUserDetails();
        userDetails.setUsername(this.memberID);
        userDetails.setPassword(this.memberKey);
        userDetails.setUuid(this.uuid);
        userDetails.setEnabled(!this.deactivated);
        userDetails.setAccountNonLocked(!this.deactivated);
        userDetails.setCredentialsNonExpired(!this.deactivated);
        userDetails.setAccountNonExpired(!this.deactivated);

        List<GrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority(ROLE_MEMBER));
        userDetails.setAuthorities(list);
        return userDetails;
    }

    public List<String> getRoles() {
        Set<String> memberRoles = new HashSet<String>();
        for (MemberUserRole userRole : getMemberUserRoles()) {
            memberRoles.add(userRole.getMemberRole());
        }
        return new ArrayList<String>(memberRoles);
    }

    public Set<MemberUserRole> getMemberUserRoles() {
        return memberUserRoles;
    }

    public void setMemberUserRoles(Set<MemberUserRole> memberUserRoles) {
        this.memberUserRoles = memberUserRoles;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof MemberSecurity)) {
            return false;
        }
        MemberSecurity other = (MemberSecurity) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return getEmailAddress();
    }

}
