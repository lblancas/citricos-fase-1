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
public class Productores  implements Serializable{

    private Integer id;
    private String pais;
    private String estado;
    private String nombre;
    private String paterno;
    private String materno;
    private String numero;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNombre() {
    	if(nombre==null) 
    		return "";
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPaterno() {
    	if(paterno==null) 
    		return "";
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getMaterno() {
    	if(materno==null) 
    		return "";
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
    
    
    @Override
    public String toString() {
        return "Productor{" + "id=" + id + ", nombre=" + nombre + ", paterno=" + paterno+ ", materno=" + materno + '}';
    }
    
}
