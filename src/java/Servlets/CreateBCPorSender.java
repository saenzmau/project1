/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Objetos.BusinessContact;
import Objetos.Consultas;
import Objetos.RelacionBusiness;
import Objetos.SenderReceiver;
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
@WebServlet(name = "CreateBCPorSender", urlPatterns = {"/CreateBCPorSender"})
public class CreateBCPorSender extends HttpServlet {

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
        
        if(request.getParameter("EnviarRelacion") != null)
        {
            
             LinkedList<SenderReceiver> RelacionSR = con.consultarSenderReceiver(""
                     + "SELECT A.idRelationSR, A.idSender, B.SenderName, A.idReceiver, C.ReceiverName \n" +
                       "FROM senderreceiver A, sender B, receiver C\n" +
                       "WHERE A.idSender=B.idSender AND A.idReceiver=C.idReceiver AND A.idRelationSR = "+request.getParameter("BusinessContact")+"");
             
             LinkedList<BusinessContact> ContactosUsados = con.consultarBusinessContacts(""
                     + "SELECT A.idBusinessContact, A.BusinessContact, A.BusinessEmail \n" +
                       "FROM businesscontact A, relationbusiness B\n" +
                       "WHERE A.idBusinessContact = B.idBusinessContact AND B.idRelationSR = "+request.getParameter("BusinessContact")+"");
             
             LinkedList<BusinessContact> ContactosTodos = con.consultarBusinessContacts(""
                     + "SELECT * FROM `businesscontact`");
             
             
             boolean ContactoIsThere = false;
             
             LinkedList<BusinessContact> ContactosDisponibles = new LinkedList<BusinessContact>();
             
             for(BusinessContact ct: ContactosTodos)
             {
                 ContactoIsThere = false;
                 
                 for(BusinessContact cu: ContactosUsados)
                 {
                     if(cu.getIdBusinessContact().equalsIgnoreCase(ct.getIdBusinessContact()))
                     {
                         ContactoIsThere = true;
                     }
                 }
                 
                 if(!ContactoIsThere)
                 {
                     ContactosDisponibles.add(ct);
                 }
             }
             
              sesion.setAttribute("Contactos", ContactosDisponibles);
              sesion.setAttribute("Relacion", RelacionSR);
              
              response.sendRedirect("AddBusinessContact.jsp");
              
             
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
            Logger.getLogger(CreateBCPorSender.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(CreateBCPorSender.class.getName()).log(Level.SEVERE, null, ex);
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
