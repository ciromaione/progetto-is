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
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import model.entities.Conto;
import model.entities.OrdineStaff;
import model.managers.OrdineManager;

/**
 *
 * @author ciro
 */
@Path("client/azioni")
@Stateless
public class ClientAzioniService {
    
    @Inject
    private OrdineManager om;
    @Inject @ConfermaEvent
    private Event<String> eventConferma;
    @Inject @ContoEvent
    private Event<String> eventConto;

    @POST
    @Path("conferma")
    public Response confermaOrdine(@QueryParam("ordine") String ordineJSON) {
        Gson gson = new Gson();
        OrdineStaff ordine = gson.fromJson(ordineJSON, OrdineStaff.class);
        try {
            ordine = om.addOrdine(ordine);
            ordineJSON = gson.toJson(ordine);
            eventConferma.fire(ordineJSON);
        } catch (Throwable t) {
            t.printStackTrace();
            return Response.serverError().build();
        }
        return Response.ok().build();
        
    }

    @GET
    @Path("conto/{tavolo}/{metodo}")
    public Response richiestaConto(@PathParam("tavolo") String tavolo, @PathParam("metodo") String metodo) {
        Conto conto = new Conto();
        conto.setTavolo(tavolo);
        conto.setMetodo(metodo);
        conto = om.addRichiestaConto(conto);
        String json = new Gson().toJson(conto);
        eventConto.fire(json);
        return Response.ok().build();
    }
}
