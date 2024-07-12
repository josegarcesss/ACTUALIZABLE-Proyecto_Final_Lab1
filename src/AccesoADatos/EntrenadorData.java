/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoADatos;

import Entidades.Entrenador;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Alakyan
 */
public class EntrenadorData {
    private Connection con=null;

    public EntrenadorData() {
        con=Conexion.getConexion();
    }
    public void guardarEntrenador(Entrenador entrenador){
    String sql="INSERT INTO entrenador (dni, nombre, apellido, especialidad, estado) VALUES (?,?,?,?,?)";
  
        try {
            PreparedStatement ps=con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS );
            ps.setInt(1, entrenador.getDni());
            ps.setString(2, entrenador.getNombre());
            ps.setString(3, entrenador.getApellido());
            ps.setString(4, entrenador.getEspecialidad());
            ps.setBoolean(5, entrenador.isEstado());
            ps.executeUpdate();
            ResultSet rs=ps.getGeneratedKeys();
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Entrenador añadido con exito");
            }
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo conectar a la tabla entrenador"+ ex);
        }
    }
    
    public void eliminarEntrenador(int idEntrenador){
        String sql = "UPDATE entrenador SET estado=0 WHERE idEntrenador=?";
        PreparedStatement ps = null;
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, idEntrenador);
            int exito=ps.executeUpdate();
            if (exito==1) {
                JOptionPane.showMessageDialog(null, "Eliminado con exito");
            }else {
                JOptionPane.showMessageDialog(null, "El entrenador no está Registrado!");
            }
        }catch (SQLException ex) {
           
            JOptionPane.showMessageDialog(null, "Error al ingresar a la base de datos");
            }      
        
    }
    
    public List<Entrenador> listarEntrenadores(){
        List<Entrenador> entrenadores=new ArrayList<>();
        String sql= "SELECT * FROM entrenador";
        PreparedStatement ps = null;
        try{
            ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Entrenador entrenador=new Entrenador();
                entrenador.setIdEntrenador(rs.getInt("idEntrenador"));
                entrenador.setDni(rs.getInt("dni"));
                entrenador.setEspecialidad(rs.getString("especialidad"));
                entrenador.setEstado(rs.getBoolean("estado"));
                entrenador.setNombre(rs.getString("nombre"));
                entrenador.setApellido(rs.getString("apellido"));
                entrenadores.add(entrenador);
            }
            
           ps.close();
        }catch(SQLException ex){
           JOptionPane.showMessageDialog(null, "Error al ingresar a la base de datos");
           return null;
        }
        return entrenadores;        
    }
    
    public void modificarEntrenador(Entrenador entrenador){
        String sql = "UPDATE entrenador SET dni=?,nombre=?,apellido=?,especialidad=?,estado=? WHERE idEntrenador=?";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, entrenador.getDni());
            ps.setString(2, entrenador.getNombre());
            ps.setString(3, entrenador.getApellido());
            ps.setString(4, entrenador.getEspecialidad());
            ps.setBoolean(5, entrenador.isEstado());
            ps.setInt(6, entrenador.getIdEntrenador());
            int exito=ps.executeUpdate();
            if (exito==1) {
                JOptionPane.showMessageDialog(null, "modificado con exito");
            }else {
                JOptionPane.showMessageDialog(null, "El entrenador no está Registrado!");
            }
        } catch (SQLException ex) {
           
            JOptionPane.showMessageDialog(null, "Error al ingresar a la base de datos");
            }      
        }
    
    public Entrenador buscarEntrenador(int idEntrenador){
        Entrenador entrenador=null;
        String sql = "SELECT idEntrenador, nombre, apellido, estado FROM entrenador WHERE idEntrenador = ?";
        PreparedStatement ps = null;
    try {
    ps = con.prepareStatement(sql);
    ps.setInt(1,idEntrenador );
    ResultSet rs = ps.executeQuery();
    
    if(rs.next()){
     entrenador=new Entrenador(); 
     entrenador.setIdEntrenador(idEntrenador);
     entrenador.setNombre(rs.getString("nombre"));
     entrenador.setApellido(rs.getString("apellido"));
     entrenador.setEstado(rs.getBoolean("estado"));
        }else{
        JOptionPane.showMessageDialog(null, "No está registrado ese entrenador");
        }
    }catch(SQLException ex){
    JOptionPane.showMessageDialog(null, "Error al acceder a la base de datos");
    }
        return entrenador;

}
    
   public Entrenador buscarEntrenadorActivo(int idEntrenador){
        Entrenador entrenador=null;
        String sql = "SELECT idEntrenador, nombre, apellido, estado FROM Entrenador WHERE idEntrenador = ? AND estado = 1";
        PreparedStatement ps = null;
    try {
    ps = con.prepareStatement(sql);
    ps.setInt(1,idEntrenador );
    ResultSet rs = ps.executeQuery();
    
    if(rs.next()){
     entrenador=new Entrenador(); 
     entrenador.setIdEntrenador(idEntrenador);
     entrenador.setNombre(rs.getString("nombre"));
     entrenador.setApellido(rs.getString("apellido"));
     entrenador.setEstado(rs.getBoolean("estado"));
        }else{
        JOptionPane.showMessageDialog(null, "No está registrado ese entrenador");
        }
    }catch(SQLException ex){
    JOptionPane.showMessageDialog(null, "Error al acceder a la base de datos");
    }
        return entrenador;
} 
   
