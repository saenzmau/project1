/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Objetos.BusinessContact;
import Objetos.Consultas;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Mauricio
 */
@WebServlet(name = "ProcessUpdateBySender", urlPatterns = {"/ProcessUpdateBySender"})
public class ProcessUpdateBySender extends HttpServlet {

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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        Consultas con = new Consultas();
        
        HttpSession sesion = request.getSession();
        if(request.getParameter("Envio") != null)
        {
            
            
            
            boolean isContactThere = false; 
            LinkedList<BusinessContact> ContactosBC = con.consultarBusinessContacts(""
                    + "SELECT * FROM businesscontact WHERE idBusinessContact !="+request.getParameter("idContacto")+"");
            for(BusinessContact bc : ContactosBC)
            {
                if(bc.getEmailContacto().equalsIgnoreCase(request.getParameter("ContactEmail"))
                   || bc.getNombreContacto().equalsIgnoreCase(request.getParameter("ContactName")))
                {
                    isContactThere = true;
                }
            }
            
            if(!isContactThere)
            {
                con.Insertar("UPDATE businesscontact SET BusinessContact = '"+request.getParameter("ContactName")+"', "
                        + "BusinessEmail='"+request.getParameter("ContactEmail")+"' WHERE idBusinessContact = "+request.getParameter("idContacto")+"");
                 sesion.setAttribute("NameBCUpdate",request.getParameter("ContactName"));
                 sesion.setAttribute("EmailBCUpdate",request.getParameter("ContactEmail"));
            }
            
            response.sendRedirect("UpdateBusinessContactSender.jsp?msgUpdate=UpdatedContact"
                    + "&contactoUpdateNew="+request.getParameter("ContactName")
                    + "&emailUpdateNew="+request.getParameter("ContactEmail")
                    + "&MsgContactUpdate="+isContactThere);
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ProcessUpdateBySender.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ProcessUpdateBySender.class.getName()).log(Level.SEVERE, null, ex);
        }
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
