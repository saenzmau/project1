package Test;

import Objetos.Conexion;
import Objetos.Consultas;
import Objetos.Documento;
import Objetos.Receiver;
import Objetos.Sender;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class PruebasXLSX {
     
    public static void main(String[] args) throws FileNotFoundException, IOException, SQLException {
        
        String csvFile = "C:\\Users\\Mauricio\\Documents\\NetBeansProjects\\BusinessContact\\test\\ReporteTPA.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        ArrayList<Documento> Documentos = new ArrayList<Documento>();
        ArrayList<Sender> Senders = new ArrayList<Sender>();
        ArrayList<Receiver> Receivers = new ArrayList<Receiver>();
        
        
        
        Consultas cons = new Consultas();
        
        
        
        try {

            br = new BufferedReader(new FileReader(csvFile));
            int cont = 1;
            
            while ((line = br.readLine()) != null) {
                
                
                // use comma as separator
                String[] trans = line.split(cvsSplitBy);
                
                System.out.println("Longitud: "+trans.length + "  Index: "+ cont);
                System.out.println();
                //System.out.println("Sender: " + trans[0] + " , Receiver: " + trans[1]);
                
                Sender nsend = new Sender();
                Receiver nrec = new Receiver();
                Documento nfac = new Documento();
                
                boolean sthere = false;
                boolean rthere = false;
                boolean fthere = false;
                
                
                for(int i = 0; i < Senders.size(); i++)
                {
                    if(trans[0].equalsIgnoreCase(Senders.get(i).getSenderName()))
                    {
                        sthere = true;
                    }
                }
                
                if(!sthere)
                {
                    nsend.setSenderName(trans[0]);
                    Senders.add(nsend);
                }
                
                
                for(int i = 0; i < Receivers.size(); i++)
                {
                    if(trans[1].equalsIgnoreCase(Receivers.get(i).getReceiverName()))
                    {
                        rthere = true;
                    }
                }
                
                if(!rthere)
                {
                    nrec.setReceiverName(trans[1]);
                    Receivers.add(nrec);
                }
                
                
                for(int i = 0; i < Documentos.size(); i++)
                {
                    if(trans[3].equalsIgnoreCase(Documentos.get(i).getDocumento()))
                    {
                        fthere = true;
                    }
                }
                
                if(!fthere)
                {
                    if(trans[2].equalsIgnoreCase("Inbound"))
                    {
                        nfac.setIdDireccion("1");
                    }
                    
                    if(trans[2].equalsIgnoreCase("Outbound"))
                    {
                        nfac.setIdDireccion("2");
                    }
                    nfac.setDocumento(trans[3]);
                    Documentos.add(nfac);
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
        
        
            
        for(int i = 0; i < Senders.size(); i++)
        {
            System.out.println("Sender: " + Senders.get(i).getSenderName());
        }
        
        System.out.println("");
        System.out.println("Número de Senders: " +Senders.size());
        
        
        System.out.println("");
        System.out.println("------------------------------------------------------");
        System.out.println("");
        
        for(int i = 0; i < Receivers.size(); i++)
        {
            System.out.println("Receiver: " + Receivers.get(i).getReceiverName());
        }
        
        System.out.println("");
        System.out.println("Número de Receivers: " +Receivers.size());
        
        
        System.out.println("");
        System.out.println("------------------------------------------------------");
        System.out.println("");
        
        
        for(int i = 0; i < Documentos.size(); i++)
        {
            System.out.println("Documento: " + Documentos.get(i).getDocumento());
        }
        
        System.out.println("");
        System.out.println("Número de Documentos: " +Documentos.size());
        
     }
}
