package mx.org.spring;

import java.io.Serializable;

public class Recepcion extends Folio implements Serializable 
{   
    private double norejas;
    private String comprador;
    private String facturar;
    private String tamanorejas;
    private String tipolimon;
    private String agronomo;
    private String ticket;
    private String observaciones;
    private int dejorejas;
    
    public Recepcion(String msj) 
    {
		super();
		this.setId(0);
		this.setFolio("");
		this.setMsg(msj);
		this.setOpc(0);
	}
	public Recepcion(
			int id, String folio,double bruto,double tara, double neto,
			double norejas, String comprador, String facturar, String tamanorejas, String tipolimon,
			String agronomo, String ticket, String observaciones, int dejorejas,String status) {
		super();
		this.setId(id);
		this.setFolio(folio);
		this.setBruto(bruto);
		this.setTara(tara);
		this.setNeto(neto);
		this.setStatus(status);
		this.norejas = norejas;
		this.comprador = comprador;
		this.facturar = facturar;
		this.tamanorejas = tamanorejas;
		this.tipolimon = tipolimon;
		this.agronomo = agronomo;
		this.ticket = ticket;
		this.observaciones = observaciones;
		this.dejorejas = dejorejas;
		this.setOpc(1);
		this.setMsg("OK");
	}
	public double getNorejas() {
		return norejas;
	}
	public void setNorejas(double norejas) {
		this.norejas = norejas;
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
	public String getTamanorejas() {
		return tamanorejas;
	}
	public void setTamanorejas(String tamanorejas) {
		this.tamanorejas = tamanorejas;
	}
	public String getTipolimon() {
		return tipolimon;
	}
	public void setTipolimon(String tipolimon) {
		this.tipolimon = tipolimon;
	}
	public String getAgronomo() {
		return agronomo;
	}
	public void setAgronomo(String agronomo) {
		this.agronomo = agronomo;
	}
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public int getDejorejas() {
		return dejorejas;
	}
	public void setDejorejas(int dejorejas) {
		this.dejorejas = dejorejas;
	}
    
}
