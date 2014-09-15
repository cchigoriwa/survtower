package com.survtower.client.linker.util;

import java.io.Serializable;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Charles Chigoriwa
 */
public class AppUserUtil implements Serializable{
    
    @NotEmpty
    @Size(min = 8, max=30)
    private String password;
    @NotEmpty
    @Size(min = 8, max=30)
    private String confirmPassword;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
    
    
}
