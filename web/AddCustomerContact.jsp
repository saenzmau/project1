<%-- 
    Document   : AddCustomerContact
    Created on : 22/06/2018, 11:55:48 AM
    Author     : Mauricio
--%>

<%@page import="Objetos.CustomerContact"%>
<%@page import="java.util.List"%>
<%@page import="Objetos.SenderReceiver"%>
<%@page import="java.util.LinkedList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel ="stylesheet" href ="css/bootstrap.min.css">
        <title>Add Customer Contact</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        
        <script>
            $(function()
            {
                $("#header").load("header.jsp");
            });
        </script>
    </head>
    <body>
        <div id="header"></div>
        <%
            
         if(session.getAttribute("RelacionCC") != null)
         {
             List<SenderReceiver> rs = (LinkedList<SenderReceiver>)session.getAttribute("RelacionCC");
             
             out.print("<h1>Add new Customer Contact to Relationship </h1><br>"); 
             out.print("<h3>Trading Partner: "+rs.get(0).getSender() +"</h3>");
             out.print("<h3>Receiving Partner: "+rs.get(0).getReceiver() +"</h3><br>");
             
             out.print("<h5>Add new contact in database</h5>");
             out.print("<form method='post' action='AddNewCCPorServlet'>");
             out.print("<input hidden type='text' name='IdRelacion' value="+rs.get(0).getIdRelacion()+">");
             out.print("<label>Customer Contact Name: </label><input required type='text' name='NombreContacto'><br>");
             out.print("<label>Customer Contact Email: </label><input required type='email' name = 'Email'><br>");
             out.print("<input type = 'submit' name = 'Envio' value='Submit'>");
             out.print("</form>");
             
             out.print("<br>");
             out.print("<br>");
             
             if(request.getParameter("MsgContactSaved")!= null)
             {
                 boolean MsgContactSaved = Boolean.parseBoolean(request.getParameter("MsgContactSaved"));
                 String Contact = (String)request.getParameter("contactoguardadoNew");
                 String Email = (String)request.getParameter("emailcontactoNew");
                 
                 if(!MsgContactSaved)
                 {
                     out.print("<div class='alert alert-success'>");
                     out.print("<strong>¡Success!</strong> Contact '"+Contact+"' with email '"+Email+"' saved");
                     out.print("</div>");
                 }
                 else
                 {
                     out.print("<div class='alert alert-warning'>");
                     out.print("<strong>¡Warning!</strong> Name contact or Email already in database");
                     out.print("</div>");
                 }
             }
             
         }
         
         %>
         
         <% 
             
          if(session.getAttribute("ContactosCC") != null)
          {
              List<SenderReceiver> rs = (LinkedList<SenderReceiver>)session.getAttribute("RelacionCC");
              List<CustomerContact> bcs = (LinkedList<CustomerContact>)session.getAttribute("ContactosCC");
              
              out.print("<h5>Or choose existing contact</h5>");
              
              
              out.print("<table class='table table-bordered'>");
              
              out.print("<thead><tr>");
              out.print("<th>Customer Contact Name</th>");
              out.print("<th>Contact Email</th>");
              out.print("</tr></thead>");
             
              out.print("<tbody>");
              for(CustomerContact bc: bcs)
              {
                  out.print("<tr>");
                  out.print("<td>"+bc.getNombreContacto()+"</td><td>"+bc.getEmailContacto()+"</td>");
                  out.print("<td>");
                  out.print("<form method='post' action='AddCCPorServlet'>");
                  out.print("<input hidden type='text' name='idContacto' value="+bc.getIdCustomerContact()+">");
                  out.print("<input hidden type='text' name='idRelacion' value="+rs.get(0).getIdRelacion()+">");
                  out.print("<input type='submit' name='Envio' value='Add this contact'>");
                  out.print("</form></td>");
                  
                  
                  
                  
                  out.print("</tr>");
              }
              out.print("</tbody>");
              
              out.print("</table>");
          }
          
         %>
    </body>
</html>
