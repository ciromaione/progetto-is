/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.managers;

import javax.ejb.embeddable.EJBContainer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ciro
 */
public class AuthenticationManagerTest {
    
    public AuthenticationManagerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of loginTitolare method, of class AuthenticationManager.
     */
    @Test
    public void testLoginTitolare() throws Exception {
        System.out.println("loginTitolare");
        String password = "";
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        AuthenticationManager instance = (AuthenticationManager)container.getContext().lookup("java:global/classes/AuthenticationManager");
        boolean expResult = false;
        boolean result = instance.loginTitolare(password);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loginStaff method, of class AuthenticationManager.
     */
    @Test
    public void testLoginStaff() throws Exception {
        System.out.println("loginStaff");
        String password = "";
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        AuthenticationManager instance = (AuthenticationManager)container.getContext().lookup("java:global/classes/AuthenticationManager");
        boolean expResult = false;
        boolean result = instance.loginStaff(password);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updatePasswordTitolare method, of class AuthenticationManager.
     */
    @Test
    public void testUpdatePasswordTitolare() throws Exception {
        System.out.println("updatePasswordTitolare");
        String newPassword = "";
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        AuthenticationManager instance = (AuthenticationManager)container.getContext().lookup("java:global/classes/AuthenticationManager");
        instance.updatePasswordTitolare(newPassword);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updatePasswordStaff method, of class AuthenticationManager.
     */
    @Test
    public void testUpdatePasswordStaff() throws Exception {
        System.out.println("updatePasswordStaff");
        String newPassword = "";
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        AuthenticationManager instance = (AuthenticationManager)container.getContext().lookup("java:global/classes/AuthenticationManager");
        instance.updatePasswordStaff(newPassword);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
