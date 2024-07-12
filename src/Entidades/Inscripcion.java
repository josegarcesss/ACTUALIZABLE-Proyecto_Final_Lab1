/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

public class Inscripcion {
    private int idInscripcion;
    private int idSocio;
    private int idClase;
    private boolean estado;

    public Inscripcion(int idInscripcion, int idSocio, int idClase, boolean estado) {
        this.idInscripcion = idInscripcion;
        this.idSocio = idSocio;
        this.idClase = idClase;
        this.estado = estado;
    }

    public Inscripcion(int idSocio, int idClase, boolean estado) {
        this.idSocio = idSocio;
        this.idClase = idClase;
        this.estado = estado;
    }

    public int getIdInscripcion() {
        return idInscripcion;
    }

    public void setIdInscripcion(int idInscripcion) {
        this.idInscripcion = idInscripcion;
    }

    public int getIdSocio() {
        return idSocio;
    }

    public void setIdSocio(int idSocio) {
        this.idSocio = idSocio;
    }

    public int getIdClase() {
        return idClase;
    }

    public void setIdClase(int idClase) {
        this.idClase = idClase;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
