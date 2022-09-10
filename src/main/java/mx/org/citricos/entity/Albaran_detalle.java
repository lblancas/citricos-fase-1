/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.citricos.entity;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author luisa
 */
public class Albaran_detalle  implements Serializable {
    private Integer id;
    private Integer id_albaran;
    private String  calibre;
    private String  pos_calibre;
    private Float peso;
    private Float fruta;
    private Float porcentaje;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_albaran() {
        return id_albaran;
    }

    public void setId_albaran(Integer id_albaran) {
        this.id_albaran = id_albaran;
    }

    public String getCalibre() {
        return calibre;
    }

    public void setCalibre(String calibre) {
        this.calibre = calibre;
    }

    public String getPos_calibre() {
        return pos_calibre;
    }

    public void setPos_calibre(String pos_calibre) {
        this.pos_calibre = pos_calibre;
    }

    public Float getPeso() {
        return peso;
    }

    public void setPeso(Float peso) {
        this.peso = peso;
    }

    public Float getFruta() {
        return fruta;
    }

    public void setFruta(Float fruta) {
        this.fruta = fruta;
    }

    public Float getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(Float porcentaje) {
        this.porcentaje = porcentaje;
    }
    
    
}
