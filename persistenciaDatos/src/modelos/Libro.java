/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
/**
 *
 * @author Jairo
 */
@Entity
public class Libro implements Serializable {
    private static final long serialVersionUID = 1L;
    
    //Generado automáticamente
    @Id @GeneratedValue
    private long numeroRef;

    private String nombre;
    private String autor;
    private String genero;
    private String leido;
    private double puntuacion;
    private String comentarios;
    
    public Libro(String nombre, String autor, String genero, String leido, double puntuacion, String comentarios) {
        this.nombre = nombre;
        this.autor = autor;
        this.genero = genero;
        this.leido = leido;
        this.puntuacion = puntuacion;
        this.comentarios = comentarios;
    }    
    
    public long getNumeroRef() {
        return numeroRef;
    }

    public void setNumeroRef(long numeroRef) {
        this.numeroRef = numeroRef;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getLeido() {
        return leido;
    }

    public void setLeido(String leido) {
        this.leido = leido;
    }

    public double getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(double puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    @Override
    public String toString(){
        return String.format(
                "%d - Nombre: %s - Autor: %s - Genero: %s "
                        + "- Leido: %s - Puntuacion: %.1f - Comentarios: %s",
                this.numeroRef, this.nombre,this.autor,this.genero,this.leido,this.puntuacion,this.comentarios);
    }
}
