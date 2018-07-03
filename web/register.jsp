<%-- 
    Document   : register
    Created on : 26/06/2018, 11:42:08 AM
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
        <script language='javascript' type='text/javascript'>
            function check(input){
                if(input.value != document.getElementById('idPassword').value)
                {
                    input.setCustomValidity('Password Must Be Matching.');
                }
                else
                {
                    input.setCustomValidity('');
                }
            }
        </script>
    </head>
    <body>
        <div id="imagen"  style="position:relative;left:30px;margin-top:10px">
                <img src="images/Colgate_palmolive_logo.png" width="400" height="50">
         </div>
        <br>
        
        
        <div class="container">
        <h3>User Registration</h3>
        <form action ='RegistrationController' method="Post">
            <div class="form-group">
                <label for="idName">Name: </label>
                <input id="idName" class="form-control" name="UserName" type="text" placeholder="Enter your Name" required>
            </div>
            <div class="form-group">
                <label for="idSurname">Surname: </label>
                <input id="idUser" class="form-control" name="UserSurname" type="text" placeholder="Enter your Surname" required>
            </div>
            <div class="form-group">
                <label for="idEmail">Email: </label>
                <input id="idEmail" class="form-control" name="UserEmail" type="email" placeholder="Enter email" required>
            </div>
            
            <%
                
                if(request.getParameter("msgContactThere") != null)
                {
                        out.print("<div class='alert alert-danger'>");
                        out.print("<strong>Warning!</strong> User already registered with email '"+request.getParameter("msgContactThere")+"'.");
                        out.print("</div>");
                }
             %>
            
            <div class="form-group">
                <label for="idPassword">Password: </label>
                <input id="idPassword" class="form-control" name="UserPassword" type="password" placeholder="Enter Password" required>
            </div>
            <div class="form-group">
                <label for="PasswordC">Confirm Password: </label>
                <input id="idPasswordC" class="form-control" name="UserPasswordConfirm" type="password" placeholder="Enter Password Again" oninput="check(this)" required>
                
            </div>
            <button type="submit" name="Envio" class="btn btn-primary">Submit</button>
        </form>
        </div>
    </body>
</html>
