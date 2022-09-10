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
 * @author BID
 */
public class Transporte  implements Serializable
{
    private Integer id;
    private String numerotermo;
    private String numerosello;
    private String lector_temp;
    private String lote;
    private String fecha;
    private Date   fechat;
    private Integer idcliente;
    private Integer idtransportista;
    private Integer idactivo;
    private String numero;
    private String  activos;
    private String  transportista;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumerotermo() {
        return numerotermo;
    }

    public void setNumerotermo(String numerotermo) {
        this.numerotermo = numerotermo;
    }

    public String getNumerosello() {
        return numerosello;
    }

    public void setNumerosello(String numerosello) {
        this.numerosello = numerosello;
    }

    public String getLector_temp() {
        return lector_temp;
    }

    public void setLector_temp(String lector_temp) {
        this.lector_temp = lector_temp;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Integer getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Integer idcliente) {
        this.idcliente = idcliente;
    }

    public Integer getIdtransportista() {
        return idtransportista;
    }

    public void setIdtransportista(Integer idtransportista) {
        this.idtransportista = idtransportista;
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

    public Date getFechat() {
        return fechat;
    }

    public void setFechat(Date fechat) {
        this.fechat = fechat;
    }

    public String getActivos() {
        return activos;
    }

    public void setActivos(String activos) {
        this.activos = activos;
    }
    
    
    
    public String getTransportista() {
		return transportista;
	}

	public void setTransportista(String transportista) {
		this.transportista = transportista;
	}

	@Override
    public String toString() {
        return "Transporte{" + "id=" + id + ", numerotermo=" + numerotermo + ", numerosello=" + numerosello + ", lector_temp=" + lector_temp + ", lote=" + lote + ", fecha=" + fecha + ", idcliente=" + idcliente + ", idtransportista=" + idtransportista + ", idactivo=" + idactivo + ", numero=" + numero + '}';
    }
    
}
