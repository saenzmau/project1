package Objetos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;


public class Consultas extends Conexion
{
     public void Insertar(String insSQL) throws SQLException
     {
        
        PreparedStatement ps = con.prepareStatement(insSQL);
        ps.executeUpdate();
     }
     
    public LinkedList<Sender> consultarSenders(String consSQL) throws SQLException
    {
        LinkedList<Sender> lSenders = new LinkedList<Sender>();
        
        PreparedStatement ps = con.prepareStatement(consSQL);
        ResultSet rs = ps.executeQuery();
        
        while(rs.next())
        {
            Sender prd = new Sender();
            prd.setIdSender(rs.getString("idSender"));
            prd.setSenderName(rs.getString("SenderName"));
            lSenders.add(prd);
        }
        
        ps.close();
        rs.close();
        
        return lSenders;
    }
    
    
     public LinkedList<Receiver> consultarReceivers(String consSQL) throws SQLException
     {
        LinkedList<Receiver> lReceivers = new LinkedList<Receiver>();
        
        PreparedStatement ps = con.prepareStatement(consSQL);
        ResultSet rs = ps.executeQuery();
        
        while(rs.next())
        {
            Receiver prd = new Receiver();
            prd.setIdReceiver(rs.getString("idReceiver"));
            prd.setReceiverName(rs.getString("ReceiverName"));
            lReceivers.add(prd);
        }
        
        ps.close();
        rs.close();
        
        return lReceivers;
    }
     
     
    public LinkedList<Documento> consultarDocumentos(String consSQL) throws SQLException
     {
        LinkedList<Documento> lFacturas = new LinkedList<Documento>();
        
        PreparedStatement ps = con.prepareStatement(consSQL);
        ResultSet rs = ps.executeQuery();
        
        while(rs.next())
        {
            Documento prd = new Documento();
            prd.setIdDocumento(rs.getString("idDocument"));
            prd.setDocumento(rs.getString("DocumentName"));
            prd.setIdDireccion(rs.getString("idDirection"));
            prd.setDireccion(rs.getString("DirectionName"));
            prd.setIdTipo(rs.getString("idType"));
            prd.setTipo(rs.getString("TypeName"));
            lFacturas.add(prd);
        }
        
        ps.close();
        rs.close();
        
        return lFacturas;
    }
    
    public LinkedList<Direccion> consultarDireccion(String consSQL) throws SQLException
     {
        LinkedList<Direccion> lDireccion = new LinkedList<Direccion>();
        
        PreparedStatement ps = con.prepareStatement(consSQL);
        ResultSet rs = ps.executeQuery();
        
        while(rs.next())
        {
            Direccion prd = new Direccion();
            prd.setIdDireccion(rs.getString("idDirection"));
            prd.setDireccion(rs.getString("DirectionName"));
            lDireccion.add(prd);
        }
        
        ps.close();
        rs.close();
        
        return lDireccion;
    }
    
    
     public LinkedList<SenderReceiver> consultarSenderReceiver(String consSQL) throws SQLException
     {
        LinkedList<SenderReceiver> lSR = new LinkedList<SenderReceiver>();
        
        PreparedStatement ps = con.prepareStatement(consSQL);
        ResultSet rs = ps.executeQuery();
        
        while(rs.next())
        {
            SenderReceiver prd = new SenderReceiver();
            prd.setIdRelacion(rs.getString("idRelationSR"));
            prd.setIdSender(rs.getString("idSender"));
            prd.setSender(rs.getString("SenderName"));
            prd.setIdReceiver(rs.getString("idReceiver"));
            prd.setReceiver(rs.getString("ReceiverName"));
            lSR.add(prd);
        }
        
        ps.close();
        rs.close();
        
        return lSR;
    }
     
     
      public LinkedList<ReporteDocumento> consultarReportesDocumentos(String consSQL) throws SQLException
     {
        LinkedList<ReporteDocumento> lSR = new LinkedList<ReporteDocumento>();
        
        PreparedStatement ps = con.prepareStatement(consSQL);
        ResultSet rs = ps.executeQuery();
        
        while(rs.next())
        {
            ReporteDocumento prd = new ReporteDocumento();
            prd.setIdReporte(rs.getString("idReport"));
            prd.setIdRelacion(rs.getString("idRelationSR"));
            prd.setIdSender(rs.getString("idSender"));
            prd.setSender(rs.getString("SenderName"));
            prd.setIdReceiver(rs.getString("idReceiver"));
            prd.setReceiver(rs.getString("ReceiverName"));
            prd.setIdDocumento(rs.getString("idDocument"));
            prd.setDocumento(rs.getString("DocumentName"));
            prd.setIdDireccion(rs.getString("idDirection"));
            prd.setDireccion(rs.getString("DirectionName"));
            prd.setIdTipoDocumento(rs.getString("idType"));
            prd.setTipoDocumento(rs.getString("TypeName"));
         
            lSR.add(prd);
        }
        
        ps.close();
        rs.close();
        
        return lSR;
    }
      
      
      public LinkedList<RelacionBusiness> consultarRelacionBusiness(String consSQL) throws SQLException
     {
        LinkedList<RelacionBusiness> lSR = new LinkedList<RelacionBusiness>();
        
        PreparedStatement ps = con.prepareStatement(consSQL);
        ResultSet rs = ps.executeQuery();
        
        while(rs.next())
        {
            RelacionBusiness prd = new RelacionBusiness();
            prd.setIdRelacionRB(rs.getString("idRelationRB"));
            prd.setIdRelacionSR(rs.getString("idRelationSR"));
            prd.setIdBusinessContact(rs.getString("idBusinessContact"));
            prd.setNombreContacto(rs.getString("BusinessContact"));
            prd.setEmailContacto(rs.getString("BusinessEmail"));
            lSR.add(prd);
        }
        
        ps.close();
        rs.close();
        
        return lSR;
    }
      
