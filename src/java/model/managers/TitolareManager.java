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
    
    public class PiattoXQuantita {
        private Piatto piatto;
        private int quantita;

        public PiattoXQuantita(Piatto piatto, int quantita) {
            this.piatto = piatto;
            this.quantita = quantita;
        }

        public Piatto getPiatto() {
            return piatto;
        }

        public void setPiatto(Piatto piatto) {
            this.piatto = piatto;
        }

        public int getQuantita() {
            return quantita;
        }

        public void setQuantita(int quantita) {
            this.quantita = quantita;
        }
    }

    @Inject
    private Connection conn;

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
        try {
            PreparedStatement ps = conn.prepareStatement(""
                    + "INSERT INTO ingrediente (nome, categoria, sovrapprezzo_cent) "
                    + "VALUES (?, ?, ?)");
            ps.setString(1, ingrediente.getNome());
            ps.setString(2, ingrediente.getCategoria());
            ps.setInt(3, ingrediente.getSovrapprezzoCent());
            
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public List<PiattoXQuantita> popolaritaPiattiMensile(int mese, int anno) {
        try {
            PreparedStatement ps = conn.prepareStatement(""
                    + "SELECT p.nome, p.categoria, p.prezzo_cent, SUM(op.quantita) AS quantita, p.id "
                    + "FROM ordine o "
                    + "RIGHT JOIN ordineXpiatto op ON o.id = op.id_ordine "
                    + "RIGHT JOIN piatto p ON (op.id_piatto = p.id AND MONTH(o.data) = ? AND YEAR(o.data) = ?)"
                    + "GROUP BY p.id, p.nome, p.categoria, p.prezzo_cent "
                    + "ORDER BY quantita DESC");
            ps.setInt(1, mese);
            ps.setInt(2, anno);
            ResultSet rs = ps.executeQuery();
            List<PiattoXQuantita> results = new ArrayList<>();
            while(rs.next()) {
                Piatto p = new Piatto();
                p.setNome(rs.getString(1));
                p.setCategoria(rs.getString(2));
                p.setPrezzoCent(rs.getInt(3));
                p.setId(rs.getInt(5));
                Integer quantita = rs.getInt(4);
                if(quantita == null) quantita = 0;
                results.add(new PiattoXQuantita(p, quantita));
            }
            
            return results;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        
    }
    
    public List<PiattoXQuantita> guadagnoGiornalieroPiatti(Date data) {
        try {
            PreparedStatement ps = conn.prepareStatement(""
                    + "SELECT p.id, p.nome, p.categoria, SUM(op.quantita), p.prezzo_cent "
                    + "FROM piatto p, ordineXpiatto op, ordine o "
                    + "WHERE p.id = op.id_piatto AND op.id_ordine = o.id AND o.data = ? "
                    + "GROUP BY p.id");
            ps.setDate(1, data);
            ResultSet rs = ps.executeQuery();
            List<PiattoXQuantita> results = new ArrayList<>();
            while(rs.next()) {
                Piatto p = new Piatto();
                p.setId(rs.getInt(1));
                p.setNome(rs.getString(2));
                p.setCategoria(rs.getString(3));
                int quantita = rs.getInt(4);
                p.setPrezzoCent(rs.getInt(5));
                
                results.add(new PiattoXQuantita(p, quantita));
            }
            return results;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public int guadagnoGiornaliero(Date data) {
        try {
            PreparedStatement ps = conn.prepareStatement(""
                    + "SELECT SUM(o.totale_cent) "
                    + "FROM ordine o "
                    + "WHERE o.data = ?");
            ps.setDate(1, data);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
                return rs.getInt(1);
            else
                return 0;
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
}
