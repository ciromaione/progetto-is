/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import entities.*;
import java.util.Collection;
import java.util.HashMap;
import javax.ejb.Singleton;

/**
 *
 * @author ciro
 */
@Singleton
public class OrdineManager {

    private HashMap<Integer, Ordine> ordiniAttivi;

    public OrdineManager() {
        this.ordiniAttivi = new HashMap<Integer, Ordine>();
    }
    
    public void addToOrdiniAttivi(Ordine ordine) {
        if(ordine == null)
            throw new RuntimeException("Ordine Null!");
        if(ordine.getPiatti() == null)
                throw new RuntimeException("Ordine vuoto!");
        
        if(ordiniAttivi.containsKey(ordine.getId())) {
            Collection<Piatto> piatti = ordiniAttivi
                    .get(ordine.getId())
                    .getPiatti();
            Integer totale = ordiniAttivi
                    .get(ordine.getId())
                    .getTotaleCent();
            piatti.addAll(ordine.getPiatti());
            totale += ordine.getTotaleCent();
            ordiniAttivi.get(ordine.getId())
                    .setPiatti(piatti);
            ordiniAttivi.get(ordine.getId())
                    .setTotaleCent(totale);
        }
        else
            ordiniAttivi.put(ordine.getId(), ordine);
    }
    
    public void removeFromOrdiniAttivi(Integer id) {
        ordiniAttivi.remove(id);
    }
    
}
