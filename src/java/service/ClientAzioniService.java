/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.google.gson.Gson;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import model.entities.OrdineStaff;
import model.managers.OrdiniSingleton;

/**
 *
 * @author ciro
 */
@Path("client/azioni")
@Stateless
public class ClientAzioniService {
    
    @Inject
    private OrdiniSingleton os;
    @Inject @Conferma
    private Event<String> eventConferma;
    @Inject @Conto
    private Event<String> eventConto;

    @POST
    @Path("conferma")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response confermaOrdine(OrdineStaff ordine) {
        try {
            os.addOrdine(ordine);
            String ordineJSON = new Gson().toJson(ordine);
            eventConferma.fire(ordineJSON);
        } catch (Throwable t) {
            t.printStackTrace();
            return Response.serverError().build();
        }
        return Response.ok().build();
        
    }

    @GET
    @Path("conto/{tavolo}/{metodo}")
    public void richiestaConto(@PathParam("tavolo") String tavolo, @PathParam("metodo") String metodo) {
        String json = "{"
                + "tavolo:'"+tavolo+"',"
                + "metodo:'"+metodo+"'}";
        os.removeFromOrdiniAttivi(json);
        eventConto.fire(tavolo);
    }
}
