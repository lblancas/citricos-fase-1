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
public class CorridaR   implements Serializable{
    private int id_folio;
    private double verde_japon;
    private double verde_110;
    private double verde_150;
    private double verde_175;
    private double verde_200;
    private double verde_230;
    private double verde_250;
    private double empaque_110;
    private double empaque_150;
    private double empaque_175;
    private double empaque_200;
    private double empaque_230;
    private double empaque_250;
    private double eur_110;
    private double eur_150;
    private double eur_175;
    private double eur_200;
    private double eur_230;
    private double eur_250;
    private double segundas;
    private double terceras;
    private double torreon;
    private double coleada;
    private double promedio_original;
    private String fecha_modificacion;
    private double promedio_modificacion;
    private int  modificado;
    private int  creado;
    private String comprador;
    private String facturar;
    private int  idcomprador;
    private int  idfacturar;
    private int  idtipo;
    private String tipostr;
    private Corrida corrida;
    private Folios folio;
    private Precios  precios;
    public CorridaR()
    {}
 

    public int getId_folio() {
        return id_folio;
    }

    public void setId_folio(int id_folio) {
        this.id_folio = id_folio;
    }

    public double getVerde_japon() {
        return verde_japon;
    }

    public void setVerde_japon(double verde_japon) {
        this.verde_japon = verde_japon;
    }

    public double getVerde_110() {
        return verde_110;
    }

    public void setVerde_110(double verde_110) {
        this.verde_110 = verde_110;
    }

    public double getVerde_150() {
        return verde_150;
    }

    public void setVerde_150(double verde_150) {
        this.verde_150 = verde_150;
    }

    public double getVerde_175() {
        return verde_175;
    }

    public void setVerde_175(double verde_175) {
        this.verde_175 = verde_175;
    }

    public double getVerde_200() {
        return verde_200;
    }

    public void setVerde_200(double verde_200) {
        this.verde_200 = verde_200;
    }

    public double getVerde_230() {
        return verde_230;
    }

    public void setVerde_230(double verde_230) {
        this.verde_230 = verde_230;
    }

    public double getVerde_250() {
        return verde_250;
    }

    public void setVerde_250(double verde_250) {
        this.verde_250 = verde_250;
    }

    public double getEmpaque_110() {
        return empaque_110;
    }

    public void setEmpaque_110(double empaque_110) {
        this.empaque_110 = empaque_110;
    }

    public double getEmpaque_150() {
        return empaque_150;
    }

    public void setEmpaque_150(double empaque_150) {
        this.empaque_150 = empaque_150;
    }

    public double getEmpaque_175() {
        return empaque_175;
    }

    public void setEmpaque_175(double empaque_175) {
        this.empaque_175 = empaque_175;
    }

    public double getEmpaque_200() {
        return empaque_200;
    }

    public void setEmpaque_200(double empaque_200) {
        this.empaque_200 = empaque_200;
    }

    public double getEmpaque_230() {
        return empaque_230;
    }

    public void setEmpaque_230(double empaque_230) {
        this.empaque_230 = empaque_230;
    }

    public double getEmpaque_250() {
        return empaque_250;
    }

    public void setEmpaque_250(double empaque_250) {
        this.empaque_250 = empaque_250;
    }

    public double getEur_110() {
        return eur_110;
    }

    public void setEur_110(double eur_110) {
        this.eur_110 = eur_110;
    }

    public double getEur_150() {
        return eur_150;
    }

    public void setEur_150(double eur_150) {
        this.eur_150 = eur_150;
    }

    public double getEur_175() {
        return eur_175;
    }

    public void setEur_175(double eur_175) {
        this.eur_175 = eur_175;
    }

    public double getEur_200() {
        return eur_200;
    }

    public void setEur_200(double eur_200) {
        this.eur_200 = eur_200;
    }

    public double getEur_230() {
        return eur_230;
    }

    public void setEur_230(double eur_230) {
        this.eur_230 = eur_230;
    }

    public double getEur_250() {
        return eur_250;
    }

    public void setEur_250(double eur_250) {
        this.eur_250 = eur_250;
    }

    public double getSegundas() {
        return segundas;
    }

    public void setSegundas(double segundas) {
        this.segundas = segundas;
    }

    public double getTerceras() {
        return terceras;
    }

    public void setTerceras(double terceras) {
        this.terceras = terceras;
    }

    public double getTorreon() {
        return torreon;
    }

    public void setTorreon(double torreon) {
        this.torreon = torreon;
    }

    public double getColeada() {
        return coleada;
    }

    public void setColeada(double coleada) {
        this.coleada = coleada;
    }

    public double getPromedio_original() {
        return promedio_original;
    }

    public void setPromedio_original(double promedio_original) {
        this.promedio_original = promedio_original;
    }

    public String getFecha_modificacion() {
        return fecha_modificacion;
    }

    public void setFecha_modificacion(String fecha_modificacion) {
        this.fecha_modificacion = fecha_modificacion;
    }

    public double getPromedio_modificacion() {
        return promedio_modificacion;
    }

    public void setPromedio_modificacion(double promedio_modificacion) {
        this.promedio_modificacion = promedio_modificacion;
    }

    public int getModificado() {
        return modificado;
    }

    public void setModificado(int modificado) {
        this.modificado = modificado;
    }

    public int getCreado() {
        return creado;
    }

    public void setCreado(int creado) {
        this.creado = creado;
    }

    public String getComprador() {
        return comprador;
    }

    public void setComprador(String comprador) {
        this.comprador = comprador;
    }

    public String getFacturar() {
        return facturar;
    }

    public void setFacturar(String facturar) {
        this.facturar = facturar;
    }

    public int getIdcomprador() {
        return idcomprador;
    }

    public void setIdcomprador(int idcomprador) {
        this.idcomprador = idcomprador;
    }

    public int getIdfacturar() {
        return idfacturar;
    }

    public void setIdfacturar(int idfacturar) {
        this.idfacturar = idfacturar;
    }

    public int getIdtipo() {
        return idtipo;
    }

    public void setIdtipo(int idtipo) {
        this.idtipo = idtipo;
    }

    public Corrida getCorrida() {
        return corrida;
    }

    public void setCorrida(Corrida corrida) {
        this.corrida = corrida;
    }

    public Folios getFolio() {
        return folio;
    }

    public void setFolio(Folios folio) {
        this.folio = folio;
    }

    public String getTipostr() {
        return tipostr;
    }

    public void setTipostr(String tipostr) {
        this.tipostr = tipostr;
    }

    public Precios getPrecios() {
        return precios;
    }

    public void setPrecios(Precios precios) {
        this.precios = precios;
    }
    
    
}