      public LinkedList<RelacionCustomer> consultarRelacionCustomer(String consSQL) throws SQLException
     {
        LinkedList<RelacionCustomer> lSR = new LinkedList<RelacionCustomer>();
        
        PreparedStatement ps = con.prepareStatement(consSQL);
        ResultSet rs = ps.executeQuery();
        
        while(rs.next())
        {
            RelacionCustomer prd = new RelacionCustomer();
            prd.setIdRelacionRC(rs.getString("idRelationRC"));
            prd.setIdRelacionSR(rs.getString("idRelationSR"));
            prd.setIdCustomerContact(rs.getString("idCustomerContact"));
            prd.setNombreContacto(rs.getString("CustomerContact"));
            prd.setEmailContacto(rs.getString("CustomerEmail"));
            lSR.add(prd);
        }
        
        ps.close();
        rs.close();
        
        return lSR;
    }
      
     public LinkedList<BusinessContact> consultarBusinessContacts(String consSQL) throws SQLException
     {
        LinkedList<BusinessContact> lSR = new LinkedList<BusinessContact>();
        
        PreparedStatement ps = con.prepareStatement(consSQL);
        ResultSet rs = ps.executeQuery();
        
        while(rs.next())
        {
            BusinessContact prd = new BusinessContact();
            prd.setIdBusinessContact(rs.getString("idBusinessContact"));
            prd.setNombreContacto(rs.getString("BusinessContact"));
            prd.setEmailContacto(rs.getString("BusinessEmail"));
            lSR.add(prd);
        }
        
        ps.close();
        rs.close();
        
        return lSR;
    }
     
     
    public LinkedList<CustomerContact> consultarCustomerContacts(String consSQL) throws SQLException
     {
        LinkedList<CustomerContact> lSR = new LinkedList<CustomerContact>();
        
        PreparedStatement ps = con.prepareStatement(consSQL);
        ResultSet rs = ps.executeQuery();
        
        while(rs.next())
        {
            CustomerContact prd = new CustomerContact();
            prd.setIdCustomerContact(rs.getString("idCustomerContact"));
            prd.setNombreContacto(rs.getString("CustomerContact"));
            prd.setEmailContacto(rs.getString("CustomerEmail"));
            lSR.add(prd);
        }
        
        ps.close();
        rs.close();
        
        return lSR;
    }
    
    public LinkedList<User> consultarUsuario(String consSQL) throws SQLException
    {
        LinkedList<User> lUsers = new LinkedList<User>();
        
        PreparedStatement ps = con.prepareStatement(consSQL);
        ResultSet rs = ps.executeQuery();
        
        while(rs.next())
        {
            User prd = new User();
            prd.setIdUser(rs.getString("idUser"));
            prd.setName(rs.getString("Name"));
            prd.setSurnames(rs.getString("Surnames"));
            prd.setEmail(rs.getString("Email"));
            prd.setPassword(rs.getString("Password"));
            lUsers.add(prd);
        }
        
        ps.close();
        rs.close();
        
        return lUsers;
    }
   
}



