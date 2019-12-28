/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import ejb.PiattoFacade;
import entities.Piatto;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author ciro
 */
@Path("/clientManager")
@Stateless
public class ClientAppManagerWS {

    @Inject
    private PiattoFacade pf;
    
    @GET
    @Path("/menu")
    @Produces(MediaType.APPLICATION_XML)
    public List<Piatto> getMenu() {
        return pf.findAll();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List<String> getCategorie() {
        return pf.findCategorie();
    }
}
