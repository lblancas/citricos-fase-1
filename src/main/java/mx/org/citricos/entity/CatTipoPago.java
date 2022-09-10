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
public class CatTipoPago  implements Serializable{
    private Integer    id;
    private String nombre;
    private Integer tipo;
    private Integer    activo;
    private String activos;
    private String tipos;
    
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

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public String getActivos() {
        return activos;
    }

    public void setActivos(String activos) {
        this.activos = activos;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public String getTipos() {
        if(this.tipo==1)
            return "Positivo";
        return "Negativo";
    }

    public void setTipos(String tipos) {
        this.tipos = tipos;
    }

    @Override
    public String toString() {
        return "CatTipoPago{" + "id=" + id + ", nombre=" + nombre + ", activo=" + activo + ", activos=" + activos + '}';
    }
    
}
