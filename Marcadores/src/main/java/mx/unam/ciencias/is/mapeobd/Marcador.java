/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unam.ciencias.is.mapeobd;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Clase que modela un marcador apartir de la tabla marcador
 * @author jonathan
 */
@Entity
@Table(name="marcador")
public class Marcador {
    @Id@GeneratedValue(strategy =GenerationType.IDENTITY )
    @Column(name = "idmarcador")
    private int marcador_id;
    
    //Aqui va tu codigo
    @Column(name = "nombreM")
    private String nombre;
    
    @Column(name = "latitud")
    private double latitud;
    
    @Column(name = "longitud")
    private double longitud;
    
    @Column(name = "descripcion")
    private String descripcion;
    
    /**Nos da el id del marcador
     * @return el id del marcador 
    */
    public int getMarcador_id() {
        return marcador_id;
    }
    
    /** Pone el id del marcador.
      @param marcador_id 
    */
    public void setMarcador_id(int marcador_id) {
        this.marcador_id = marcador_id;
    }
    
    
    //Aqui va tu codigo
    /**
     * Nos da el nombre del marcador
     * @return el nombre del marcador
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Le da nombre al marcador
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * Nos da la latitud del marcador
     * @return valor de latitud del marcador
     */
    public double getLatitud() {
        return latitud;
    }

    /**
     * Le da el valor de la latitud al marcador
     * @param latitud 
     */
    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }
    
    /**
     * Nos da la el valor de la longitud del marcador
     * @return valor de longitud del marcador
     */
    public double getLongitud() {
        return longitud;
    }

    /**
     * Asigna un valor a la longitud el marcador
     * @param longitud 
     */
    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    /**
     * Nos da la descripcion del marcador
     * @return 
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * 
     * @param descripcion 
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}