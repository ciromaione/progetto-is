/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.managers;

import java.math.BigInteger;
import java.security.MessageDigest;
import javax.ejb.Stateless;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.inject.Inject;

/**
 *
 * @author ciro
 */
@Stateless
public class AuthenticationManager {
    
    @Inject 
    private Connection conn;
    
    public AuthenticationManager() {
    }
    
    public boolean loginTitolare(String password) {
        try {
            PreparedStatement ps = conn.prepareStatement(""
                    + "SELECT password FROM passwords WHERE nome = 'titolare'");
            ResultSet rs = ps.executeQuery();
            String titolareHash;
            if(rs.next())
                titolareHash = rs.getString(1);
            else throw new RuntimeException();
            return titolareHash.equals(encrypt(password));
        } catch (NoSuchAlgorithmException | SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public boolean loginStaff(String password) {
        try {
            PreparedStatement ps = conn.prepareStatement(""
                    + "SELECT password FROM passwords WHERE nome = 'staff'");
            ResultSet rs = ps.executeQuery();
            String staffHash;
            if(rs.next())
                staffHash = rs.getString(1);
            else throw new RuntimeException();
            return staffHash.equals(encrypt(password));
        } catch (NoSuchAlgorithmException | SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public void updatePasswordTitolare(String newPassword) {
        try {
            PreparedStatement ps = conn.prepareStatement(""
                    + "UPDATE passwords SET password = ? WHERE nome = 'titolare'");
            ps.setString(1, encrypt(newPassword));
            ps.executeUpdate();
        } catch (NoSuchAlgorithmException | SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public void updatePasswordStaff(String newPassword) {
        try {
            PreparedStatement ps = conn.prepareStatement(""
                    + "UPDATE passwords SET password = ? WHERE nome = 'staff'");
            ps.setString(1, encrypt(newPassword));
            ps.executeUpdate();
        } catch (NoSuchAlgorithmException | SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    private String encrypt(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-1");
        digest.reset();
        digest.update(password.getBytes(StandardCharsets.UTF_8));
        return String.format("%040x", new BigInteger(1, digest.digest()));
    }
    
}
