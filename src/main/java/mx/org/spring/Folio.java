package mx.org.spring;

import java.io.Serializable;

public class Folio implements Serializable{
	private int    id=0;
    private double bruto=0d;
    private double tara=0d;
    private double neto=0d;
    private String folio="";
    private String status="";
    private String ticket="";
    private int opc;
    private String msg;
    private String posicion;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getBruto() {
		return bruto;
	}
	public void setBruto(double bruto) {
		this.bruto = bruto;
	}
	public double getTara() {
		return tara;
	}
	public void setTara(double tara) {
		this.tara = tara;
	}
	public double getNeto() {
		return neto;
	}
	public void setNeto(double neto) {
		this.neto = neto;
	}
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getOpc() {
		return opc;
	}
	public void setOpc(int opc) {
		this.opc = opc;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	public String getPosicion() {
		return posicion;
	}
	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}
    
}

