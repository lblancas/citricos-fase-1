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
public class Precios implements Serializable {
    private Integer id=0;
    private Double  verde_japon=0d;
    private Double  verde_110=0d;
    private Double  verde_150=0d;
    private Double  verde_175=0d;
    private Double  verde_200=0d;
    private Double  verde_230=0d;
    private Double  verde_250=0d;
    private Double  empaque_110=0d;
    private Double  empaque_150=0d;
    private Double  empaque_175=0d;
    private Double  empaque_200=0d;
    private Double  empaque_230=0d;
    private Double  empaque_250=0d;	
    private Double  segundas=0d;
    private Double  terceras=0d;
    private Double  torreon=0d;
    private Double  coleada=0d;
    private String  fecha; 
    private String  fecha_act;
    private String  boton; 
    private String confirmar;
    private String usuario; 
    private boolean activo;

    public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getVerde_japon() {
        return verde_japon;
    }

    public void setVerde_japon(Double verde_japon) {
        this.verde_japon = verde_japon;
    }

    public Double getVerde_110() {
        return verde_110;
    }

    public void setVerde_110(Double verde_110) {
        this.verde_110 = verde_110;
    }

    public Double getVerde_150() {
        return verde_150;
    }

    public void setVerde_150(Double verde_150) {
        this.verde_150 = verde_150;
    }

    public Double getVerde_175() {
        return verde_175;
    }

    public void setVerde_175(Double verde_175) {
        this.verde_175 = verde_175;
    }

    public Double getVerde_200() {
        return verde_200;
    }

    public void setVerde_200(Double verde_200) {
        this.verde_200 = verde_200;
    }

    public Double getVerde_230() {
        return verde_230;
    }

    public void setVerde_230(Double verde_230) {
        this.verde_230 = verde_230;
    }

    public Double getVerde_250() {
        return verde_250;
    }

    public void setVerde_250(Double verde_250) {
        this.verde_250 = verde_250;
    }

    public Double getEmpaque_110() {
        return empaque_110;
    }

    public void setEmpaque_110(Double empaque_110) {
        this.empaque_110 = empaque_110;
    }

    public Double getEmpaque_150() {
        return empaque_150;
    }

    public void setEmpaque_150(Double empaque_150) {
        this.empaque_150 = empaque_150;
    }

    public Double getEmpaque_175() {
        return empaque_175;
    }

    public void setEmpaque_175(Double empaque_175) {
        this.empaque_175 = empaque_175;
    }

    public Double getEmpaque_200() {
        return empaque_200;
    }

    public void setEmpaque_200(Double empaque_200) {
        this.empaque_200 = empaque_200;
    }

    public Double getEmpaque_230() {
        return empaque_230;
    }

    public void setEmpaque_230(Double empaque_230) {
        this.empaque_230 = empaque_230;
    }

    public Double getEmpaque_250() {
        return empaque_250;
    }

    public void setEmpaque_250(Double empaque_250) {
        this.empaque_250 = empaque_250;
    }

    public Double getSegundas() {
        return segundas;
    }

    public void setSegundas(Double segundas) {
        this.segundas = segundas;
    }

    public Double getTerceras() {
        return terceras;
    }

    public void setTerceras(Double terceras) {
        this.terceras = terceras;
    }

    public Double getTorreon() {
        return torreon;
    }

    public void setTorreon(Double torreon) {
        this.torreon = torreon;
    }

    public Double getColeada() {
        return coleada;
    }

    public void setColeada(Double coleada) {
        this.coleada = coleada;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getFecha_act() {
        return fecha_act;
    }

    public void setFecha_act(String fecha_act) {
        this.fecha_act = fecha_act;
    }

    public String getBoton() {
        return boton;
    }

    public void setBoton(String boton) {
        this.boton = boton;
    }

    public String getConfirmar() {
        return confirmar;
    }

    public void setConfirmar(String confirmar) {
        this.confirmar = confirmar;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    } 
    
}
