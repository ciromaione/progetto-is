/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.managers;

import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJBException;
import javax.ejb.embeddable.EJBContainer;
import model.entities.Conto;
import model.entities.OrdineStaff;
import model.entities.PiattoEffettivo;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ciro
 */
public class OrdineManagerTest {
    
    private Map<String, Object> properties;
    
    public OrdineManagerTest() {
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
    
    private void initODS(EJBContainer container) throws Exception {
        OrdineDatiSingleton singleton = (OrdineDatiSingleton) container.getContext().lookup("java:global/classes/OrdineDatiSingleton");
        
        OrdineStaff ordine = new OrdineStaff();
        ordine.setTavolo("2");
        List<PiattoEffettivo> piatti = new ArrayList<>();
        PiattoEffettivo piatto = new PiattoEffettivo();
        piatto.setIdPiatto(1);
        piatto.setQuantita(1);
        piatto.setNomePiatto("Blu Ginz");
        piatti.add(piatto);
        ordine.setTotaleCent(850);
        ordine.setPiatti(piatti);
        singleton.addOrdine(ordine);
        
    }
    
    @Test
    public void TC_OM_1() throws Exception {
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer(properties);
        OrdineManager instance = (OrdineManager) container.getContext().lookup("java:global/classes/OrdineManager");
        
        initDB();
        initODS(container);
        
        String tavolo;
        
        //TC_OM_1.1
        tavolo = null;
        try {
            instance.salvaConto(tavolo);
            fail("Eccezione non lanciata");
        } catch (EJBException ex) {
            
        }
        
        //TC_OM_1.2
        tavolo = "1";
        try {
            instance.salvaConto(tavolo);
            fail("Eccezione non lanciata");
        } catch (EJBException ex) {
            
        }
        
        //TC_OM_1.3
        tavolo = "2";
        try(Connection conn = ConnectionProducer.getConnection()) {
            int id = instance.salvaConto(tavolo);
            PreparedStatement ps = conn.prepareStatement(""
                    + "SELECT data, totale_cent "
                    + "FROM ordine WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            assertTrue(rs.next());
            assertEquals(rs.getInt(2), 850);
            GregorianCalendar oggi = new GregorianCalendar();
            GregorianCalendar data = new GregorianCalendar();
            data.setTime(rs.getDate(1));
            boolean res = oggi.get(Calendar.DAY_OF_MONTH) == data.get(Calendar.DAY_OF_MONTH)
                    && oggi.get(Calendar.MONTH) == data.get(Calendar.MONTH)
                    && oggi.get(Calendar.YEAR) == data.get(Calendar.YEAR);
            assertTrue(res);
            
            ps = conn.prepareStatement(""
                    + "SELECT id_piatto, quantita "
                    + "FROM ordineXpiatto "
                    + "WHERE id_ordine = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            assertTrue(rs.next());
            assertEquals(rs.getInt(1), 1);
            assertEquals(rs.getInt(2), 1);
            
        } catch (EJBException ex) {
            fail("Non deve lanciare eccezioni"); 
        }
        
        finally {
            container.close();
        }
    }
    
    @Test
    public void TC_OM_2() throws Exception {
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer(properties);
        OrdineManager instance = (OrdineManager) container.getContext().lookup("java:global/classes/OrdineManager");
        
        initDB();
        initODS(container);
        
        OrdineStaff ordine;
        
        //TC_OM_2.1
        ordine = null;
        try {
            instance.addOrdine(ordine);
            fail("Eccezione non lanciata");
        } catch (EJBException ex) {
            
        }
        
        //TC_OM_2.2
        ordine = new OrdineStaff();
        try {
            instance.addOrdine(ordine);
            fail("Eccezione non lanciata");
        } catch (EJBException ex) {
            
        }
        
        //TC_OM_2.3
        ordine = new OrdineStaff();
        ordine.setTavolo("3");
        ordine.setPiatti(null);
        try {
            instance.addOrdine(ordine);
            fail("Eccezione non lanciata");
        } catch (EJBException ex) {
            
        }
        
        //TC_OM_2.4
        ordine = new OrdineStaff();
        ordine.setTavolo("3");
        try {
            instance.addOrdine(ordine);
            fail("Eccezione non lanciata");
        } catch (EJBException ex) {
            
        }
        
        //TC_OM_2.5
        ordine = new OrdineStaff();
        ordine.setTavolo("3");
        ArrayList<PiattoEffettivo> piatti = new ArrayList<>();
        PiattoEffettivo piatto = new PiattoEffettivo();
        piatto.setIdPiatto(1);
        piatto.setQuantita(2);
        piatto.setNomePiatto("Blu Ginz");
        piatti.add(piatto);
        ordine.setPiatti(piatti);      
        try {
            instance.addOrdine(ordine);
            fail("Eccezione non lanciata");
        } catch (EJBException ex) {
            
        }
        
        //TC_OM_2.6
        ordine = new OrdineStaff();
        ordine.setTavolo("3");
        piatto.setIdPiatto(1);
        piatto.setQuantita(2);
        piatto.setNomePiatto("Blu Ginz");
        piatti.add(piatto);
        ordine.setPiatti(piatti);
        ordine.setTotaleCent(0);
        try {
            instance.addOrdine(ordine);
            fail("Eccezione non lanciata");
        } catch (EJBException ex) {
            
        }
        
        //TC_OM_2.7
        ordine = new OrdineStaff();
        ordine.setTavolo("3");
        piatto.setIdPiatto(1);
        piatto.setQuantita(2);
        piatto.setNomePiatto("Blu Ginz");
        piatti.add(piatto);
        ordine.setPiatti(piatti);
        ordine.setTotaleCent(1700);
        try {
            instance.addOrdine(ordine);
            assertTrue(instance.getOrdiniDaCompletare().contains(ordine));
        } catch (EJBException ex) {
            fail("Non deve lanciare eccezioni");
        }
        
        finally {
            container.close();
        }
    }
    
    
    @Test
    public void TC_OM_3() throws Exception {
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer(properties);
        OrdineManager instance = (OrdineManager) container.getContext().lookup("java:global/classes/OrdineManager");
        
        initDB();
        initODS(container);
        
        Conto conto;
        
        //TC_OM_3.1
        conto = null;
        try {
            instance.addRichiestaConto(conto);
            fail("Eccezione non lanciata");
        } catch (EJBException ex) {
            
        }
        
        //TC_OM_3.2
        conto = new Conto();
        conto.setTavolo(null);
        try {
            instance.addRichiestaConto(conto);
            fail("Eccezione non lanciata");
        } catch (EJBException ex) {
            
        }
        
        //TC_OM_3.3
        conto = new Conto();
        conto.setTavolo("1");
        try {
            instance.addRichiestaConto(conto);
            fail("Eccezione non lanciata");
        } catch (EJBException ex) {
            
        }
        
        //TC_OM_3.4
        conto = new Conto();
        conto.setTavolo("2");
        try {
            conto = instance.addRichiestaConto(conto);
            assertTrue(instance.getRichiesteConto().contains(conto));
        } catch (EJBException ex) {
            fail("Non deve lanciare eccezioni");
        }
        
        finally {
            container.close();
        }
    }
    
}
