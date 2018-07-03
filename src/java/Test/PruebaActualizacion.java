/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Objetos.Consultas;
import Objetos.Direccion;
import Objetos.Documento;
import Objetos.Receiver;
import Objetos.ReporteDocumento;
import Objetos.Sender;
import Objetos.SenderReceiver;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author Mauricio
 */
public class PruebaActualizacion {
     
    public static void main(String[] args) throws FileNotFoundException, IOException, SQLException 
    {
        String csvFile = "C:\\Users\\Mauricio\\Documents\\NetBeansProjects\\BusinessContact\\test\\ReporteTPA.csv";
        Consultas  cons = new Consultas();
        
        
        BufferedReader br = null;
        BufferedWriter wr = null;
        String line = "";
        String cvsSplitBy = ",";
        
        br = new BufferedReader(new FileReader(csvFile));
        
        int cont = 0;
            
         try {

            
            
            while ((line = br.readLine()) != null) 
            {
            
                String[] trans = line.split(cvsSplitBy);
                
                if(cont != 0)
                {
                    if(trans.length == 4)
                    {
                        System.out.println("Sender: " + trans[0] + "  Receiver: " +trans[1] + "  Factura: " + trans[3] +"  Direccion: " +trans[2]);
                        LinkedList<Sender> lsenders = cons.consultarSenders("SELECT * FROM sender");
                        LinkedList<Receiver> lreceivers = cons.consultarReceivers("SELECT * FROM receiver");
                        LinkedList<Documento> ldocumentos = cons.consultarDocumentos(""
                                + "SELECT A.idDocument, A.DocumentName, A.idDirection, \n" +
                                  "B.DirectionName, A.idType, C.TypeName \n" +
                                  "FROM documents A, documentdirection B, documenttypes C \n" +
                                  "WHERE A.idDirection = B.idDirection AND A.idType = C.idType");
                        
                        LinkedList <SenderReceiver> lSenderReceiver = cons.consultarSenderReceiver(""
                                + "SELECT A.idRelationSR, A.idSender, B.SenderName, C.idReceiver, C.ReceiverName \n" +
                                  "FROM senderreceiver A, sender B, receiver C\n" +
                                  "WHERE A.idSender = B.idSender AND A.idReceiver = C.idReceiver");
                        
                        LinkedList <ReporteDocumento> lReportesDocumentos = cons.consultarReportesDocumentos(""
                                + "SELECT  A.idReport, A.idRelationSR, C.idSender, C.SenderName, D.idReceiver, D.ReceiverName, \n" +
                                  "A.idDocument, E.DocumentName, E.idDirection, F.DirectionName, E.idType, G.TypeName\n" +
                                  "FROM documentreports A, senderreceiver B, sender C, receiver D, documents E, documentdirection F, documenttypes G   \n" +
                                  "WHERE A.idRelationSR = B.idRelationSR AND B.idSender = C.idSender AND B.idReceiver = D.idReceiver \n" +
                                  "AND A.idDocument = E.idDocument AND E.idDirection = F.idDirection AND E.idType = G.idType");
                        
                        
                        boolean SenderThere = false;
                        boolean ReceiverThere = false;
                        boolean FacturaThere = false;
                        boolean SenderReceiverThere = false;
                        boolean ReporteDocumentoThere = false;
                        
                        for(Sender sen: lsenders)
                        {
                            if(sen.getSenderName().equalsIgnoreCase(trans[0]))
                                SenderThere = true;    
                        }
                        
                        
                        for(Receiver rec: lreceivers)
                        {
                            if(rec.getReceiverName().equalsIgnoreCase(trans[1]))
                                ReceiverThere = true;
                        }
                        
                        
                        for(Documento doc: ldocumentos)
                        {
                            if(doc.getDocumento().equalsIgnoreCase(trans[3]))
                                FacturaThere = true;
                        }
                        
                        
                        for(SenderReceiver sen: lSenderReceiver)
                        {
                            if(sen.getSender().equalsIgnoreCase(trans[0]) && sen.getReceiver().equalsIgnoreCase(trans[1]))
                                SenderReceiverThere = true;
                            
                        }
                        
                        for(ReporteDocumento ref: lReportesDocumentos)
                        {
                            if(ref.getSender().equalsIgnoreCase(trans[0]) && ref.getReceiver().equalsIgnoreCase(trans[1])
                                 && ref.getDocumento().equalsIgnoreCase(trans[3]))
                                    ReporteDocumentoThere = true;
                                
         
                        }
                        
                        
                        if(!SenderThere)
                            cons.Insertar("INSERT INTO sender (SenderName) Values ('"+trans[0]+"') ");
                        
                        if(!ReceiverThere)
                            cons.Insertar("INSERT INTO receiver (ReceiverName) Values ('"+trans[1]+"') ");
                        
                        if(!FacturaThere)
                        {
                            LinkedList<Direccion> ldireccion = cons.consultarDireccion(""
                                    + "SELECT * FROM documentdirection WHERE DirectionName = '"+trans[2]+"'");                          
                            cons.Insertar("INSERT INTO documents (DocumentName, idDirection) Values ('"+trans[3]+"' , "+ldireccion.get(0).getIdDireccion()+" ) ");
                        }
                        
                        if(!SenderReceiverThere)
                        {
                            LinkedList<Sender> lconsender = cons.consultarSenders("SELECT * FROM sender WHERE SenderName = '"+trans[0]+"'");
                            LinkedList<Receiver> lconreceiver = cons.consultarReceivers("SELECT * FROM receiver WHERE ReceiverName = '"+trans[1]+"'");
                            cons.Insertar("INSERT INTO senderreceiver (idSender, idReceiver) Values ("+lconsender.get(0).getIdSender()+" , "+lconreceiver.get(0).getIdReceiver()+" ) ");
                        }
                        
                        if(!ReporteDocumentoThere)
                        {
                            LinkedList<SenderReceiver> lconsenderreceiver = cons.consultarSenderReceiver(""
                                  + "SELECT A.idRelationSR, A.idSender, B.SenderName, C.idReceiver, C.ReceiverName \n" +
                                    "FROM senderreceiver A, sender B, receiver C \n" +
                                    "WHERE A.idSender = B.idSender AND A.idReceiver = C.idReceiver \n" +
                                    "AND B.SenderName = '"+trans[0]+"' AND C.ReceiverName = '"+trans[1]+"'");
                            
                            LinkedList<Documento> lconDocumento = cons.consultarDocumentos(""
                                  + "SELECT A.idDocument, A.DocumentName, A.idDirection, B.DirectionName, A.idType, C.TypeName \n" +
                                    "FROM documents A,  documentdirection B, documenttypes C \n" +
                                    "WHERE A.idDirection = B.idDirection AND A.idType = C.idType AND A.DocumentName = '"+trans[3]+"'");
                            
                            cons.Insertar("INSERT INTO documentreports (idRelationSR, idDocument) VALUES ( "+lconsenderreceiver.get(0).getIdRelacion()+" , "+lconDocumento.get(0).getIdDocumento()+" )");
                            
                        }
                        
                    }
                    else{
                        cont--;
                    }
                }
                
                
                cont++;
            }
         }
          catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
         
         cont--;
        
         System.out.println("Total de lineas: "+cont);
         
     
    }
}
