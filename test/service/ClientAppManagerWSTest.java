/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.embeddable.EJBContainer;
import javax.ws.rs.core.Response;
import model.entities.Ingrediente;
import model.entities.OrdineCliente;
import model.entities.Piatto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author ciro
 */
public class ClientAppManagerWSTest {
    
    public ClientAppManagerWSTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getMenu method, of class ClientAppManagerWS.
     */
    @Test
    public void testGetMenu() throws Exception {
        System.out.println("getMenu");
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        ClientAppManagerWS instance = (ClientAppManagerWS)container.getContext().lookup("java:global/classes/ClientAppManagerWS");
        
        List<Piatto> expResult = new ArrayList<>();
        
        List<Ingrediente> ingredientiListP1 = new ArrayList<>();
        List<Ingrediente> ingredientiListP2 = new ArrayList<>();
        List<Ingrediente> ingredientiListP3 = new ArrayList<>();
        Ingrediente ing1 = new Ingrediente(1, "ing1", "cat_ing_1", 100);
        Ingrediente ing2 = new Ingrediente(2, "ing2", "cat_ing_1", 50);
        Ingrediente ing3 = new Ingrediente(3, "ing3", "cat_ing_2", 150);
        Ingrediente ing4 = new Ingrediente(1, "ing4", "cat_ing_3", 200);
        Ingrediente ing5 = new Ingrediente(5, "ing5", "cat_ing_4", 50);
        
        ingredientiListP1.add(ing1);
        ingredientiListP1.add(ing2);
        ingredientiListP1.add(ing3);
        ingredientiListP2.add(ing5);
        ingredientiListP2.add(ing3);
        ingredientiListP2.add(ing1);
        ingredientiListP3.add(ing1);
        ingredientiListP3.add(ing3);
        ingredientiListP3.add(ing4);
        ingredientiListP3.add(ing5);
        
        expResult.add(new Piatto(1, "piatto1", null, 1000, "cat_piatto_1", ingredientiListP1, null, null));
        expResult.add(new Piatto(2, "piatto2", null, 900, "cat_piatto_2", ingredientiListP1, null, null));
        expResult.add(new Piatto(3, "piatto3", null, 850, "cat_piatto_3", ingredientiListP1, null, null));
        
        List<Piatto> result = instance.getMenu();
        assertEquals(expResult, result);
        container.close();
        
    }

    /**
     * Test of getCategorie method, of class ClientAppManagerWS.
     */
    @Test
    public void testGetCategorie() throws Exception {
        System.out.println("getCategorie");
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        ClientAppManagerWS instance = (ClientAppManagerWS)container.getContext().lookup("java:global/classes/ClientAppManagerWS");
        List<String> expResult = null;
        List<String> result = instance.getCategorie();
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of confermaOrdine method, of class ClientAppManagerWS.
     */
    @Test
    public void testConfermaOrdine() throws Exception {
        System.out.println("confermaOrdine");
        OrdineCliente ordineCliente = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        ClientAppManagerWS instance = (ClientAppManagerWS)container.getContext().lookup("java:global/classes/ClientAppManagerWS");
        Response expResult = null;
        Response result = instance.confermaOrdine(ordineCliente);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of richiestaConto method, of class ClientAppManagerWS.
     */
    @Test
    public void testRichiestaConto() throws Exception {
        System.out.println("richiestaConto");
        String tavolo = "";
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        ClientAppManagerWS instance = (ClientAppManagerWS)container.getContext().lookup("java:global/classes/ClientAppManagerWS");
        instance.richiestaConto(tavolo);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
