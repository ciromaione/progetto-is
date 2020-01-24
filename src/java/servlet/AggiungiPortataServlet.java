/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import com.google.gson.Gson;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.entities.Piatto;
import model.managers.AuthenticationManager;
import model.managers.FotoIdManager;
import model.managers.TitolareManager;

/**
 *
 * @author ciro
 */
@WebServlet(name = "AggiungiPortataServlet", urlPatterns = {"/aggiungi"})
@MultipartConfig
public class AggiungiPortataServlet extends HttpServlet {

    @Inject
    private TitolareManager tm;
    
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
            request.setAttribute("target", "aggiungiportate");
            request.setAttribute("authTypeReq", AuthenticationManager.TITOLARE);
            request.getRequestDispatcher("login")
                    .forward(request, response);
        }
        else if(authAs == AuthenticationManager.STAFF) {
            request.getSession().setAttribute("authAs", null);
            request.setAttribute("errMSG", "Devi loggarti come Titolare per accedere all'area riservata!");
            request.setAttribute("target", "aggiungiportate");
            request.setAttribute("authTypeReq", AuthenticationManager.TITOLARE);
            request.getRequestDispatcher("login")
                    .forward(request, response);
        }
        else if(authAs == AuthenticationManager.TITOLARE) {
            String nome = request.getParameter("nome");
            String categoria = request.getParameter("categoria");
            String prezzoString = request.getParameter("prezzo");
            String ingJSON = request.getParameter("ing-fissi");
            String ingAggJSON = request.getParameter("ing-agg");
            String ingRimJSON = request.getParameter("ing-rim");
            
            
            Part filePart = request.getPart("immagine");
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            
            int index = fileName.lastIndexOf('.');
            String ext;
            if (index == -1) {
                ext = ".png";
            } else {
                ext = "."+fileName.substring(index + 1);
            }
                                
            InputStream initialStream = filePart.getInputStream();
            
            fileName = "/img_"+FotoIdManager.getInstance().getNewIndex()+ext;
            String newFilePath = getServletContext().getRealPath("img")+fileName;
            
            File targetFile = new File(newFilePath);
            
            java.nio.file.Files.copy(
                    initialStream,
                    targetFile.toPath(),
                    StandardCopyOption.REPLACE_EXISTING);
            initialStream.close();
            
            
            prezzoString = prezzoString.replace(',', '.');
            int prezzoCent = (int)(Float.parseFloat(prezzoString)*100);
            
            Gson gson = new Gson();
            int[] ing = gson.fromJson(ingJSON, int[].class);
            int[] ingAgg = gson.fromJson(ingAggJSON, int[].class);
            int[] ingRim = gson.fromJson(ingRimJSON, int[].class);
            
            Piatto piatto = new Piatto();
            piatto.setNome(nome);
            piatto.setCategoria(categoria);
            piatto.setPrezzoCent(prezzoCent);
            piatto.setFoto(fileName);
            
            tm.aggiungiPiatto(piatto, ing, ingAgg, ingRim);
            
            request.setAttribute("conferma", true);
            request.getRequestDispatcher("aggiungiportate")
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
