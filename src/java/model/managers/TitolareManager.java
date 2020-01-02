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
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import model.entities.Ingrediente;
import model.entities.Ordine;
import model.entities.Piatto;

/**
 *
 * @author ciro
 */
@Stateless
public class TitolareManager {

    @Inject
    Connection conn;

    public TitolareManager() {
    }
    
    public void aggiungiPiatto(Piatto piatto) {
        try {
            PreparedStatement ps = conn.prepareStatement(""
                    + "INSERT INTO piatto (nome, categoria, prezzo_cent, foto) "
                    + "VALUES (?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, piatto.getNome());
            ps.setString(2, piatto.getCategoria());
            ps.setInt(3, piatto.getPrezzoCent());
            ps.setString(4, piatto.getFoto());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()) piatto.setId(rs.getInt(1));
            else throw new RuntimeException();
            
            saveRelationship(piatto.getId(), piatto.getIngredienti(), "piattoXing");
            saveRelationship(piatto.getId(), piatto.getIngredientiAggiungibili(), "piattoXing_ins");
            saveRelationship(piatto.getId(), piatto.getIngredientiRimovibili(), "piattoXing_rem");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public void rimuoviPiatto(Integer id) {
        try {
            PreparedStatement ps = conn.prepareStatement(""
                    + "DELETE FROM piatto "
                    + "WHERE id = ?");
            ps.setInt(1, id);
            
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        
    }
    
    public void aggiungiIngrediente(Ingrediente ingrediente) {
        
    }
    
    public List<Ordine> popolaritaPiattiMensile(int mese) {
        return null;
        
    }
    
    public List<Piatto> guadagnoGiornaliero(Date data) {
        return null;
    }
    
    
    
    private void saveRelationship(int id, Collection<Ingrediente> ing, String table) throws SQLException {
        PreparedStatement ps;
        for (Ingrediente i : ing) {
            ps = conn.prepareStatement(""
                    + "INSERT INTO "+table+" (id_ingrediente, id_piatto) "
                    + "VALUES (?, ?)");
            ps.setInt(1, i.getId());
            ps.setInt(2, id);
            ps.executeUpdate();
        }
    }
}
