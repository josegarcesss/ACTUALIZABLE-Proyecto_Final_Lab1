/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoADatos;
import Entidades.Membresia;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author sanbe
 */
public class MembresiaData {
    private Connection con;

    public MembresiaData() {
        con = Conexion.getConexion();
    }

    public void guardarMembresia(Membresia membresia) {
        String sql = "INSERT INTO membresia (idSocio, cantidadPases, fechaInicio, fechaFin, costo, estado) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, membresia.getSocio().getIdSocio());
            ps.setInt(2, membresia.getCantidadPases());
            ps.setDate(3, Date.valueOf(membresia.getFechaInicio()));
            ps.setDate(4, Date.valueOf(membresia.getFechaFin()));
            ps.setDouble(5, membresia.getCosto());
            ps.setBoolean(6, membresia.isEstado());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Membresia añadida con exito.");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar la membresia: " + ex.getMessage());
        }
    }

    public void modificarMembresia(Membresia membresia) {
        String sql = "UPDATE membresia SET idSocio=?, cantidadPases=?, fechaInicio=?, fechaFin=?, costo=?, estado=? WHERE idMembresia=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, membresia.getSocio().getIdSocio());
            ps.setInt(2, membresia.getCantidadPases());
            ps.setDate(3, Date.valueOf(membresia.getFechaInicio()));
            ps.setDate(4, Date.valueOf(membresia.getFechaFin()));
            ps.setDouble(5, membresia.getCosto());
            ps.setBoolean(6, membresia.isEstado());
            ps.setInt(7, membresia.getIdMembresia());
            int success = ps.executeUpdate();
            if (success == 1) {
                JOptionPane.showMessageDialog(null, "Membresia modificada con exito.");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró la membresia para modificar.");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al modificar la membresia: " + ex.getMessage());
        }
    }
    
    public void usarMembresia(int idMembresia) {
        String sql = "UPDATE membresia SET cantidadPases=cantidadPases-1 WHERE idMembresia=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idMembresia);
            
            int success = ps.executeUpdate();
            if (success == 1) {
                JOptionPane.showMessageDialog(null, "MembresiaUsada.");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró la membresia para modificar.");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al modificar la membresia: " + ex.getMessage());
        }
    }

    public Membresia buscarMembresia(int idMembresia) {
        Membresia membresia = null;
        String sql = "SELECT * FROM membresia WHERE idMembresia = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idMembresia);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                membresia = new Membresia();
                membresia.setIdMembresia(rs.getInt("idMembresia"));
                membresia.setSocio(new SocioData().buscarSocioID(rs.getInt("idSocio")));
                membresia.setCantidadPases(rs.getInt("cantidadPases"));
                membresia.setFechaInicio(rs.getDate("fechaInicio").toLocalDate());
                membresia.setFechaFin(rs.getDate("fechaFin").toLocalDate());
                membresia.setCosto(rs.getDouble("costo"));
                membresia.setEstado(rs.getBoolean("estado"));
            } else {
                JOptionPane.showMessageDialog(null, "No se encontro la membresia.");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar la membresia: " + ex.getMessage());
        }
        return membresia;
    }
    

    public List<Membresia> listarMembresias() {
        List<Membresia> membresias = new ArrayList<>();
        String sql = "SELECT * FROM membresia";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Membresia membresia = new Membresia();
                membresia.setIdMembresia(rs.getInt("idMembresia"));
                membresia.setSocio(new SocioData().buscarSocioID(rs.getInt("idSocio")));
                membresia.setCantidadPases(rs.getInt("cantidadPases"));
                membresia.setFechaInicio(rs.getDate("fechaInicio").toLocalDate());
                membresia.setFechaFin(rs.getDate("fechaFin").toLocalDate());
                membresia.setCosto(rs.getDouble("costo"));
                membresia.setEstado(rs.getBoolean("estado"));
                membresias.add(membresia);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al listar las membresias: " + ex.getMessage());
        }
        return membresias;
    }

    public void eliminarMembresia(int idMembresia) {
        String sql = "UPDATE membresia SET estado=0 WHERE idMembresia=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idMembresia);
            int success = ps.executeUpdate();
            if (success == 1) {
                JOptionPane.showMessageDialog(null, "Membresia eliminada con exito.");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró la membresia para eliminar.");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar la membresia: " + ex.getMessage());
        }
    }
    
     
    
    
    
    
    
            public List<Membresia> listarMembresiasPorSocio(int idSocio) {
        List<Membresia> membresiasDelSocio = new ArrayList<>();
        String sql = "SELECT * FROM membresia WHERE idSocio = ? AND estado = 1";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idSocio);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Membresia membresia = new Membresia();
                membresia.setIdMembresia(rs.getInt("idMembresia"));
                membresia.setCantidadPases(rs.getInt("cantidadPases"));
                membresia.setFechaInicio(rs.getDate("fechaInicio").toLocalDate());
                membresia.setFechaFin(rs.getDate("fechaFin").toLocalDate());
                membresia.setCosto(rs.getDouble("costo"));
                membresia.setEstado(rs.getBoolean("estado"));
                membresiasDelSocio.add(membresia);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al obtener membresías del socio: " + ex.getMessage());
        }
        return membresiasDelSocio;
    }
    
    
    
    
    
}
