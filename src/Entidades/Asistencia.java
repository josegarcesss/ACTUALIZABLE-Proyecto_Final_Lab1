
package Entidades;

import java.time.LocalDate;

public class Asistencia {
    
    private int idAsistencia;
    private Socio socio;
    private Clase clase;
    private LocalDate fechaAsistencia;

    public Asistencia() {
    }

    
    
    public Asistencia(Socio socio, Clase clase, LocalDate fechaAsistencia) {
        this.socio = socio;
        this.clase = clase;
        this.fechaAsistencia = fechaAsistencia;
    }

    public Asistencia(int idAsistencia, Socio socio, Clase clase, LocalDate fechaAsistencia) {
        this.idAsistencia = idAsistencia;
        this.socio = socio;
        this.clase = clase;
        this.fechaAsistencia = fechaAsistencia;
    }

    public int getIdAsistencia() {
        return idAsistencia;
    }

    public void setIdAsistencia(int idAsistencia) {
        this.idAsistencia = idAsistencia;
    }

    public Socio getSocio() {
        return socio;
    }

    public void setSocio(Socio socio) {
        this.socio = socio;
    }

    public Clase getClase() {
        return clase;
    }

    public void setClase(Clase clase) {
        this.clase = clase;
    }

    public LocalDate getFechaAsistencia() {
        return fechaAsistencia;
    }

    public void setFechaAsistencia(LocalDate fechaAsistencia) {
        this.fechaAsistencia = fechaAsistencia;
    }

    @Override
    public String toString() {
        return "Asistencia{" + "idAsistencia=" + idAsistencia + ", socio=" + socio + ", clase=" + clase + ", fechaAsistencia=" + fechaAsistencia + '}';
    }
    
    
}
