/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cbk.sqsitma.controller.util;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author cobrakik
 */
@Named(value = "navManagedBean")
@SessionScoped
public class NavManagedBean implements Serializable {

    /**
     * Creates a new instance of NavManagedBean
     */
    public NavManagedBean() {
    }
    
    public String active(String url){
        return JsfUtil.active(url);
    }
    
}
