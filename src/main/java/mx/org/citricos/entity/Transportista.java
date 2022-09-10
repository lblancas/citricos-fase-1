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
public class Transportista   implements Serializable
{
    private Integer id;
    private String  nombre;
    private String  placas;
    private String  numero_economico;
    private Integer idactivo;
    private String  numero;
    private String  activos;
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

    public String getPlacas() {
        return placas;
    }

    public void setPlacas(String placas) {
        this.placas = placas;
    }

    public String getNumero_economico() {
        return numero_economico;
    }

    public void setNumero_economico(String numero_economico) {
        this.numero_economico = numero_economico;
    }

    public Integer getIdactivo() {
        return idactivo;
    }

    public void setIdactivo(Integer idactivo) {
        this.idactivo = idactivo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getActivos() {
        return activos;
    }

    public void setActivos(String activos) {
        this.activos = activos;
    }
    
    @Override
    public String toString() {
        return "Transportista{" + "id=" + id + ", nombre=" + nombre + ", placas=" + placas + ", numero_economico=" + numero_economico + ", idactivo=" + idactivo + ", numero=" + numero + '}';
    }
    
}
