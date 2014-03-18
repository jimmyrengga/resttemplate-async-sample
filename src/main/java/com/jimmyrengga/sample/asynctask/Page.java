/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jimmyrengga.sample.asynctask;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author jimmy
 */

@JsonIgnoreProperties(ignoreUnknown=true)
public class Page {
    
    private String name;
    private String website;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Override
    public String toString() {
        return "Result{" + "name=" + name + ", website=" + website + '}';
    }
    
}
