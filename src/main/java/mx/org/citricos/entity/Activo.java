/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.citricos.entity;

import java.io.Serializable;

/**
 *
 * @author BID
 */
public class Activo implements Serializable
{
    private String nombre;
    private Integer    id;
    public Activo()
    {
    }
    public Activo(int id, String nom)
    {
        this.id=  id;
        this.nombre =  nom;
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
        return "Activo{" + "nombre=" + nombre + ", id=" + id + '}';
    }
    
}
