/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author ciro
 */
public class ConnectionProducer {
    
    @Produces
    private Connection getConnection() {
        Connection conn = null;
        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource)ctx.lookup("jdbc/menumaxi");
            conn = ds.getConnection();
        } catch (NamingException | SQLException ex) {
            ex.printStackTrace();
        }
        return conn;
    } 
    
    private void closeConnection(@Disposes Connection conn) throws SQLException {
        conn.close();
    }
    
}
