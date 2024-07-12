/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoADatos;


import Entidades.Inscripcion;
import Entidades.Socio;
import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


/**
 *
 * @author Alakyan
 */
public class InscripcionData {
    private Connection con = null;

    public InscripcionData() {
        con = Conexion.getConexion();
    }
    
    
     public void guardarInscripcion(Inscripcion inscripcion) {
        String sql = "INSERT INTO inscripcion(idSocio, idClase, estado) VALUES (?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, inscripcion.getIdSocio());
            ps.setInt(2, inscripcion.getIdClase());
            ps.setBoolean(3, inscripcion.isEstado());
            
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Inscripcion Realizada.");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en la Base de Datos: " + ex.getMessage());
        }
    }
     
     public void eliminarInscripcion(int idSocio,int idClase){
         String sql="UPDATE inscripcion SET estado=0 WHERE idSocio=? AND idClase=? AND estado=1";
         try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idSocio);
            ps.setInt(2, idClase);
            
            int success = ps.executeUpdate();
            if (success == 1) {
                JOptionPane.showMessageDialog(null, "Inscripcion eliminada con exito.");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró la inscripcion para eliminar.");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en la Base de Datos: " + ex.getMessage());
        }   
     }
    
     public List<Socio> listarSociosInscriptos(int idClase){  
         List<Socio> socios=new ArrayList<>();
         String sql="SELECT socio.idSocio,socio.dni,socio.nombre,socio.apellido,socio.edad,socio.correo,socio.telefono,socio.estado FROM socio JOIN inscripcion ON (socio.idSocio=inscripcion.idSocio) WHERE inscripcion.idClase=? AND inscripcion.estado=1 AND socio.estado=1";
         try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idClase);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Socio socio =new Socio();
                socio.setIdSocio(rs.getInt("idSocio"));
                socio.setDni(rs.getInt("dni"));
                socio.setNombre(rs.getString("nombre"));
                socio.setApellido(rs.getString("apellido"));
                socio.setEdad(rs.getInt("edad"));
                socio.setCorreo(rs.getString("correo"));
                socio.setTelefono(rs.getInt("telefono"));
                socio.setEstado(rs.getBoolean("estado"));
                socios.add(socio);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al listar las membresias: " + ex.getMessage());
        }
        return socios;
     }
     
     
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
