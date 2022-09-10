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
public class Pallet_desc  implements Serializable {
    private Integer    id;
    private Integer    idx;
    private Integer    cajas;
    private Integer    idcalidad;
    private Integer    idcalibre; 
    private Integer    idtarima;
    private Integer    idactivo;
    private String     cdi="";
    private String     calidad="";
    private String     calibre=""; 
    private String     activo="";
    
    private String     marca=""; 
    private String     tarima="";
    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCajas() {
        return cajas;
    }

    public void setCajas(Integer cajas) {
        this.cajas = cajas;
    }

    public Integer getIdcalidad() {
        return idcalidad;
    }

    public void setIdcalidad(Integer idcalidad) {
        this.idcalidad = idcalidad;
    }

    public Integer getIdcalibre() {
        return idcalibre;
    }

    public void setIdcalibre(Integer idcalibre) {
        this.idcalibre = idcalibre;
    }

    public Integer getIdtarima() {
        return idtarima;
    }

    public void setIdtarima(Integer idtarima) {
        this.idtarima = idtarima;
    }

    public Integer getIdactivo() {
        return idactivo;
    }

    public void setIdactivo(Integer idactivo) {
        this.idactivo = idactivo;
    }

    public String getCdi() {
        return cdi;
    }

    public void setCdi(String cdi) {
        this.cdi = cdi;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public String getCalidad() {
        return calidad;
    }

    public void setCalidad(String calidad) {
        this.calidad = calidad;
    }

    public String getCalibre() {
        return calibre;
    }

    public void setCalibre(String calibre) {
        this.calibre = calibre;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTarima() {
        return tarima;
    }

    public void setTarima(String tarima) {
        this.tarima = tarima;
    }
     
    @Override
    public String toString() {
        return "Pallet_desc{" + "id=" + id + ", idx=" + idx + ", cajas=" + cajas + ", idcalidad=" + idcalidad + ", idcalibre=" + idcalibre + ", idtarima=" + idtarima + ", idactivo=" + idactivo + ", cdi=" + cdi + ", calidad=" + calidad + ", calibre=" + calibre + ", activo=" + activo + '}';
    }

    
     
}
