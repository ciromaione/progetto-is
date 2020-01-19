/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.managers;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.EJBException;
import javax.ejb.embeddable.EJBContainer;
import model.entities.Ingrediente;
import model.entities.Piatto;
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
    
    @Test
    public void TC_TM_1() throws Exception {
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer(properties);
        TitolareManager instance = (TitolareManager) container.getContext().lookup("java:global/classes/TitolareManager");
        
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
        
        
        container.close();
    }
    
   
    @Test
    public void TC_TM_2() throws Exception {
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer(properties);
        TitolareManager instance = (TitolareManager) container.getContext().lookup("java:global/classes/TitolareManager");
        
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
        
        
        container.close();
    }
    
    
}
