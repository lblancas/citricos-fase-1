package mx.org.spring;

import java.io.Serializable;

public class Bascula extends Folio implements Serializable
{
    private String productor="";
    public Bascula() {
    }

    public Bascula(String msg) 
    {
    	this.setId(0);
		this.setBruto(0.0);
		this.setTara(0.0);
		this.setNeto(0.0);
		this.setFolio("");
		this.setStatus("");
		this.setOpc(0);
		this.setMsg(msg);
        this.productor = "";
    }
    public Bascula(int id,double bruto, double tara, double neto, 
    		String productor, String folio, String status,int opc, String msg) {
    	this.setId(id);
		this.setBruto(bruto);
		this.setTara(tara);
		this.setNeto(neto);
		this.setFolio(folio);
		this.setStatus(status);
		this.setOpc(opc);
		this.setMsg(msg);
		this.productor = productor;
    }
    public Bascula(int id,double bruto, double tara, double neto, 
    		String productor, String folio, String status,int opc, String msg,String tck, String  posicion) {
    	this.setId(id);
		this.setBruto(bruto);
		this.setTara(tara);
		this.setTicket(tck);
		this.setNeto(neto);
		this.setFolio(folio);
		this.setStatus(status);
		this.setOpc(opc);
		this.setMsg(msg);
		this.productor = productor;
		this.setPosicion(posicion);
    }
    public Bascula(int id,double bruto, double tara, double neto, 
    		String productor, String folio, String status,int opc, String msg,String tck) {
    	this.setId(id);
		this.setBruto(bruto);
		this.setTara(tara);
		this.setTicket(tck);
		this.setNeto(neto);
		this.setFolio(folio);
		this.setStatus(status);
		this.setOpc(opc);
		this.setMsg(msg);
		this.productor = productor;
    }
 
 
    public String getProductor() {
        return productor;
    }

 	public void setProductor(String productor) {
		this.productor = productor;
	}    
}
