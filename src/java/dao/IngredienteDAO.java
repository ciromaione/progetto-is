/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Ingrediente;
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
public class IngredienteDAO {

    @Inject
    Connection conn;

    public IngredienteDAO() {
    }
    
    public Ingrediente save(Ingrediente entity) {
        try {
            PreparedStatement ps = conn.prepareStatement(""
                    + "INSERT INTO ingrediente (nome, categoria, sovrapprezzo_cent) "
                    + "VALUES (?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, entity.getNome());
            ps.setString(2, entity.getCategoria());
            ps.setInt(3, entity.getSovrapprezzoCent());
            
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next())
                entity.setId(rs.getInt(1));
            else
                throw new RuntimeException();
            
            return entity;
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        
    }

    public Ingrediente findById(Integer id) {
        try {
            PreparedStatement ps = conn.prepareStatement(""
                    + "SELECT id, nome, categoria, sovrapprezzoCent "
                    + "FROM ingrediente "
                    + "WHERE id = ?");
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()) {
                Ingrediente ing = new Ingrediente();
                ing.setId(rs.getInt(1));
                ing.setNome(rs.getString(2));
                ing.setCategoria(rs.getString(3));
                ing.setSovrapprezzoCent(rs.getInt(4));
                return ing;
            }
            return null;
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<Ingrediente> findAll() {
        try {
            PreparedStatement ps = conn.prepareStatement(""
                    + "SELECT id, nome, categoria, sovrapprezzoCent "
                    + "FROM ingrediente");
            
            ResultSet rs = ps.executeQuery();
            
            ArrayList<Ingrediente> all = new ArrayList<>();
            while(rs.next()) {
                Ingrediente ing = new Ingrediente();
                ing.setId(rs.getInt(1));
                ing.setNome(rs.getString(2));
                ing.setCategoria(rs.getString(3));
                ing.setSovrapprezzoCent(rs.getInt(2));
                all.add(ing);
            }
            return all;
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    
    
    
}
