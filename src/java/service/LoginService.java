/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import model.managers.AuthenticationManager;

/**
 *
 * @author ciro
 */
@Stateless
@Path("client/login")
public class LoginService {
    
    @Inject
    private AuthenticationManager am;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Boolean tryLogin(@PathParam("password") String password) {
        return am.loginStaff(password) || am.loginTitolare(password);
    }
}
