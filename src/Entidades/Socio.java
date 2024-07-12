
package Entidades;


public class Socio {
    private int idSocio;
    private int dni;
    private String nombre;
    private String apellido;
    private int edad;
    private String correo;
    private int telefono;
    private boolean estado;

    public Socio() {
    }

    public Socio(int dni, String nombre, String apellido, int edad, String correo, int telefono, boolean estado) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.correo = correo;
        this.telefono = telefono;
        this.estado = estado;
    }

    public Socio(int idSocio, int dni, String nombre, String apellido, int edad, String correo, int telefono, boolean estado) {
        this.idSocio = idSocio;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.correo = correo;
        this.telefono = telefono;
        this.estado = estado;
    }

    public int getIdSocio() {
        return idSocio;
    }

    public void setIdSocio(int idSocio) {
        this.idSocio = idSocio;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
//        return "Socio{" + "idSocio=" + idSocio + ", dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido + ", edad=" + edad + ", correo=" + correo + ", telefono=" + telefono + ", estado=" + estado + '}';
        return (dni+" - "+nombre+" "+apellido+" - "+idSocio+" - "+estado);
    }
    
    
    
}
