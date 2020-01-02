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
    
    public List<PiattoXQuantita> popolaritaPiattiMensile(int mese) {
        try {
            PreparedStatement ps = conn.prepareStatement(""
                    + "SELECT p.nome, p.categoria, p.prezzo_cent, SUM(op.quantita), p.id "
                    + "FROM piatto p, ordine o, ordineXpiatto op "
                    + "WHERE MONTH(o.data) = ? AND p.id = op.id_piatto AND o.id = op.id_ordine "
                    + "GROUP BY p.id, p.nome, p.categoria, p.prezzo_cent "
                    + "ORDER BY quantita DESC");
            ps.setInt(1, mese);
            ResultSet rs = ps.executeQuery();
            List<PiattoXQuantita> results = new ArrayList<>();
            while(rs.next()) {
                Piatto p = new Piatto();
                p.setNome(rs.getString(1));
                p.setCategoria(rs.getString(2));
                p.setPrezzoCent(rs.getInt(3));
                p.setId(rs.getInt(5));
                results.add(new PiattoXQuantita(p, rs.getInt(4)));
            }
            return results;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        
    }
    
    public List<Ordine> guadagnoGiornaliero(Date data) {
        try {
            PreparedStatement ps = conn.prepareStatement(""
                    + "SELECT o.id, o.data, o.totale_cent "
                    + "FROM ordine o "
                    + "WHERE o.data = ? "
                    + "ORDER BY o.id");
            ps.setDate(1, data);
            ResultSet rs = ps.executeQuery();
            List<Ordine> results = new ArrayList<>();
            while(rs.next()) {
                Ordine o = new Ordine();
                o.setId(rs.getInt(1));
                o.setData(rs.getDate(2));
                o.setTotaleCent(rs.getInt(3));
                results.add(o);
            }
            return results;
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
