<%-- 
    Document   : UpdateCustomerContactSender
    Created on : 22/06/2018, 08:40:10 AM
    Author     : Mauricio
--%>

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
        <h1>Update Customer Contact</h1>
        
        <%
            if(request.getParameter("Update") != null || session.getAttribute("UpdateButton") != null)
            {
                String button = "lol";
                session.setAttribute("UpdateButton", button);
                
                if(session.getAttribute("idCCUpdate")== null)
                session.setAttribute("idCCUpdate",request.getParameter("idCustomerContact"));
                
                if(session.getAttribute("NameCCUpdate")== null)
                session.setAttribute("NameCCUpdate",request.getParameter("CustomerName"));
                
                if(session.getAttribute("EmailCCUpdate")== null)
                session.setAttribute("EmailCCUpdate",request.getParameter("CustomerEmail"));
                
                out.print("<form action='ProcessUpdateCCBySender'>");
                out.print("<input hidden type='text' name='idContacto' value='"+session.getAttribute("idCCUpdate")+"'>");
                out.print("<label>Contact Name:</label><input required type='text' name='ContactName' value='"+session.getAttribute("NameCCUpdate")+"'><br>");
                out.print("<label>Contact Email:</label><input required type='text' name='ContactEmail' value='"+session.getAttribute("EmailCCUpdate")+"'><br>");
                out.print("<input type='submit' name='Envio' value='Update'>");
                out.print("</form>");
               
            }
            
            if(request.getParameter("msgUpdate")!= null)
            {
                 boolean MsgContactSaved = Boolean.parseBoolean(request.getParameter("MsgContactUpdate"));
                 String Contact = (String)request.getParameter("contactoUpdateNew");
                 String Email = (String)request.getParameter("emailUpdateNew");
                 
                 if(!MsgContactSaved)
                 {
                     out.print("<div class='alert alert-success'>");
                     out.print("<strong>¡Success!</strong> Contact '"+Contact+"' with email '"+Email+"' updated");
                     out.print("</div>");
                 }
                 else
                 {
                     out.print("<div class='alert alert-warning'>");
                     out.print("<strong>¡Warning!</strong> Name contact or Email already in database");
                     out.print("</div>");
                 }
            }
        %>
        
    </body>
</html>
