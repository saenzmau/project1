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

/**
 *
 * @author Mauricio
 */
@WebServlet(name = "AddNewBCPorSender", urlPatterns = {"/AddNewBCPorSender"})
public class AddNewBCPorSender extends HttpServlet {

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
        
        if(request.getParameter("Envio")!=null)
        {
            
            boolean isContactThere = false; 
            
            LinkedList<BusinessContact> ContactosBC = con.consultarBusinessContacts("SELECT * FROM businesscontact");
            
            for(BusinessContact bc : ContactosBC)
            {
                if(bc.getEmailContacto().equalsIgnoreCase(request.getParameter("Email"))
                   || bc.getNombreContacto().equalsIgnoreCase(request.getParameter("NombreContacto")))
                {
                    isContactThere = true;
                }
            }
            
            if(!isContactThere)
            {
                
                
                con.Insertar("INSERT INTO businesscontact (BusinessContact, BusinessEmail) "
                        + "VALUES ('"+request.getParameter("NombreContacto")+"','"+request.getParameter("Email")+"')");
                LinkedList<BusinessContact> contactoañadido = con.consultarBusinessContacts(""
                        + "SELECT * FROM businesscontact WHERE BusinessEmail = '"+request.getParameter("Email")+"'");
                con.Insertar("INSERT INTO relationbusiness (idRelationSR, idBusinessContact) "
                        + "   VALUES ( "+request.getParameter("IdRelacion")+" , "+contactoañadido.get(0).getIdBusinessContact()+" )");
 
            }
            
            
            
            response.sendRedirect("AddBusinessContact.jsp?msgNew=NewContactSaved"
                    + "&contactoguardadoNew="+request.getParameter("NombreContacto")
                    + "&emailcontactoNew="+request.getParameter("Email")
                    + "&MsgContactSaved="+isContactThere);
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
            Logger.getLogger(AddNewBCPorSender.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AddNewBCPorSender.class.getName()).log(Level.SEVERE, null, ex);
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
