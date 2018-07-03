<%-- 
    Document   : BCManager.jsp
    Created on : 27/06/2018, 01:29:09 PM
    Author     : Mauricio
--%>

<%@page import="Objetos.BusinessContact"%>
<%@page import="java.util.LinkedList"%>
<%@page import="Objetos.Consultas"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel ="stylesheet" href ="css/bootstrap.min.css">
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
       
       <div class="container">
           <h2>Business Contact Manager</h2>
       </div>
       
       <br>
       <table class="table table-bordered">
           <thead>
                <tr>
                    <th>ID</th>
                    <th>Contact Name</th>
                    <th>Contact Email</th>
                </tr>
           </thead>
           <tbody>
               <%
                    Consultas con = new Consultas();
                    
                    LinkedList<BusinessContact> BCList = con.consultarBusinessContacts("SELECT * FROM businesscontact");
                    
                    
                    for(BusinessContact bc: BCList)
                    {
                        out.print("<tr>");
                        out.print("<td>"+bc.getIdBusinessContact()+"</td>");
                        out.print("<td>"+bc.getNombreContacto()+"</td>");
                        out.print("<td>"+bc.getEmailContacto()+"</td>");
                        out.print("<td><button class='btn btn-warning' type = 'Submit' Name ='Update'>Update <span class='glyphicon glyphicon-pencil'></span></button></td>");
                        out.print("<td><button class='btn btn-danger' type = 'Submit' Name ='Delete'> Delete <span class='glyphicon glyphicon-trash'></span></button></td>");
                        out.print("</tr>");
                    }

                    
               %>
           </tbody>
       </table>
    </body>
</html>
