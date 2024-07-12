/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoADatos;

import Entidades.Asistencia;
import Entidades.Clase;
import Entidades.Socio;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Alakyan
 */
public class AsistenciaData {
    private Connection con = null;

    public AsistenciaData() {
        con = Conexion.getConexion();
    }
    
    public void guardarAsistencia(Asistencia asistencia) {
        String sql = "INSERT INTO asistencia (idSocio, idClase, fechaAsistencia) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, asistencia.getSocio().getIdSocio());
            ps.setInt(2, asistencia.getClase().getIdClase());
            ps.setDate(3, Date.valueOf(asistencia.getFechaAsistencia()));
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Asistencia Confirmada.");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Clase: " + ex.getMessage());
        }
    }
    

    
    
    public List<Integer> sociosIDPresentes(int idClase ,LocalDate fechaAsistencia){
        Date fecha=Date.valueOf(fechaAsistencia);
        List<Integer> presentes=new ArrayList<>();
        String sql="SELECT asistencia.idSocio FROM asistencia WHERE idClase=? AND fechaAsistencia=?";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idClase);
            ps.setDate(2, fecha);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
               presentes.add(rs.getInt(1));
            }
            ps.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al Buscar Presentes!");
        }
        return presentes;  
    }
    
    
    
    
    public void eliminarAsistencia(int idAsistencia) {
        String sql = "DELETE FROM `asistencia` WHERE idAsistencia=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idAsistencia);
            int success = ps.executeUpdate();
            if (success == 1) {
                JOptionPane.showMessageDialog(null, "Asistencia eliminada con exito.");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró la Asistencia para eliminar.");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar la Asistencia: " + ex.getMessage());
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
