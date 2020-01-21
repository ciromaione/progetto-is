/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.managers;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJBException;
import javax.ejb.embeddable.EJBContainer;
import model.entities.Ingrediente;
import model.entities.Piatto;
import model.managers.TitolareManager.PiattoXQuantita;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author ciro
 */
public class TitolareManagerTest {
    
    private final int[] ing = null;
    private final int[] ingAgg = null;
    private final int[] ingRim = null;
    
    private Map<String, Object> properties;
    
    public TitolareManagerTest() {
        properties = new HashMap<String, Object>();
        properties.put(EJBContainer.MODULES, new File("build/jar"));
    }
    
    
    private void initDB() {
        
        try (Connection conn = ConnectionProducer.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(
                    "TRUNCATE ordineXpiatto");
            ps.executeUpdate();
            
            ps = conn.prepareStatement(
                    "TRUNCATE piattoXing");
            ps.executeUpdate();
            
            ps = conn.prepareStatement(
                    "TRUNCATE piattoXing_ins");
            ps.executeUpdate();
            
            ps = conn.prepareStatement(
                    "TRUNCATE piattoXing_ins");
            ps.executeUpdate();
            
            ps = conn.prepareStatement(
                    "DELETE FROM ordine;");
            ps.executeUpdate();
            
            ps = conn.prepareStatement(
                    "DELETE FROM piatto;");
            ps.executeUpdate();
            
            ps = conn.prepareStatement(
                    "DELETE FROM ingrediente;");
            ps.executeUpdate();
            
            ps = conn.prepareStatement(""
                    + "INSERT INTO ordine(id, data) VALUES (1, '2020-01-21'), (2, '2020-01-10')");
            ps.executeUpdate();
            
            ps = conn.prepareStatement("INSERT INTO piatto (id, nome, prezzo_cent, categoria) VALUES "
                    +"(1, 'Blu Ginz', 850, 'panini'), (2, 'Bufalotto', 850, 'panini'), (3, 'gingillo', 1000, 'panini'), (4, 'Coca Cola', 250, 'bibite')");
            ps.executeUpdate();
            
            ps = conn.prepareStatement(""
                    + "INSERT INTO ordineXpiatto (id_ordine, id_piatto, quantita) VALUES "
                    + "(1,1,1),(1,2,1),(1,4,1),(2,1,3),(2,2,1),(2,4,2)");
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    @Test
    public void TC_TM_1() throws Exception {
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer(properties);
        TitolareManager instance = (TitolareManager) container.getContext().lookup("java:global/classes/TitolareManager");
        
        initDB();
        
        //TC_TM_1.1
        Piatto piatto = null;
        try {
            instance.aggiungiPiatto(piatto, ing, ingAgg, ingRim);
            fail("Eccezione non lanciata");
        } catch (EJBException ex) {
        }
        
        //TC_TM_1.2
        piatto = new Piatto();
        piatto.setNome("Panino con porchetta e fonduta di cheddar caramellato");
        try {
            instance.aggiungiPiatto(piatto, ing, ingAgg, ingRim);
            fail("Eccezione non lanciata");
        } catch (EJBException ex) {
        }
        
        //TC_TM_1.3
        piatto = new Piatto();
        piatto.setNome("Panino con Porchetta");
        piatto.setFoto("Qwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnm.jpg");
        try {
            instance.aggiungiPiatto(piatto, ing, ingAgg, ingRim);
            fail("Eccezione non lanciata");
        } catch (EJBException ex) {
        }
        
        //TC_TM_1.4
        piatto = new Piatto();
        piatto.setNome("Panino con Porchetta");
        piatto.setFoto("Panino_con_porchetta.jpg");
        piatto.setCategoria("pppppppaaaaannnnniiiiinnnnnooooo");
        try {
            instance.aggiungiPiatto(piatto, ing, ingAgg, ingRim);
            fail("Eccezione non lanciata");
        } catch (EJBException ex) {
        }
        
        //TC_TM_1.5
        piatto = new Piatto();
        piatto.setNome("Panino con Porchetta");
        piatto.setFoto("Panino_con_porchetta.jpg");
        piatto.setCategoria("panino");
        piatto.setPrezzoCent(-3);
        try {
            instance.aggiungiPiatto(piatto, ing, ingAgg, ingRim);
            fail("Eccezione non lanciata");
        } catch (EJBException ex) {
        }
        
        //TC_TM_1.6
        piatto = new Piatto();
        piatto.setNome("Panino con Porchetta");
        piatto.setFoto("Panino_con_porchetta.jpg");
        piatto.setCategoria("panino");
        piatto.setPrezzoCent(800);
        piatto = instance.aggiungiPiatto(piatto, ing, ingAgg, ingRim);
        try (Connection conn = ConnectionProducer.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT * FROM piatto WHERE id = ?");
            ps.setInt(1, piatto.getId());
            assertTrue(ps.executeQuery().next());
        }
        
        
        finally {
            container.close();
        }
    }
    
   
    @Test
    public void TC_TM_2() throws Exception {
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer(properties);
        TitolareManager instance = (TitolareManager) container.getContext().lookup("java:global/classes/TitolareManager");
        
        initDB();
        
        //TC_TM_2.1
        Ingrediente ing = null;
        try {
            instance.aggiungiIngrediente(ing);
            fail("Eccezione non lanciata");
        } catch (EJBException ex) {
        }
        
        //TC_TM_2.2
        ing = new Ingrediente();
        ing.setNome("iiiinnnnngggggrrrrreeeeedddddiiiiieeeeennnnnttttteeeee");
        try {
            instance.aggiungiIngrediente(ing);
            fail("Eccezione non lanciata");
        } catch (EJBException ex) {
        }
        
        //TC_TM_2.3
        ing = new Ingrediente();
        ing.setNome("Porchetta");
        ing.setCategoria("cccccaaaaarrrrrnnnnneeeeeeeeeeeeeee");
        try {
            instance.aggiungiIngrediente(ing);
            fail("Eccezione non lanciata");
        } catch (EJBException ex) {
        }
        
        //TC_TM_2.4
        ing = new Ingrediente();
        ing.setNome("Porchetta");
        ing.setCategoria("carne");
        ing.setSovrapprezzoCent(-10);
        try {
            instance.aggiungiIngrediente(ing);
            fail("Eccezione non lanciata");
        } catch (EJBException ex) {
        }
        
        //TC_TM_2.5
        ing = new Ingrediente();
        ing.setNome("Porchetta");
        ing.setCategoria("Carne");
        ing.setSovrapprezzoCent(150);
        int id = instance.aggiungiIngrediente(ing);
        try (Connection conn = ConnectionProducer.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT * FROM ingrediente WHERE id = ?");
            ps.setInt(1, id);
            assertTrue(ps.executeQuery().next());
        }
        
        
        finally {
            container.close();
        }
    }
    
    @Test
    public void TC_TM_3() throws Exception {
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer(properties);
        TitolareManager instance = (TitolareManager) container.getContext().lookup("java:global/classes/TitolareManager");
        
        initDB();
        
        int mese = 0;
        int anno = 0;
        List<PiattoXQuantita> oracolo = new ArrayList<>();
        
        try (Connection conn = ConnectionProducer.getConnection()) {
            
            PreparedStatement ps = conn.prepareStatement(""
                    + "SELECT p.id, SUM(op.quantita) AS quantita " 
                    + "FROM ordine o "
                    + "RIGHT JOIN ordineXpiatto op ON o.id = op.id_ordine "
                    + "RIGHT JOIN piatto p ON (op.id_piatto = p.id AND MONTH(o.data) = 1 AND YEAR(o.data) = 2020) "
                    + "GROUP BY p.id, p.nome, p.categoria, p.prezzo_cent "
                    + "ORDER BY quantita DESC");
            ResultSet risultatoAtteso = ps.executeQuery();
            
            while(risultatoAtteso.next()){
                Piatto p = new Piatto();
                p.setId(risultatoAtteso.getInt(1));
                int quantita = risultatoAtteso.getInt(2);
                oracolo.add(new PiattoXQuantita(p, quantita));
            }
        }
        
        
        //TC_TM_3.1
        mese = 13;
        try {
            instance.popolaritaPiattiMensile(mese, anno);
            fail("Eccezione non lanciata");
        } catch (EJBException ex) {
            
        }
        
        //TC_TM_3.2
        mese = 5;
        anno = 202;
        try {
            instance.popolaritaPiattiMensile(mese, anno);
            fail("Eccezione non lanciata");
        } catch (EJBException ex) {
            
        }
        
        //TC_TM_3.3
        mese = 5;
        anno = 2020;
        try {
            instance.popolaritaPiattiMensile(mese, anno);
            fail("Eccezione non lanciata");
        } catch (EJBException ex) {
            
        }
        
        
        //TC_TM_3.4
        mese = 5;
        anno = 2019;
        try {
            instance.popolaritaPiattiMensile(mese, anno);
        } catch (EJBException ex) {
            fail("Non deve lanciare alcuna eccezione");
        }
        
        //TC_TM_3.5
        mese = 1;
        anno = 2020;
        try {
            List<PiattoXQuantita> risultato = instance.popolaritaPiattiMensile(mese, anno);
            boolean res = true;
            if(risultato.size() != oracolo.size())
                fail("Il risultato ha lunghezza diversa dai risultati attesi");
            else {
                for(int i = 0; i < risultato.size(); ++i) {
                    if(risultato.get(i).getPiatto().getId() != oracolo.get(i).getPiatto().getId())
                        res = false;
                    if(risultato.get(i).getQuantita() != oracolo.get(i).getQuantita())
                        res = false;
                }
                assertTrue(res);
            }
        } catch (EJBException ex) {
            fail("Non deve lanciare alcuna eccezione");
        }
        
        //TC_TM_3.6
        mese = 1;
        anno = 2019;
        try {
            instance.popolaritaPiattiMensile(mese, anno);
        } catch (EJBException ex) {
            fail("Non deve lanciare alcuna eccezione");
        }
        
        
        finally {
            container.close();
        }
    }
}
