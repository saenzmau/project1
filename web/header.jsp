<%-- 
    Document   : header
    Created on : 25/06/2018, 10:52:18 AM
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
    </head>
    <body>
        
            
            <div id="imagen"  style="position:relative;left:30px;margin-top:10px">
                <img src="images/Colgate_palmolive_logo.png" width="400" height="50">
            </div>
            
            <div id = "menu">
                <nav class="navbar navbar-default">
                    <div class="container-fluid">
                      <!--<div class="navbar-header">
                        <a class="navbar-brand" href="#">WebSiteName</a>
                      </div>-->
                      <ul class="nav navbar-nav">
                          <li class="active"><a href="menu.jsp">Home <span class="glyphicon glyphicon-home"></span></a></li>
                        <li><a href="BusquedaPorSender.jsp">Search by Senders</a></li>
                        <li><a href="BusquedaPorReceiver.jsp">Search by Receivers</a></li>
                        <li><a href="BCManager.jsp">Business Contacts</a></li>
                        <li><a href="CCManger.jsp">Customer Contacts</a></li>
                        <%
                            if(request.getSession()!=null)
                            {
                                out.print("<li><a href='LogoutController'>Logout</a></li>");
                            }
                        %>
                      </ul>
                    </div>
                </nav>
            </div>
            
       
    </body>
</html>
