/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Ordine;
import entities.Piatto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.ejb.Stateless;
import javax.inject.Inject;

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

    public Ordine save(Ordine entity) {
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
            
            for (Piatto p : entity.getPiatti()) {
                ps = conn.prepareStatement(""
                        + "INSERT INTO ordineXpiatto (id_ordine, id_piatto) "
                        + "VALUES (?, ?)");
                ps.setInt(1, entity.getId());
                ps.setInt(2, p.getId());
                ps.executeUpdate();
            }
 
            return entity;
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    
    
}
