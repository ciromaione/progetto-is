public class OrdineManagerTest{

    @Test
    public void TC_OM_1() throws Exception {
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer(properties);
        OrdineManagerTest instance = (OrdineManager) container.getContext().lookup("java:global/classes/OrdineManager");
        
        //TC_OM_1.1
        String tavolo = null;
        try {
            instance.salvaConto(tavolo);
            fail("Eccezione non lanciata");
        } catch (EJBException ex) {
        }

        //TC_OM_1.2
        tavolo = null;
        OrdineStaff ordineStaff = null;
         try {
             instance.salvaConto(tavolo);
             fail("Eccezione non lanciata");
         } catch (EJBException ex) {
         }

         //TC_OM_1.3
        tavolo = "tavolo1";
        ordineStaff.setTavolo("tavolo", null, null);
        instance.salvaConto(tavolo);
        try (Connection conn = ConnectionProducer.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT * FROM ordineStaff WHERE tavolo = ?");
            ps.setInt(1, tavolo);
            assertTrue(ps.executeQuery().next());
        }            
        container.close();
    }

    @Test
    public void TC_OM_2() throws Exception {
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer(properties);
        OrdineManager instance = (OrdineManager) container.getContext().lookup("java:global/classes/OrdineManager");
         
        //TC_OM_2.1
         OrdineStaff ordine = null;
         try {
             instance.addOrdine(ordine);
             fail("Eccezione non lanciata");
         } catch (EJBException ex) {
         }

         //TC_OM_2.1 e TC_OM_2.2 sono uguali poiché ordine non puó essere diverso da null se tutti i suoi componenti sono null

         //TC_OM_2.3
         ordine.setTavolo("tavolo 1");
         try {
             instance.addOrdine(ordine);
             fail("Eccezione non lanciata");
         } catch (EJBException ex) {
         }

        //TC_OM_2.4
        ordine.setTavolo("tavolo 1");
        piatto = new Piatto();
        piatto.setNome("Panino con Porchetta");
        piatto.setFoto("Panino_con_porchetta.jpg");
        piatto.setCategoria("panino");
        piatto.setPrezzoCent(800);
        ordine.setPiatti(piatto);
        try {
            instance.addOrdine(ordine);
            fail("Eccezione non lanciata");
        } catch (EJBException ex) {
        }

        //TC_OM_2.5
        ordine.setTavolo("tavolo 1");
        piatto = new Piatto();
        piatto.setNome("Panino con Porchetta");
        piatto.setFoto("Panino_con_porchetta.jpg");
        piatto.setCategoria("panino");
        piatto.setPrezzoCent(800);
        ordine.setPiatti(piatto);
        ordine.setTotaleCent(800);
        instance.addOrdine(ordine);
        try (Connection conn = ConnectionProducer.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT * FROM ordineStaff WHERE ordineStaff = ?");
            ps.setInt(1, ordine);
            assertTrue(ps.executeQuery().next());
        }
        container.close();
     }

     @Test
    public void TC_OM_3() throws Exception {
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer(properties);
        OrdineManager instance = (OrdineManager) container.getContext().lookup("java:global/classes/OrdineManager");
        
        //TC_OM_3.1
        Conto conto = null;
        try {
            instance.addRichiestaConto(conto);
            fail("Eccezione non lanciata");
        } catch (EJBException ex) {
        }

        //TC_OM_3.3
        conto.setTavolo("tavolo 1");
        try {
            instance.addRichiestaConto(conto);
            fail("Eccezione non lanciata");
        } catch (EJBException ex) {
        }

        //TC_OM_3.4
        conto.setTavolo("tavolo 1");
        conto.setMetodo("carta");
        conto.setTotale("10.00");
        instance.addRichiestaConto(conto);
        try (Connection conn = ConnectionProducer.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT * FROM conto WHERE id = ?");
            ps.setInt(1, conto.getId());
            assertTrue(ps.executeQuery().next());
        }
        
        
        container.close();
    }
}
