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
public class CorridaFolio implements Serializable 
{
    private Integer id_folio; 
    private String  folio="";
    private String  fecha="";
    private Integer id_productor=0;
    private Double peso_bruto=0.00d;
    private Double peso_tara=0.00d;
    private Double peso_neto=0.00d;
    //---------------------------------------
    private Double no_rejas=0.00d;
    private Integer tipo_rejas=0;
    private Integer tipo_limon=0;
    private Integer id_agronomo=0;
    private Integer dejo=0;
    private boolean isdejo=false;
    private String observaciones="";
    private Integer calidad_empaque;
    //---------------------------------------
    private Double segundas=0.00d;
    private Double terceras=0.00d;
    private Double torreon=0.00d;
    private Double coleada=0.00d;
    private Double japon=0.00d;
    //---------------------------------------
    //---------------------------------------
    private String productor="";
    private String direccion="";
    private String agronomo="";
    private String tipos_rejas="";
    private String tipos_limones="";
    private String codigo="";
    
    private Integer idcorrida=0;
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
    
    private Double  eur_110=0d;
    private Double  eur_150=0d;
    private Double  eur_175=0d;
    private Double  eur_200=0d;
    private Double  eur_230=0d;
    private Double  eur_250=0d;	
    
    private Double  sig_segundas=0d;
    private Double  sig_terceras=0d;
    private Double  sig_torreon=0d;
    private Double  sig_coleada=0d;
    private String  comprador=""; 
    private String  facturar="";
    private Integer idcomprador=0; 
    private Integer idfacturar=0;
    private Integer idtipo=0;
    private Integer estatus=0;
    private Double  verde_suma=0d;
    private Double  empaque_suma=0d;
    private Double  corrida_suma=0d;

    public Double getVerde_suma() {
        return verde_suma;
    }

    public void setVerde_suma(Double verde_suma) {
        this.verde_suma = verde_suma;
    }

    public Double getEmpaque_suma() {
        return empaque_suma;
    }

    public void setEmpaque_suma(Double empaque_suma) {
        this.empaque_suma = empaque_suma;
    }
     
    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Integer getId_productor() {
        return id_productor;
    }

    public void setId_productor(Integer id_productor) {
        this.id_productor = id_productor;
    }

    public Integer getTipo_rejas() {
        return tipo_rejas;
    }

    public void setTipo_rejas(Integer tipo_rejas) {
        this.tipo_rejas = tipo_rejas;
    }

    public Integer getTipo_limon() {
        return tipo_limon;
    }

    public void setTipo_limon(Integer tipo_limon) {
        this.tipo_limon = tipo_limon;
    }

    public Integer getId_agronomo() {
        return id_agronomo;
    }

    public void setId_agronomo(Integer id_agronomo) {
        this.id_agronomo = id_agronomo;
    }

    public Integer getDejo() {
        return dejo;
    }

    public void setDejo(Integer dejo) {
        this.dejo = dejo;
    }

    public boolean isIsdejo() {
        return isdejo;
    }

    public void setIsdejo(boolean isdejo) {
        this.isdejo = isdejo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getProductor() {
        return productor;
    }

    public void setProductor(String productor) {
        this.productor = productor;
    }

    public String getAgronomo() {
        return agronomo;
    }

    public void setAgronomo(String agronomo) {
        this.agronomo = agronomo;
    }

    public String getTipos_rejas() {
        return tipos_rejas;
    }

    public void setTipos_rejas(String tipos_rejas) {
        this.tipos_rejas = tipos_rejas;
    }

    public String getTipos_limones() {
        return tipos_limones;
    }

    public void setTipos_limones(String tipos_limones) {
        this.tipos_limones = tipos_limones;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public Double getSig_segundas() {
        return sig_segundas;
    }

    public void setSig_segundas(Double sig_segundas) {
        this.sig_segundas = sig_segundas;
    }

    public Double getSig_terceras() {
        return sig_terceras;
    }

    public void setSig_terceras(Double sig_terceras) {
        this.sig_terceras = sig_terceras;
    }

    public Double getSig_torreon() {
        return sig_torreon;
    }

    public void setSig_torreon(Double sig_torreon) {
        this.sig_torreon = sig_torreon;
    }

    public Double getSig_coleada() {
        return sig_coleada;
    }

    public void setSig_coleada(Double sig_coleada) {
        this.sig_coleada = sig_coleada;
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

    public Double getCorrida_suma() {
        return corrida_suma;
    }

    public void setCorrida_suma(Double corrida_suma) {
        this.corrida_suma = corrida_suma;
    }

    public Integer getIdcorrida() {
        return idcorrida;
    }

    public void setIdcorrida(Integer idcorrida) {
        this.idcorrida = idcorrida;
    }

    public Integer getEstatus() {
        return estatus;
    }

    public void setEstatus(Integer estatus) {
        this.estatus = estatus;
    }

    public Integer getCalidad_empaque() {
        return calidad_empaque;
    }

    public void setCalidad_empaque(Integer calidad_empaque) {
        this.calidad_empaque = calidad_empaque;
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

    public Double getPeso_bruto() {
        return peso_bruto;
    }

    public void setPeso_bruto(Double peso_bruto) {
        this.peso_bruto = peso_bruto;
    }

    public Double getPeso_tara() {
        return peso_tara;
    }

    public void setPeso_tara(Double peso_tara) {
        this.peso_tara = peso_tara;
    }

    public Double getPeso_neto() {
        return peso_neto;
    }

    public void setPeso_neto(Double peso_neto) {
        this.peso_neto = peso_neto;
    }

    public Double getNo_rejas() {
        return no_rejas;
    }

    public void setNo_rejas(Double no_rejas) {
        this.no_rejas = no_rejas;
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

    public Double getJapon() {
        return japon;
    }

    public void setJapon(Double japon) {
        this.japon = japon;
    }

    public Integer getId_folio() {
        return id_folio;
    }

    public void setId_folio(Integer id_folio) {
        this.id_folio = id_folio;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    
    
}
