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

    public OrdiniSingleton() {
        ordiniAttivi = new HashMap<>();
        ordiniDaCompletare = new LinkedList<>();
    }
    
    public Collection<OrdineStaff> getOrdiniAttivi() {
        return ordiniAttivi.values();
    }
    
    public Collection<OrdineStaff> getOrdiniDaCompletare() {
        return ordiniDaCompletare;
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
    
    public void removeFromOrdiniAttivi(String tavolo) {
        ordiniAttivi.remove(tavolo);
    }
}
