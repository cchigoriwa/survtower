package com.survtower.client.member.utility;

import java.io.Serializable;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class MessageInfor implements Serializable {

    public static void errorMessages(String error) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", error));
    }

    public static void inforMessages(String infor) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Infor", infor));
    }
    
    public static String saved() {
        return "Saved";
    }

    public static String deleted() {
        return "Deleted";
    }

    public static String changesSaved() {
        return "Changes Saved";
    }

    public void navigate(String query) {
        ConfigurableNavigationHandler handler = (ConfigurableNavigationHandler) FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
        handler.performNavigation(query);
    }
}
