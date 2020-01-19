/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.managers;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.EJBException;
import javax.ejb.embeddable.EJBContainer;
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
    
    public TitolareManagerTest() {
    }
    
    @Test
    public void TC_TM_1() throws Exception {
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put(EJBContainer.MODULES, new File("build/jar"));
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer(properties);
        TitolareManager instance = (TitolareManager) container.getContext().lookup("java:global/classes/TitolareManager");
        
        Piatto piatto = null;
        try {
            instance.aggiungiPiatto(piatto, ing, ingAgg, ingRim);
            fail("Eccezione non lanciata");
        } catch (EJBException ex) {
            assertTrue(true);
        }
        
        container.close();
    }

    
    
}
