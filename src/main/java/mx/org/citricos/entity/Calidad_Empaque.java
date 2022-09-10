/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.citricos.entity;

import java.io.Serializable;

/**
 *
 * @author luisa
 */
public class Calidad_Empaque   implements Serializable
{
    
    private String nombre;
    private Integer    id;

    public Calidad_Empaque(String nombre, Integer id) {
        this.nombre = nombre;
        this.id = id;
    }

    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Calidad_Empaque  {" + "nombre=" + nombre + ", id=" + id + '}';
    }
    
}
