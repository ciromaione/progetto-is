/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.managers;

import java.sql.Connection;
import javax.ejb.Singleton;
import javax.inject.Inject;
import model.entities.OrdineStaff;

/**
 *
 * @author ciro
 */
@Singleton
public class StaffManager {

    @Inject
    Connection conn;
    @Inject
    OrdiniSingleton os;

    public StaffManager() {
    }
    
    public void stampaConto(String tavolo) {
        OrdineStaff ordineStaff = os.removeFromOrdiniAttivi(tavolo);
        if(ordineStaff == null)
            throw new RuntimeException("Ordine non presente in lista");
        
        Collection <Piatto> piatti = new ArrayList<>();
            for(PiattoFinale p:ordineCliente.getPiatti())
                for(int i = 0; i<p.getQuantita(); ++i)
                    piatti.add(new Piatto(p.getIdPiatto()));
    }
    
}
