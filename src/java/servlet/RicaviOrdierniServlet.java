/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.managers.AuthenticationManager;
import model.managers.TitolareManager;

/**
 *
 * @author Alice Vidoni
 */
@WebServlet(name = "RicaviOdierniServlet", urlPatterns = {"/ricaviodierni"})
public class RicaviOrdierniServlet extends HttpServlet {
    
    @Inject
    TitolareManager tm;

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
        Integer authAs = (Integer) request.getSession()
                .getAttribute("authAs");
        
        Date date=(Date) new java.util.Date();
        
        if(authAs == null) {
            request.setAttribute("target", "ricaviodierni");
            request.getRequestDispatcher("login")
                    .forward(request, response);
        }
        else if(authAs == AuthenticationManager.STAFF) {
            request.getSession().setAttribute("authAs", null);
            request.setAttribute("errMSG", "Devi loggarti come Titolare per accedere all'area riservata!");
            request.setAttribute("target", "ricaviodierni");
            request.getRequestDispatcher("login")
                    .forward(request, response);
        }
        else if(authAs == AuthenticationManager.TITOLARE) {
            List <TitolareManager.PiattoXQuantita> ricavi=tm.guadagnoGiornalieroPiatti(date);
            int guadagno_totale=tm.guadagnoGiornaliero(date);
            request.setAttribute("ricavi", ricavi);
            request.setAttribute("totale", guadagno_totale);
            request.getRequestDispatcher("ricaviGiornalieri.jsp")
                    .forward(request, response);
        
        }
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
