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
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.Stateless;
import model.entities.Ingrediente;
import model.entities.Piatto;

/**
 *
 * @author ciro
 */

/*
* Questa classe permette di effettuare le operazioni riservate al titolare
* offre la modifica del menu e permette di ottenere i guadagni giornalieri relativi a ogni piatto, il totale incassato
* un determinato giorno e la lista dei piatti ordinati per unità vendute relative ad un mese
*/
@Stateless
public class TitolareManager {
    
    /*
    * classe che rappresenta il prodotto cartesiano piatto X quantita
    * utile per la costruzione del risultato dei metodi successivi
    */
    public static class PiattoXQuantita {
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

    //@Inject
    private Connection conn;

    public TitolareManager() {
        conn = ConnectionProducer.getConnection();
    }
    
    public Piatto aggiungiPiatto(Piatto piatto, int[] ing, int[] ingAgg, int[] ingRim) {
        
        if(piatto == null) throw new IllegalArgumentException("Il piatto non può essere null");
        if(piatto.getNome().length() > 50) throw new IllegalArgumentException("Nome troppo lungo");
        if(piatto.getFoto().length() > 50) throw new IllegalArgumentException("Nome foto troppo lungo");
        if(piatto.getCategoria().length() > 30) throw new IllegalArgumentException("Categoria troppo lungo");
        if(piatto.getPrezzoCent() < 10) throw new IllegalArgumentException("Il prezzo non può essere inferiore a 10 centesimi");
        
        try {
            PreparedStatement ps = conn.prepareStatement(""
                    + "INSERT INTO piatto (nome, categoria, prezzo_cent, foto) "
                    + "VALUES (?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, piatto.getNome());
            ps.setString(2, piatto.getCategoria());
            ps.setInt(3, piatto.getPrezzoCent());
            ps.setString(4, piatto.getFoto());
            if(ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            piatto.setId(id);
            
            saveRelationship(id, ing, "piattoXing");
            saveRelationship(id, ingAgg, "piattoXing_ins");
            saveRelationship(id, ingRim, "piattoXing_rem");
            return piatto;
            
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
    
    public int aggiungiIngrediente(Ingrediente ingrediente) {
        
        if(ingrediente == null) throw new IllegalArgumentException("L'ingrediente non può essere null");
        if(ingrediente.getNome().length() > 50) throw new IllegalArgumentException("Nome troppo lungo");
        if(ingrediente.getCategoria().length() > 30) throw new IllegalArgumentException("Categoria troppo lunga");
        if(ingrediente.getSovrapprezzoCent() < 0) throw new IllegalArgumentException("Il prezzo non può essere negativo");
        
        try {
            PreparedStatement ps = conn.prepareStatement(""
                    + "INSERT INTO ingrediente (nome, categoria, sovrapprezzo_cent) "
                    + "VALUES (?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, ingrediente.getNome());
            ps.setString(2, ingrediente.getCategoria());
            ps.setInt(3, ingrediente.getSovrapprezzoCent());
        
            if(ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            return rs.getInt(1);

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    /*
    * Metodo che restituisce la popolarità dei piatti di un determinato mese
    * @param mese il mese di cui si vuole cercare la popolarità dei piatti
    * @param anno anno di cui si vuole cercare la popolarità dei piatti venduti
    * @result restituisce la lista dei piatti più venduti durante il messe passato e relativa quantità
    */
    public List<PiattoXQuantita> popolaritaPiattiMensile(int mese, int anno) {
        
        GregorianCalendar oggi = new GregorianCalendar();
        int meseOggi = oggi.get(Calendar.MONTH)+1;
        int annoOggi = oggi.get(Calendar.YEAR);
        
        if(mese < 1 || mese > 12) throw new IllegalArgumentException("Mese non valido");
        if(anno < 2019 || anno > annoOggi) throw new IllegalArgumentException("Anno non valido");
        if(anno == annoOggi && mese > meseOggi) throw new IllegalArgumentException("La data é nel futuro");
        
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
    /*
    * restituisce la lista dei piatti con relativa quantità venduti in un giorno
    */
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
    
    /*
    * restituisce il totale in centesimi incassate nella data inserita come parametro
    */
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
    
    public List<Piatto> getPiattiRimozione() {
        try {
            PreparedStatement ps = conn.prepareStatement(""
                    + "SELECT id, nome, categoria, prezzo_cent "
                    + "FROM piatto "
                    + "ORDER BY nome");
            
            ResultSet rs = ps.executeQuery();
            
            List<Piatto> results = new ArrayList<>();
            while(rs.next()) {
                Piatto piatto = new Piatto();
                piatto.setId(rs.getInt(1));
                piatto.setNome(rs.getString(2));
                piatto.setCategoria(rs.getString(3));
                piatto.setPrezzoCent(rs.getInt(4));
                results.add(piatto);
            }
            return results;
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    
    private void saveRelationship(int id, int[] ing, String table) throws SQLException {
        PreparedStatement ps;
        if(ing != null) {
            for (Integer idIng : ing) {
                ps = conn.prepareStatement(""
                        + "INSERT INTO "+table+" (id_ingrediente, id_piatto) "
                        + "VALUES (?, ?)");
                ps.setInt(1, idIng);
                ps.setInt(2, id);
                ps.executeUpdate();
            }
        }
    }
}
