/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Objetos.Consultas;
import Objetos.User;
import static Objetos.hash.sha1;
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
@WebServlet(name = "RegistrationController", urlPatterns = {"/RegistrationController"})
public class RegistrationController extends HttpServlet {

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
            
            LinkedList<User> Usuarios = con.consultarUsuario("SELECT * FROM users");
            
            boolean isUserThere = false;
            
            for(User usr: Usuarios)
            {
                if(usr.getEmail().equalsIgnoreCase(request.getParameter("UserEmail")))
                {
                    isUserThere = true;
                }
            }
            
            if(!isUserThere)
            {
                
                con.Insertar("INSERT INTO users (Name,Surnames,Email,Password) \n"
                           + "VALUES ('"+request.getParameter("UserName")+"' , '"
                                        +request.getParameter("UserSurname") +"' , '"
                                        +request.getParameter("UserEmail") +"' , '"
                                        +sha1(request.getParameter("UserPassword")) +"' "
                                     +")");
                 LinkedList<User> UsuarioIngresado = con.consultarUsuario(""
                    + "SELECT * FROM users WHERE Email='"+request.getParameter("UserEmail")+"' \n"
                            + "AND Password='"+sha1(request.getParameter("UserPassword"))+"'");
                
                HttpSession sesion = request.getSession(true);
                sesion.setAttribute("NombreUsuario", UsuarioIngresado.get(0).getName());
                response.sendRedirect("menu.jsp");
               
            }
            else
            {
                response.sendRedirect("register.jsp?msgContactThere="+request.getParameter("UserEmail"));
            }
            
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
            Logger.getLogger(RegistrationController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(RegistrationController.class.getName()).log(Level.SEVERE, null, ex);
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
