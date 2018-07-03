/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Objetos.BusinessContact;
import Objetos.Consultas;
import static java.awt.SystemColor.window;
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
@WebServlet(name = "AddBCPorSender", urlPatterns = {"/AddBCPorSender"})
public class AddBCPorSender extends HttpServlet {

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
        if(request.getParameter("Envio") != null)
        {
            
            HttpSession sesion = request.getSession();
            
            con.Insertar("INSERT INTO relationbusiness (idRelationSR, idBusinessContact) VALUES \n"
                        +"("+request.getParameter("idRelacion")+","+request.getParameter("idContacto")+")");

            LinkedList<BusinessContact> ContactoAgregado = con.consultarBusinessContacts(""
                    + "SELECT * FROM businesscontact WHERE idBusinessContact = "+request.getParameter("idContacto")+"");
            
            LinkedList<BusinessContact> Contactos = (LinkedList<BusinessContact>)sesion.getAttribute("Contactos");
            
            
            int id_remove =-1;
            for(int i = 0; i < Contactos.size(); i++)
            {
                if(Contactos.get(i).getIdBusinessContact().equalsIgnoreCase(request.getParameter("idContacto")))
                {
                    id_remove = i;
                }
            }
            
            Contactos.remove(id_remove);
            
            
            sesion.setAttribute("Contactos",Contactos);
         
            
            //Hay que actualizar la lista de contactos en esta pagina
            //sesion.setAttribute("Contactos");
            response.sendRedirect("AddBusinessContact.jsp?msg=DatosGuardados"
                    + "&contactoguardado="+ContactoAgregado.get(0).getNombreContacto()
                    + "&emailcontacto="+ContactoAgregado.get(0).getEmailContacto());

           
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
            Logger.getLogger(AddBCPorSender.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AddBCPorSender.class.getName()).log(Level.SEVERE, null, ex);
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
