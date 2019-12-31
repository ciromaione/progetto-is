/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import javax.ejb.Singleton;
import javax.enterprise.event.Observes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseEventSink;

/**
 *
 * @author ciro
 */
@Path("staff/conti")
@Singleton
public class ContoBroadcastService extends AbstractBroadcastService {

    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    @Override
    public void register(@Context Sse sse, @Context SseEventSink eventSink) {
        super.register(sse, eventSink);
    }
    
    @Override
    public void sender(@Observes @Conto String data) {
        this.broadcaster.broadcast(this.sse.newEvent(data));
    }
}
