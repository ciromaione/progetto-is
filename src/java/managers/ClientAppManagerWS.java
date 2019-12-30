/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import ejb.PiattoFacade;
import entities.OrdineCliente;
import entities.Piatto;
import entities.PiattoFinale;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 *
 * @author ciro
 */
@Path("clientmanager")
@Stateless
public class ClientAppManagerWS {

    @Inject
    private PiattoFacade pf;
    @Inject
    private OrdineManager om;
    
    
    @GET
    @Path("menu")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Piatto> getMenu() {
        return pf.findAll();
    }
    
    @GET
    @Path("categorie")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<String> getCategorie() {
        return pf.findCategorie();
    }
    
    @POST
    @Path("conferma")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response confermaOrdine(OrdineCliente ordineCliente) {
        try {
            ArrayList<Integer> ids = new ArrayList<>();
            for(PiattoFinale p:ordineCliente.getPiatti())
                ids.add(p.getIdPiatto());
            Collection <Piatto> piatti = pf.findByIds(ids);
            om.addToOrdiniAttivi(ordineCliente.getTavolo(), piatti, ordineCliente.getTotaleCent());
        } catch (Throwable t) {
            t.printStackTrace();
            return Response.serverError().build();
        }
        return Response.ok().build();
        /*-----------
        * * * * * * * * *
        * * * * * * * * *  implements AbstractDAO<Piatto>
            MANCA VISUALIZZAZIONE ORDINE
        * * * * * * * * *
        * * * * * * * * * 
        ------------*/
    }

    @GET
    @Path("conto/{tavolo}")
    public void richiestaConto(@PathParam("tavolo") String tavolo) {
        om.removeFromOrdiniAttivi(tavolo);
        /*-----------
        * * * * * * * * *
        * * * * * * * * * 
            MANCA VISUALIZZAZIONE CONTO
        * * * * * * * * *
        * * * * * * * * * 
        ------------*/
    }
    
}