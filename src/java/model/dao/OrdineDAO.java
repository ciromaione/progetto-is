/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import javax.ejb.Stateless;
import javax.inject.Inject;
import model.entities.Ordine;
import model.entities.Piatto;

/**
 *
 * @author ciro
 */
@Stateless
public class OrdineDAO {
    
    @Inject
    Connection conn;

    public OrdineDAO() {
    }

    public void save(Ordine entity) {
        try {
            PreparedStatement ps = conn.prepareStatement(""
                    + "INSERT INTO ordine (data, totale_cent) "
                    + "VALUES (?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setDate(1, entity.getData());
            ps.setInt(2, entity.getTotaleCent());
            
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next())
                entity.setId(rs.getInt(1));
            else
                throw new RuntimeException();
            
            addPiatti(entity.getId(), entity.getPiatti());
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void addPiatti(Integer idOrdine, Collection<Piatto> piatti) throws SQLException {
        class PQuant {
            public Integer id;
            public Integer q;
            public PQuant(Integer id) {this.id = id; q = 0;}
            public void increment() {q+=1;}
        }
        HashMap<Integer, PQuant> pquant = new HashMap<>();
        for (Piatto p:piatti)
            if(pquant.containsKey(p.getId()))
                pquant.get(p.getId()).increment();
            else
                pquant.put(p.getId(), new PQuant(p.getId()));
        PreparedStatement ps;
        for(PQuant pq:pquant.values()) {
            ps = conn.prepareStatement(""
                        + "INSERT INTO ordineXpiatto (id_ordine, id_piatto, quantita) "
                        + "VALUES (?, ?, ?)");
            ps.setInt(1, idOrdine);
            ps.setInt(2, pq.id);
            ps.setInt(3, pq.q);
            ps.executeUpdate();
        }
                    
    }
    
    
}
