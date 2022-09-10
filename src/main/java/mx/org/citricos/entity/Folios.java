/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.citricos.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author luisa
 */
public class Folios implements Serializable
{
    private Integer id;
    private String  folio;
    private String  fecha;
    private Integer id_productor;
    private Double peso_bruto=0d;
    private Double peso_tara=0d;
    private Double peso_neto=0d;
    //---------------------------------------
    private Double no_rejas=0d;
    private Integer tipo_rejas=0;
    private Integer tipo_limon=0;
    private Integer id_agronomo=0;
    private Integer dejo;
    private boolean isdejo;
    private String observaciones;
    private Integer calidad_empaque=0;
    private String facturar;
    private String comprador;
    //---------------------------------------
    private Double segundas=0d;
    private Double terceras=0d;
    private Double torreon=0d;
    private Double coleada=0d;
    private Double japon=0d;
    //---------------------------------------
    //---------------------------------------
    private String productor;
    private String agronomo;
    private String tipos_rejas;
    private String tipos_limones;
    
    private String codigo="";
    private Integer estatus=0;
    private Corrida corrida;
    
    private String fechabas="";
    private String fecharec="";
    private String fechacon="";
    private Integer proceso=0;
    private String mensaje1="";
    private String mensaje2="";
    private String mensaje3="";
    private String botonBascula="";
    private String botonRecepcion="";
    private String botonConfirma="";
    private Integer statusSiguiente;
    private String ticket;
    private int idRejas;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getFecha() 
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date d=new Date();
        String fechaN=format.format(d);
        this.fecha = fechaN;
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

    public boolean isIsdejo() {
        return isdejo;
    }

    public void setIsdejo(boolean isdejo) {
        this.isdejo = isdejo;
    }  
 
    
    @Override
	public String toString() {
		return "Folios [id=" + id + ", folio=" + folio + ", fecha=" + fecha + ", id_productor=" + id_productor
				+ ", peso_bruto=" + peso_bruto + ", peso_tara=" + peso_tara + ", peso_neto=" + peso_neto + ", no_rejas="
				+ no_rejas + ", tipo_rejas=" + tipo_rejas + ", tipo_limon=" + tipo_limon + ", id_agronomo="
				+ id_agronomo + ", dejo=" + dejo + ", isdejo=" + isdejo + ", observaciones=" + observaciones
				+ ", calidad_empaque=" + calidad_empaque + ", facturar=" + facturar + ", comprador=" + comprador
				+ ", segundas=" + segundas + ", terceras=" + terceras + ", torreon=" + torreon + ", coleada=" + coleada
				+ ", japon=" + japon + ", productor=" + productor + ", agronomo=" + agronomo + ", tipos_rejas="
				+ tipos_rejas + ", tipos_limones=" + tipos_limones + ", codigo=" + codigo + ", estatus=" + estatus
				+ ", corrida=" + corrida + ", fechabas=" + fechabas + ", fecharec=" + fecharec + ", fechacon="
				+ fechacon + ", proceso=" + proceso + ", mensaje1=" + mensaje1 + ", mensaje2=" + mensaje2
				+ ", mensaje3=" + mensaje3 + ", botonBascula=" + botonBascula + ", botonRecepcion=" + botonRecepcion
				+ ", botonConfirma=" + botonConfirma + ", statusSiguiente=" + statusSiguiente + ", ticket=" + ticket
				+ "]";
	}

	public String getCodigo() 
    { 
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Corrida getCorrida() {
        return corrida;
    }

    public void setCorrida(Corrida corrida) {
        this.corrida = corrida;
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

    public String getMensaje1() {
        return mensaje1;
    }

    public void setMensaje1(String mensaje1) {
        this.mensaje1 = mensaje1;
    }

    public String getMensaje2() {
        return mensaje2;
    }

    public void setMensaje2(String mensaje2) {
        this.mensaje2 = mensaje2;
    }

    public String getMensaje3() {
        return mensaje3;
    }

    public void setMensaje3(String mensaje3) {
        this.mensaje3 = mensaje3;
    }

    public String getFechabas() {
        return fechabas;
    }

    public void setFechabas(String fechabas) {
        this.fechabas = fechabas;
    }

    public String getFecharec() {
        return fecharec;
    }

    public void setFecharec(String fecharec) {
        this.fecharec = fecharec;
    }

    public String getFechacon() {
        return fechacon;
    }

    public void setFechacon(String fechacon) {
        this.fechacon = fechacon;
    }

    public Integer getProceso() {
        return proceso;
    }

    public void setProceso(Integer proceso) {
        this.proceso = proceso;
    }

    public String getBotonBascula() {
        return botonBascula;
    }

    public void setBotonBascula(String botonBascula) {
        this.botonBascula = botonBascula;
    }

    public String getBotonRecepcion() {
        return botonRecepcion;
    }

    public void setBotonRecepcion(String botonRecepcion) {
        this.botonRecepcion = botonRecepcion;
    }

    public String getBotonConfirma() {
        return botonConfirma;
    }

    public void setBotonConfirma(String botonConfirma) {
        this.botonConfirma = botonConfirma;
    }

    public Integer getStatusSiguiente() {
        return statusSiguiente;
    }

    public void setStatusSiguiente(Integer statusSiguiente) {
        this.statusSiguiente = statusSiguiente;
    }

    public String getFacturar() {
        return facturar;
    }

    public void setFacturar(String facturar) {
        this.facturar = facturar;
    }

    public String getComprador() {
        return comprador;
    }

    public void setComprador(String comprador) {
        this.comprador = comprador;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

	public int getIdRejas() {
		return idRejas;
	}

	public void setIdRejas(int idRejas) {
		this.idRejas = idRejas;
	}
    
    
}
