/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.EJBException;
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
@WebServlet(name = "PopolaritaPortateServlet", urlPatterns = {"/popolaritaportate"})
public class PopolaritaPortateServlet extends HttpServlet {

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
            request.setAttribute("target", "popolaritaportate");
            request.setAttribute("authTypeReq", AuthenticationManager.TITOLARE);
            request.getRequestDispatcher("login")
                    .forward(request, response);
        }
        else if(authAs == AuthenticationManager.STAFF) {
            request.getSession().setAttribute("authAs", null);
            request.setAttribute("errMSG", "Devi loggarti come Titolare per accedere all'area riservata!");
            request.setAttribute("target", "popolaritaportate");
            request.setAttribute("authTypeReq", AuthenticationManager.TITOLARE);
            request.getRequestDispatcher("login")
                    .forward(request, response);
        }
        else if(authAs == AuthenticationManager.TITOLARE) {
            
            String meseString = request.getParameter("mese");
            String annoString = request.getParameter("anno");
            
            int mese, anno;
            
            if(meseString == null || annoString == null) {
                GregorianCalendar date=new GregorianCalendar();
                mese=date.get(Calendar.MONTH)+1;
                anno=date.get(Calendar.YEAR);
            }
            else {
                mese = Integer.parseInt(meseString);
                anno = Integer.parseInt(annoString);
            }
            System.out.println(mese+" "+anno);
            List <TitolareManager.PiattoXQuantita> piatti = null;
            try {
                piatti=tm.popolaritaPiattiMensile(mese, anno);
            } catch (EJBException e) {
                request.setAttribute("msg", "input non valido");
                request.getRequestDispatcher("error.jsp")
                        .forward(request, response);
            }
            request.setAttribute("piatti", piatti);
            request.getRequestDispatcher("popolaritaPiatti.jsp")
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
