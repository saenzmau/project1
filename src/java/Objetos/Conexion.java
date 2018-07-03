package Objetos;

import java.sql.DriverManager;
import com.mysql.jdbc.Connection;
import java.sql.SQLException;
        
public class Conexion {
    public static final String USERNAME = "root";
    public static final String PASSWORD = "";
    public static final String HOST = "localhost";
    public static final String PORT = "3306";
    public static final String DATABASE = "reportetpa";
    public static final String CLASSNAME = "com.mysql.jdbc.Driver";
    public static final String URL = "jdbc:mysql://" + HOST + ":"+PORT+"/"+DATABASE;
    
    public java.sql.Connection con;
    
    public Conexion()
    {
        try{
            Class.forName(CLASSNAME);
            con = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            //System.out.println("Conectado a la base");
        }
        catch(ClassNotFoundException e)
        {
            System.out.println("Error en la Clase");
        }
         catch(SQLException e)
        {
            System.out.println("Error SQL");
        }
    }
    
    /*public static void main(String[] args)
    {
        Conexion con = new Conexion();
    }*/
}
