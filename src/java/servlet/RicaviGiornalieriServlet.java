/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.managers.AuthenticationManager;
import model.managers.TitolareManager;
import model.managers.TitolareManager.PiattoXQuantita;

/**
 *
 * @author ciro
 */
@WebServlet(name = "RicaviGiornalieriServlet", urlPatterns = {"/ricavigiornalieri"})
public class RicaviGiornalieriServlet extends HttpServlet {
    
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
        
        if(authAs == null) {
            request.setAttribute("target", "ricavigiornalieri");
            request.setAttribute("authTypeReq", AuthenticationManager.TITOLARE);
            request.getRequestDispatcher("login")
                    .forward(request, response);
        }
        else if(authAs == AuthenticationManager.STAFF) {
            request.getSession().setAttribute("authAs", null);
            request.setAttribute("errMSG", "Devi loggarti come Titolare per accedere all'area riservata!");
            request.setAttribute("target", "ricavigiornalieri");
            request.setAttribute("authTypeReq", AuthenticationManager.TITOLARE);
            request.getRequestDispatcher("login")
                    .forward(request, response);
        }
        else if(authAs == AuthenticationManager.TITOLARE) {
            
            String data = request.getParameter("date");
            System.out.println(data);
            Date date1 = null;
            if(data == null)
                date1 = new Date(new java.util.Date().getTime());
            else {
                try {  
                     java.util.Date d = new SimpleDateFormat("yyyy-MM-dd").parse (data);
                     date1 = new Date(d.getTime());
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
            }
            
            List <PiattoXQuantita> ricavi = tm.guadagnoGiornalieroPiatti(date1);
            Integer guadagno_totale = tm.guadagnoGiornaliero(date1);
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
