/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.managers;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import model.entities.Conto;
import model.entities.OrdineStaff;

/**
 *
 * @author ciro
 */
@Singleton
@Lock(LockType.WRITE)
public class OrdiniSingleton {

    private HashMap<String, OrdineStaff> ordiniAttivi;
    private List<OrdineStaff> ordiniDaCompletare;
    private HashMap<String, Conto> richiesteConto;

    public OrdiniSingleton() {
        ordiniAttivi = new HashMap<>();
        ordiniDaCompletare = new LinkedList<>();
        richiesteConto = new HashMap<>();
    }
    
    public Collection<OrdineStaff> getOrdiniAttivi() {
        return ordiniAttivi.values();
    }
    
    public Collection<OrdineStaff> getOrdiniDaCompletare() {
        return ordiniDaCompletare;
    }
    
    public Collection<Conto> getRichiesteConto() {
        return richiesteConto.values();
    }
    
    
    public void addOrdine(OrdineStaff ordine) {
        if(ordiniAttivi.containsKey(ordine.getTavolo())) {
            ordiniAttivi.get(ordine.getTavolo())
                    .addPiatti(ordine.getPiatti());
            ordiniAttivi.get(ordine.getTavolo())
                    .addTotaleCent(ordine.getTotaleCent());
        }
        else
            ordiniAttivi.put(ordine.getTavolo(), ordine);
        
        ordiniDaCompletare.add(ordine);
    }
    
    public void removeFromOrdiniDaCompletare(int index) {
        ordiniDaCompletare.remove(index);
    }
    
    public OrdineStaff removeFromOrdiniAttivi(String tavolo) {
        return ordiniAttivi.remove(tavolo);
    }
    
    public void removeFromRichiesteConto(String tavolo) {
        richiesteConto.remove(tavolo);
    }
}
