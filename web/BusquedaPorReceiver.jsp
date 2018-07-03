<%-- 
    Document   : BusquedaPorReceiver
    Created on : 25/06/2018, 10:40:20 AM
    Author     : Mauricio
--%>

<%@page import="Objetos.ReporteDocumento"%>
<%@page import="Objetos.RelacionCustomer"%>
<%@page import="Objetos.RelacionBusiness"%>
<%@page import="java.util.List"%>
<%@page import="Objetos.SenderReceiver"%>
<%@page import="java.util.LinkedList"%>
<%@page import="Objetos.Consultas"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
        
        <h1>Search for Receivers by Trading Partner</h1>
        
        <form action = "ShowReceiverPartners">
            <label>Receiving Partner: </label>  <input name = "Receiver" type="text">
            <br>     
            <button type="submit" name="Envio" class="btn btn-primary">
                Search <span class="glyphicon glyphicon-search"></span>
            </button>
        </form>
        <br>
      
        <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>Receiver</th>
                        <th>Trading Partner</th>
                        <th>Business Contact</th>
                        <th>Customer Contact</th>
                        <th>Documents Reported</th>
                    </tr>
                </thead>
                <tbody>
         <%
            if(session.getAttribute("lSenderReceiversREC")!=null)
            {   
                
                Consultas con = new Consultas();
                List<SenderReceiver> lSenderReceivers =(LinkedList<SenderReceiver>)session.getAttribute("lSenderReceiversREC");
                
                for(SenderReceiver sr: lSenderReceivers)
                {
                    
                    LinkedList<RelacionBusiness> lRelacionB = con.consultarRelacionBusiness(""
                            + "SELECT A.idRelationRB, A.idRelationSR, A.idBusinessContact, B.BusinessContact, B.BusinessEmail \n" +
                              "FROM relationbusiness A, businesscontact B \n" +
                              "WHERE A.idBusinessContact = B.idBusinessContact AND A.idRelationSR = "+sr.getIdRelacion()+"");
                    
                    LinkedList<RelacionCustomer> lRelacionC = con.consultarRelacionCustomer(""
                            + "SELECT A.idRelationRC, A.idRelationSR, A.idCustomerContact, B.CustomerContact, B.CustomerEmail \n" +
                              "FROM relationcustomer A, customercontact B \n" +
                              "WHERE A.idCustomerContact = B.idCustomerContact AND A.idRelationSR = "+sr.getIdRelacion()+"");
                     
                    
                         out.print("<tr>");
                         out.print("<td>"+sr.getReceiver()+"</td>");
                         out.print("<td>"+sr.getSender()+"</td>");
                         
                         
                        if(lRelacionB.size()== 0)
                        {
                            out.print("<td><table>");
                            out.print("<tr><td>N/A</td></tr>");
                            out.print("<tr><td><form method='post' action = 'CreateBCPorSender'>");
                           
                            out.print("<input hidden type='text' name ='BusinessContact' value='"+sr.getIdRelacion()+"'>");
                            out.print("<button class='btn btn-success' type = 'Submit' name ='EnviarRelacion'> Add contact <span class='glyphicon glyphicon-plus'></span></button></form></td></tr>");
                            out.print("</table></td>");
                        }
                        else
                        {
                             out.print("<td><table>");
                             for(RelacionBusiness rb : lRelacionB)
                             {
                                 out.print("<tr><td>"+rb.getNombreContacto()+" || "+rb.getEmailContacto() + " </td> ");
                                 out.print("<td>");
                                 out.print("<form method='post' action = 'UpdateBusinessContactSender.jsp'>");
                                 out.print("<input hidden type='text' name ='idBusinessContact' value='"+rb.getIdBusinessContact()+"'>");
                                 out.print("<input hidden type='text' name ='BusinessName' value='"+rb.getNombreContacto()+"'>");
                                 out.print("<input hidden type='text' name ='BusinessEmail' value='"+rb.getEmailContacto()+"'>");
                                 out.print("<button class='btn btn-warning' type = 'Submit' Name ='Update'> Update <span class='glyphicon glyphicon-pencil'></span></button></form> </td> ");
                                 out.print("<td>");
                                 out.print("<form method='post' action = 'DeleteBCPorSender'>");
                                 out.print("<input hidden type='text' name ='BusinessContact' value='"+rb.getIdRelacionRB()+"'>");
                                 out.print("<button class='btn btn-danger' type = 'Submit' Name ='Delete'> Delete <span class='glyphicon glyphicon-trash'></span></button></form> </td> ");
                                 
                                 out.print("</tr>");
                                 
                             }
                             
                             out.print("<tr><td>");
                             
                             out.print("<form method='post' action = 'CreateBCPorSender'>");
                             out.print("<input hidden type='text' name ='BusinessContact' value='"+sr.getIdRelacion()+"'>");
                             out.print("<button class='btn btn-success' type = 'Submit' Name ='EnviarRelacion'> Add contact <span class='glyphicon glyphicon-plus'></span></button></form>");
                             out.print("</td></tr>");
                             out.print("</table></td>");
                        }
                        
                        
                        if(lRelacionC.size()== 0)
                        {
                            out.print("<td><table>");
                            out.print("<tr><td>N/A</td></tr>");
                            out.print("<tr><td><form method='post' action = 'CreateCCPorSender'>");
                            out.print("<input hidden type='text' name ='CustomerContact' value='"+sr.getIdRelacion()+"'>");
                            out.print("<button class='btn btn-success' type = 'Submit' Name ='EnviarRelacion'> Add contact <span class='glyphicon glyphicon-plus'></span></button></form></td></tr>");
                            out.print("</table></td>");
                        }
                        else
                        {
                             out.print("<td><table>");
                             for(RelacionCustomer rb : lRelacionC)
                             {
                                 out.print("<tr><td>"+rb.getNombreContacto()+" || "+rb.getEmailContacto() + " </td> ");
                                 out.print(" <td> ");
                                 out.print("<form method='post' action = 'UpdateCustomerContactSender.jsp'>");
                                 out.print("<input hidden type='text' name ='idCustomerContact' value='"+rb.getIdCustomerContact()+"'>");
                                 out.print("<input hidden type='text' name ='CustomerName' value='"+rb.getNombreContacto()+"'>");
                                 out.print("<input hidden type='text' name ='CustomerEmail' value='"+rb.getEmailContacto()+"'>");
                                 out.print("<button class='btn btn-warning' type = 'Submit' Name ='Update'> Update <span class='glyphicon glyphicon-pencil'></span></button></form> </td> ");
                                 out.print(" <td> ");
                                 out.print("<form method='post' action = 'DeleteCCPorSender'>");
                                 out.print("<input hidden type='text' name ='CustomerContact' value='"+rb.getIdRelacionRC()+"'>");
                                 out.print("<button class='btn btn-danger' type = 'submit' Name ='Delete'> Delete <span class='glyphicon glyphicon-trash'></span></button></form> </td> ");
                                 out.print("</tr>");    
                             } 
                             out.print("<tr><td>");
                             
                             out.print("<form method='post' action = 'CreateCCPorSender'>");
                             out.print("<input hidden type='text' name ='CustomerContact' value='"+sr.getIdRelacion()+"'>");
                             out.print("<button class='btn btn-success' type = 'Submit' Name ='EnviarRelacion'> Add contact <span class='glyphicon glyphicon-plus'></span></button></form>");
                             out.print("</td></tr>");
                             out.print("</table></td>");
                        }
                        
                        
                    LinkedList<ReporteDocumento> lRelacionDocumento = con.consultarReportesDocumentos(
                   "SELECT A.idReport, A.idRelationSR, B.idSender, C.SenderName, B.idReceiver, D.ReceiverName, \n"+
                   "E.idDocument, E.DocumentName, E.idDirection, F.DirectionName, E.idType, G.TypeName \n"+
                   "FROM documentreports A, senderreceiver B, sender C, receiver D,\n"+
                   "documents E, documentdirection F, documenttypes G \n"+
                   "WHERE A.idRelationSR = B.idRelationSR AND B.idSender = C.idSender AND B.idReceiver = D.idReceiver \n"+
                   "AND A.idDocument = E.idDocument AND E.idDirection = F.idDirection AND E.idType = G.idType \n"+
                   "AND A.idRelationSR = "+sr.getIdRelacion()+" ");
                     
                     
                    out.print("<td><table>");
                    for(ReporteDocumento rd: lRelacionDocumento)
                    {
                        out.print("<tr><td> "+rd.getDocumento()+" || "+rd.getTipoDocumento()+" || "+rd.getDireccion()+"</td></tr>" );
                    }
                    out.print("</td></table>");
                    out.print("</tr>");
                    
                    
                             
                             
                    
                }
            }

         %>
         
         </tbody>
        </table>
    </body>
</html>
