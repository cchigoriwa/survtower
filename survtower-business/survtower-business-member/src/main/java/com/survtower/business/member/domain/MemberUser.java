package com.survtower.business.member.domain;

import com.survtower.business.common.AppUserDetails;
import com.survtower.business.common.BaseEntity;
import com.survtower.business.common.domain.Program;
import com.survtower.business.common.domain.UserRole;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author Takunda Dhlakama
 * @author Charles Chigoriwa
 */
@Entity
@XmlRootElement
@Table(name = "member_user")
public class MemberUser extends BaseEntity {

    private String username;
    private String password;
    @Column(unique = true)
    private String email;
    private Boolean deactivated = Boolean.FALSE;
    private static final long serialVersionUID = 1L;
    @ManyToMany
    private List<Program> programs = new ArrayList<>();
    @ManyToMany
    private List<Region> regions = new ArrayList<>();
    @ManyToMany
    private List<UserRole> userRoles = new ArrayList<>();

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

    public List<Program> getPrograms() {
        return programs;
    }

    public void setPrograms(List<Program> programs) {
        this.programs = programs;
    }

    public List<Region> getRegions() {
        return regions;
    }

    public void setRegions(List<Region> regions) {
        this.regions = regions;
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
        for (UserRole userRole : userRoles) {
            list.add(new SimpleGrantedAuthority(userRole.getRole()));
        }
        userDetails.setAuthorities(list);
        return userDetails;
    }

    public static String ROLE_COUNTRY_ADMINISTRATOR = "ROLE_COUNTRY_ADMINISTRATOR";

    public static String ROLE_HEALTH_INFORMATION_OFFICER = "ROLE_HEALTH_INFORMATION_OFFICER";

    public static String ROLE_COUNTRY_DISEASE_MANAGER = "ROLE_COUNTRY_DISEASE_MANAGER";

    public static final String SADC_CD_MANAGER = "SADC_CD_MANAGER";

}
