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
public class Corrida  implements Serializable
{
    private Integer folio=0;
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
    private Double  albaran=0d;
    private Double  eur_110=0d;
    private Double  eur_150=0d;
    private Double  eur_175=0d;
    private Double  eur_200=0d;
    private Double  eur_230=0d;
    private Double  eur_250=0d;	
    
    private Double  segundas=0d;
    private Double  terceras=0d;
    private Double  torreon=0d;
    private Double  coleada=0d;
    private String  comprador=""; 
    private String  direccion=""; 
    private String  facturar="";
    private String  tipo="";
    private Integer idcomprador=0; 
    private Integer idfacturar=0;
    private Integer idtipo=0;

    private Double  verde_suma=0d;
    private Double  empaque_suma=0d;
    private Double  corrida_suma=0d;
    private Double  promedio1=0d;
    private Double  promedio2=0d;
    private Double  subsuma1=0d;
    private Double  subsuma2=0d;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getIdcomprador() {
        return idcomprador;
    }

    public void setIdcomprador(Integer idcomprador) {
        this.idcomprador = idcomprador;
    }

    public Integer getIdfacturar() {
        return idfacturar;
    }

    public void setIdfacturar(Integer idfacturar) {
        this.idfacturar = idfacturar;
    }

    public Integer getIdtipo() {
        return idtipo;
    }

    public void setIdtipo(Integer idtipo) {
        this.idtipo = idtipo;
    }

    public Integer getFolio() {
        return folio;
    }

    public void setFolio(Integer folio) {
        this.folio = folio;
    }

    public Double getVerde_suma() {
        this.verde_suma =
            this.verde_japon+
            this.verde_110+
            this.verde_150+
            this.verde_175+
            this.verde_200+
            this.verde_230+
            this.verde_250;
        return verde_suma;
    }

    public void setVerde_suma(Double verde_suma) {
        this.verde_suma = verde_suma;
    }

    public Double getEmpaque_suma() {
        this.empaque_suma =
            this.empaque_110+
            this.empaque_150+
            this.empaque_175+
            this.empaque_200+
            this.empaque_230+
            this.empaque_250;
        return empaque_suma;
    }

    public void setEmpaque_suma(Double empaque_suma) {
        this.empaque_suma = empaque_suma;
    }

    public Double getCorrida_suma() {
        this.corrida_suma =
            this.segundas+
            this.terceras+
            this.torreon+
            this.coleada;
        return corrida_suma;
    }

    public void setCorrida_suma(Double corrida_suma) {
        this.corrida_suma = corrida_suma;
    }

    public String sumaverdes()
    {
        this.verde_suma =
            this.verde_japon+
            this.verde_110+
            this.verde_150+
            this.verde_175+
            this.verde_200+
            this.verde_230+
            this.verde_250;
        return "";
    }
    
    public String empaquesuma()
    {
        this.empaque_suma =
            this.empaque_110+
            this.empaque_150+
            this.empaque_175+
            this.empaque_200+
            this.empaque_230+
            this.empaque_250;
        return "";
    }
    public String corridasuma()
    {
        this.corrida_suma =
            this.segundas+
            this.terceras+
            this.torreon+
            this.coleada;
        return "";
    }

    public Double getEur_110() {
        return eur_110;
    }

    public void setEur_110(Double eur_110) {
        this.eur_110 = eur_110;
    }

    public Double getEur_150() {
        return eur_150;
    }

    public void setEur_150(Double eur_150) {
        this.eur_150 = eur_150;
    }

    public Double getEur_175() {
        return eur_175;
    }

    public void setEur_175(Double eur_175) {
        this.eur_175 = eur_175;
    }

    public Double getEur_200() {
        return eur_200;
    }

    public void setEur_200(Double eur_200) {
        this.eur_200 = eur_200;
    }

    public Double getEur_230() {
        return eur_230;
    }

    public void setEur_230(Double eur_230) {
        this.eur_230 = eur_230;
    }

    public Double getEur_250() {
        return eur_250;
    }

    public void setEur_250(Double eur_250) {
        this.eur_250 = eur_250;
    }

    public Double getPromedio1() {
        return promedio1;
    }

    public void setPromedio1(Double promedio1) {
        this.promedio1 = promedio1;
    }

    public Double getPromedio2() {
        return promedio2;
    }

    public void setPromedio2(Double promedio2) {
        this.promedio2 = promedio2;
    }

    public Double getSubsuma1() {
        return subsuma1;
    }

    public void setSubsuma1(Double subsuma1) {
        this.subsuma1 = subsuma1;
    }

    public Double getSubsuma2() {
        return subsuma2;
    }

    public void setSubsuma2(Double subsuma2) {
        this.subsuma2 = subsuma2;
    }

    public Double getAlbaran() {
		return albaran;
	}

	public void setAlbaran(Double albaran) {
		this.albaran = albaran;
	}

	@Override
    public String toString() {
        return "Corrida {" + " folio=" + folio + "\n, "
                + "[verde_japon=" + verde_japon + ", verde_110=" + verde_110 + ", verde_150=" + verde_150 + ", verde_175=" + verde_175 + ", verde_200=" + verde_200 + ", verde_230=" + verde_230 + ", verde_250=" + verde_250 +"],\n "
                + "[empaque_110=" + empaque_110 + ", empaque_150=" + empaque_150 + ", empaque_175=" + empaque_175 + ", empaque_200=" + empaque_200 + ", empaque_230=" + empaque_230 + ", empaque_250=" + empaque_250 +"],\n "
                + "[segundas=" + segundas + ", terceras=" + terceras + ", torreon=" + torreon + ", coleada=" + coleada + ", comprador=" + comprador + ", facturar=" + facturar + ", tipo=" + tipo + ", idcomprador=" + idcomprador + "],\n "
                + "[eur_110=" + eur_110 + ", eur_150=" + eur_150 + ", eur_175=" + eur_175 + ", eur_200=" + eur_200 + ", eur_230=" + eur_230 + ", eur_250=" + eur_250 + "]" 
                + ",idfacturar=" + idfacturar + ", idtipo=" + idtipo + "}";
    }

    
    
}
