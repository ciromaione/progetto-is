/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.managers;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.inject.Inject;
import model.entities.Conto;
import model.entities.OrdineStaff;
import model.entities.Piatto;
import model.entities.PiattoEffettivo;

/**
 *
 * @author ciro
 */

/*
* Questa classe permette di salvare un conto e offre una facciata che nasconde
* le operazioni di OrdineDatiSingleton
*/
@Stateless
public class OrdineManager {

    //@Inject
    private Connection conn;
    @Inject
    private OrdineDatiSingleton os;

    public OrdineManager() {
        conn = ConnectionProducer.getConnection();
    }
    
    public int salvaConto(String tavolo) {
        
        if(tavolo == null) throw new IllegalArgumentException("Il tavolo non può essere null");
        
        OrdineStaff ordineStaff = os.removeFromOrdiniAttivi(tavolo);
        if(ordineStaff == null)
            throw new RuntimeException("Ordine non presente in lista");
        
        Collection <Piatto> piatti = new ArrayList<>();
        for(PiattoEffettivo p:ordineStaff.getPiatti())
                for(int i = 0; i<p.getQuantita(); ++i)
                    piatti.add(new Piatto(p.getIdPiatto()));
            
        try {
            PreparedStatement ps = conn.prepareStatement(""
                    + "INSERT INTO ordine (data, totale_cent) "
                    + "VALUES (?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setDate(1, new Date(new java.util.Date().getTime()));
            ps.setInt(2, ordineStaff.getTotaleCent());
            
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            Integer id;
            if(rs.next())
                id = rs.getInt(1);
            else
                throw new RuntimeException();
            
            salvaPiatti(id, ordineStaff.getPiatti());
            
            return id;
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        
    }
    
    public Collection<OrdineStaff> getOrdiniDaCompletare() {
        return os.getOrdiniDaCompletare();
    }
    
    public Collection<Conto> getRichiesteConto() {
        return os.getRichiesteConto();
    }
    
    
    public OrdineStaff addOrdine(OrdineStaff ordine) {
        
        if(ordine == null) throw new IllegalArgumentException("L'ordine non può essere null");
        if(ordine.getTavolo() == null) throw new IllegalArgumentException("Il tavolo non può essere null");
        if(ordine.getPiatti() == null) throw new IllegalArgumentException("L'ordine deve contenere almeno un piatto");
        if(ordine.getPiatti().isEmpty()) throw new IllegalArgumentException("L'ordine deve contenere almeno un piatto");
        if(ordine.getTotaleCent() == null) throw new IllegalArgumentException("Il prezzo deve essere definito");
        if(ordine.getTotaleCent() <= 0) throw new IllegalArgumentException("Il prezzo deve essere positivo");
        
        return os.addOrdine(ordine);
    }
    
    public Conto addRichiestaConto(Conto conto) {
        
        if(conto == null) throw new IllegalArgumentException("Il conto non può essere null");
        if(conto.getTavolo() == null) throw new IllegalArgumentException("Il tavolo non può essere null");
        
        return os.addRichiestaConto(conto);
    }
    
    public void removeFromOrdiniDaCompletare(int id) {
        os.removeFromOrdiniDaCompletare(id);
    }
    
    public void removeFromRichiesteConto(String tavolo) {
        os.removeFromRichiesteConto(tavolo);
    }

    private void salvaPiatti(Integer idOrdine, List<PiattoEffettivo> piatti) throws SQLException {
        
        Map<Integer, Integer> pxq = new HashMap<>();
        
        for(PiattoEffettivo ps:piatti)
            if(pxq.containsKey(ps.getIdPiatto())) {
                int q = pxq.get(ps.getIdPiatto()) + ps.getQuantita();
                pxq.put(ps.getIdPiatto(), q);
            }
            else
                pxq.put(ps.getIdPiatto(), ps.getQuantita());
        
        PreparedStatement ps;
        for(Map.Entry<Integer, Integer> e:pxq.entrySet()) {
            ps = conn.prepareStatement(""
                    + "INSERT INTO ordineXpiatto (id_ordine, id_piatto, quantita) "
                        + "VALUES (?, ?, ?)");
            ps.setInt(1, idOrdine);
            ps.setInt(2, e.getKey());
            ps.setInt(3, e.getValue());
            ps.executeUpdate();
        }
    }
    
    
}
