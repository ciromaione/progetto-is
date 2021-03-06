/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.Set;

/**
 *
 * @author ciro
 */
@javax.ws.rs.ApplicationPath("rest")
public class ApplicationConfig extends javax.ws.rs.core.Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(service.ClientAzioniService.class);
        resources.add(service.ClientMenuService.class);
        resources.add(service.ContoBroadcastService.class);
        resources.add(service.LoginService.class);
        resources.add(service.OrdineBroadcastService.class);
    }
    
}
