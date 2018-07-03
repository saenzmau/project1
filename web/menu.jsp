<%-- 
    Document   : menu.jsp
    Created on : 7/06/2018, 11:15:28 AM
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
        
        
        <div class="container">
            <%
                if(request.getSession()!= null)
                {
                    if(session.getAttribute("NombreUsuario")!= null)
                    {
                        out.print("<h3>¡ Welcome "+session.getAttribute("NombreUsuario")+" !</h3>");
                    }
                    else
                    {
                        response.sendRedirect("index.jsp");
                    }
                }
                else
                {
                    response.sendRedirect("index.jsp");
                }

            %>

            <br>

            <form action='BusquedaPorSender.jsp'>
                <input type = 'submit' name='envio' value='Search Contacts by Sender'>
            </form>

            <br>
            <br>

            <form action='BusquedaPorReceiver.jsp' method = 'post'>
            <input type = 'submit' name='envio' value='Search Contacts by Receivers'>
            </form>

            <br>
            <br>

            <form action='BCManager.jsp' method = 'post'>
            <input type = 'submit' name='envio' value='Business Contacts Manager'>
            </form>

            <br>
            <br>

            <form action='CCManger.jsp' method = 'post'>
            <input type = 'submit' name='envio' value='Customer Contacts Manager'>
            </form>

            <br>
            <br>
        </div>
        
        
    </body>
</html>
