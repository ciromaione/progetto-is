import java.sql.SQLException;

public class TestTitolareManager extends TestCase{
 
    private Piatto piatto;
    private Ingrediente Ingrediente;
    private String nome, foto, categoria;
     
    public TestTitolareManager(String name)
    {
        super(name);
         
    }
     
   public void testAggiungiPiatto() throws SQLException
   {
       piatto = new Piatto("null", "null", "null", "null");
       TitolareManager.addPiatto(Piatto, null, null, null);
       assertThrows(RunTimeException.class);

       piatto = new Piatto("con porchetta e fonduta di cheddar caramellato", null, null, null);
       TitolareManager.addPiatto(Piatto, null, null, null);
       assertThrows(RuntimeException.class);

       piatto = new Piatto("con porchetta e fonduta di cheddar caramellato", null, null, null);
       TitolareManager.addPiatto(Piatto, null, null, null);
       assertThrows(RuntimeException.class);

       piatto = new Piatto("Panino con Porchetta", "pppppppaaaaannnnniiiiinnnnnooooo", null, "Panino_con_porchetta.jpg");
       TitolareManager.addPiatto(Piatto, null, null, null);
       assertThrows(RuntimeException.class);
   
       piatto = new Piatto("Panino con Porchetta", "Panino", -3, "Panino_con_porchetta.jpg");
       TitolareManager.addPiatto(Piatto, null, null, null);
       assertThrows(RuntimeException.class);

       piatto = new Piatto("Panino con Porchetta", "Panino", 300, "Panino_con_porchetta.jpg");
       TitolareManager.addPiatto(Piatto, null, null, null);
       //poiché non deve restituire true non lancia nessun assert quindi nessun eccezione nel caso la lancia non é andato a buon fine
    }


    public void testAggiungiIngrediente() throws SQLException
    {
        ingrediente = new Ingrediente();
        ingrediente = TitolareManager.addIngrediente(null, null, null);
        assertThrows(RuntimeException.class);

        ingrediente = new Ingrediente();
        ingrediente = TitolareManager.addIngrediente("Iiiiinnnnngggggrrrrreeeeedddddiiiiieeeeennnnnttttteeeee", null, null);
        assertThrows(RuntimeException.class);

        ingrediente = new Ingrediente();
        ingrediente = TitolareManager.addIngrediente("Porchetta", "cccccaaaaarrrrrnnnnneeeeeeeeeeeeeee", null);
        assertThrows(RuntimeException.class);

        ingrediente = new Ingrediente();
        ingrediente = TitolareManager.addIngrediente("Porchetta", "Carne", 0);
        assertNotNull();
    }


    public void testPopolaritaPiattiMensile() throws SQLException
    {
        List<PiattoXQuantita> list;
        
        list= TitolareManager.PopolaritaPiattiMensile(13, null);
        assertThrows(RuntimeException.class);

        list = TitolareManager.PopolaritaPiattiMensile(05, 202);
        assertThrows(RuntimeException.class);

        list = TitolareManager.PopolaritaPiattiMensile(05, 2020);
        assertThrows(RuntimeException.class);

        list = TitolareManager.PopolaritaPiattiMensile(05, 2019);
        assertNotNull(list);

        list = TitolareManager.PopolaritaPiattiMensile(01, 2020);
        assertNotNull(list);

        list = TitolareManager.PopolaritaPiattiMensile(01, 2019);
        assertNotNull(list);
    }


    public static Test suite(){
        TestSuite suite = new TestSuite();
        suite.addTest(new TestTitolareManager("testAggiungiPiatto"));
        suite.addTest(new TestTitolareManager("testAggiungiIngrediente"));
        suite.addTest(new TestTitolareManager("testPopolaritaPiattiMensile"));
        return suite;
         
    }

}