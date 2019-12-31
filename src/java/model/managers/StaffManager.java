/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.managers;

import java.sql.Connection;
import javax.ejb.Singleton;
import javax.inject.Inject;

/**
 *
 * @author ciro
 */
@Singleton
public class StaffManager {

    @Inject
    Connection conn;
    @Inject
    OrdiniSingleton os;

    public StaffManager() {
    }
    
    public void stampaConto(String tavolo) {
        
    }
    
}
