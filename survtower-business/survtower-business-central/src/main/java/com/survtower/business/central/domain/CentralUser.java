package com.survtower.business.central.domain;

import com.survtower.business.common.AppUserDetails;
import com.survtower.business.common.BaseEntity;
import com.survtower.business.common.domain.UserRole;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author Takunda Dhlakama
 * @author Daniel Nkhoma
 */
@Entity
@XmlRootElement
@Table(name = "central_user")
public class CentralUser extends BaseEntity {

    @Column(name = "username")
    private String username;
    @Column(name = "password", updatable = false)
    private String password;
    @Column(unique = true)
    private String email;
    @Column(name = "deactivated")
    private Boolean deactivated = Boolean.FALSE;
    private static final long serialVersionUID = 1L;
    @ManyToMany
    @JoinTable(joinColumns = {
        @JoinColumn(name = "central_user_id")}, inverseJoinColumns = {
        @JoinColumn(name = "user_role_id")})
    private List<UserRole> userRoles;

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

    public List<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<UserRole> userRoles) {
        this.userRoles = userRoles;
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
        for (UserRole role : userRoles) {
            list.add(new SimpleGrantedAuthority(role.getRole()));
        }
        userDetails.setAuthorities(list);
        return userDetails;
    }

    public static final String ROLE_GLOBAL_ADMINISTRATOR = "ROLE_GLOBAL_ADMINISTRATOR";

    public static final String ROLE_SADC_DATA_MANAGER = "ROLE_SADC_DATA_MANAGER";

}
