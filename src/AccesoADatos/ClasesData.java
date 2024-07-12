/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoADatos;

import Entidades.Clase;
import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
/**
 *
 * @author sanbe
 */
public class ClasesData {
    private Connection con = null;

    public ClasesData() {
        con = Conexion.getConexion();
    }

    public void guardarClase(Clase clase) {
        String sql = "INSERT INTO clase (nombre, idEntrenador, horario, capacidad, estado) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, clase.getNombre());
            ps.setInt(2, clase.getEntrenador().getIdEntrenador());
            ps.setTime(3, clase.getHorario());
            ps.setInt(4, clase.getCapacidad());
            ps.setBoolean(5, clase.isEstado());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                clase.setIdClase(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Clase añadida con exito.");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Clase: " + ex.getMessage());
        }
    }

    public Clase buscarClase(int id) {
        Clase clase = null;
        String sql = "SELECT * FROM clase WHERE idClase = ? AND estado = 1";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                clase = new Clase();
                clase.setIdClase(rs.getInt("idClase"));
                clase.setNombre(rs.getString("nombre"));
                clase.setEntrenador(new EntrenadorData().buscarEntrenadorActivo(rs.getInt("idEntrenador")));
                clase.setHorario(rs.getTime("horario"));
                clase.setCapacidad(rs.getInt("capacidad"));
                clase.setEstado(rs.getBoolean("estado"));
            } else {
                JOptionPane.showMessageDialog(null, "No existe la clase.");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Clase: " + ex.getMessage());
        }
        return clase;
    }
    
     public ArrayList<Clase> BuscarClaseXNombre(String nombre){
     ArrayList<Clase> clases=new ArrayList<>();
     String sql="SELECT * FROM clase WHERE nombre=?";
          try {
            PreparedStatement ps= con.prepareStatement(sql);
            ps.setString(1, nombre);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
            Clase clas=new Clase();
            clas.setIdClase(rs.getInt(1));
            clas.setNombre(rs.getString(2));
            clas.setEntrenador(new EntrenadorData().buscarEntrenador(rs.getInt(3)));
            clas.setHorario(rs.getTime(4));
            clas.setCapacidad(rs.getInt(5));
            clas.setEstado(rs.getBoolean(6));
            clases.add(clas);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al listar el nombre");
        }
        return clases;
    }
    

    
    public List<Integer> listarIDPresentesAhora(Clase clase){
        List<Integer> presentes=new ArrayList<>();
        String sql="SELECT asistencia.idSocio FROM asistencia JOIN clase ON (asistencia.idClase=clase.id_clase) WHERE clase.id_clase=?";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, clase.getIdClase());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Integer id=rs.getInt("idSocio");
                presentes.add(id);
            }
            ps.close();
        }catch(SQLException ex){
           JOptionPane.showMessageDialog(null,"Error al Listar ID de los Asistentes"+ex.getMessage());  
        }
        return presentes;
    }
    
    public List<Time> listarHorariosXEntrenador(int idEntrenador){
            List<Time> horas = new ArrayList<>();
    String sql="SELECT clase.horario FROM clase JOIN entrenador ON (clase.idEntrenador=entrenador.idEntrenador) WHERE entrenador.idEntrenador =? AND clase.estado=1";
    try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idEntrenador);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Time hora;
                hora=rs.getTime("horario");
                horas.add(hora);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Clase: " + ex.getMessage());
        }
        return horas;
    
}

    public List<Clase> listarClases() {
        List<Clase> clases = new ArrayList<>();
        String sql = "SELECT * FROM clase WHERE estado = 1";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Clase clase = new Clase();
                clase.setIdClase(rs.getInt("idClase"));
                clase.setNombre(rs.getString("nombre"));
                clase.setEntrenador(new EntrenadorData().buscarEntrenadorActivo(rs.getInt("idEntrenador")));
                clase.setHorario(rs.getTime("horario"));
                clase.setCapacidad(rs.getInt("capacidad"));
                clase.setEstado(rs.getBoolean("estado"));
                clases.add(clase);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Clase: " + ex.getMessage());
        }
        return clases;
    }

    public void modificarClase(Clase clase) {
        String sql = "UPDATE clase SET nombre = ?, idEntrenador = ?, horario = ?, capacidad = ?, estado = ? WHERE idClase = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, clase.getNombre());
            ps.setInt(2, clase.getEntrenador().getIdEntrenador());
            ps.setTime(3, clase.getHorario());
            ps.setInt(4, clase.getCapacidad());
            ps.setBoolean(5, clase.isEstado());
            ps.setInt(6, clase.getIdClase());
            int exito = ps.executeUpdate();
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "Clase modificada con exito.");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontro la clase para modificar.");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al modificar la clase: " + ex.getMessage());
        }
    }

    public void eliminarClase(int id) {
        String sql = "UPDATE clase SET estado = 0 WHERE idClase = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int fila = ps.executeUpdate();
            if (fila == 1) {
                JOptionPane.showMessageDialog(null, "Clase eliminada con exito.");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontro la clase para eliminar.");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar la clase: " + ex.getMessage());
        }
    }
}
