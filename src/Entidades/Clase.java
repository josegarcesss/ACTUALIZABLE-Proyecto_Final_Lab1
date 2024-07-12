
package Entidades;
import java.sql.Time;

public class Clase {
    private int idClase;
    private String nombre;
    private Entrenador entrenador;
    private Time horario;
    private int capacidad;
    private boolean estado;

    public Clase() {
    }

    public Clase(String nombre, Entrenador entrenador, Time horario, int capacidad, boolean estado) {
        this.nombre = nombre;
        this.entrenador = entrenador;
        this.horario = horario;
        this.capacidad = capacidad;
        this.estado = estado;
    }

    public Clase(int idClase, String nombre, Entrenador entrenador, Time horario, int capacidad, boolean estado) {
        this.idClase = idClase;
        this.nombre = nombre;
        this.entrenador = entrenador;
        this.horario = horario;
        this.capacidad = capacidad;
        this.estado = estado;
    }

    public int getIdClase() {
        return idClase;
    }

    public void setIdClase(int idClase) {
        this.idClase = idClase;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Entrenador getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }

    public Time getHorario() {
        return horario;
    }

    public void setHorario(Time horario) {
        this.horario = horario;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
//        return "Clase{" + "idClase=" + idClase + ", nombre=" + nombre + ", entrenador=" + entrenador + ", horario=" + horario + ", capacidad=" + capacidad + ", estado=" + estado + '}';
        return nombre+" - "+entrenador+" - "+horario+" - "+capacidad+" - "+idClase;
    }
    
    
}
