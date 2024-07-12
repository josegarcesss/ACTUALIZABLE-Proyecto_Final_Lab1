package AccesoADatos;
import Entidades.Membresia;
import Entidades.Socio;
import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class SocioData {
    
    private Connection con=null;

    public SocioData() {
       con=Conexion.getConexion();
    }
    
  public void guardarSocio(Socio socio){
  String sql="INSERT INTO socio (dni, nombre, apellido, edad, correo, telefono,estado) VALUES (?,?,?,?,?,?,?)";
  
        try {
            PreparedStatement ps=con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS );
            ps.setInt(1, socio.getDni());
            ps.setString(2, socio.getNombre());
            ps.setString(3, socio.getApellido());
            ps.setInt(4, socio.getEdad());
            ps.setString(5, socio.getCorreo());
            ps.setInt(6, socio.getTelefono());
            ps.setBoolean(7, socio.isEstado());
            ps.executeUpdate();
            ResultSet rs=ps.getGeneratedKeys();
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Socio añadido con exito");
            }
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo conectar a la tabla socio "+ ex);
        }
  }

        /*ESTE METODO MODIFICA DEVUELVE TRUE SI LE QUEDAN CLASES Y/O LA MEMBRESIA ESTA VENCIDA
            EN CASO DE ENCONTRAR QUE LA/S MEMBRESIA/S ESTAN VENCIDAS LAS ELIMINA DE FORMA LOGICA <3 */
    public boolean membresiaActiva(int idSocio){
        boolean activa=false;
        List<Membresia> membresias = new ArrayList<>();
        String sql="SELECT membresia.idMembresia,membresia.cantidadPases,membresia.fechaFin,membresia.estado FROM socio JOIN membresia ON(socio.idSocio=membresia.idSocio) WHERE membresia.idSocio=? AND membresia.estado=1";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idSocio);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Membresia membresia=new Membresia();
                membresia.setIdMembresia(rs.getInt("idMembresia"));
                membresia.setCantidadPases(rs.getInt("cantidadPases"));
                membresia.setFechaFin(rs.getDate("fechaFin").toLocalDate());
                membresia.setEstado(rs.getBoolean("estado"));
                membresias.add(membresia);
            }
            ps.close();
            for (Membresia membresia : membresias) {
                if(ChronoUnit.DAYS.between(LocalDate.now(),membresia.getFechaFin() )>=0){
                    activa=true;
                }else{
                    MembresiaData md=new MembresiaData();
                    md.eliminarMembresia(membresia.getIdMembresia());
                }
                
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Socio sin membresias! " + ex.getMessage());
        }
        return activa;
    }  


  
  public Socio buscarSocioID(int idSocio) {
        Socio socio = null;
        String sql = "SELECT dni, nombre, apellido, edad, correo, telefono, estado FROM Socio WHERE idSocio = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idSocio);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                socio = new Socio();
                socio.setIdSocio(idSocio);
                socio.setDni(rs.getInt("dni"));
                socio.setNombre(rs.getString("nombre"));
                socio.setApellido(rs.getString("apellido"));
                socio.setEdad(rs.getInt("edad"));
                socio.setCorreo(rs.getString("correo"));
                socio.setTelefono(rs.getInt("telefono"));
                socio.setEstado(rs.getBoolean("estado"));
            } else {
                JOptionPane.showMessageDialog(null, "No se encontro el socio");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar el socio: " + ex.getMessage());
        }
        return socio;
    }
  
  public Socio buscarSocioDNI(int DNI) {
        Socio socio = null;
        String sql = "SELECT idSocio,dni, nombre, apellido, edad, correo, telefono, estado FROM Socio WHERE dni = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, DNI);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                socio = new Socio();
                socio.setIdSocio(rs.getInt("idSocio"));
                socio.setDni(rs.getInt("dni"));
                socio.setNombre(rs.getString("nombre"));
                socio.setApellido(rs.getString("apellido"));
                socio.setEdad(rs.getInt("edad"));
                socio.setCorreo(rs.getString("correo"));
                socio.setTelefono(rs.getInt("telefono"));
                socio.setEstado(rs.getBoolean("estado"));
            } 
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar el socio: " + ex.getMessage());
        }
        return socio;
    }
  
  
  
  public List<Socio> buscarSocioNombre(String nombre) {
         List<Socio> socios = new ArrayList<>();
        String sql = "SELECT * FROM Socio WHERE nombre = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Socio socio = new Socio();
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
            JOptionPane.showMessageDialog(null, "Error al buscar el socio: " + ex.getMessage());
        }
        return socios;
    }

    public List<Socio> listarSocios() {
        List<Socio> socios = new ArrayList<>();
        String sql = "SELECT * FROM Socio";
        try {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Socio socio = new Socio();
                socio.setIdSocio(resultSet.getInt("idSocio"));
                socio.setDni(resultSet.getInt("dni"));
                socio.setNombre(resultSet.getString("nombre"));
                socio.setApellido(resultSet.getString("apellido"));
                socio.setEdad(resultSet.getInt("edad"));
                socio.setCorreo(resultSet.getString("correo"));
                socio.setTelefono(resultSet.getInt("telefono"));
                socio.setEstado(resultSet.getBoolean("estado"));
                socios.add(socio);
            }
            statement.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al listar los socios: " + ex.getMessage());
        }
        return socios;
    }
    public List<Socio> listarSociosActivos() {
        List<Socio> socios = new ArrayList<>();
        String sql = "SELECT * FROM Socio WHERE estado=1";
        try {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Socio socio = new Socio();
                socio.setIdSocio(resultSet.getInt("idSocio"));
                socio.setDni(resultSet.getInt("dni"));
                socio.setNombre(resultSet.getString("nombre"));
                socio.setApellido(resultSet.getString("apellido"));
                socio.setEdad(resultSet.getInt("edad"));
                socio.setCorreo(resultSet.getString("correo"));
                socio.setTelefono(resultSet.getInt("telefono"));
                socio.setEstado(resultSet.getBoolean("estado"));
                socios.add(socio);
            }
            statement.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al listar los socios: " + ex.getMessage());
        }
        return socios;
    }

    public void modificarSocio(Socio socio) {
        String sql = "UPDATE Socio SET dni = ?, nombre = ?, apellido = ?, edad = ?, correo = ?, telefono = ?, estado = ? WHERE idSocio = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, socio.getDni());
            ps.setString(2, socio.getNombre());
            ps.setString(3, socio.getApellido());
            ps.setInt(4, socio.getEdad());
            ps.setString(5, socio.getCorreo());
            ps.setInt(6, socio.getTelefono());
            ps.setBoolean(7, socio.isEstado());
            ps.setInt(8, socio.getIdSocio());
            int rowCount = ps.executeUpdate();
            if (rowCount > 0) {
                JOptionPane.showMessageDialog(null, "Socio modificado con exito");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontro el socio para modificar.");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al modificar el socio: " + ex.getMessage());
        }
    }
//cambiar booleano
    public void eliminarSocio(int idSocio) {
        String sql = "UPDATE Socio SET estado=0 WHERE idSocio = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idSocio);
            int rowCount = ps.executeUpdate();
            if (rowCount > 0) {
                JOptionPane.showMessageDialog(null, "Socio eliminado con exito");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontro el socio para eliminar.");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el socio: " + ex.getMessage());
        }
    }
}  
    

