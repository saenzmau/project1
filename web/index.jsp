<%-- 
    Document   : login
    Created on : 7/06/2018, 11:17:20 AM
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
        <style>
            div.relative {
                position: relative;
                left: 30px;
                border: 3px solid #73AD21;
            }
        </style>
    </head>
    <body>
        
        <div id="imagen"  style="position:relative;left:30px;margin-top:10px">
                <img src="images/Colgate_palmolive_logo.png" width="400" height="50">
         </div>
        <br>
        <%--
                if(session.getAttribute("NombreUsuario")!= null)
                {
                    out.print("<h3>La sesion SIGUE EN PIE</h3>");
                }
                else
                {
                    out.print("<h3>La sesion ESTÁ ACABADA</h3>");
                }
        --%>
        <div class="container">
        <h3>Login</h3>
        <form action ='LoginController' method="Post">
            <div class="form-group">
                <label for="idUser">Email: </label>
                <input id="idUser" class="form-control" name="User" type="email" placeholder="Enter email" required>
            </div>
            <div class="form-group">
                <label for="Password">Password: </label>
                <input id="idPassword" class="form-control" name="Password" type="password" placeholder="Password" required>
                
            </div>
            <button type="submit" name="Envio" class="btn btn-primary">Submit</button>
            
            <%
                if(request.getParameter("msgIncorrectUser") != null)
                {
                    out.print("<div class='alert alert-danger'>");
                    out.print("<strong>Warning!</strong> Email or Password Incorrect.");
                    out.print("</div>");
                }
            %>  
        </form>
        <br>
        <a href="register.jsp">¿New User? Click here!</a>
        </div>
        
    </body>
</html>
