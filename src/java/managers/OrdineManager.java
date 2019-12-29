/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import ejb.OrdineFacade;
import entities.*;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import javax.ejb.Singleton;
import javax.inject.Inject;

/**
 *
 * @author ciro
 */
@Singleton
public class OrdineManager {

    private HashMap<String, Ordine> ordiniAttivi;
    
    @Inject
    private OrdineFacade of;

    public OrdineManager() {
        this.ordiniAttivi = new HashMap<String, Ordine>();
    }
    
    public void addToOrdiniAttivi(String tavolo, Collection<Piatto> piatti, Integer totCent) {
        if(tavolo == null || piatti == null || totCent == null)
            throw new RuntimeException("Argomenti non validi");
        if(ordiniAttivi.containsKey(tavolo)) {
            piatti.addAll(ordiniAttivi.get(tavolo).getPiatti());
            ordiniAttivi.get(tavolo)
                    .setPiatti(piatti);
            ordiniAttivi.get(tavolo)
                    .setTotaleCent(totCent + ordiniAttivi.get(tavolo).getTotaleCent());
        }
        else
            ordiniAttivi.put(tavolo, new Ordine(new Date(), totCent, piatti));
    }
    
    public void removeFromOrdiniAttivi(String tavolo) {
        of.addOrdine(ordiniAttivi.get(tavolo));
        ordiniAttivi.remove(tavolo);
    }
    
}
