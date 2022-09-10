package mx.org.spring;

import java.io.Serializable;

public class Confirma extends Folio implements Serializable{

	private double japon;
	private double segundas;
	private double terceras;
	private double torreon;
	private double coleada;
	
	
	public Confirma(
			int  id,double bruto,double tara,double neto,
			String folio,String status,int opc,String msg,
			double japon, double segundas, double terceras, double torreon, double coleada) 
	{
		super();
		this.setId(id);
		this.setBruto(bruto);
		this.setTara(tara);
		this.setNeto(neto);
		this.setFolio(folio);
		this.setStatus(status);
		this.setOpc(opc);
		this.setMsg(msg);
		
		this.japon = japon;
		this.segundas = segundas;
		this.terceras = terceras;
		this.torreon = torreon;
		this.coleada = coleada;
	}
	public Confirma(String msj) 
    {
		super();
		this.setId(0);
		this.setFolio("");
		this.setMsg(msj);
		this.setOpc(0);
	}
	public double getJapon() {
		return japon;
	}
	public void setJapon(double japon) {
		this.japon = japon;
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
	
	
}
