/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import model.entities.Piatto;
import model.managers.ClienteManager;

/**
 *
 * @author ciro
 */
@Path("client/menu")
@Stateless
public class ClientMenuService {
    
    @Inject
    private ClienteManager cm;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Piatto> getMenu() {
        return cm.getMenu();
    }
    
    @GET
    @Path("categorie")
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> getCategorie() {
        return cm.getCategorie();
    }
    
}
