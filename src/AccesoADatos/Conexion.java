
package AccesoADatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class Conexion {
    private static String url="jdbc:mariadb://localhost/gimnasio_1";
    private static String usuario="root";
    private static String password="";
    
    private static Conexion conexion=null;
    
    private Conexion(){
        try{
            //Cargar Driver
            Class.forName("org.mariadb.jdbc.Driver");
        }catch (ClassNotFoundException ex){
            JOptionPane.showMessageDialog(null, "Clase Conexion: Error al cargar el Driver");
       
        }
    }
    
    
     public static Connection getConexion(){
            Connection con=null;
            if(conexion == null){
                conexion= new Conexion();
            }
            try{
                //Setup the connection with the DB
                con= DriverManager.getConnection(url+"?useLegacyDatatimeCode=false&serverTimezone=UTC"+"&user="+usuario+"&password="+password);
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, "Error de conexion");
                }
            return con;
        }
}
