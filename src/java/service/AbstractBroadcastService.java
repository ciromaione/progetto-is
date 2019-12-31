/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseBroadcaster;
import javax.ws.rs.sse.SseEventSink;

/**
 *
 * @author ciro
 */
abstract class AbstractBroadcastService {
    
    protected SseBroadcaster broadcaster;
    protected Sse sse;
    
    public void register(Sse sse, SseEventSink eventSink) {
        this.sse = sse;
        if (broadcaster == null) 
            this.broadcaster = sse.newBroadcaster();
        this.broadcaster.register(eventSink);
    }
    
    public abstract void sender(String data);
    
}
