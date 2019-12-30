/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Ingrediente;
import entities.Piatto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ciro
 */
@Stateless
public class PiattoDAO {
    
    @Inject
    Connection conn;

    public PiattoDAO() {
    }
    

    public Piatto save(Piatto entity) {
        try {
            PreparedStatement ps = conn.prepareStatement(""
                    + "INSERT INTO piatto (nome, categoria, prezzo_cent, foto) "
                    + "VALUES (?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, entity.getNome());
            ps.setString(2, entity.getCategoria());
            ps.setInt(3, entity.getPrezzoCent());
            ps.setString(4, entity.getFoto());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()) entity.setId(rs.getInt(1));
            else throw new RuntimeException();
            
            saveRelationship(entity.getId(), entity.getIngredienti(), "piattoXing");
            saveRelationship(entity.getId(), entity.getIngredientiAggiungibili(), "piattoXing_ins");
            saveRelationship(entity.getId(), entity.getIngredientiRimovibili(), "piattoXing_rem");
            
            return entity;
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
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

    public void remove(Piatto entity) {
        try {
            PreparedStatement ps = conn.prepareStatement(""
                    + "DELETE FROM piatto "
                    + "WHERE id = ?");
            ps.setInt(1, entity.getId());
            
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public Piatto findById(Integer id) {
        try {
            PreparedStatement ps = conn.prepareStatement(""
                    + "SELECT id, nome, categoria, prezzo_cent, foto "
                    + "FROM piatto "
                    + "WHERE id = ?");
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()) {
                Piatto piatto = new Piatto();
                piatto.setId(rs.getInt(1));
                piatto.setNome(rs.getString(2));
                piatto.setCategoria(rs.getString(3));
                piatto.setPrezzoCent(rs.getInt(4));
                piatto.setFoto(rs.getString(5));
                piatto.setIngredienti(getIngredientiList(piatto.getId(), "piattoXing"));
                piatto.setIngredientiAggiungibili(getIngredientiList(piatto.getId(), "piattoXing_ins"));
                piatto.setIngredientiRimovibili(getIngredientiList(piatto.getId(), "piattoXing_rem"));
                return piatto;
            }
            return null;
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    private Collection<Ingrediente> getIngredientiList(int id, String table) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(""
                + "SELECT i.id, i.nome, i.categoria, i.sovrapprezzoCent "
                    + "FROM ingrediente i, "+table+" pi "
                    + "WHERE pi.id_piatto = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        ArrayList<Ingrediente> all = new ArrayList<>();
        while (rs.next()) {
            Ingrediente ing = new Ingrediente();
            ing.setId(rs.getInt(1));
            ing.setNome(rs.getString(2));
            ing.setCategoria(rs.getString(3));
            ing.setSovrapprezzoCent(rs.getInt(2));
            all.add(ing);
        }
        return all;
    }

    public List<Piatto> findAll() {
        try {
            PreparedStatement ps = conn.prepareStatement(""
                    + "SELECT id, nome, categoria, prezzo_cent, foto "
                    + "FROM piatto");
            
            ResultSet rs = ps.executeQuery();
            
            ArrayList<Piatto> all = new ArrayList<>();
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
                all.add(piatto);
            }
            return all;
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public List<String> findCategorie() {
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

}