public ArrayList<Entrenador> buscarEntrenadoresXEspecialidad(String especialidad){
    
    ArrayList<Entrenador> entrenadores=new ArrayList<>();
    String sql = "SELECT * FROM entrenador WHERE especialidad=?";
    try {
                PreparedStatement ps= con.prepareStatement(sql);
                ps.setString(1, especialidad);
                ResultSet rs=ps.executeQuery();
                while (rs.next()){
                Entrenador entrenador=new Entrenador();
                entrenador.setIdEntrenador(rs.getInt(1));
                entrenador.setDni(rs.getInt(2));
                entrenador.setNombre(rs.getString(3));
                entrenador.setApellido(rs.getString(4));
                entrenador.setEspecialidad(rs.getString(5));
                entrenador.setEstado(rs.getBoolean(6));
                entrenadores.add(entrenador);
            }
            ps.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Error al obtener los Entrenadores");
            }
        return entrenadores;
}
    
public ArrayList<Entrenador> buscarEntrenadoresXNombre(String nombre){
    
    ArrayList<Entrenador> entrenadores=new ArrayList<>();
    String sql = "SELECT * FROM entrenador WHERE nombre=?";
    try {
                PreparedStatement ps= con.prepareStatement(sql);
                ps.setString(1, nombre);
                ResultSet rs=ps.executeQuery();
                while (rs.next()){
                Entrenador entrenador=new Entrenador();
                entrenador.setIdEntrenador(rs.getInt(1));
                entrenador.setDni(rs.getInt(2));
                entrenador.setNombre(rs.getString(3));
                entrenador.setApellido(rs.getString(4));
                entrenador.setEspecialidad(rs.getString(5));
                entrenador.setEstado(rs.getBoolean(6));
                entrenadores.add(entrenador);
            }
            ps.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Error al obtener los Entrenadores");
            }
        return entrenadores;
}

public ArrayList<Entrenador> buscarEntrenadoresXApellido(String apellido){
    
    ArrayList<Entrenador> entrenadores=new ArrayList<>();
    String sql = "SELECT * FROM entrenador WHERE apellido=?";
    try {
                PreparedStatement ps= con.prepareStatement(sql);
                ps.setString(1, apellido);
                ResultSet rs=ps.executeQuery();
                while (rs.next()){
                Entrenador entrenador=new Entrenador();
                entrenador.setIdEntrenador(rs.getInt(1));
                entrenador.setDni(rs.getInt(2));
                entrenador.setNombre(rs.getString(3));
                entrenador.setApellido(rs.getString(4));
                entrenador.setEspecialidad(rs.getString(5));
                entrenador.setEstado(rs.getBoolean(6));
                entrenadores.add(entrenador);
            }
            ps.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Error al obtener los Entrenadores");
            }
        return entrenadores;
}
    
public ArrayList<Entrenador> buscarEntrenadoresXNombreyApellido(String nombre, String apellido){
    
    ArrayList<Entrenador> entrenadores=new ArrayList<>();
    String sql = "SELECT * FROM entrenador WHERE nombre=? AND apellido=?";
    try {
                PreparedStatement ps= con.prepareStatement(sql);
                ps.setString(1, nombre);
                ps.setString(2, apellido);
                ResultSet rs=ps.executeQuery();
                while (rs.next()){
                Entrenador entrenador=new Entrenador();
                entrenador.setIdEntrenador(rs.getInt(1));
                entrenador.setDni(rs.getInt(2));
                entrenador.setNombre(rs.getString(3));
                entrenador.setApellido(rs.getString(4));
                entrenador.setEspecialidad(rs.getString(5));
                entrenador.setEstado(rs.getBoolean(6));
                entrenadores.add(entrenador);
            }
            ps.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Error al obtener los Entrenadores");
            }
        return entrenadores;
}    


    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
