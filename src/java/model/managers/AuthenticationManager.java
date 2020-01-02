/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.managers;

import java.sql.Connection;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ciro
 */
@Stateless
public class AuthenticationManager {
    
    private String hashPasswordTitolare;
    private String hashPasswordStaff;
    
    @Inject
    Connection conn;

    public AuthenticationManager() {
    }
    
    public boolean loginTitolare(String hash) {
        
    }
    
    public boolean loginStaff(String hash) {
        
    }
    
    public boolean updatePasswordTitolare(String newPassword) {
        
    }
    
    public boolean updatePasswordStaff(String newPassword) {
        
    }
    
}
