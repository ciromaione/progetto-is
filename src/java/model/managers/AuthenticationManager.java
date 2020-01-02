/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.managers;

import java.sql.Connection;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ciro
 */
@Stateless
public class AuthenticationManager {
    
    public AuthenticationManager() {
    }
    
    
    public boolean loginTitolare(String hash) {
        return true;
    }
    
    public boolean loginStaff(String hash) {
        return true;
    }
    
    public boolean updatePasswordTitolare(String newPassword) {
        return true;
    }
    
    public boolean updatePasswordStaff(String newPassword) {
        return true;
    }
    
}
