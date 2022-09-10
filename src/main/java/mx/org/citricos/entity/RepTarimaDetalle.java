package mx.org.citricos.entity;

import java.util.List;

public class RepTarimaDetalle {
	private int id;
	private String  numeroTarima;
	private int id_cat_tarima;
	private String  tarima_desc;
	private int id_cat_marca;
	private String  marca_desc;
	private int cajas;
	private List<RepTarimaDetallePallet> pallets; 
	public RepTarimaDetalle(int id, String numeroTarima, int id_cat_tarima, String tarima_desc, int id_cat_marca,
			String marca_desc) {
		super();
		this.id = id;
		this.numeroTarima = numeroTarima;
		this.id_cat_tarima = id_cat_tarima;
		this.tarima_desc = tarima_desc;
		this.id_cat_marca = id_cat_marca;
		this.marca_desc = marca_desc;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNumeroTarima() {
		return numeroTarima;
	}
	public void setNumeroTarima(String numero) {
		this.numeroTarima = numero;
	}
	public int getId_cat_tarima() {
		return id_cat_tarima;
	}
	public void setId_cat_tarima(int id_cat_tarima) {
		this.id_cat_tarima = id_cat_tarima;
	}
	public String getTarima_desc() {
		return tarima_desc;
	}
	public void setTarima_desc(String tarima_desc) {
		this.tarima_desc = tarima_desc;
	}
	public int getId_cat_marca() {
		return id_cat_marca;
	}
	public void setId_cat_marca(int id_cat_marca) {
		this.id_cat_marca = id_cat_marca;
	}
	public String getMarca_desc() {
		return marca_desc;
	}
	public void setMarca_desc(String marca_desc) {
		this.marca_desc = marca_desc;
	}
	public int getCajas() {
		return cajas;
	}
	public void setCajas(int cajas) {
		this.cajas = cajas;
	}
	public List<RepTarimaDetallePallet> getPallets() {
		return pallets;
	}
	public void setPallets(List<RepTarimaDetallePallet> pallets) {
		this.pallets = pallets;
	}	
	
	
	
}
