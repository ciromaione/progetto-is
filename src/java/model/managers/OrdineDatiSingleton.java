/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.managers;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import model.entities.Conto;
import model.entities.OrdineStaff;

/**
 *
 * @author ciro
 */

/*
* Singleton che memorizza: 
* -gli ordiniAttivi, ovvero l'insieme degli ordini relativi a un
* tavolo di cui non è stato ancora richiesto il conto;
* -gli ordiniDaCompletare, ovvero gli ordini di cui non è stata ancora completata la preparazione;
* -le richiesteConto.
* Per gli ordiniDaCompletare, ad ogni aggiunta di un ordine, viene associato un id intero univoco generato con un 
* semplice metodo autoincrementale, si tiene traccia dell'ultimo id usato con la varile lastId che viene azzerata
* ogni qual volta gli ordini attivi e gli oridi da completare sono entrambi vuoti.
*/
@Singleton
@Lock(LockType.WRITE)
public class OrdineDatiSingleton {

    private Map<String, OrdineStaff> ordiniAttivi;
    private Map<Integer, OrdineStaff> ordiniDaCompletare;
    private HashMap<String, Conto> richiesteConto;
    private int lastId = -1;

    public OrdineDatiSingleton() {
        ordiniAttivi = new HashMap<>();
        ordiniDaCompletare = new HashMap<>();
        richiesteConto = new HashMap<>();
    }
    
    public Collection<OrdineStaff> getOrdiniDaCompletare() {
        return ordiniDaCompletare.values();
    }
    
    public Collection<Conto> getRichiesteConto() {
        return richiesteConto.values();
    }
    
    
    public OrdineStaff addOrdine(OrdineStaff ordine) {
        if(ordiniAttivi.containsKey(ordine.getTavolo())) {
            ordiniAttivi.get(ordine.getTavolo())
                    .addPiatti(ordine.getPiatti());
            ordiniAttivi.get(ordine.getTavolo())
                    .addTotaleCent(ordine.getTotaleCent());
        }
        else
            ordiniAttivi.put(ordine.getTavolo(), new OrdineStaff(ordine));
        
        int id = getId();
        ordiniDaCompletare.put(id, ordine);
        ordine.setId(id);
        return ordine;
    }
    
    public Conto addRichiestaConto(Conto conto) {
        OrdineStaff ordine =ordiniAttivi.get(conto.getTavolo());
        if(ordine == null) throw new RuntimeException("ordine non presente in lista");
        String totale = ordine.getPrezzoString();
        conto.setTotale(totale);
        richiesteConto.put(conto.getTavolo(), conto);
        return conto;
    }
    
    public void removeFromOrdiniDaCompletare(int id) {
        ordiniDaCompletare.remove(id);
        
    }
    
    public OrdineStaff removeFromOrdiniAttivi(String tavolo) {
        OrdineStaff tmp = ordiniAttivi.remove(tavolo);
        if(ordiniAttivi.isEmpty() && ordiniDaCompletare.isEmpty())
            this.lastId = -1;
        return tmp;
    }
    
    public void removeFromRichiesteConto(String tavolo) {
        richiesteConto.remove(tavolo);
    }
    
    private int getId() {
        this.lastId++;
        return this.lastId;
    }
}
