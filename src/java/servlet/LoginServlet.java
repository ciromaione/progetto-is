/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.managers.AuthenticationManager;

/**
 *
 * @author ciro
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/performlogin"})
public class LoginServlet extends HttpServlet {
    
    @Inject
    AuthenticationManager am;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String atrequest = request.getParameter("auth-type-request");
        String target = request.getParameter("target");
        String pass = request.getParameter("password");
        
        boolean authFlag = false;
        
        switch(Integer.parseInt(atrequest)) {
            case AuthenticationManager.TITOLARE:
                if(am.loginTitolare(pass)) {
                    request.getSession()
                            .setAttribute("authAs", AuthenticationManager.TITOLARE);
                    authFlag = true;
                }
                break;
            case AuthenticationManager.STAFF:
                if(am.loginStaff(pass)) {
                    request.getSession()
                            .setAttribute("authAs", AuthenticationManager.STAFF);
                    authFlag = true;
                }
                break;
        }
        
        if(!authFlag) {
            request.setAttribute("errMSG", "Password errata!");
            request.setAttribute("target", target);
            request.getRequestDispatcher("login")
                    .forward(request, response);
        }
        if(target == null)
            response.sendRedirect("");
        else
            response.sendRedirect("areariservata");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
