package com.survtower.business.central.domain;

import com.survtower.business.common.AppUserDetails;
import com.survtower.business.common.BaseEntity;
import com.survtower.business.common.domain.Program;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
public class CentralUser extends BaseEntity {

    @Transient
    public static final String ROLE_GLOBAL_ADMINISTRATOR = "ROLE_GLOBAL_ADMINISTRATOR";
    @Transient
    public static final String ROLE_SADC_DATA_MANAGER = "ROLE_SADC_DATA_MANAGER";
    private String username;
    private String password;
    private String email;
    private Boolean deactivated = Boolean.FALSE;
    private static final long serialVersionUID = 1L;
    @JoinTable(name = "CentralUser_Role", joinColumns = {
        @JoinColumn(name = "central_user_id")}, inverseJoinColumns = {
        @JoinColumn(name = "central_role")})
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<CentralUserRole> centralUserRoles = new HashSet<CentralUserRole>();
    @Transient
    private List<String> roles;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        Set<String> memberRoles = new HashSet<String>();
        for (CentralUserRole userRole : getCentralUserRoles()) {
            memberRoles.add(userRole.getMemberRole());
        }        
        return new ArrayList<String>(memberRoles);
    }

    public Set<CentralUserRole> getCentralUserRoles() {
        return centralUserRoles;
    }

    public void setCentralUserRoles(Set<CentralUserRole> centralUserRoles) {
        this.centralUserRoles = centralUserRoles;
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

}
