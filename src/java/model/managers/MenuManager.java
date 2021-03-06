/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.managers;

import java.sql.Connection;
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
import model.entities.Ingrediente;
import model.entities.Menu;
import model.entities.Piatto;

/**
 *
 * @author ciro
 */
/*
* Classe che consente di ottenere il Menu completo oppure le categorie e gli ingredienti
*/
@Stateless
public class MenuManager {

    //@Inject
    Connection conn;

    public MenuManager() {
        conn = ConnectionProducer.getConnection();
    }
    
    
    
    public Menu getMenu() {
        try {
            PreparedStatement ps = conn.prepareStatement(""
                    + "SELECT id, nome, categoria, prezzo_cent, foto "
                    + "FROM piatto");
            
            ResultSet rs = ps.executeQuery();
            
           Menu menu = new Menu();
            while(rs.next()) {
                Piatto piatto = new Piatto();
                piatto.setId(rs.getInt(1));
                piatto.setNome(rs.getString(2));
                piatto.setCategoria(rs.getString(3));
                piatto.setPrezzoCent(rs.getInt(4));
                piatto.setFoto(rs.getString(5));
                piatto.setIngredienti(getIngredientiList(piatto.getId(), "piattoXing"));
                piatto.setIngredientiAggiungibili(getIngredientiList(piatto.getId(), "piattoXing_ins"));
                piatto.setIngredientiRimovibili(getIngredientiList(piatto.getId(), "piattoXing_rem"));
                menu.addPiatto(piatto);
            }
            return menu;
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public List<String> getCategorie() {
        try {
            PreparedStatement ps = conn.prepareStatement(""
                    + "SELECT DISTINCT categoria "
                    + "FROM piatto");
            
            ResultSet rs = ps.executeQuery();
            
            ArrayList<String> all = new ArrayList<>();
            while(rs.next()) 
                all.add(rs.getString(1));
            
            return all;
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public List<Ingrediente> getIngredienti() {
        try {
            PreparedStatement ps = conn.prepareStatement(""
                    + "SELECT id, nome, categoria, sovrapprezzo_cent "
                    + "FROM ingrediente");
            
            ResultSet rs = ps.executeQuery();
            
            ArrayList<Ingrediente> results = new ArrayList<>();
            while(rs.next()){
                Ingrediente ing = new Ingrediente();
                ing.setId(rs.getInt(1));
                ing.setNome(rs.getString(2));
                ing.setCategoria(rs.getString(3));
                ing.setSovrapprezzoCent(rs.getInt(4));
                results.add(ing);
            }
            
            return results;
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }            
    
    private Collection<Ingrediente> getIngredientiList(int id, String table) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(""
                + "SELECT i.id, i.nome, i.categoria, i.sovrapprezzo_cent "
                    + "FROM ingrediente i, "+table+" pi "
                    + "WHERE pi.id_piatto = ? AND i.id = pi.id_ingrediente");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        ArrayList<Ingrediente> all = new ArrayList<>();
        while (rs.next()) {
            Ingrediente ing = new Ingrediente();
            ing.setId(rs.getInt(1));
            ing.setNome(rs.getString(2));
            ing.setCategoria(rs.getString(3));
            ing.setSovrapprezzoCent(rs.getInt(4));
            all.add(ing);
        }
        return all;
    }
}
