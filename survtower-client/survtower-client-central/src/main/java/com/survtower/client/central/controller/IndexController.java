package com.survtower.client.central.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Charles Chigoriwa
 */
@ManagedBean
@RequestScoped
public class IndexController {

    /**
     * Creates a new instance of IndexController
     */
    public IndexController() {
    }
    
    public String getMessage(){
        return "Hello World!";
    }
    
}
