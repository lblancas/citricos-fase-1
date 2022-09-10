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
public class Tipop  implements Serializable{
    private Integer    id;
    private String nombre; 
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
 
    @Override
    public String toString() {
        return "Tipo Productor {" + "id=" + id + ", nombre=" + nombre   + '}';
    }
    
}
